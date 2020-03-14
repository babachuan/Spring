package com.qhc.mybatis2.dao;

import com.qhc.mybatis2.beans.User;
import com.qhc.mybatis2.config.SqlSessionFactorySource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDao {
    SqlSessionFactory sessionFactory = SqlSessionFactorySource.getSqlSessionFactory();

    public void addUser(User user) {
        SqlSession sqlSession = sessionFactory.openSession();
        int count = sqlSession.insert("com.qhc.mybatis2.UserMapper.addUser",user);
        sqlSession.commit();
        sqlSession.close();

    }

    public int deleteUser(User user) {
        SqlSession sqlSession = sessionFactory.openSession();
        int count = sqlSession.delete("com.qhc.mybatis2.UserMapper.deleteUser", user);
        sqlSession.commit();
        sqlSession.close();
        return count;

    }

    public int updateUser(User user) {
        SqlSession sqlSession = sessionFactory.openSession();
        int updateCount = sqlSession.update("com.qhc.mybatis2.UserMapper.updateUser", user);
        sqlSession.commit();
        sqlSession.close();
        return updateCount;
    }

    public User getOneById(int id) {
        SqlSession sqlSession = sessionFactory.openSession();
        User user = sqlSession.selectOne("com.qhc.mybatis2.UserMapper.selectOne", id);
        sqlSession.commit();
        sqlSession.close();
        return user;
    }

    public List<User> getAll() {
        SqlSession sqlSession = sessionFactory.openSession();
        List<User> list = sqlSession.selectList("com.qhc.mybatis2.UserMapper.getAll");
        return list;
    }

    public List<User> getByLikeName(String name){
        SqlSession sqlSession= sessionFactory.openSession();
        List<User> list = sqlSession.selectList("com.qhc.mybatis2.UserMapper.getByLikeName", "%"+name+"%");
        sqlSession.commit();
        sqlSession.close();
        return list;
    }
}
