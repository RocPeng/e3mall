package com.liger.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liger.common.jedis.JedisClient;
import com.liger.common.model.EasyUIDataGridResult;
import com.liger.common.utils.E3Result;
import com.liger.common.utils.IDUtils;
import com.liger.common.utils.JsonUtils;
import com.liger.mapper.TbItemDescMapper;
import com.liger.mapper.TbItemMapper;
import com.liger.model.Item;
import com.liger.model.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.jms.*;
import java.util.Date;
import java.util.List;

/**
 * Created by roc_peng on 2017/8/28.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Destination topicDestination;
//    private ActiveMQTopic topicDestination;
    @Autowired
    private JedisClient jedisClient;
    @Value("${REDIS_ITEM_PRE}")
    private String REDIS_ITEM_PRE;
    @Value("${REDIS_ITEM_DESC_PRE}")
    private String REDIS_ITEM_DESC_PRE;
    @Value("${ITEM_CACHE_EXPIRE}")
    private Integer ITEM_CACHE_EXPIRE;



    @Override
    public Item getItemById(long id) {
        //先去redis中查找 缓存命中返回对象 key为表名:类名:主键名
        String json = jedisClient.get(REDIS_ITEM_PRE + ":Item" + ":id");
        if(!StringUtils.isEmpty(json)){
            Item item = JsonUtils.jsonToPojo(json, Item.class);
            return item;
        }
        //缓存中没有  先查找 再放入缓存  并设置失效时间
        Item item = itemMapper.selectByPrimaryKey(id);
        updateItemRedis(item);
        return item;
    }

    @Override
    public ItemDesc getItemDescById(long id) {
        //先去redis中查找 缓存命中返回对象
        String json = jedisClient.get(REDIS_ITEM_DESC_PRE + ":ItemDesc" + ":id");
        if(!StringUtils.isEmpty(json)){
            ItemDesc itemDesc = JsonUtils.jsonToPojo(json, ItemDesc.class);
            return itemDesc;
        }
        //缓存中没有  先查找 再放入缓存  并设置失效时间
        ItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(id);
        updateItemDescRedis(itemDesc);
        return itemDesc;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<Item> items = itemMapper.selectAll();
        PageInfo<Item> pageInfo = new PageInfo<>(items);
        EasyUIDataGridResult result = new EasyUIDataGridResult(pageInfo.getTotal(),items);
        return result;
    }

    @Override
    public E3Result addItem(Item item, String desc) throws Exception {
        // 1、生成商品id
        final long itemId = IDUtils.genItemId();
        // 2、补全TbItem对象的属性
        item.setId(itemId);
        //商品状态，1-正常，2-下架，3-删除
        item.setStatus((byte)1);
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        // 3、向商品表插入数据
        itemMapper.insert(item);
        // 4、创建一个ItemDesc对象
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        // 6、向商品描述表插入数据
        itemDescMapper.insert(itemDesc);
        //redis中添加缓存
        updateItemRedis(item);
        updateItemDescRedis(itemDesc);
        //发送商品添加消息
        //search-service的ItemAddMessageListener监听器监听 添加solr索引
        //item-web的HtmlGenListener监听器监听 生成freemarker静态页面
        jmsTemplate.send(topicDestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(itemId + "");
                return textMessage;
            }
        });
        // 7、E3Result.ok()
        return E3Result.ok();
    }

    @Override
    public E3Result updateItem(Item item, String desc) {
        itemMapper.updateByPrimaryKeySelective(item);
        ItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(item.getId());
        itemDesc.setItemDesc(desc);
        itemDescMapper.updateByPrimaryKeySelective(itemDesc);
        //同时更新redis缓存
        updateItemRedis(itemMapper.selectByPrimaryKey(item.getId()));
        updateItemDescRedis(itemDesc);
        return E3Result.ok();
    }

    /**
     * 批量删除  提高效率
     * @param ids
     * @return
     */
    @Override
    public E3Result deleteItems(List<Long> ids) {
        if(!CollectionUtils.isEmpty(ids)){
            /*for(Long id : ids){
                itemMapper.deleteByPrimaryKey(id);
                itemDescMapper.deleteByPrimaryKey(id);
                //同时清除缓存
                updateItemRedis(null);
                updateItemDescRedis(null);
            }*/
            Example example1 = new Example(Item.class);
            example1.createCriteria().andIn("id",ids);
            Example example2 = new Example(ItemDesc.class);
            example2.createCriteria().andIn("itemId",ids);
            itemMapper.deleteByExample(example1);
            itemDescMapper.deleteByExample(example2);
            //同时清除缓存
            updateItemRedis(null);
            updateItemDescRedis(null);
        }
        return E3Result.ok();
    }

    @Override
    public E3Result reshelfItems(List<Long> ids) {
        if(!CollectionUtils.isEmpty(ids)){
            for(Long id : ids){
                Item item = new Item();
                item.setId(id);
                item.setStatus((byte)1);
                itemMapper.updateByPrimaryKeySelective(item);
                //更新redis缓存
                updateItemRedis(itemMapper.selectByPrimaryKey(id));
            }
        }
        return E3Result.ok();
    }

    @Override
    public E3Result instockItems(List<Long> ids) {
        if(!CollectionUtils.isEmpty(ids)){
            for(Long id : ids){
                Item item = new Item();
                item.setId(id);
                item.setStatus((byte)2);
                itemMapper.updateByPrimaryKeySelective(item);
                //更新redis缓存
                updateItemRedis(itemMapper.selectByPrimaryKey(id));
            }
        }
        return E3Result.ok();
    }

    /**
     * 更新item的redis缓存 key:  表名:字段名:字段值
     * @param item
     */
    public void updateItemRedis(Item item){
        Long id = item.getId();
        jedisClient.set(REDIS_ITEM_PRE +  ":id:" + id+"",JsonUtils.objectToJson(item));
        jedisClient.expire(REDIS_ITEM_PRE +  ":id:" + id+"",ITEM_CACHE_EXPIRE);
    }

    /**
     * 更新ItemDesc的redis缓存 key:  表名:字段名:字段值
     * @param itemDesc
     */
    public void updateItemDescRedis(ItemDesc itemDesc){
        Long itemId = itemDesc.getItemId();
        jedisClient.set(REDIS_ITEM_DESC_PRE + ":itemId:" + itemId+"",JsonUtils.objectToJson(itemDesc));
        jedisClient.expire(REDIS_ITEM_DESC_PRE + ":itemId:" + itemId+"",ITEM_CACHE_EXPIRE);
    }
}
