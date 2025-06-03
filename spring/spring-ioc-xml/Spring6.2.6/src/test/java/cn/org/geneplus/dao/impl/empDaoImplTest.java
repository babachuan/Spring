package cn.org.geneplus.dao.impl;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @Author:quhaichuan
 * @Date:2025/6/3 11:23
 */
public class empDaoImplTest {


    @Test
    public void testAddEmp() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        empDaoImpl empDaoImpl = applicationContext.getBean("empDaoImpl",empDaoImpl.class);
        empDaoImpl.addEmp();

    }
}