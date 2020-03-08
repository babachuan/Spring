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



