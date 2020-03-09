package com.qhc.springboot1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
*
* 通过url：http://localhost:8088/demo/hello/m  访问得到结果“hello"
 */
@Controller
@RequestMapping("/hello")
public class UserController {

    @ResponseBody//使用responsebody不会经过模板引擎thymeleaf
    @RequestMapping("/m")
    public String hello(){
        return "hello";
    }

}
