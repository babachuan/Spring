package cn.org.geneplus.service;

import cn.org.geneplus.config.SpringConfig;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author:quhaichuan
 * @Date:2025/6/11 17:02
 */
public class UserServiceTest extends TestCase {

    // 测试通知
    @Test
    public void testAopAdvice(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = applicationContext.getBean("userService",UserService.class);
        userService.register("xiaoming","123456");
        System.out.println("----------------------");
        userService.login("admin","123456");
    }

}