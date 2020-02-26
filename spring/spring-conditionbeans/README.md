

<nav>
<a href="#"></a><br/>
<a href="#Spring基础-条件化注册bean">Spring基础条件化注册bean</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#Profile注解方式">Profile注解方式</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#profile注解使用小例子">profile注解使用小例子</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#profile声明在方法上">profile声明在方法上</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#自定义快捷注解">自定义快捷注解</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#一次设置多个profile">一次设置多个profile</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#在XML中设置profile">在XML中设置profile</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#在两个XML中定义">在两个XML中定义</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#在一个XML中定义">在一个XML中定义</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#激活profile">激活profile</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#环境变量">环境变量</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#代码方式">代码方式</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#配置文件方式">配置文件方式</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#单元测试中引入">单元测试中引入</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#DefaultProfile">DefaultProfile</a><br/>
</nav>

# Spring基础-条件化注册bean

在日常工作中，我们经常会遇到生产环境和测试环境配置切换的问题，每次测试时修改一套环境，上线时又是一套环境，面对复杂的系统，可能会出现修改风险。Spring早就为我们准备好了解决方法

- 通过@Profile注解:环境运行时根据bean属于哪个profile(需要)来决定是否创建对应的bean;
- 通过实现@Conditional接口:注解应用到带@Bean注解的方法上，根据给定的条件计算如果结果为true，就会创建bean,如果为false，这个bean就会忽略；

## Profile注解方式

下面通过一个简单的例子来介绍下@Profile注解的使用过程。

---

### profile注解使用小例子

这里还是使用DiscDriver的例子进行讲解，将Disc插到DiscDriver里面，然后读取Disc的内容。

**ShowInterface接口**

```
package com.qhc.profile;

public interface ShowInterface {
    public void show();
}
```

定义一个统一接口，方便在@Profile激活时有统一的bean类型指定。

----

**DevBean类**

```
package com.qhc.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevBean implements ShowInterface{
    private String msg="This is development Envirenment";
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public void show() {
        System.out.println(this.msg);
    }
}
```

这里使用`@Profile`注解定义这个bean为dev，并实现了`ShowInterface`接口，方便在profile激活时返回统一bean;

---

**ProdBean类**

```
package com.qhc.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProdBean implements ShowInterface{
    private String msg="This is production Envirenment";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public void show() {
        System.out.println(this.msg);
    }
}
```

- 这里使用`@Profile`注解定义这个bean为prod，并实现了`ShowInterface`接口，方便在profile激活时返回统一bean;

---

**BeanConfiguration类**

```
package com.qhc.profile;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.qhc.profile")
public class BeanConfiguration {
}
```

自动装配的配置类

---

**ProfileTest测试类**

```
import com.qhc.profile.BeanConfiguration;
import com.qhc.profile.ShowInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeanConfiguration.class)
@ActiveProfiles("dev")
public class ProfileTest {
    @Autowired
    private ShowInterface showInterface;
    @Test
    public void Test1(){
        showInterface.show();
    }
}

打印结果：This is development Envirenment
```

在测试类里通过`@ActiveProfiles("dev")`注解激活对应的dev profile，那么在容器中就只会创建带有`@Profile("dev")`注解的bean,从结果也印证了。这种设置极大的方便了环境切换或条件化产生bean.

