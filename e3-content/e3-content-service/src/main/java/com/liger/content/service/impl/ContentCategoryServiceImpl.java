package com.liger.content.service.impl;

import com.liger.common.model.EasyUITreeNode;
import com.liger.common.utils.E3Result;
import com.liger.content.service.ContentCategoryService;
import com.liger.mapper.TbContentCategoryMapper;
import com.liger.model.ContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by roc_peng on 2017/8/30.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getContentCatList(long parentId) {
        // 1、取查询参数id，parentId
        // 2、根据parentId查询tb_content_category，查询子节点列表。
        //设置查询条件
        Example example = new Example(ContentCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId",parentId);
        List<ContentCategory> contentCategories = contentCategoryMapper.selectByExample(example);
        //执行查询
        // 3、得到List<TbContentCategory>
        // 4、把列表转换成List<EasyUITreeNode>
        List<EasyUITreeNode> list = new ArrayList<>();
        for(ContentCategory contentCategory : contentCategories){
            EasyUITreeNode treeNode = new EasyUITreeNode();
            treeNode.setText(contentCategory.getName());
            treeNode.setId(contentCategory.getId());
            treeNode.setState(contentCategory.getIsParent()?"closed":"open");
            list.add(treeNode);
        }
        return list;
    }

    @Override
    public E3Result addContentCategory(long parentId, String name) {
        ContentCategory contentCategory = new ContentCategory();
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        //1(正常),2(删除)
        contentCategory.setStatus(1);
        //默认排序就是1
        contentCategory.setSortOrder(1);
        //一定是叶子节点
        contentCategory.setIsParent(false);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        contentCategoryMapper.insert(contentCategory);

        //更新父节点信息
        ContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
        parent.setIsParent(true);
        contentCategoryMapper.updateByPrimaryKeySelective(parent);
        return E3Result.ok(contentCategory);
    }

    @Override
    public E3Result updateContentCategory(long id, String name) {
        ContentCategory contentCategory = new ContentCategory();
        contentCategory.setId(id);
        contentCategory.setName(name);
        contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
        return E3Result.ok();
    }

    /**
     * 删除某节点  正确做法应该是:删除后找到父节点,判断父节点下面是否还有子节点,然后修改父节点isParent状态
     * @param id
     * @return
     */
    @Override
    public E3Result deleteContentCategory(long id) {
        ContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        ContentCategory parent = contentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
//        contentCategoryMapper.deleteByPrimaryKey(id);
        //递归删除所有节点
        recursionDelete(id);
        //删除之后获取父节点下的子对象  判断父节点是否还有子节点
        List<EasyUITreeNode> contentCatList = getContentCatList(parent.getId());
        if(contentCatList.size()==0){
            parent.setIsParent(false);
        }
        contentCategoryMapper.updateByPrimaryKeySelective(parent);
        return E3Result.ok();
    }

    //递归删除方法
    public void recursionDelete(long id){
        //获取节点下面所有的子节点
        List<EasyUITreeNode> contentCatList = getContentCatList(id);
        contentCategoryMapper.deleteByPrimaryKey(id);
        for(EasyUITreeNode node :contentCatList){
            recursionDelete(node.getId());
        }
    }
}
