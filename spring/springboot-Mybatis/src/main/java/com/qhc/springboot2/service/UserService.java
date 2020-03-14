package com.qhc.springboot2.service;

import com.qhc.springboot2.beans.User;
import com.qhc.springboot2.config.SqlFactory;
import com.qhc.springboot2.dao.UserDao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    SqlFactory sqlFactory;

    public User queryUser(int id) {
        return userDao.queryById(id);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public void addUser(User user) {

        SqlSession sqlSession =  sqlFactory.getSqlSessionFactory().openSession();
        UserDao userDao2 = sqlSession.getMapper(UserDao.class);
        userDao2.addUser(user);
        sqlSession.commit();
        sqlSession.close();
    }
}
