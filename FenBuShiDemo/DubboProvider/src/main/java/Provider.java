import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Provider.xml");
        context.start();
        System.out.println("启动完成");
        System.in.read(); // 按任意键退出
    }
}
