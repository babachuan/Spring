<nav>
<a href="#"></a><br/>
<a href="#1）代理">1）代理</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#2）静态代理">2）静态代理</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#2-1）静态代理优缺点：">21）静态代理优缺点：</a><br/>
<a href="#3）JDK动态代理">3）JDK动态代理</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#3-1）JDK动态代理使用条件：">31）JDK动态代理使用条件：</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#3-2）jdk动态代理示例演示">32）jdk动态代理示例演示</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#3-3）JDK动态代理优缺点">33）JDK动态代理优缺点</a><br/>
<a href="#4）CGLIB动态代理">4）CGLIB动态代理</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#4-1）示例演示">41）示例演示</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#4-2）CGLIB实现原理">42）CGLIB实现原理</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#4-3）CGLIB和JDK动态代理如何选择">43）CGLIB和JDK动态代理如何选择</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#4-4）JDK动态代理和CGLIB字节码生成的区别">44）JDK动态代理和CGLIB字节码生成的区别</a><br/>
</nav>

# 1）代理

按照代理的创建时期，代理类可以分为两种： 

- 静态：由程序员创建代理类或特定工具自动生成源代码再对其编译。在程序运行前代理类的.class文件就已经存在了。
- 动态：在程序运行时运用反射机制动态创建而成。


## 2）静态代理

静态代理实现条件

要想采用静态代理方式，需要具备以下条件：
- 目标类具有接口，并实现了其接口。
- 代理类也得实现目标类的接口，并有一个属性是目标类接口。
- 代理类的得有一个无参构造方法和一个构造方法，参数为目标类接口类型，用于接收目标对象赋值给代理类的目标类接口属性。
- 代理类必须实现接口的所有方法，并在在方法中访问目标类对象的方法，在访问之前和之后都可以进行一些代理操作。

静态代理案例演示

**Decorater接口**

```
package com.qhc.proxy.staticproxy;

public interface Decorater {
    public void buybox(); //装饰方法，用来装饰BOX
}
```

---

**Box类**

```
package com.qhc.proxy.staticproxy;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("box")
public class Box implements Decorater{
    @Override
    public void buybox() {
        System.out.println("This box id buyed from England.");
    }
}
```

这里的Box类实现`Decorater`接口，并交给spring托管。

---

**BoxProxy代理类**

```
package com.qhc.proxy.staticproxy;

import org.springframework.stereotype.Component;

@Component
public class BoxProxy implements Decorater {

    private Decorater decorater;

    public BoxProxy(Decorater decorater) {
        this.decorater = decorater;
    }

    @Override
    public void buybox() {
        //监控原来box类的buybox行为，并为其进行增强
        System.out.println("Put a flower to the box");
        this.decorater.buybox();
        System.out.println("This box is a England box with a flower!");
    }
}
```

这个是实现静态代理的关键，对照下上面的实现条件，首先`代理类要实现接口`，其次代理类有一个`有参构造方法`用来接收被代理类型，最后在`代理类里也同样调用了被代理类的方法`（增强的方法），然后暴露出去给其他人调用，此时便实现了类型增强。

---

**StaticProxyTest测试类**

```
import com.qhc.proxy.staticproxy.Box;
import com.qhc.proxy.staticproxy.BoxProxy;
import com.qhc.proxy.staticproxy.MainConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainConfiguration.class)
public class StaticProxyTest {
    @Autowired
    @Qualifier("box")
    private Box box;

    @Test
    public void test(){
        BoxProxy boxProxy = new BoxProxy(box);
        boxProxy.buybox();
    }
}
```

测试结果：

```
Put a flower to the box
This box id buyed from England.
This box is a England box with a flower!
```

调用代理类后方法进行了增强。

## 2-1）静态代理优缺点：

1.优点

- 静态代理对客户（测试类）隐藏了被代理类接口（目标类接口）的具体实现类，在一定程度上实现了解耦合，同时提高了安全性！

2.缺点

