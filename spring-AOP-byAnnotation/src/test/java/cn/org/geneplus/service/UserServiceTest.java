package cn.org.geneplus.service;

import cn.org.geneplus.config.SpringConfig;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author:quhaichuan
 * @Date:2025/6/11 10:45
 */
public class UserServiceTest extends TestCase {

    @Test
    public void testAnnotationProxy(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = applicationContext.getBean("userService",UserService.class);
        userService.login("xiaoming","123456");
    }

    // 测试通过反射获取注解示例
    @Test
    public void testGetAnnotation(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = applicationContext.getBean("userService",UserService.class);
        userService.login("xiaoming","123456");
    }

}