> [参考官方文档](https://docs.spring.io/spring/docs/5.1.13.RELEASE/spring-framework-reference/core.html#beans-definition-profiles)

### profile声明在方法上

profile不仅可以像上面那样声明在类上，也可以声明在方法上

```
//这是官方文档的例子
@Configuration
public class AppConfig {

    @Bean("dataSource")
    @Profile("development") 
    public DataSource standaloneDataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("classpath:com/bank/config/sql/schema.sql")
            .addScript("classpath:com/bank/config/sql/test-data.sql")
            .build();
    }

    @Bean("dataSource")
    @Profile("production") 
    public DataSource jndiDataSource() throws Exception {
        Context ctx = new InitialContext();
        return (DataSource) ctx.lookup("java:comp/env/jdbc/datasource");
    }
}
```

从上面的例子看出，`@Profile`声明在方法上，并且返回的bean的名字和类型都是一样的。

### 自定义快捷注解

我们可以直接使用`@Profile(name)`注解来定义profile，也可以通过自定义注解的方式来快捷注解

```
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Profile("production")
public @interface Production {
}
```

这样就可以直接使用`@Production`注解进行定义了，效果跟上面是一样的

### 一次设置多个profile

在类、方法或注解上使用`@Profile`注解时，对应的类、方法bean或注解对应的项目会被创建或被忽略的条件是可以多个设置的

```
@Profile({"p1", "p2"})
```

这个bean在p1**或**p2被激活的条件下，才会创建bean.

```
@Profile({"p1", "!p2"})
```

这个bean在**p1被激活**或**p2不被激活**满足的条件下，才会创建bean.

- `!`: A logical “not” of the profile

- `&`: A logical “and” of the profiles
- `|`: A logical “or” of the profiles

*注意*：在使用过程中注意括号的使用`production & us-east | eu-central`这个是不合格的,`production & (us-east | eu-central)`这种方式才是合法的。

### 在XML中设置profile

#### 在两个XML中定义

```
// development文件定义
<beans profile="development"
    xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:jdbc="http://www.springframework.org/schema/jdbc"
    xsi:schemaLocation="...">

    <jdbc:embedded-database id="dataSource">
        <jdbc:script location="classpath:com/bank/config/sql/schema.sql"/>
        <jdbc:script location="classpath:com/bank/config/sql/test-data.sql"/>
    </jdbc:embedded-database>
</beans>
```

这里在顶层的`<beans profile="development"`进行定义

---

```
// production文件定义
<beans profile="production"
    xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:jee="http://www.springframework.org/schema/jee"
    xsi:schemaLocation="...">

    <jee:jndi-lookup id="dataSource" jndi-name="java:comp/env/jdbc/datasource"/>
</beans>
```

这里在顶层的`<beans profile="production""`进行定义

#### 在一个XML中定义

```
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:jdbc="http://www.springframework.org/schema/jdbc"
    xmlns:jee="http://www.springframework.org/schema/jee"
    xsi:schemaLocation="...">

    <!-- other bean definitions -->

    <beans profile="development">
        <jdbc:embedded-database id="dataSource">
            <jdbc:script location="classpath:com/bank/config/sql/schema.sql"/>
            <jdbc:script location="classpath:com/bank/config/sql/test-data.sql"/>
        </jdbc:embedded-database>
    </beans>

    <beans profile="production">
        <jee:jndi-lookup id="dataSource" jndi-name="java:comp/env/jdbc/datasource"/>
    </beans>
</beans>
```

*注意*：在XML中进行定义比较不方便使用上面的逻辑判断。

## 激活profile

Spring在决定哪个profile处于激活状态时，需要依赖两个独立的属性：

- spring.profiles.active
- spring.profiles.default

如果设置了spring.profiles.active属性的话，那么它的值就会用来确定哪个profile是激活的。

如果没有设置spring.profiles.active的话，spring将会查找spring.profiles.default的值。

如果spring.profiles.active和spring.profiles.default均没有设置的话，那就没有激活的profile,因此只会创建那些没有定义profile的bean.

**激活方式**：

- 作为DispatcherServlet的初始化参数
- 作为Web应用的上下文参数
- 作为JNDI条目
- 作为环境变量
- 作为JVM的系统属性
- 在集成测试类上，使用@ActiveProfiles注解设置

### 环境变量

#### 代码方式

为了配合代码方式查看，分别对`DevBean`和`ProdBean`添加无参构造函数,这样在测试的时候就知道创建的是哪个bean了。

```
    public ProdBean() {
        System.out.println("=====prod====");
    }
```

```
    public DevBean() {
        System.out.println("=====dev====");
    }
```



激活profile有多种实现方式，这里提供一种使用spring.profiles.active的方式

**EnvProfileTest测试类**

```
import com.qhc.profile.BeanConfiguration;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EnvProfileTest {
    @Test
    public void envTest() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("prod");
        ctx.register(BeanConfiguration.class);
        ctx.refresh();

    }
}
```

这里使用`ctx.getEnvironment().setActiveProfiles("prod")`来激活对应的profile。

*注意*：这里也可以同时激活多个`ctx.getEnvironment().setActiveProfiles("profile1", "profile2")`

#### 配置文件方式

在resources目录下新建`application.properties`文件，内容如下：

```
spring.profiles.active = prod
```

*注意*：在指定value值的时候不要加双引号,`spring.profiles.active = "prod"`这种方式不能别识别

---

**BeanConfiguration类**

```
package com.qhc.profile;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.qhc.profile")
@PropertySource("classpath:application.properties")
public class BeanConfiguration {
}
```

在配置类里通过`@PropertySource("classpath:application.properties")`属性加载配置类文件

---

**PropertyTest测试类**

```
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeanConfiguration.class)
public class PropertyTest {
    @Autowired
    private ShowInterface showInterface;

    @Test
    public void Test1(){
        showInterface.show();
    }
}
```

**测试结果**

```
=====prod====
This is production Envirenment
```

### 单元测试中引入

如代码中

```
import com.qhc.profile.BeanConfiguration;
import com.qhc.profile.ShowInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeanConfiguration.class)
@ActiveProfiles("dev")
public class ProfileTest {
    @Autowired
    private ShowInterface showInterface;
    @Test
    public void Test1(){
        showInterface.show();
    }
}
```

使用`@ActiveProfiles("dev")`注解在单元测试中激活profile。

## DefaultProfile

default profile就是前面提到的默认值，设置也很简单

```
@Configuration
@Profile("default")
public class DefaultDataConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("classpath:com/bank/config/sql/schema.sql")
            .build();
    }
}
```

使用`@Profile("default")`注解声明即可。