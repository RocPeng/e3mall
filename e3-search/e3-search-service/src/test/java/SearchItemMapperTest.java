import com.liger.common.model.SearchItem;
import com.liger.search.mapper.SearchItemMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by roc_peng on 2017/8/31.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class SearchItemMapperTest {

    @Autowired
    private SearchItemMapper searchItemMapper;

    @Test
    public void test() throws Exception{
        List<SearchItem> itemList = searchItemMapper.getItemList();
        System.out.println(itemList);
    }
}
