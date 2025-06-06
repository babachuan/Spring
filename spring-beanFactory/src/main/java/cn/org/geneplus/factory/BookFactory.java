package cn.org.geneplus.factory;

import cn.org.geneplus.bena.Book;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Author:quhaichuan
 * @Date:2025/6/4 13:56
 */
public class BookFactory implements FactoryBean<Book> {
    @Override
    public Book getObject() throws Exception {
        Book book = new Book("Spring", "quhaichuan");
        return book;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
