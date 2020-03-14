# Mybatis进行关联查询

在平时工作中经常会有这种模型，一个`Book`实体类，里面包含了作者`Author`，这种是很有可能的。下面就有这样两个类

**Book**

```
public class Book {
    private int bookId;
    private String bookName;
    private int publisherId;
    //注意：这里是一个对象
    private Author author;
    private int price;
    ...
    }
```

`Book`类里面有一个`Author`属性，这个属性是一个实体类。

**Author**

```
public class Author {
    private int authorId;
    private String authorName;
    ...
    }
```

在关联查询是时候需要着重注意`BookMapper.xml`里对`ResultMap`的类型设置，参见如下：

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.qhc.multi.bookMapper">

    <resultMap type="_Book" id="BookResultMap">
        <id property="bookId" column="id"></id>
        <result property="bookName" column="name"></result>
        <result property="publisherId" column="publisher_id"></result>
        <result property="price" column="price"></result>
        <association property="author" column="author_id" javaType="_Author">
            <id property="authorId" column="author_id"></id>
            <result property="authorName" column="name"></result>
        </association>
    </resultMap>

    <select id="OOselect" parameterType="String" resultMap="BookResultMap">
        select * from bookinfo b,author a where b.name= #{bookname} and a.author_id=b.author_id
    </select>

</mapper>
```

这里使用了`association`属性，来关联引用。其他的操作如SQL都跟其他一样。



**小甜点**

让Mybatis打印日志，在Mybatis的配置文件中添加如下属性

```
    <settings>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
    </settings>
```

如果添加报错（我添加的时候位置添加不合适就报错了，怎么也排查不了），就调整下标签的顺序

```
(properties>—,settings>----,typeAliases>----,typeHandlers>----,objectFactory>----,objectWrapperFactory>----,plugins>----,environments>----,databaseIdProvider>----,mappers?)
```



