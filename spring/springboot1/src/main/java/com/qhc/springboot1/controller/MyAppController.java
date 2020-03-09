package com.qhc.springboot1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
/*
*通过url:http://localhost:8088/demo/  访问的结果：{"a":"AAA","b":"BBB","c":"CCC","d":"DDD"}
 */

@RestController //RestController = @Controller+@ResponseBody
public class MyAppController {
    @RequestMapping("/")
    public Map<String,String> myindex(){
        Map<String,String> map = new HashMap<>();

        map.put("a","AAA");
        map.put("b","BBB");
        map.put("c","CCC");
        map.put("d","DDD");
        return map;
    }
}
