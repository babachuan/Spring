package com.qhc.springboot2.controller;

import com.qhc.springboot2.beans.City;
import com.qhc.springboot2.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MyController {
    @Autowired
    CityService cityService;

    @RequestMapping("/list") //通过url:http://localhost:8088/demo/list  访问已经添加的信息
    public String list(ModelMap map) {
        List<City> list = cityService.findAll();

        map.addAttribute("list", list);
        return "list";
    }

    @RequestMapping("/add")   //添加页面，将前台传递过来的数据添加到dao,具体url:http://localhost:8088/demo/add
    public String add(@ModelAttribute City city, Model map) {
        String sucess = cityService.add(city);
        map.addAttribute("success", sucess);
        return "add";
    }
    @RequestMapping("/addPage")  //通过http://localhost:8088/demo/addPage访问添加页面
    public String addPage() {

        return "add";
    }
}
