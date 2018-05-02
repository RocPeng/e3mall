package com.liger.service;

import com.liger.common.model.EasyUITreeNode;
import com.liger.mapper.TbItemCatMapper;
import com.liger.model.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roc_peng on 2017/8/29.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper itemCatMapper;

    /**
     * Criteria包含一个Cretiron的集合,每一个Criteria对象内包含的Cretiron之间是由AND连接的,是逻辑与的关系。
     * @param parentId
     * @return
     */
    @Override
    public List<EasyUITreeNode> getCatList(long parentId) {
        Example example = new Example(ItemCat.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId",parentId);
        List<ItemCat> itemCats = itemCatMapper.selectByExample(example);
        List<EasyUITreeNode> list = new ArrayList<>();
        for(ItemCat itemCat : itemCats){
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(itemCat.getId());
            node.setText(itemCat.getName());
            node.setState(itemCat.getIsParent()?"closed":"open");
            list.add(node);
        }
        return list;
    }
}