- 静态代理类需要实现目标类（被代理类）的接口，并实现其方法，造成了代码的大量冗余。
- 静态代理只能对某个固定接口的实现类进行代理服务，其灵活性不强。故一般大项目不会选择静态代理

# 3）JDK动态代理

## 3-1）JDK动态代理使用条件：

- 必须实现InvocationHandler接口；
- 使用Proxy.newProxyInstance产生代理对象；
- 被代理的对象必须要实现接口；

## 3-2）jdk动态代理示例演示

**BuyFromWorld接口**

```
package com.qhc.proxy.jdkdynamic;

public interface BuyFromWorld {
    public void buyFromEngland();
    public void buyFromUAS();
}
```

---

**Boxes被代理类**

```
package com.qhc.proxy.jdkdynamic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("boxes")
public class Boxes  implements BuyFromWorld{
    @Override
    public void buyFromEngland() {
        System.out.println("The box is producted in England.");
    }

    @Override
    public void buyFromUAS() {
        System.out.println("The box is producted in USA.");
    }
}
```

被代理类也是交给spring托管

---

**JDKHandler代理类**

```
package com.qhc.proxy.jdkdynamic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class JDKHandler implements InvocationHandler {

    private Object target;

    public Object getBuyer(Object objectTarget) {
        this.target=objectTarget;
        Object instance = Proxy.newProxyInstance(objectTarget.getClass().getClassLoader(), objectTarget.getClass().getInterfaces(),
                this);
        return instance;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + " ->>方法被执行");
        if (method.getName().equals("buyFromEngland")) {
            System.out.println("Prepare Folwer A");
            Object object = method.invoke(target, args);
            System.out.println("FlowerA is combined with the England Box.");
            return object;
        } else {
            System.out.println("Prepare Folwer B");
            Object object = method.invoke(target, args);
            System.out.println("FlowerB is combined with the USA Box.");
            return object;
        }
    }

}
```

需要实现jdk的`InvocationHandler`，重写`invoke`

---

**配置类**

```
package com.qhc.proxy.jdkdynamic;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.qhc.proxy.jdkdynamic")
public class JKDMainConfiguration {
}
```

---

**测试类**

```
import com.qhc.proxy.jdkdynamic.Boxes;
import com.qhc.proxy.jdkdynamic.BuyFromWorld;
import com.qhc.proxy.jdkdynamic.JDKHandler;
import com.qhc.proxy.jdkdynamic.JKDMainConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JKDMainConfiguration.class)
public class JDKProxyTest {
    @Autowired
    public JDKHandler jdkHandler;

    @Autowired
    @Qualifier("boxes")
    public Boxes boxes;

    @Test
    public void test(){
        BuyFromWorld buyFromWorld = (BuyFromWorld) jdkHandler.getBuyer(boxes);
        buyFromWorld.buyFromEngland();
        System.out.println("--------");
        buyFromWorld.buyFromUAS();
    }
}
```

测试结果：

```
buyFromEngland ->>方法被执行
Prepare Folwer A
The box is producted in England.
FlowerA is combined with the England Box.
--------
buyFromUAS ->>方法被执行
Prepare Folwer B
The box is producted in USA.
FlowerB is combined with the USA Box.
```

## 3-3）JDK动态代理优缺点

1.优点

- 动态代理实现了只需要将被代理对象作为参数传入代理类就可以获取代理类对象，从而实现类代理，具有较强的灵活性。
- 动态代理的服务内容不需要像静态代理一样写在每个代码块中，只需要写在invoke()方法中即可，降低了代码的冗余度。

2.缺点

- JDK动态代理类仍然需要实现接口，而CGLIB可以不用实现接口

