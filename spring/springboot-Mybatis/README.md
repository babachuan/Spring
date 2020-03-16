# 1）springboot整合mybatis

引入pom依赖

```
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.0.1</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
```

创建映射文件

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.qhc.springboot2.mappers.UserMapper">

    <resultMap id="BaseResultMap" type="com.qhc.springboot2.beans.User">
        <result column="id" jdbcType="INTEGER" property="id"/>
        <result column="userName" jdbcType="VARCHAR" property="userName"/>
        <result column="passWord" jdbcType="VARCHAR" property="passWord"/>
        <result column="realName" jdbcType="VARCHAR" property="realName"/>
    </resultMap>

    <select id="queryById" resultMap="BaseResultMap">
        select * from user where id = #{id}
    </select>

    <select id="getAll" resultMap="BaseResultMap">
        select * from user
    </select>

    <!--    插入数据-->
    <insert id="addUser" parameterType="com.qhc.springboot2.beans.User">
        insert into user (userName,passWord) values (#{userName},#{passWord})
    </insert>

</mapper>
```

这个文件是在maven项目的resources目录下的mapping目录下创建的。在这个文件里面定义了namespace

`com.qhc.springboot2.mappers.UserMapper`这个就是对应接口文件的位置。下面看下接口文件

**UserMapper**

```
package com.qhc.springboot2.mappers;

import com.qhc.springboot2.beans.User;

import java.util.List;

public interface UserMapper {
    User queryById(int id);

    List<User> getAll();

    int addUser(User user);
}
```

这里只有简简单单几个方法名称和返回类型。没有在接口上加`@Mapper`注解时因为在SpringBoot的启动两类里添加了`@MapperScan("com.qhc.springboot2.mappers")`，这种批量扫描的会方便一些，也可以单独使用注解。

# 2)添加mybatis日志

在springboot中添加打印日志很方便，只需要在对应的配置文件配置即可。mybatis打印日志针对接口进行设置，也就是针对`com/qhc/springboot2/mappers`目录下的所有接口类打印对应的操作日志（即SQL语句），配置文件`application-prod.yml`里配置如下：

```
logging:
  level:
    com:
      qhc:
        springboot2:
                  mappers: debug
```

设置到`debug`即可，打印效果如下：

```
 [nio-8089-exec-1] c.q.s.mappers.UserMapper.getAll          : ==>  Preparing: select * from user 
 [nio-8089-exec-1] c.q.s.mappers.UserMapper.getAll          : ==> Parameters: 
 [nio-8089-exec-1] c.q.s.mappers.UserMapper.getAll          : <==      Total: 5
```

# 3）设置生成环境和开发环境

在日常开发中，我们经常会遇到测试环境和开发环境切换的问题。在spring中可以使用`@Profile`注解，那么在springboot中已经很方便的为我们做了实现。

首先需要创建对应开发和生产环境的配置，如下；

application-dev.yml

application-prod.yml

然后再在`application.yml`中进行配置，如下：

```
spring:
  profiles:
    active: prod
```

这里配置的是`prod`，也就是开启`application-prod.yml`的配置。

# 4）全局类型设置别名

在上面UserMapper.xml配置文件中使用`parameterType="com.qhc.springboot2.beans.User"`这样一段长长的名字，不方便开发，那如果把parameterType修改为`User`呢，实践会告诉你报错：

```
 Error resolving class. Cause: org.apache.ibatis.type.TypeException: Could not resolve type alias 'User'.  Cause: java.lang.ClassNotFoundException: Cannot find class: User
```

**解决办法**

在application-prod.yml(因为我测试的就是这个)文件中加入如下配置；

```
mybatis:
  type-aliases-package: com.qhc.springboot2
  mapper-locations: classpath:mapping/*.xml
```

重点看`type-aliases-package: com.qhc.springboot2`，这个就是为包下的类起别名，加上配置之后，只要User为这个包下的实体类，就都可以直接用类名来**代替全限定名**,是不是很爽。下面是使用效果：

```
    <insert id="addUser" parameterType="User">
        insert into user (userName,passWord) values (#{userName},#{passWord})
    </insert>
```

# 5）使用Pagehelper分页打印

引入POM

```
 <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper-spring-boot-autoconfigure</artifactId>
            <version>1.2.13</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.github.pagehelper/pagehelper -->
        <dependency>
            <groupId>com.github.pagehelper</groupId>
            <artifactId>pagehelper</artifactId>
            <version>5.1.10</version>
        </dependency>
```

我再环境测试时如果不引入`pagehelper-spring-boot-autoconfigure`无法实现分页功能。另外在引入依赖后尽量在maven里clean一下。

**在代码中使用分页**

```
    public List<User> getAll(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return userDao.getAll();
    }
```

使用分页很简单，直接在service代码里引用` PageHelper.startPage(pageNum,pageSize)`就可以。当然还有很多其他的功能，这里抛砖引玉。