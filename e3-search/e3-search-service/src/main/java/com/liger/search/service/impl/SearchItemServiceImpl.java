package com.liger.search.service.impl;

import com.liger.common.model.SearchItem;
import com.liger.common.utils.E3Result;
import com.liger.search.mapper.SearchItemMapper;
import com.liger.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by roc_peng on 2017/8/31.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {
    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    private SolrServer solrServer;

    @Override
    public E3Result importAllItems() {
        try {
            List<SearchItem> itemList = searchItemMapper.getItemList();
            for(SearchItem item : itemList){
                SolrInputDocument document = new SolrInputDocument();
                document.addField("id",item.getId());
                document.addField("item_title",item.getTitle());
                document.addField("item_sell_point", item.getSell_point());
                document.addField("item_price", item.getPrice());
                document.addField("item_image", item.getImage());
                document.addField("item_category_name", item.getCategory_name());
                solrServer.add(document);
            }
            solrServer.commit();
            return E3Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return E3Result.build(500,"数据导入发生异常");
        }
    }

    @Override
    public E3Result addDocument(long itemId) throws Exception {
        //查询id的商品信息
        SearchItem searchItem = searchItemMapper.getItemById(itemId);
        //创建一个文档对象
        SolrInputDocument document = new SolrInputDocument();
        //向文档对象中添加域
        document.addField("id", searchItem.getId());
        document.addField("item_title", searchItem.getTitle());
        document.addField("item_sell_point", searchItem.getSell_point());
        document.addField("item_price", searchItem.getPrice());
        document.addField("item_image", searchItem.getImage());
        document.addField("item_category_name", searchItem.getCategory_name());

        //把文档写入索引库
        solrServer.add(document);
        //提交
        solrServer.commit();
        return E3Result.ok();
    }


}
