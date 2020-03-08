<nav>
<a href="#"></a><br/>
<a href="#AOP术语">AOP术语</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#通知">通知</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#连接点">连接点</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#切点">切点</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#切面">切面</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#织入">织入</a><br/>
<a href="#SpringAOP使用示例">SpringAOP使用示例</a><br/>
</nav>

# AOP术语

关于AOP的一些相关概念或过程看参考下图：

![示意图](https://note.youdao.com/yws/public/resource/13ff7d046b0aa4afd087ac0c813282a0/xmlnote/81420383F6344CEE9741497F59C035D1/14186)

上面图中，在一个或多个连接点上，可以把切面织入到程序的执行过程中。下面分别对上面提到的概念进行解释。

## 通知

**通知就是描述切面要完成什么工作**。

通知有以下几种类型：

- 前置通知：在目标方法被调用之前调用通知功能；

- 后置通知：在目标方法完成之后调用通知；

- 返回通知：在目标方法成功执行之后调用通知

- 异常通知：在目标方法抛出异常后调用通知

- 环绕通知：通知包裹了被通知的方法，在被通知的方法调用之前和调用之后执行的通知。

## 连接点

连接点可以相对程序执行过程来说，比如一个程序可以分为**执行前、执行后、异常等，都可以看做连接点**。也可以看成程序切入的时机。

## 切点

切点的概念可以对应AOP这个过程来说，切点会匹配通知要织入的一个或多个连接点，**通常使用明确的类和方法名称**。或是利用正则表达式定义匹配的类和方法来指定这些切点。

## 切面

**切面可以看成通知和切点的结合**。通知和切点共同定义了切面的全部内容，在什么时候，在什么地方完成何种功能。如果使用`@AspectJ style`风格的就是指标注`@AspectJ`注解的类。

## 织入

织入是把切面应用到目标对象并创建新的代理对象的过程。切面在指定的连接点被织入到目标对象中。在目标对象的生命周期里有多个点可以进行织入：编译器、类加载期、运行期。

**Spring AOP就是在运行期进行织入的**。

# SpringAOP使用示例

基本步骤：

- 定义切面（切面指定切点和通知）
- 开启AOP`@EnableAspectJAutoProxy`

下面介绍示例：

**引入的POM**

```
<dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.5</version>
        </dependency>

        <dependency>
            <groupId>cglib</groupId>
            <artifactId>cglib</artifactId>
            <version>3.2.12</version>
        </dependency>

        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjrt</artifactId>
            <version>1.9.4</version>
        </dependency>
```

---

**演出类Show**

```
package com.qhc.aop;

import org.springframework.stereotype.Component;

@Component
public class Show {
    private String title;
    private String actor;

    public Show(String title, String actor) {
        this.title = title;
        this.actor = actor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public Show() {
    }

    public void show(){
        System.out.println("一场精彩的表演");
    }
}
```

这里定义了一个演出方法`show()`，将对这个方法进行增强（拦截）

---

**Audience切面类**

```
package com.qhc.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Audience {
    @Pointcut("execution(* Show.show(..))")
    public void perform() {}

    @After("perform()")
    public void applause(){
        System.out.println("表演非常精彩");
    }

    @Before("perform()")
    public void entrance(){
        System.out.println("观众已经进场");
    }
}
```

这个切面类定义成观众，可以做成观众对演出的评价。其中使用`@Aspect`将其定义成切面类，并使用`@Before("perform()")`通知方法调用前的行为，用`@After("perform()")`通知方法调用后的行为。

---

**AOPConfiguration配置类**

```
package com.qhc.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.qhc.aop")
public class AOPConfiguration {
}

```

这里使用`@EnableAspectJAutoProxy`注解开启AOP功能。

---

**测试类AOPTest**

```
import com.qhc.aop.Show;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = com.qhc.aop.AOPConfiguration.class)
public class AOPTest {
    @Autowired
    private Show show;

    @Test
    public void test1(){
        show.show();
    }
}
```

在测试类里调用`show.show()`方法时，由于对调用方法前后分别进行了增强，所以测试结果如下：

```
观众已经进场
一场精彩的表演
表演非常精彩
```





