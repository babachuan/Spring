<nav>
<a href="#"></a><br/>
<a href="#自动装配过程中的不唯一情况">自动装配过程中的不唯一情况</a><br/>
<a href="#NoUniqueBeanDefinitionException异常">NoUniqueBeanDefinitionException异常</a><br/>
<a href="#primary&nbsp;注解使用">primary-注解使用</a><br/>
<a href="#qualifier&nbsp;注解使用">qualifier-注解使用</a><br/>
<a href="#Primary注解的高级使用">Primary注解的高级使用</a><br/>
</nav>

# 自动装配过程中的不唯一情况

使用Spring的自动装配比较方便，但只有一个bean匹配的时候自动装配才有效，如果不止一个bean能够匹配的话，就不能进行自动装配，会抛`NoUniqueBeanDefinitionException`异常。

代码示例包说明：

- nouniquexception：异常示例演示
- primary                   ：使用@Primary注解的示例
- qualifier                   ：使用@Qualifier注解的示例
- priorqualifier           ：@Qualifier注解的高阶用法

# NoUniqueBeanDefinitionException异常

**Dessert1接口**

```
package com.qhc.ambiguous.nouniquexception;

public interface Dessert1 {
    public void taste();
}
```

一个普通接口，让后面的三个类进行实现，并在注入的时候用接口类别接收，这样就会出现注入不唯一的情况。

---

**Cake1类**

```
package com.qhc.ambiguous.nouniquexception;

import org.springframework.stereotype.Component;

@Component
public class Cake1 implements Dessert1{
    private String name = "cake1";
    @Override
    public void taste() {
        System.out.println("it claims like cake1.");
    }

    public String getName() {
        return name;
    }
}
```

实现接口`Dessert1`，并且用`@Component`进行自动扫描并创建bean。

---

**Cookies1类**：

```
package com.qhc.ambiguous.nouniquexception;
import org.springframework.stereotype.Component;

@Component
public class Cookies1 implements Dessert1{
    private String name = "cookies1";
    @Override
    public void taste() {
        System.out.println("it claims like cookies1.");
    }
    public String getName() {
        return name;
    }
}
```

实现接口`Dessert1`，并且用`@Component`进行自动扫描并创建bean。

---

**IceCream1类**

```
package com.qhc.ambiguous.nouniquexception;
import org.springframework.stereotype.Component;

@Component
public class IceCream1 implements Dessert1{
    private String name = "iceCream1";
    @Override
    public void taste() {
        System.out.println("it claims like iceCream1.");
    }

    public String getName() {
        return name;
    }
}
```

实现接口`Dessert1`，并且用`@Component`进行自动扫描并创建bean。

---

**NouniqexceptionConfiguration配置类**

```
package com.qhc.ambiguous.nouniquexception;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.qhc.ambiguous.nouniquexception")
public class NouniqexceptionConfiguration {
}
```

通过包扫描`@ComponentScan(basePackages = "com.qhc.ambiguous.nouniquexception")`的方式加载相关的bean，这里会把`Cake1` `Cookies1` `IceCream1` 都加载到容器中，并且都可以用接口`Dessert1`进行接收。

---

**Myfavorite1关键使用类**

```
package com.qhc.ambiguous.nouniquexception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Myfavorite1 {
    @Autowired
    private Dessert1 dessert;

    public void show(){
        System.out.println("my favorite is==");
        dessert.taste();
    }
}
```

这里使用到了`@Autowired`，并使用`Dessert1`接口进行接收。实际有3个类满足条件，这种情况下，spring就会报错。

---

**NouniquexceptionTest测试类**

```
import com.qhc.ambiguous.nouniquexception.Myfavorite1;
import com.qhc.ambiguous.nouniquexception.NouniqexceptionConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NouniqexceptionConfiguration.class)
public class NouniquexceptionTest {
    @Autowired
    private Myfavorite1 myfavorite1;

    @Test
    public void Test(){
        myfavorite1.show();
    }
}
```

测试结果：

