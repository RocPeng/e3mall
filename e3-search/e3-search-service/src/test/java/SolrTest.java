import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by roc_peng on 2017/8/31.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */


public class SolrTest {

    @Test
    public void Test() throws Exception{

    }

    /**
     * 添加数据
     * @throws Exception
     */
    @Test
    public void addDocumentTest() throws Exception{
        // 第一步：把solrJ的jar包添加到工程中。
        // 第二步：创建一个SolrServer，使用HttpSolrServer创建对象。
        HttpSolrServer httpSolrServer = new HttpSolrServer("http://localhost:8999/solr/core1");
        // 第三步：创建一个文档对象SolrInputDocument对象
        SolrInputDocument document = new SolrInputDocument();
        // 第四步：向文档中添加域。必须有id域，域的名称必须在schema.xml中定义。
        document.addField("id","test001");
        document.addField("item_title","测试商品1");
        document.addField("item_price","99");
        // 第五步：把文档添加到索引库中。
        httpSolrServer.add(document);
        // 第六步：提交。
        httpSolrServer.commit();
    }

    /**
     * 删除数据
     * @throws Exception
     */
    @Test
    public void deleteTest() throws Exception{
        HttpSolrServer httpSolrServer = new HttpSolrServer("http://localhost:8999/solr/core1");
        httpSolrServer.deleteById("test001");
        httpSolrServer.commit();
    }


    /**
     * 简单查询
     * @throws Exception
     */
    @Test
    public void queryTest() throws Exception{
        // 第一步：创建一个SolrServer对象
        SolrServer solrServer = new HttpSolrServer("http://localhost:8999/solr/core1");
        // 第二步：创建一个SolrQuery对象。
        SolrQuery query = new SolrQuery();
        // 第三步：向SolrQuery中添加查询条件、过滤条件。。。
        query.setQuery("*:*");
        // 第四步：执行查询。得到一个Response对象。
        QueryResponse response = solrServer.query(query);
        // 第五步：取查询结果。
        SolrDocumentList solrDocumentList = response.getResults();
        System.out.println("查询结果的总记录数：" + solrDocumentList.getNumFound());
        // 第六步：遍历结果并打印。
        for (SolrDocument solrDocument : solrDocumentList) {
            System.out.println(solrDocument.get("id"));
            System.out.println(solrDocument.get("item_title"));
            System.out.println(solrDocument.get("item_price"));
        }
    }


    /**
     * 高亮测试
     * @throws Exception
     */
    @Test
    public void hightLightTest() throws Exception{
        // 第一步：创建一个SolrServer对象
        SolrServer solrServer = new HttpSolrServer("http://localhost:8999/solr/core1");
        // 第二步：创建一个SolrQuery对象。
        SolrQuery query = new SolrQuery();
        // 第三步：向SolrQuery中添加查询条件、过滤条件。。。
        query.setQuery("测试");
        //指定默认搜索域
        query.set("df", "item_keywords");
        //开启高亮显示
        query.setHighlight(true);
        //高亮显示的域
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em>");
        query.setHighlightSimplePost("</em>");
        // 第四步：执行查询。得到一个Response对象。
        QueryResponse response = solrServer.query(query);
        // 第五步：取查询结果。
        SolrDocumentList solrDocumentList = response.getResults();
        System.out.println("查询结果的总记录数：" + solrDocumentList.getNumFound());
        // 第六步：遍历结果并打印。
        for (SolrDocument solrDocument : solrDocumentList) {
            System.out.println(solrDocument.get("id"));
            //取高亮显示
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String itemTitle = null;
            if (list != null && list.size() > 0) {
                itemTitle = list.get(0);
            } else {
                itemTitle = (String) solrDocument.get("item_title");
            }
            System.out.println(itemTitle);
            System.out.println(solrDocument.get("item_price"));
        }

    }
}
