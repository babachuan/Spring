package cn.org.geneplus.bean;

import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:quhaichuan
 * @Date:2025/6/4 14:49
 */
public class UserTest extends TestCase {

    @Test
    public void testLifeCycle(){
        // 如果要执行容器的销毁方法，需要调用容器的关闭，否则，容器销毁方法不会执行
        // 这里使用ApplicationContext的子类，ClassPathXmlApplicationContext来实现，并调用close方法
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        User user = applicationContext.getBean("user", User.class);
        System.out.println("第四步：获取完bean方法");
        System.out.println(user);
        applicationContext.close(); // 手动执行关闭，关闭到时候会调用销毁方法
    }

}