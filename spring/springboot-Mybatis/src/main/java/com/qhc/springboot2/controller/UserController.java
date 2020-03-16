package com.qhc.springboot2.controller;

import com.qhc.springboot2.beans.User;
import com.qhc.springboot2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("getuser/{id}")  //通过http://localhost:8088/demo/getuser/3访问添加页面
    public User getUser(@PathVariable int id) {
        return userService.queryUser(id);
    }


    @RequestMapping("getAll")
    public List<User> getAll(@RequestParam Integer pageNum,@RequestParam Integer pageSize){
        return userService.getAll(pageNum,pageSize);
    }


    @RequestMapping("addUser")
    public int addUser(){
        User xiaoming = new User();
        xiaoming.setUserName("xiaoming");
        xiaoming.setPassWord("ming123456");
        int i = userService.addUser(xiaoming);
        return i;
    }

}
