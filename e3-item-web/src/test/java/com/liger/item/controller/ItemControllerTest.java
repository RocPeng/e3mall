package com.liger.item.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.*;

/**
 * ItemController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>九月 2, 2017</pre>
 */
public class ItemControllerTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: showItemInfo(@PathVariable("itemId") Long itemId, Model model)
     */
    @Test
    public void testShowItemInfo() throws Exception {

    }

    @Test
    public void genFile() throws Exception {
        // 第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 第二步：设置模板文件所在的路径。
        configuration.setDirectoryForTemplateLoading(new File("/Users/roc_peng/IDE/IntelliJ_SSM/e3-parent/e3-item-web/src/main/webapp/WEB-INF/ftl"));
        // 第三步：设置模板文件使用的字符集。一般就是utf-8.
        configuration.setDefaultEncoding("utf-8");
        // 第四步：加载一个模板，创建一个模板对象。
        Template template = configuration.getTemplate("hello.ftl");
        // 第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
        //向数据集中添加数据
        Map data = new HashMap();
        data.put("hello","Hello,My name is Roc_Peng");
        // 第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
        FileWriter fileWriter = new FileWriter("/Users/roc_peng/Downloads/hello.html");
        // 第七步：调用模板对象的process方法输出文件。
        template.process(data,fileWriter);
        // 第八步：关闭流。
        fileWriter.close();
    }

    @Test
    public void genFile2() throws Exception {
        // 第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 第二步：设置模板文件所在的路径。
        configuration.setDirectoryForTemplateLoading(new File("/Users/roc_peng/IDE/IntelliJ_SSM/e3-parent/e3-item-web/src/main/webapp/WEB-INF/ftl"));
        // 第三步：设置模板文件使用的字符集。一般就是utf-8.
        configuration.setDefaultEncoding("utf-8");
        // 第四步：加载一个模板，创建一个模板对象。
        Template template = configuration.getTemplate("student.ftl");
        // 第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
        //向数据集中添加数据
        List<Student> list = new ArrayList<>();
        list.add(new Student(001,"alice",22,"上海"));
        list.add(new Student(002,"bob",18,"北京"));
        list.add(new Student(003,"roc",33,"上海"));
        HashMap hashMap = new HashMap();
        hashMap.put("hello","hello a i am roc_peng");
        hashMap.put("student",new Student(000,"roc_Peng",23,"阿拉斯加"));

        hashMap.put("stuList",list);
        hashMap.put("date",new Date());
        hashMap.put("val","定义了val!!!");
        // 第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
        FileWriter fileWriter = new FileWriter("/Users/roc_peng/Downloads/student.html");
        // 第七步：调用模板对象的process方法输出文件。
        template.process(hashMap,fileWriter);
        // 第八步：关闭流。
        fileWriter.close();
    }

    @Test
    public void testFreeMarker() throws Exception {
        //1、创建一个模板文件
        //2、创建一个Configuration对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //3、设置模板文件保存的目录
        configuration.setDirectoryForTemplateLoading(new File("/Users/roc_peng/IDE/IntelliJ_SSM/e3-parent/e3-item-web/src/main/webapp/WEB-INF/ftl"));
        //4、模板文件的编码格式，一般就是utf-8
        configuration.setDefaultEncoding("utf-8");
        //5、加载一个模板文件，创建一个模板对象。
//		Template template = configuration.getTemplate("hello.ftl");
        Template template = configuration.getTemplate("student.ftl");
        //6、创建一个数据集。可以是pojo也可以是map。推荐使用map
        Map data = new HashMap<>();
        data.put("hello", "hello freemarker!");
        //创建一个pojo对象
        Student student = new Student(1, "小明", 18, "回龙观");
        data.put("student", student);
        //添加一个list
        List<Student> stuList = new ArrayList<>();
        stuList.add(new Student(1, "小明1", 18, "回龙观"));
        stuList.add(new Student(2, "小明2", 19, "回龙观"));
        stuList.add(new Student(3, "小明3", 20, "回龙观"));
        stuList.add(new Student(4, "小明4", 21, "回龙观"));
        stuList.add(new Student(5, "小明5", 22, "回龙观"));
        stuList.add(new Student(6, "小明6", 23, "回龙观"));
        stuList.add(new Student(7, "小明7", 24, "回龙观"));
        stuList.add(new Student(8, "小明8", 25, "回龙观"));
        stuList.add(new Student(9, "小明9", 26, "回龙观"));
        data.put("stuList", stuList);
        //添加日期类型
        data.put("date", new Date());
        //null值的测试
        data.put("val", "123");
        //7、创建一个Writer对象，指定输出文件的路径及文件名。
//		Writer out = new FileWriter(new File("D:/temp/JavaEE32/freemarker/hello.txt"));
        Writer out = new FileWriter(new File("/Users/roc_peng/Downloads/student.html"));
        //8、生成静态页面
        template.process(data, out);
        //9、关闭流
        out.close();
    }

/*class Student implements Serializable{
    private Integer id;
    private String name;
    private Integer age;
    private String address;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    public Student() {
    }

    public Student(Integer id, String name, Integer age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Integer getId() {
        return 1;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }*/
}
