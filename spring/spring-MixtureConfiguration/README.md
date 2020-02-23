<nav>
<a href="#"></a><br/>
<a href="#Spring基础-导入和混合配置">Spring基础导入和混合配置</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#在java配置中引入java配置">在java配置中引入java配置</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#在XML中引入JAVA配置">在XML中引入JAVA配置</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#在JAVA配置中引入XML配置">在JAVA配置中引入XML配置</a><br/>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="#在XML配置中引入XML配置">在XML配置中引入XML配置</a><br/>
</nav>

# Spring基础-导入和混合配置

假设自动装配或XML配置文件已经比较复杂，需要拆分，那么现在既有自动装配，又有Java配置，还有XML配置，那么如果三种配置均存在该如何配置呢？这里包括4中情况

- 在JavaConfig中引入JavaConfig(或自动装配)-java2java项目
- 在XML配置中引入JavaConfig-java2xml项目
- 在JavaConfig中引入XML配置-xml2java项目
- 在XML配置中引入XML配置-xml2xml项目

---

## 在java配置中引入java配置

涉及的java类如下，主要是java2java包中：

- `Java2javaDisc.java`
- `Java2javaDiscConfiguration.java`
- `Java2javaDiscDriver.java`
- `Java2javaDiscDriverConfiguration.java` :star:

**Java2javaDisc类**

```
package com.qhc.mix.java2java;

public class Java2javaDisc {
    private String content="This is a beautiful music=Java2javaDisc";
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
```

这只是一个普通的JAVA bean。

----

**Java2javaDiscConfiguration类**

```
package com.qhc.mix.java2java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Java2javaDiscConfiguration {
    @Bean
    public Java2javaDisc java2javaDisc(){
        return new Java2javaDisc();
    }
}
```

这个配置文件通过JavaConfig进行bean的初始化

---

**Java2javaDiscDriver类**

```
package com.qhc.mix.java2java;

import org.springframework.beans.factory.annotation.Autowired;

public class Java2javaDiscDriver {
    @Autowired
    private Java2javaDisc java2javaDisc;
    public void paly(){
        System.out.println(java2javaDisc.getContent());
    }
}
```

这是一个普通的java bean,这里有依赖`Java2javaDisc`.

---

**Java2javaDiscDriverConfiguration类**

```
package com.qhc.mix.java2java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(Java2javaDiscConfiguration.class)
public class Java2javaDiscDriverConfiguration {
    @Bean
    public Java2javaDiscDriver java2javaDiscDriver(){
        return new Java2javaDiscDriver();
    }
}
```

在这个java配置类中通过`@Import(Java2javaDiscConfiguration.class)`引入另外一个java配置文件。

## 在XML中引入JAVA配置

涉及的java类如下，主要是java2xml包中：

- `Java2xmlDisc.java`
- `Java2xmlDiscConfiguration.java`
- `Java2xmlDiscDriver.java`
- `java2xml-spring-context.txt.xml`:star:

---

**Java2xmlDisc类**

```
package com.qhc.mix.java2xml;

public class Java2xmlDisc {
    private String content="This is a beautiful music=Java2xmlDisc";
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
```

这是一个普通的java bean.

**Java2xmlDiscConfiguration类**

```
package com.qhc.mix.java2xml;

import com.qhc.mix.java2java.Java2javaDisc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Java2xmlDiscConfiguration {
    @Bean
    public Java2xmlDisc java2xmlDisc(){
        return new Java2xmlDisc();
    }
}
```

这里使用`@Configuration`的java配置方式声明了bean的初始化。

---

**Java2xmlDiscDriver类**

```
package com.qhc.mix.java2xml;

public class Java2xmlDiscDriver {

    private Java2xmlDisc java2xmlDisc;

    public Java2xmlDiscDriver(Java2xmlDisc java2xmlDisc) {
        this.java2xmlDisc = java2xmlDisc;
    }
    public void paly() {
        System.out.println(java2xmlDisc.getContent());
    }
    public Java2xmlDiscDriver() {
    }
    public Java2xmlDisc getJava2xmlDisc() {
        return java2xmlDisc;
    }
}
```

这里依赖注入了`Java2xmlDisc`，因为要使用XML配置这个类，所以添加了无参构造器和有参构造器`Java2xmlDiscDriver(Java2xmlDisc java2xmlDisc)`（`constructor-arg`用）

