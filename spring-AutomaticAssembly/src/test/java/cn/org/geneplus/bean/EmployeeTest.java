package cn.org.geneplus.bean;

import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:quhaichuan
 * @Date:2025/6/4 16:41
 */
public class EmployeeTest extends TestCase {

    @Test
    public void testEmployee()
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Employee employee = applicationContext.getBean("employee", Employee.class);
        System.out.println(employee);
    }

    // 根据类型自动装配的测试用例
    @Test
    public void testEmployeeByType()
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Employee employee = applicationContext.getBean("employee2", Employee.class);
        System.out.println(employee);
    }

}