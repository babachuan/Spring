package com.qhc.multi.dao;

import com.qhc.multi.beans.Book;
import com.qhc.multi.conf.SqlSessionFactoryResource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class BookDao {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryResource.getSqlSessionFactory();

    public Book getBookAuthor(String bookname){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Book book = sqlSession.selectOne("com.qhc.multi.bookMapper.OOselect", bookname);
        sqlSession.commit();
        sqlSession.close();
        return book;
    }
}
