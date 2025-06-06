package cn.org.geneplus.factory;

import cn.org.geneplus.bena.Book;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:quhaichuan
 * @Date:2025/6/4 13:59
 */
public class BookFactoryTest extends TestCase {

    @Test
    public void testFactory(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        /** 注意，这里通过工厂<bean id="book" class="cn.org.geneplus.factory.BookFactory"></bean>
         * 创建的bean，返回的是book对象，而不是BookFactory对象
         */

        Book book = applicationContext.getBean("book", Book.class);
        System.out.println(book);
    }

}