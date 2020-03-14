# 1.通过XML配置进行增删改查操作

在`mappers/UserMapper.xml`新建对应 的映射文件

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.qhc.mybatis2.UserMapper">

    <!--插入数据-->
    <insert id="addUser" parameterType="com.qhc.mybatis2.beans.User">
        insert into user (userName,passWord) values (#{userName},#{passWord})
    </insert>

<!--    删除数据-->
    <delete id="deleteUser" parameterType="com.qhc.mybatis2.beans.User">
        delete from user where userName = #{userName}
    </delete>

<!--    修改数据-->
    <update id="updateUser" parameterType="com.qhc.mybatis2.beans.User">
        update user set passWord= #{passWord} where userName= #{userName}
    </update>

<!--    查询一个-->
    <select id="selectOne" resultType="com.qhc.mybatis2.beans.User">
        select * from user where id= #{id}
    </select>

<!--    查询所有-->
    <select id="getAll" resultType="com.qhc.mybatis2.beans.User">
        select * from user
    </select>

    <!--    模糊查询-->
    <select id="getByLikeName" resultType="com.qhc.mybatis2.beans.User">
        select * from user where userName like #{name}
    </select>
</mapper>
```

这里操作的都是单表，都是简单的SQL语句。

然后将映射文件加载到Mybatis的配置文件中`mybatis-config.xml`

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

这里使用`mapper resource="mappers/UserMapper.xml"`加载映射文件，然后将映射文件对应的查询，根据全限定名在dao类里分别进行实现

**UserDao类**

```
package com.qhc.mybatis2.dao;

import com.qhc.mybatis2.beans.User;
import com.qhc.mybatis2.config.SqlSessionFactorySource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDao {
    SqlSessionFactory sessionFactory = SqlSessionFactorySource.getSqlSessionFactory();

    public void addUser(User user) {
        SqlSession sqlSession = sessionFactory.openSession();
        int count = sqlSession.insert("com.qhc.mybatis2.UserMapper.addUser",user);
        sqlSession.commit();
        sqlSession.close();

    }

    public int deleteUser(User user) {
        SqlSession sqlSession = sessionFactory.openSession();
        int count = sqlSession.delete("com.qhc.mybatis2.UserMapper.deleteUser", user);
        sqlSession.commit();
        sqlSession.close();
        return count;

    }

    public int updateUser(User user) {
        SqlSession sqlSession = sessionFactory.openSession();
        int updateCount = sqlSession.update("com.qhc.mybatis2.UserMapper.updateUser", user);
        sqlSession.commit();
        sqlSession.close();
        return updateCount;
    }

    public User getOneById(int id) {
        SqlSession sqlSession = sessionFactory.openSession();
        User user = sqlSession.selectOne("com.qhc.mybatis2.UserMapper.selectOne", id);
        sqlSession.commit();
        sqlSession.close();
        return user;
    }

    public List<User> getAll() {
        SqlSession sqlSession = sessionFactory.openSession();
        List<User> list = sqlSession.selectList("com.qhc.mybatis2.UserMapper.getAll");
        return list;
    }

    public List<User> getByLikeName(String name){
        SqlSession sqlSession= sessionFactory.openSession();
        List<User> list = sqlSession.selectList("com.qhc.mybatis2.UserMapper.getByLikeName", "%"+name+"%");
        sqlSession.commit();
        sqlSession.close();
        return list;
    }
}
```

上面分别使用`sqlSession.***`操作进行对应，第一个参数使用的都是在`UserMapper.xml`文件中对应的`namespace`+`id`名字进行限定引用。

`sqlSession.commit()`是对数据库操作进行手动提交，也可以设置成自动提交。

对模糊查询过程中的`%%`号的使用，这里是放到dao层进行参数封装`"%"+name+"%"`,不能直接放到`UserMapper.xml`里面，Mybatis不能对`%`进行识别。

下面是对应的操作类

**DeleteTest**

```
import com.qhc.mybatis2.beans.User;
import com.qhc.mybatis2.dao.UserDao;
import org.junit.Test;

public class DeleteTest {
    @Test
    public void insertTest(){
        User user = new User();
        user.setUserName("zhangfei");
        user.setPassWord("f123456");

        UserDao userDao = new UserDao();
        int count = userDao.deleteUser(user);
        System.out.println("删除了"+count+"条记录");
    }
}
```

**InsertTest**

```
import com.qhc.mybatis2.beans.User;
import com.qhc.mybatis2.dao.UserDao;
import org.junit.Test;

public class InsertTest {
    @Test
    public void insertTest(){
        User user = new User();
        user.setUserName("zhangfei");
        user.setPassWord("f123456");

        UserDao userDao = new UserDao();
        userDao.addUser(user);
    }
}
```

**SelectAllTest**

```
import com.qhc.mybatis2.beans.User;
import com.qhc.mybatis2.dao.UserDao;
import org.junit.Test;

import java.util.List;

public class SelectAllTest {
    @Test
    public void selectAllTest(){
        UserDao userDao = new UserDao();
        List<User> list = userDao.getAll();
        System.out.println("查询到的结果是："+list);
    }
}
```

**SelectLikeNameTest**

```
import com.qhc.mybatis2.beans.User;
import com.qhc.mybatis2.dao.UserDao;
import org.junit.Test;

import java.util.List;

public class SelectLikeNameTest {
    @Test
    public void selectLikeNameTest(){
        UserDao userDao = new UserDao();
        List<User> list = userDao.getByLikeName("s");
        System.out.println("查询到的结果是："+list);
    }
}
```

**SelectOneTest**

```
import com.qhc.mybatis2.beans.User;
import com.qhc.mybatis2.dao.UserDao;
import org.junit.Test;

public class SelectOneTest {
    @Test
    public void selectOneTest(){
        UserDao userDao = new UserDao();
        User user = userDao.getOneById(5);
        System.out.println("查询到的结果是："+user);
    }
}
```

**UpdateTest**

```
import com.qhc.mybatis2.beans.User;
import com.qhc.mybatis2.dao.UserDao;
import org.junit.Test;

public class UpdateTest {
    @Test
    public void updateTest(){
        User user = new User();
        user.setUserName("shaseng");
        user.setPassWord("ssss123");

        UserDao userDao = new UserDao();
        int count = userDao.updateUser(user);
        System.out.println("更新了"+count+"条记录");
    }
}
```

