# 1）Thymleaf基础使用

引入Pom

```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
```

定义一个Controller

```
@Controller
public class MyController {

    @RequestMapping("/list") //通过url:http://localhost:8088/demo/list  访问已经添加的信息
    public String list(ModelMap map) {
        map.addAttribute("name","<b>Chian</b>,USA,UK");
        map.addAttribute("age","18");
        return "list";
}
```

这里使用`ModelMap`接收数据，并传递数据，方面在页面里使用。这里返回的`return "list"`就是去找list.html页面。

---

**list.html**

```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"><!--在url上加上@符号，自动帮我们加上contentpath-->
    <link rel="stylesheet" type="text/css" th:href="@{/css/min.css}"/>
    <title>MyThymleaf</title>
</head>
<body>
<h1 th:utext="${name}">你好</h1>
<p th:text="${age}"></p>

</body>

</html>
```

首先需要引入`xmlns:th="http://www.thymeleaf.org"`，在就可以在页面里使用Thymleaf的相关指令。页面涉及如下几点：

`th:href="@{/css/min.css}"`这个指令，通知去哪里找css文件，`@`会自动帮我们加上content-path。

*注意*：css和js文件一般放到`resources/static`目录下，html模板文件放到`resources/template`目录下。

`th:utext="${name}"`这个是变量取值，在前面controller里定义并插入了`map.addAttribute("name","<b>Chian</b>,USA,UK");`，在这里就可以使用`${..}`的形式取出来。注意要使用`th:text(转义)`或`th:utext(不转义)`

Thymleaf还有很多其他的功能，可以参见[官网](https://www.thymeleaf.org/documentation.html)