---

**java2xml-spring-context.txt.xml文件**

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean class="com.qhc.mix.java2xml.Java2xmlDiscConfiguration"/>

    <bean id="java2xmlDiscDriver" class="com.qhc.mix.java2xml.Java2xmlDiscDriver">
        <constructor-arg ref="java2xmlDisc"/>
    </bean>

</beans>
```

**注意**：这里使用`<bean class="com.qhc.mix.java2xml.Java2xmlDiscConfiguration"/>`的方式将Java配置文件引入到XML配置中。在后面的依赖注入时直接只用被依赖的bean的名字。

## 在JAVA配置中引入XML配置

涉及的java类如下，主要是xml2java包中：

- `XML2javaDisc.java`
- `XML2javaDiscDriver.java`
- `XML2javaDiscDriverConfiguration.java`:star:
- `xml2java-spring-context.txt.xml`

---

**XML2javaDisc类**

```
package com.qhc.mix.xml2java;

public class XML2javaDisc {
    private String content="This is a beautiful music=XML2javaDisc";
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public XML2javaDisc() {
    }
}
```

普通的java bean.

---

**XML2javaDiscDriver类**

```
package com.qhc.mix.xml2java;
import org.springframework.beans.factory.annotation.Autowired;

public class XML2javaDiscDriver {
    @Autowired
    private XML2javaDisc xml2javaDisc;

    public void paly() {
        System.out.println(xml2javaDisc.getContent());
    }
}
```

普通的java bean,这里有依赖注入。

---

**XML2javaDiscDriverConfiguration类**

```
package com.qhc.mix.xml2java;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:xml2java-spring-context.txt.xml")
public class XML2javaDiscDriverConfiguration {
    @Bean
    public XML2javaDiscDriver xml2javaDiscDriver(){
        return new XML2javaDiscDriver();
    }
}
```

这个类里除了通过`@Configuration`和`@Bean`实例化`XML2javaDiscDriver`之外，还通过`@ImportResource("classpath:xml2java-spring-context.txt.xml")`引入了XML中的配置。

---

**xml2java-spring-context.txt.xml文件**

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="xml2javaDisc" class="com.qhc.mix.xml2java.XML2javaDisc"/>

</beans>
```

文件里只简单声明了XML2javaDisc的bean.

## 在XML配置中引入XML配置

涉及的java类和配置如下，主要是xml2xml包中：

- `XML2XMLDisc.java`
- `XML2XMLDiscDriver.java`
- `xml2xmlDisc-spring-context.xml`
- `xml2xmlDiscDriver-spring-context.xml`:star:

---

**XML2XMLDisc类**

```
package com.qhc.mix.xml2xml;

public class XML2XMLDisc {
    private String content="This is a beautiful music=XML2XMLDisc";
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public XML2XMLDisc() {
    }
}
```

普通的java bean.

---

**XML2XMLDiscDriver类**

```
package com.qhc.mix.xml2xml;
import org.springframework.beans.factory.annotation.Autowired;

public class XML2XMLDiscDriver {

    private XML2XMLDisc xml2XMLDisc;

    public XML2XMLDiscDriver(XML2XMLDisc xml2XMLDisc) {
        this.xml2XMLDisc = xml2XMLDisc;
    }

    public void paly() {
        System.out.println(xml2XMLDisc.getContent());
    }

    public XML2XMLDiscDriver() {
    }
}
```

这个类使用XML配置，所以设置了`XML2XMLDiscDriver(XML2XMLDisc xml2XMLDisc)`构造器。

---

**xml2xmlDisc-spring-context.xml文件**

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="xml2XMLDisc" class="com.qhc.mix.xml2xml.XML2XMLDisc"/>

</beans>
```

只是声明一个bean.

---

**xml2xmlDiscDriver-spring-context.xml文件**

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <import resource="xml2xmlDisc-spring-context.xml"/>

    <bean id="xml2XMLDiscDriver" class="com.qhc.mix.xml2xml.XML2XMLDiscDriver">
        <constructor-arg ref="xml2XMLDisc"/>
    </bean>

</beans>
```

这里通过`<import resource="xml2xmlDisc-spring-context.xml"/>`将另外一个XML配置导入，并在下面的依赖注入中直接引用前面声明的bean.