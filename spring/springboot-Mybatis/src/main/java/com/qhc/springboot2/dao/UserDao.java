package com.qhc.springboot2.dao;

import com.qhc.springboot2.beans.User;
import com.qhc.springboot2.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    @Autowired
    UserMapper userMapper;

    public User queryById(int id){
        User user = userMapper.queryById(id);
        return  user;
    }

    public List<User> getAll(){
        List<User> all = userMapper.getAll();
        return all;
    }

    public int addUser(User user){
        int i = userMapper.addUser(user);
        return i;
    }
}
