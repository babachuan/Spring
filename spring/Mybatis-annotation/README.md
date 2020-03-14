[toc]

# 1.Mybatis通过JAVA代码方式使用

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
         <dependency>
            <groupId>com.mchange</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.5.2</version>
        </dependency>
```

一个是mybatis的依赖，一个是mysql驱动（注意，在使用驱动版本的时候不要太高，可能会报错），这里使用数据源`c3p0`进行测试。

由于这是一个maven项目，要注意对resource的利用，下面分别对创建的文件进行说明：

**DataSourceConfigration配置文件**

```
package com.qhc.mybatis;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;

public class DataSourceConfigration {
    public static ComboPooledDataSource getDataSouce(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        dataSource.setInitialPoolSize(50);
        dataSource.setMaxPoolSize(100);
        return  dataSource;
    }
}
```

这里只是普通的数据源配置，为后面进行Mybatis配置做准备。

**MybatisConfiguartion配置**

```
package com.qhc.mybatis;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import javax.sql.DataSource;

public class MybatisConfiguartion {
    public SqlSessionFactory getSqlSessionFactory() {
        DataSource dataSource = DataSourceConfigration.getDataSouce();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(UserMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }
}
```

这里的配置参考Mybatis[官网](https://mybatis.org/mybatis-3/getting-started.html)上面的例子进行设定。

**UserMapper接口**

```
package com.qhc.mybatis;

import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectUser(int id);
}
```

这里直接使用Mybatis的注解`Select`并直接指定SQL语句。

**MybatisTest测试类（使用过程）**

```
import com.qhc.mybatis.MybatisConfiguartion;
import com.qhc.mybatis.User;
import com.qhc.mybatis.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;


public class MybatisTest {
    @Test
    public void test() {
        MybatisConfiguartion mybatisConfiguartion = new MybatisConfiguartion();
        SqlSessionFactory sqlSessionFactory = mybatisConfiguartion.getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.selectUser(4);
        System.out.println(user);
    }
}
```

上面可以看到，直接使用Mapper接口来进行操作

```
SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
```

相比使用全限定名确实要方便许多，但是这种使用注解也有其限制性。在进行复杂的SQL操作的时候官网还是建议使用XML配置方式，对于简单的可以使用这种注解方式。官方描述如下：

```
The annotations are a lot cleaner for simple statements, however, Java Annotations are both limited and messier for more complicated statements. Therefore, if you have to do anything complicated, you're better off with XML mapped statements.
```