```
警告: Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'myfavorite1': Unsatisfied dependency expressed through field 'dessert'; nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.qhc.ambiguous.nouniquexception.Dessert1' available: expected single matching bean but found 3: cake1,cookies1,iceCream1

```

# primary 注解使用

在声明bean的时候，可以通过将其中一个可选的bean设置为首选(primary)bean，能够一定程度上避免自动装配的歧义性。用`@Primary`注解来表达最优选。`@Primary`能够与`@Componets`组合用在组件扫描上，也可以与`@Bean`组合用在Java配置的bean声明中。下面是一个使`@Primary`的例子。

**Dessert2接口**

```
package com.qhc.ambiguous.primary;

public interface Dessert2 {
    public void taste();
}
```

---

**Cake2类**

```
package com.qhc.ambiguous.primary;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Cake2 implements Dessert2 {
    private String name = "cake2";
    @Override
    public void taste() {
        System.out.println("it claims like cake2.");
    }

    public String getName() {
        return name;
    }
}
```

这里在声明的时候使用`@Primary`注解定义为优选bean,在歧义的时候优先使用这个。

---

**Cookies2类**

```
package com.qhc.ambiguous.primary;

import org.springframework.stereotype.Component;

@Component
public class Cookies2 implements Dessert2 {
    private String name = "cookies2";
    @Override
    public void taste() {
        System.out.println("it claims like cookies2.");
    }
    public String getName() {
        return name;
    }
}
```

---

**IceCream2类**

```
package com.qhc.ambiguous.primary;

import org.springframework.stereotype.Component;

@Component
public class IceCream2 implements Dessert2 {
    private String name = "iceCream2";
    @Override
    public void taste() {
        System.out.println("it claims like iceCream2.");
    }

    public String getName() {
        return name;
    }
}
```

---

**PrimaryConfiguration配置类**

```
package com.qhc.ambiguous.primary;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.qhc.ambiguous.primary")
public class PrimaryConfiguration {
}
```

---

**Myfavorite2主要使用注入的类**

```
package com.qhc.ambiguous.primary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Myfavorite2 {
    @Autowired
    private Dessert2 dessert;

    public void show(){
        System.out.println("my favorite is==");
        dessert.taste();
    }
}
```

这里使用`@Autowired`进行bean注入，虽然后3个类型，但是Cake2使用了`@Primary`注解，所以注入的是Cake2类。

---

**PrimaryTest测试类**

```
import com.qhc.ambiguous.primary.Myfavorite2;
import com.qhc.ambiguous.primary.PrimaryConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PrimaryConfiguration.class)
public class PrimaryTest {
    @Autowired
    private Myfavorite2 myfavorite2;

    @Test
    public void Test(){
        myfavorite2.show();
    }
}
```

测试结果：

```
my favorite is==
it claims like cake2.
```

# qualifier 注解使用

设置首选bean一定程度上能够避免歧义导致的报错，但是还有更好的解决方法，使用`@Qualifier`注解。创建自定义限定符方法如下：

- Step1:`@Qualifier`可以与`@Component`注解一起使用，也可以跟`@Bean`注解一起使用，在创建bean的时候声明自定义的限定符`@Qualifier("qualifername")`，在这里指定限定符的value。如果不指定的话默认使用bean的ID。
- Step2:在使用`@Autowired`注入bean的时候只需要使用`@Qualifier("qualifername")`注解进行注释，Spring会将定义的qualifername的bean注入进来，而不会歧义。

下面是一个使用的例子

**Cake3类**

```
package com.qhc.ambiguous.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cold-cake3")
public class Cake3 implements Dessert3 {
    private String name = "cake3";
    @Override
    public void taste() {
        System.out.println("it claims like cake3.");
    }

    public String getName() {
        return name;
    }
}
```

这里使用`@Qualifier("cold-cake3")`进行修饰

---

**Dessert3接口类**

```
package com.qhc.ambiguous.qualifier;

public interface Dessert3 {
    public void taste();
}
```

---

**Cookies3类**

