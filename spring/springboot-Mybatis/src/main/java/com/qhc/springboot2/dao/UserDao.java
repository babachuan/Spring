package com.qhc.springboot2.dao;

import com.qhc.springboot2.beans.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    User queryById(int id);

    List<User> getAll();

    void addUser(User user);
}
