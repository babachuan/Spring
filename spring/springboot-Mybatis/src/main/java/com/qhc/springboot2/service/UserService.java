package com.qhc.springboot2.service;

import com.github.pagehelper.PageHelper;
import com.qhc.springboot2.beans.User;
import com.qhc.springboot2.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userDao;


    public User queryUser(int id) {
        return userDao.queryById(id);
    }

    public List<User> getAll(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return userDao.getAll();
    }

    public int addUser(User user){
        int insertCount = userDao.addUser(user);
        return insertCount;
    }

}
