# 1）三层结构的项目

新建一个springboot项目，并通过简单的三层`controller` `service` `dao` 实现一个模型式的应用。这个应用写的很简单，就是帮助理解springMVC。

**city类**

```
package com.qhc.springboot2.beans;

public class City {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

这里声明一个简单的bean.

---

**MyController**

```
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
```

这个里面有三个方法

- addPage：跳转到添加页码
- add：真正添加数据的页面，添加完后仍让留在当前页面
- list：查看所有的数据

---

**CityDao**

```
package com.qhc.springboot2.dao;

import com.qhc.springboot2.beans.City;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CityDao {

    static Map<Integer, City> dataMap =  Collections.synchronizedMap(new HashMap<Integer, City>());
    public List<City> findAll() {
        return new ArrayList<>(dataMap.values());
    }

    public void save(City city) throws Exception {
        City data = dataMap.get(city.getId());
        if(data != null){
            throw new Exception("数据已经存在");
        }else{
            dataMap.put(city.getId(),city);
            System.out.println("====数据添加成功");
        }
    }
}
```

这里使用一个` Collections.synchronizedMap`模拟数据库操作，这里是线程安全。

---

**CityService类**

```
package com.qhc.springboot2.service;

import com.qhc.springboot2.beans.City;
import com.qhc.springboot2.dao.CityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityService {

    @Autowired
    CityDao cityDao;

    public List<City> findAll() {
        return cityDao.findAll();
    }

    public String add(City city) {
        try {
            cityDao.save(city);
            return "保存成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "保存失败";
        }
    }
}
```

在service类里通过dao进行操作，体现了分层的设计思想。

---

**template包了的模板文件**

**add.html文件**

```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>My SpringBoot</title>
</head>
<body>
<h1 th:text="${success}"></h1>

<form action="add" method="post">
    id:<input name="id" type="text"><br/>
    name:<input name="name" type="text"><br/>
    <input type="submit" value="submit">

</form>
</body>
</html>
```

**list.html**

```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>My SpringBoot</title>
</head>
<body>
City List
<hr>

<table border="1">

    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>

    <tr th:each="city : ${list}">
        <!-- EL JSTL-->
        <td th:text = "${city.id}"></td>
        <td th:text = "${city.name}"></td>
    </tr>
</table>
</body>

</html>
```

在这里使用`<tr th:each="city : ${list}">`循环填充数据，并写到一个列表里面。

启动tomcat后使用`http://localhost:8088/demo/add`进行数据添加

使用`http://localhost:8088/demo/list`进行所有数据的访问

测试成功！！！