> [参照博客](https://blog.csdn.net/yhl_jxy/article/details/80586785)

# 4）CGLIB动态代理

使用条件：CGLib必须依赖于CGLib的类库，但是它需要类来实现任何接口代理的是指定的类生成一个子类，

## 4-1）示例演示

**CglibBoxes被代理类**

```
package com.qhc.proxy.cglibdynamic;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cglibboxes")
public class CglibBoxes{
    public void cglibbuyFromEngland() {
        System.out.println("The box is buyed from England by cglib.");
    }

    public void cglibbuyFromUAS() {
        System.out.println("The box is buyed from USA by cglib.");
    }
}
```

---

**CglibHandler代理类**

```
package com.qhc.proxy.cglibdynamic;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

@Component
public class CglibHandler implements MethodInterceptor {

    private Object target;

    public Object getCglibBuyer(Object objectTarget) {
        this.target=objectTarget;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(objectTarget.getClass());
        enhancer.setCallback(this);
        Object proxyObject = enhancer.create();
        return proxyObject;

    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(method.getName() + " ->>方法被执行");
        if (method.getName().equals("cglibbuyFromEngland")) {
            System.out.println("Prepare Folwer A");
            Object object = method.invoke(target, objects);
            System.out.println("FlowerA is combined with the England Box=cglib.");
            return object;
        } else {
            System.out.println("Prepare Folwer B");
            Object object = method.invoke(target, objects);
            System.out.println("FlowerB is combined with the USA Box=cglib.");
            return object;
        }
    }
}
```

这里实现了`MethodInterceptor接口`，并通过`intercept方法`进行拦截

---

**CglibMainConfiguration配置类**

```
package com.qhc.proxy.cglibdynamic;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.qhc.proxy.cglibdynamic")
public class CglibMainConfiguration {
}
```

---

**CglibProxyTest测试类**

```
import com.qhc.proxy.cglibdynamic.CglibBoxes;
import com.qhc.proxy.cglibdynamic.CglibHandler;
import com.qhc.proxy.cglibdynamic.CglibMainConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CglibMainConfiguration.class)
public class CglibProxyTest {
    @Autowired
    public CglibHandler cglibHandler;

    @Autowired
    @Qualifier("cglibboxes")
    public CglibBoxes cglibBoxes;

    @Test
    public void test(){
        CglibBoxes boxes= (CglibBoxes) cglibHandler.getCglibBuyer(cglibBoxes);
        boxes.cglibbuyFromEngland();
        System.out.println("--------");
        boxes.cglibbuyFromUAS();
    }
}
```

测试结果：

```
cglibbuyFromEngland ->>方法被执行
Prepare Folwer A
The box is buyed from England by cglib.
FlowerA is combined with the England Box=cglib.
--------
cglibbuyFromUAS ->>方法被执行
Prepare Folwer B
The box is buyed from USA by cglib.
FlowerB is combined with the USA Box=cglib.
```

## 4-2）CGLIB实现原理

利用ASM开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。

## 4-3）CGLIB和JDK动态代理如何选择

- 如果目标对象实现了接口，默认情况下会采用JDK的动态代理实现AOP。
- 如果目标对象实现了接口，可以强制使用CGLIB实现AOP。
- 如果目标对象没有实现了接口，必须采用CGLIB库，Spring会自动在JDK动态代理和CGLIB之间转换。

## 4-4）JDK动态代理和CGLIB字节码生成的区别
1）JDK动态代理只能对实现了接口的类生成代理，而不能针对类。
2）CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法，并覆盖其中方法实现增强，但是因为采用的是继承，所以该类或方法最好不要声明成final，对于final类或方法，是无法继承的。

**其他**：

1）使用CGLib实现动态代理，CGLib底层采用ASM字节码生成框架，使用字节码技术生成代理类，
在jdk6之前比使用Java反射效率要高。唯一需要注意的是，CGLib不能对声明为final的方法进行代理，
因为CGLib原理是动态生成被代理类的子类。
2）在jdk6、jdk7、jdk8逐步对JDK动态代理优化之后，在调用次数较少的情况下，JDK代理效率高于CGLIB代理效率，只有当进行大量调用的时候，jdk6和jdk7比CGLIB代理效率低一点，但是到jdk8的时候，jdk代理效率高于CGLIB代理，总之，每一次jdk版本升级，jdk代理效率都得到提升，而CGLIB代理消息确有点跟不上步伐。