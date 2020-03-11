package com.qhc.springboot2.controller;

import com.qhc.springboot2.beans.City;
import com.qhc.springboot2.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/listbd") //通过url:http://localhost:8088/demo/listbd 查询结果"保定市"
    public String listbd(ModelMap map) {
        City baoding = cityService.findByCity("保定市");

        map.addAttribute("list", baoding);
        return "list";
    }

    @RequestMapping("/listn") //通过url:http://localhost:8088/demo/listbd 查询结果"保定市"
    @ResponseBody
    public int listn() {
        int count= cityService.countByCity("保定市");
        return count;
    }

    @RequestMapping("/listbq") //通过url:http://localhost:8088/demo/listbq 查询结果"保定市"
    public String listbq(ModelMap map) {
        City qbd = cityService.findWithQuery(10,"保定市");

        map.addAttribute("list", qbd);
        return "list";
    }

    @RequestMapping("/updaten") //通过url:http://localhost:8088/demo/updaten 修改城市名字
    @ResponseBody
    public int updateName() {
        int count = cityService.updateCityYourName(1,"quhaichuan");
        return count;
    }

    //使用原生sql查询，查询的字段并不再City里
    @RequestMapping("/listbo") //通过url:http://localhost:8088/demo/listbo
    public String listbo(ModelMap map) {
        List<City> qbd = cityService.findOriginSQL("110000");

        map.addAttribute("list", qbd);
        return "list";
    }

}
