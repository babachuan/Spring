# 1）SpringBoot与JSP整合

引入Pom

```
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
        </dependency>
        <!-- jasper -->
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
            <scope>provided</scope>
        </dependency>
```

指定JSP访问路径

```
server.port=8088
server.servlet.context-path=/demo

spring.datasource.url=jdbc:mysql://localhost:3306/enjoy?characterEncoding=utf8&useSSL=false&serverTimezone=UTC
##数据库用户名
spring.datasource.username=root
##数据库密码
spring.datasource.password=123456

#JSP配置
spring.mvc.view.prefix=/WEB-INF/JSP/  //这里的目录相对webapp来讲的
spring.mvc.view.suffix=.jsp  //注意这里面的点
```

这里的访问路径`/WEB-INF/JSP/`指`src/main/webapp/WEB-INFO/JSP/`目录，注意尾缀中的`.`不能省略。

**Controller层**

```
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
```

从上面可以发现，controller层正常使用即可。

使用`http://localhost:8088/demo/list`可以访问到对应的结果

