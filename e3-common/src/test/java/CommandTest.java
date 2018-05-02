import org.junit.Test;

/**
 * Created by roc_peng on 2017/9/4.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description java调用linux代码测试
 */

public class CommandTest {

    @Test
    public void commandTest() throws Exception{
        Process process = null;
        String command1 = "chmod -R 777 /Users/roc_peng/Downloads/test1";
        process = Runtime.getRuntime().exec(command1);
        Thread thread = new Thread();
        thread.interrupt();
    }
}
