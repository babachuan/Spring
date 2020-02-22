<nav>
<a href="#"></a><br/>
&emsp;&emsp;<a href="# **Spring基础-Java代码装配Bean**">Spring基础Java代码装配Bean</a><br/>
&emsp;&emsp;&emsp;&emsp;<a href="# 示例解析">示例解析</a><br/>
&emsp;&emsp;&emsp;&emsp;<a href="# 测试结果">测试结果</a><br/>
</nav>

![spring](https://img.shields.io/badge/spring-5.1.3.RELEASE-brightgreen.svg)     ![author](https://img.shields.io/badge/author-quhaichuan-orange.svg)     ![jdk](https://img.shields.io/badge/jdk->=1.8-blue.svg)

## **Spring基础-Java代码装配Bean**

自动装配固然方便，但如果需要将第三方的组件装配到应用中，就无法在它的类上添加@Component注解了。这时候就需要显示装配。显示装配可以用Java代码，也可以使用XML，这里讲解使用Java代码。

使用Java代码装配一般需要如下两个因素，先看下官网的说明：

```
You can use @Bean-annotated methods with any Spring @Component. However, they are most often used with @Configuration beans.
```

重点来了，`@Bean`和`@Configuration`，这两个注解是使用Java代码装配的两个重要注解。下面用例子说明。

<br/>

### 示例解析

这里还是使用DiscDriver的例子进行讲解

**Disc类**

```
package com.qhc.cdplayer;

public class Disc {
    private String content="This is a beautiful music";
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}

```

Disc类的@Component去掉了

**DiscDriver类**

```
package com.qhc.cdplayer;

import org.springframework.beans.factory.annotation.Autowired;

public class DiscDriver {
    //Autowired annotation will instantiate and inject disc object in to DiscDriver.
    @Autowired
    private Disc disc;
    public void paly(){
        System.out.println(disc.getContent());
    }
}

```

DiscDriver类的@Component去掉了

**DiscDriverConfig类**

```
package com.qhc.cdplayer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscDriverConfig {
    @Bean
    public Disc disc(){
        return  new Disc();
    }

    @Bean
    public DiscDriver discDriver(){
        return new DiscDriver();
    }
}

```

从上面可以看出，之前的@Component注解全都去掉了，并且只需要一个Java配置类即可。在配置类里面声明用@Bean注解的方法，方法体中包含了最终产生bean实例的逻辑。

*注意*:

- 这种方式默认情况下，bean的ID与带有@Bean注解的方法名是一样的，可以通过@Bean(name=value)指定

**测试类**

```
import com.qhc.cdplayer.DiscDriver;
import com.qhc.cdplayer.DiscDriverConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DiscDriverConfig.class)
public class DiscDriverTest {
    @Autowired
    private DiscDriver discDriver;

    @Test
    public void Test(){
        discDriver.paly();
    }
}

```

### 测试结果

```
在控制台打印：This is a beautiful music
```





