package com.qhc.springboot2;

import com.qhc.springboot2.beans.Pet;
import com.qhc.springboot2.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class MyController {

    @RequestMapping("/list") //通过url:http://localhost:8088/demo/list  访问已经添加的信息
    public String list(ModelMap map) {
        map.addAttribute("name","<b>Chian</b>,USA,UK");
        map.addAttribute("age","18");
        Pet pet = new Pet("Tom","哈士奇");
        User user = new User("quhaichuan","20",pet);
        map.addAttribute("user",user);
        return "list";
    }


}
