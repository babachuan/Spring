package com.qhc.springboot1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 通过url:http://localhost:8088/demo/leaf 访问的结果：hello world theymleaf!
 */

@Controller
public class SimpleThymleafController {
    @RequestMapping("/leaf")
    public String index(ModelMap map){
        //加入一个属性，用来在模板中读取
        map.put("message","hello world theymleaf!");  //在html文件中通过message的key获取对应的“hello world，并插入到占位符中
        //return模板文件的名称，默认以html结尾，对应src/main/resources/templates/hello.html
        return "hello";
    }
}
