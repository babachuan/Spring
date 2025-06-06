package cn.org.geneplus.bean;

import cn.org.geneplus.config.SpringConfig;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:quhaichuan
 * @Date:2025/6/5 9:44
 */
public class UserTest extends TestCase {

    @Test
    public void testUser()
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserController userController = applicationContext.getBean("userController", UserController.class);
//        user.setName("qhc");
//        user.setAge(18);
        System.out.println(userController);
    }

    // 通过配置类加载spring容器
    @Test
    public void testUser2()
    {
        // 这里就不是指定spring.xml了，而是使用AnnotationConfigApplicationContext来加载指定的配置类的字节码
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserController userController = applicationContext.getBean("userController", UserController.class);
        System.out.println(userController);
    }

}