package com.qhc.springboot2.controller;

import com.qhc.springboot2.beans.City;
import com.qhc.springboot2.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class MyController {
    @Autowired
    CityService cityService;

    @RequestMapping("/list") //通过url:http://localhost:8088/demo/list
    public String list(ModelMap map) {
        List<City> list = cityService.findAll();

        map.addAttribute("list", list);
        return "list";
    }

}
