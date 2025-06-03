# 懒加载
在`spring.xml`配置文件中使用lazy-init属性
```
<bean id="person" class="cn.org.geneplus.beans.Person" lazy-init="true">
```
- default: 默认为false，即默认情况下，spring容器启动时，会创建所有的bean对象
- true: 懒加载，即容器启动时不创建对象，第一次使用时创建对象
- false: 即时加载，即容器启动时创建对象


# scope
scope属性用于指定bean的作用范围，即同一个bean对象在容器中的作用范围。
- singleton: 默认值，单例模式，即同一个bean对象在容器中只有一个实例，所有使用同一个bean的引用指向的都是同一个对象
- prototype: 多例模式, 即同一个bean对象在容器中存在多个实例，使用同一个bean的引用指向的都是多个对象
```
<bean id="person2" class="cn.org.geneplus.beans.Person" scope="singleton"></bean>
```

当scope属性为singleton时，输出结果如下
```
无参构造
true
```
可以发现只进行了一次创建，并且两次获取的对象为同一个对象

当scope属性为prototype时，输出结果如下，进行了2次实例创建
```
无参构造
无参构造
false
```

# 给实例注入属性
## 通过setter方法注入属性
使用这个定义需要对应的类中提前定义set方法，否则会报错
```
<!--    通过setter方法注入属性-->
    <bean id="person3" class="cn.org.geneplus.beans.Person">
        <property name="userid" value="1"></property>
        <property name="username" value="张三"></property>
        <property name="password" value="123456"></property>
    </bean>
```

## 方法二：通过构造方法注入属性
构造方法注入属性使用的是constructor-arg标签
```
    <bean id="person4" class="cn.org.geneplus.beans.Person">
        <constructor-arg name="userid" value="4"></constructor-arg>
        <constructor-arg name="username" value="李四"></constructor-arg>
        <constructor-arg name="password" value="123321"></constructor-arg>
    </bean>
```

## 方法三：通过p命名空间注入属性
p命名空间注入属性使用p命名空间，需要引入p命名空间
xmlns:p="http://www.springframework.org/schema/p"
xmlns:c="http://www.springframework.org/schema/c"

```
    <bean id="person5" class="cn.org.geneplus.beans.Person" p:userid="5" p:password="123333" p:username="张飞"></bean>

```
通过c命名空间注入属性
```
    <bean id="person6" class="cn.org.geneplus.beans.Person" c:userid="6" c:password="123444" c:username="塑料袋"></bean>
```


