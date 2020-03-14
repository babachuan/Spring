# 通过注解进行增删改查操作

使用注解进行增删改查操作的话，需要定义接口，在接口里定义对应的操作（具体的SQL语句是在这个接口里进行对应的指定），我这里使用的接口如下：

**UserMapper**

```
package com.qhc.mybatis3.mappers;

import com.qhc.mybatis3.beans.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    @Select("select * from user where id= #{id}")
    public User selectOneById(int id);

    @Select("select * from user")
    public List<User> getAll();

    @Update("update user set passWord=#{passWord} where userName= #{userName}")
    public int update(User user);

    @Insert("insert into user (userName,passWord) values (#{userName},#{passWord})")
    public int insert(User user);

    @Delete("delete from user where userName= #{userName}")
    public int delete(User user);

    @Select("select * from user where userName like #{name}")
    public List<User> getByName(String name);
}
```

接口里分别定义了对应的单表操作

注意在SqlSessionFactory操作里我直接将这个接口配置进去

**SqlSessionFactoryResource**

```
package com.qhc.mybatis3.config;

import com.qhc.mybatis3.mappers.UserMapper;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

public class SqlSessionFactoryResource {
    public static SqlSessionFactory getSqlSessionFactory(){
        DataSource dataSource = DataSouceConfig.getDataSouece();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development",transactionFactory,dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(UserMapper.class);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }
}
```

`configuration.addMapper(UserMapper.class)`这里直接将接口信息配置进来。

然后再dao里进行使用

**UserDao**

```
package com.qhc.mybatis3.dao;

import com.qhc.mybatis3.beans.User;
import com.qhc.mybatis3.config.SqlSessionFactoryResource;
import com.qhc.mybatis3.mappers.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDao {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryResource.getSqlSessionFactory();

    public User selectOneById(int id){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectOneById(id);
        sqlSession.commit();
        sqlSession.close();
        return user;
    }

    public List<User> getAll(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> all = mapper.getAll();
        sqlSession.commit();
        sqlSession.close();
        return all;
    }

    public int update (User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int updateCount = mapper.update(user);
        sqlSession.commit();
        sqlSession.close();
        return updateCount;
    }

    public int insert(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int insertCount = mapper.insert(user);
        sqlSession.commit();
        sqlSession.close();
        return insertCount;
    }

    public int delete(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int deleteCount = mapper.delete(user);
        sqlSession.commit();
        sqlSession.close();
        return deleteCount;
    }

    public List<User> getUserLikeName(String name){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //这里为了拼凑模糊查询的参数，在Mybatis的SQL里不能识别%
        List<User> list = mapper.getByName("%"+name+"%");
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

}
```

可以看到都是使用`UserMapper mapper = sqlSession.getMapper(UserMapper.class);`得到对应的接口代理类，然后再进行对应的增删改查操作。

**测试使用类**

```
import com.qhc.mybatis3.beans.User;
import com.qhc.mybatis3.dao.UserDao;
import org.junit.Test;

import java.util.List;

public class MyTest {
    @Test
    public void selectOneByIdtest(){
        UserDao userDao = new UserDao();
        User user = userDao.selectOneById(5);
        System.out.println("查到的结果是："+user);
    }
    @Test
    public void selectAllTest(){
        UserDao userDao = new UserDao();
        List<User> list = userDao.getAll();
        System.out.println("查到的结果是："+list);
    }

    @Test
    public void insertTest(){
        User zhangfei = new User();
        zhangfei.setUserName("zhangfei");
        zhangfei.setPassWord("fei44444");


        UserDao userDao = new UserDao();
        int insertCount = userDao.insert(zhangfei);
        System.out.println("插入成功了："+insertCount+"条记录");
    }

    @Test
    public void updateTest(){
        User zhangfei = new User();
        zhangfei.setUserName("zhangfei");
        zhangfei.setPassWord("fei444445555");

        UserDao userDao = new UserDao();
        int insertCount = userDao.update(zhangfei);
        System.out.println("更新成功了："+insertCount+"条记录");
    }

    @Test
    public void deleteTest(){
        User zhangfei = new User();
        zhangfei.setUserName("zhangfei");
        zhangfei.setPassWord("fei444445555");

        UserDao userDao = new UserDao();
        int deleteCount = userDao.delete(zhangfei);
        System.out.println("成功删除了："+deleteCount+"条记录");
    }

    @Test
    public void getLikeNameTest(){
        UserDao userDao = new UserDao();
        List<User> list = userDao.getUserLikeName("s");
        System.out.println("模糊匹配到的结果是："+list);
    }
}
```

由于在dao层进行了封装，测试时直接使用即可。