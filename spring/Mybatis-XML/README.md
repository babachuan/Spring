[toc]

# 1.Mybatis通过传统XML使用

在maven中引入

```
 		<dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.4</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.6</version>
        </dependency>
```

一个是mybatis的依赖，一个是mysql驱动（注意，在使用驱动版本的时候不要太高，可能会报错）

由于这是一个maven项目，要注意对resource的利用，下面分别对创建的文件进行说明：

**mybatis配置文件**

首先要配置的肯定是mybatis的配置文件`mybatis-config.xml`

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <properties resource="db.properties"/>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="mappers/UserMapper.xml"/>
    </mappers>
</configuration>
```

这是参照mybatis[官网]( https://mybatis.org/mybatis-3/getting-started.html )上面演示的示例进行调试的，这个文件是放到resources目录下的。同时将要引用的mapper在里面进行了设定`mappers/UserMapper.xml`，同时引入了`properties resource="db.properties"`数据库的配置，两个文件的内容见下

**db.properties**

```
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/test
username=root
password=123456
```

**UserMapper.xml**

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.qhc.mybatis.UserMapper">

    <resultMap column="userName" property="userName" />
        <result column="passWord" property="passWord" />
    </resultMap>
    
    <select id="selectUser" parameterType="int" resultMap="BaseResultMap">
    select * from User where id = #{id}
  </select>
</mapper>
```

*注意*：这个UserMapper.xml是放到`src/main/resources/mappers/UserMapper.xml`路径下的。

`resultMap`是多结果集的映射说明

`select`是要执行的SQL语句，包括多参数`parameterType`和结果`resultMap`的说明。

注意上面`namespace`的使用，官网的说明如下：

```
It defines a name for the mapped statement “selectBlog”, in the namespace “org.mybatis.example.BlogMapper”, which would allow you to call it by specifying the fully qualified name of “org.mybatis.example.BlogMapper.selectBlog”
```

官网使用示例：

```
Blog blog = session.selectOne(
  "org.mybatis.example.BlogMapper.selectBlog", 101);
```



**User实体类**

```
package com.qhc.mybatis;

public class User {
    private String userName;
    private String passWord;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

    public User() {
    }

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
```

**测试类**

```
import com.qhc.mybatis.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {
    @Test
    public void test(){
        String resource = "mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = sqlSessionFactory.openSession();
            User user = session.selectOne("com.qhc.mybatis.UserMapper.selectUser",3);
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

创建SQL语句如下：

```
CREATE TABLE `user` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `userName` varchar(32) NOT NULL,
  `passWord` varchar(50) NOT NULL,
  `realName` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

INSERT INTO `test`.`user`(`id`, `userName`, `passWord`, `realName`) VALUES (3, 'zhubajie', 'z123456', '猪八戒');
INSERT INTO `test`.`user`(`id`, `userName`, `passWord`, `realName`) VALUES (4, 'sunwukong', 's123456', '孙悟空');
INSERT INTO `test`.`user`(`id`, `userName`, `passWord`, `realName`) VALUES (5, 'shaseng', 'a123456', '沙僧');

```



测试结果：

```
User{userName='zhubajie', passWord='z123456'}
```

