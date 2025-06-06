package cn.org.geneplus.bean;

import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * @Author:quhaichuan
 * @Date:2025/6/4 11:25
 */
public class StudentTest extends TestCase {

    @Test
    public void testGetBookArrays() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Student student = applicationContext.getBean("student", Student.class);
        System.out.println(Arrays.toString(student.getBookArrays()));
        System.out.println(student.getBookList());
        System.out.println(student.getBookMap());
        System.out.println(student.getBookSet());
        System.out.println(student.getBookObjList());
    }

    // 测试通过外部公共集合进行注入
    @Test
    public void testOuterObjList(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring2.xml");
        Student student = applicationContext.getBean("student2", Student.class);
        System.out.println(student.getBookObjList());
    }
}