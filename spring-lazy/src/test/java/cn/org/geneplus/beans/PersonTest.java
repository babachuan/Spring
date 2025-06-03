package cn.org.geneplus.beans;


import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:quhaichuan
 * @Date:2025/6/3 17:42
 */
class PersonTest {

    // 测试懒加载
    @Test
    void getUserid() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    }

    // 测试scope属性
    @Test
    void testScope() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Person person1 = applicationContext.getBean("person2", Person.class);
        Person person2 = applicationContext.getBean("person2", Person.class);
        System.out.println(person1 == person2);
    }

    // 通过setter方法注入属性，需要类中定义相关的setter方法
    @Test
    void testSetter() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Person person = applicationContext.getBean("person3", Person.class);
        System.out.println(person);
    }

    // 通过构造方法注入属性，需要类中定义相关的构造方法
    @Test
    void testConstructor() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Person person  = applicationContext.getBean("person4", Person.class);
        System.out.println(person);
    }

    // 测试通过p空间和c空间进行属性注入
    @Test
    void testPAndC() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Person person = applicationContext.getBean("person5", Person.class);
        System.out.println(person);
        Person person1 = applicationContext.getBean("person6", Person.class);
        System.out.println(person1);
    }
}