```
package com.qhc.ambiguous.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cold-cookies3")
public class Cookies3 implements Dessert3 {
    private String name = "cookies3";
    @Override
    public void taste() {
        System.out.println("it claims like cookies3.");
    }
    public String getName() {
        return name;
    }
}
```

这里使用`@Qualifier("cold-cookies3")`进行限定。

---

**IceCream3类**

```
package com.qhc.ambiguous.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cold-icecream3")
public class IceCream3 implements Dessert3 {
    private String name = "iceCream3";
    @Override
    public void taste() {
        System.out.println("it claims like iceCream3.");
    }

    public String getName() {
        return name;
    }
}
```

这里使用`@Qualifier("cold-icecream3")`进行限定

---

**QualifierConfiguration配置类**

```
package com.qhc.ambiguous.qualifier;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.qhc.ambiguous.qualifier")
public class QualifierConfiguration {
}
```

---

**Myfavorite3主要使用类**

```
package com.qhc.ambiguous.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Myfavorite3 {
    @Autowired
    @Qualifier("cold-icecream3")
    private Dessert3 dessert;

    public void show(){
        System.out.println("my favorite is==");
        dessert.taste();
    }
}
```

这里在使用`@Autowired`进行注入的时候加了`@Qualifier("cold-icecream3")`进行限定，所以注入的是icecream3.

---

**QualifierTest测试类**

```
import com.qhc.ambiguous.primary.PrimaryConfiguration;
import com.qhc.ambiguous.qualifier.Myfavorite3;
import com.qhc.ambiguous.qualifier.QualifierConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = QualifierConfiguration.class)
public class QualifierTest {
    @Autowired
    private Myfavorite3 myfavorite3;

    @Test
    public void Test(){
        myfavorite3.show();
    }
}
```

测试结果：

```
my favorite is==
it claims like iceCream3.
```

# Primary注解的高级使用

```
@Target({ElementType.CONSTRUCTOR,ElementType.FIELD,ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface Posicle {
}
```

如上面所示，将`@Qualifer`进行注解的包装，这样可以进行双重的限定，使用如下：

**Popsicle类**

```
@Component
@Qualifier("cold-cake4")
@Posicle
public class Popsicle implements Dessert4 {
    private String name = "Popsicle";
    @Override
    public void taste() {
        System.out.println("it claims like Popsicle.");
    }

    public String getName() {
        return name;
    }
}
```

这里使用自定义的注解`@Posicle`进行限定，虽然下面还有一个使用`@Qualifier("cold-cake4")`注解的类，但是在注入的时候使用`@Posicle`注解就可以消除歧义。

---

**Cake4类**

```
package com.qhc.ambiguous.priorqualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("cold-cake4")
@com.qhc.ambiguous.priorqualifier.annotation.Cake4
public class Cake4 implements Dessert4 {
    private String name = "cake4";
    @Override
    public void taste() {
        System.out.println("it claims like cake4.");
    }

    public String getName() {
        return name;
    }
}
```

这里也使用了`@Qualifier("cold-cake4")`注解

---

**Myfavorite4主要使用的注入类**

```
@Component
public class Myfavorite4 {
    @Autowired
    @Qualifier("cold-cake4")
    @Posicle
    private Dessert4 dessert;

    public void show(){
        System.out.println("my favorite is==");
        dessert.taste();
    }
}
```

上面在注入的时候除了使用`@Qualifier("cold-cake4")`注解进行限定之外，还使用了`@Posicle`自定义的注解，这样就能唯一确定一个bean了。

---

**PriorQualifierTest测试类**

```
import com.qhc.ambiguous.priorqualifier.Myfavorite4;
import com.qhc.ambiguous.priorqualifier.PriorQualifierConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PriorQualifierConfiguration.class)
public class PriorQualifierTest {
    @Autowired
    private Myfavorite4 myfavorite4;

    @Test
    public void Test(){
        myfavorite4.show();
    }
}
```

测试结果：

```
my favorite is==
it claims like Popsicle.
```



