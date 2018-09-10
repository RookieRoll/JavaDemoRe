import Service.DemoSerivce;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Consumer {
    public static void main(String[] args) {
        try{
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
            context.start();
            DemoSerivce serivce = (DemoSerivce) context.getBean("demoService");
            String result=serivce.getValueTest("测试");
            System.out.println(result);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
}
