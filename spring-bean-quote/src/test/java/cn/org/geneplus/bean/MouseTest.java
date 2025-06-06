package cn.org.geneplus.bean;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:quhaichuan
 * @Date:2025/6/4 9:34
 */
public class MouseTest {

    @Test
    public void getName() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Mouse mouse = applicationContext.getBean("mouse1", Mouse.class);
        System.out.println(mouse);


    }

    @Test
    public void getCat(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Cat cat = applicationContext.getBean("cat", Cat.class);
        System.out.println(cat);
    }
}