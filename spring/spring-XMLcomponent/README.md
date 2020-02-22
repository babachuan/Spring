<!--toc-->

![spring](https://img.shields.io/badge/spring-5.1.3.RELEASE-brightgreen.svg)     ![author](https://img.shields.io/badge/author-quhaichuan-orange.svg)     ![jdk](https://img.shields.io/badge/jdk->=1.8-blue.svg)

## **Spring基础-XML文件装配Bean**

由于历史原因，在一定程度上Spring成为了XML配置方式的同义词。但是现在有了强大的自动化装配和基于Java代码的配置，XML已经不再作为首选，但由于历史原因，有较多的XML配置的项目，这里也做下介绍。基于XML也是要包含两个方面：

- 实例化bean(initialize bean):需要通过XML的方式声明bean,存放的spring 容器中，在这里使用`<bean>`元素
- 注入bean(injection bean):将bean注入到依赖的其他bean中，这里使用`<constructor-arg>`元素

---

### 示例解析

这里还是使用DiscDriver的例子进行讲解，将Disc插到DiscDriver里面，然后读取Disc的内容。

**Disc类**

```
package com.qhc.xml;

public class Disc {
    private String content="This is a beautiful music";
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public Disc() {  
    }
}


```

由于要使用XML的方式声明bean,所以在类中需要有一个无参构造器`public Disc()`，否则会报错。

----

**DiscDriver类**

```
package com.qhc.xml;

public class DiscDriver {
    private Disc disc;

    //借助构造器注入bean
    public DiscDriver(Disc disc) {
        this.disc = disc;
    }

    public void paly(){
        System.out.println(disc.getContent());
    }

    public DiscDriver() {
    }
}


```

DiscDriver类也要有一个无参构造器，方便XML配置。同时为了在XML中借用构造器注入初始化，创建了一个有参构造器public DiscDriver(Disc disc)

---

**spring-context.xml文件**

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="disc" class="com.qhc.xml.Disc"/>
    <bean id="discDriver" class="com.qhc.xml.DiscDriver">
        <constructor-arg ref="disc"/>
    </bean>
</beans>
```

从上面可以看出，`<bean>`标签声明了bean，使用id属性定义bean名字（如果没有指定，默认使用类的全限定类名来进行命名：com.qhc.xml.Disc#0），创建bean的类通过class属性。

通过`<constructor-arg>`元素，将元素id="disc"的bean注入进来。

*注意*:

- ref 是指引用，如果使用value="disc"就是把字面量注入，注意使用的区别。

  ---

**测试类**

```
import com.qhc.xml.DiscDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-context.xml") //这里这两种写法都是可以的
//@ContextConfiguration("/spring-context.xml")
public class XMLTest {
    @Autowired
    private DiscDriver discDriver;

    @Test
    public void xmlTest(){
        discDriver.paly();
    }
}

```

测试类中使用@ContextConfiguration注解引入spring的XML配置文件，上面的两种引入形式都是可行的。

---

### 测试结果

```
在控制台打印：This is a beautiful music
```





