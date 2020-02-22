<nav>
<a href="#"></a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#Spring基础-自动装配">Spring基础自动装配</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#示例解析">示例解析</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#注解Component">注解Component</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#注解ComponentScan">注解ComponentScan</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#注解Autowired">注解Autowired</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#测试结果">测试结果</a><br/>
</nav>

## Spring基础-自动装配

Spring从两个角度来实现自动化装配：

- **组件扫描 **  (component scanning):Spring会使用**@ComponentScan**自动发现应用上下文中所创建的bean；
- **自动装配**   (autowiring):通过使用**@Autowired**注解，自动满足bean之间的依赖。

---

## 示例解析

我们通常的概念是，一个CD碟片(Disc)需要放到CD播放机(DiscDriver)里才可以播放碟片弄的内容，我们以这个模型来认识Spring的自动装配。

### 注解Component

```
package com.qhc.cdplayer;
import org.springframework.stereotype.Component;

@Component
public class Disc {
    //This is a Disc and it can be played by DiscDriver
    private String content="This is a beautiful music";
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
```

这里使用@Component表明该类作为组件类，告知Spring要为这个类创建bean;其他地方出现的这个注解同。

可以使用@Component("beanName")为bean指定名称。

---

### 注解ComponentScan

```
package com.qhc.cdplayer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.qhc.cdplayer")
public class DiscDriverConfig {
}
```

这里使用@ComponentScan是要扫描basePackages目录下的所有有@Component注解的类。默认扫描与这个配置类相同的包以及对应的子包，通过basePackages指定扫描目录。

---

### 注解Autowired

```
package com.qhc.cdplayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiscDriver {
    @Autowired
    private Disc disc;
    
    public void paly(){
        System.out.println(disc.getContent());
    }
}
```

在disc成员变量上添加@Autowired注解，在创建bean的时候会自动再Spring应用上下文中寻找匹配Disc需求的bean,并把它注入进来。除了成员变量，还可以用到setter方法上。

---

### 测试结果

```
在控制台打印：This is a beautiful music
```





