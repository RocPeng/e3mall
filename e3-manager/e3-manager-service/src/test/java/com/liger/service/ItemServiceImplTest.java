package com.liger.service;

import com.github.pagehelper.PageHelper;
import com.liger.common.model.EasyUIDataGridResult;
import com.liger.mapper.TbItemCatMapper;
import com.liger.mapper.TbItemMapper;
import com.liger.model.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * ItemServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>八月 29, 2017</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class ItemServiceImplTest {
    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private ItemCatService itemCatService;

    @Autowired
    private ItemService itemService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void Test() throws Exception{

    }

    /**
     * Method: getItemById(long id)
     */
    @Test
    public void testGetItemById() throws Exception {
        Item item = itemMapper.selectByPrimaryKey(536563L);
        System.out.println(item);
    }

    @Test
    public void pageHelperTest() throws Exception{
        PageHelper.startPage(2,5);
        List<Item> items = itemMapper.selectAll();
        System.out.println(items);
    }

    @Test
    public void getItemListTest() throws Exception{
        EasyUIDataGridResult itemList = itemService.getItemList(2, 5);
        System.out.println(itemList);
    }

    @Test
    public void itemCatTest() throws Exception{
        itemCatService.getCatList(1L);
    }

} 
