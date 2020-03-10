<nav>
<a href="#"></a><br/>
<a href="#1）第一个SpringBoot项目">1）第一个SpringBoot项目</a><br/>
<a href="#2）配置springboot访问web的端口和项目名">2）配置springboot访问web的端口和项目名</a><br/>
<a href="#3）使用Thymeleaf模板引擎">3）使用Thymeleaf模板引擎</a><br/>
</nav>

# 1）第一个SpringBoot项目

通过IDEA工具的Spring Initializr自动生成springboot项目空包。

对应的结构目录如下：

```
.
+--- .gitignore
+--- .idea
|   +--- compiler.xml
|   +--- encodings.xml
|   +--- inspectionProfiles
|   |   +--- Project_Default.xml
|   +--- libraries
|   +--- misc.xml
|   +--- modules.xml
|   +--- springboot1.iml
|   +--- vcs.xml
|   +--- workspace.xml
+--- .mvn
|   +--- wrapper
|   |   +--- maven-wrapper.jar
|   |   +--- maven-wrapper.properties
|   |   +--- MavenWrapperDownloader.java
+--- mvnw
+--- mvnw.cmd
+--- pom.xml
+--- README.md
+--- src
|   +--- main
|   |   +--- java
|   |   |   +--- com
|   |   |   |   +--- qhc
|   |   |   |   |   +--- springboot1
|   |   |   |   |   |   +--- controller
|   |   |   |   |   |   |   +--- MyAppController.java
|   |   |   |   |   |   |   +--- SimpleThymleafController.java
|   |   |   |   |   |   |   +--- UserController.java
|   |   |   |   |   |   +--- Springboot1Application.java
|   |   +--- resources
|   |   |   +--- application.properties
|   |   |   +--- rebel.xml
|   |   |   +--- templates
|   |   |   |   +--- hello.html
|   +--- test
|   |   +--- java
|   |   |   +--- com
|   |   |   |   +--- qhc
|   |   |   |   |   +--- springboot1
|   |   |   |   |   |   +--- Springboot1ApplicationTests.java
+--- target
|   +--- classes
|   |   +--- application.properties
|   |   +--- com
|   |   |   +--- qhc
|   |   |   |   +--- springboot1
|   |   |   |   |   +--- controller
|   |   |   |   |   |   +--- MyAppController.class
|   |   |   |   |   |   +--- SimpleThymleafController.class
|   |   |   |   |   |   +--- UserController.class
|   |   |   |   |   +--- Springboot1Application.class
|   |   +--- rebel.xml
|   |   +--- templates
|   |   |   +--- hello.html
|   +--- generated-sources
|   |   +--- annotations
|   +--- generated-test-sources
|   |   +--- test-annotations
|   +--- test-classes
|   |   +--- com
|   |   |   +--- qhc
|   |   |   |   +--- springboot1
|   |   |   |   |   +--- Springboot1ApplicationTests.class

```

# 2）配置springboot访问web的端口和项目名

```
server.port=8088   //配置端口
server.servlet.context-path=/demo  //配置项目名
```

# 3）使用Thymeleaf模板引擎

**pom引入依赖**

```
       <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
```

**编写Controller**

```
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
```

返回的字符串默认是访问src/main/resources/templates目录下的`.html`文件，返回字符串的名字就是html文件的名字。

**src/main/resources/templates/hello.html文件**

```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>My SpringBoot</title>
</head>
<body>
<h1 th:text="${message}"></h1>
</body>
</html>
```

启动springboot项目，使用url:`http://localhost:8088/demo/leaf`访问得到的结果：

```
hello world theymleaf!
```

