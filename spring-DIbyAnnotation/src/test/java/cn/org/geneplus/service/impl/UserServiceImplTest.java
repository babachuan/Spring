package cn.org.geneplus.service.impl;

import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:quhaichuan
 * @Date:2025/6/5 11:05
 */
public class UserServiceImplTest extends TestCase {

    @Test
    public void testAdd() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserServiceImpl userServiceImpl = applicationContext.getBean("userServiceImpl", UserServiceImpl.class);
        userServiceImpl.add();
    }

}