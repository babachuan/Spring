package com.qhc.springboot2.mappers;

import com.qhc.springboot2.beans.User;

import java.util.List;

public interface UserMapper {
    User queryById(int id);

    List<User> getAll();

    int addUser(User user);
}
