package cn.org.geneplus.service;

import cn.org.geneplus.config.SpringConfig;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author:quhaichuan
 * @Date:2025/6/10 11:27
 */
public class UserServiceTest extends TestCase {

    @Test
    public void testRegister()
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = applicationContext.getBean("userService",UserService.class);
        String register = userService.register("xiaoming", "123456");
    }

}