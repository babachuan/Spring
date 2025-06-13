package cn.org.geneplus.dao.impl;

import cn.org.geneplus.config.SpringConfig;
import cn.org.geneplus.dao.UserDao;
import cn.org.geneplus.pojo.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Author:quhaichuan
 * @Date:2025/6/12 11:43
 */
public class UserDaoImplTest extends TestCase {

    // 测试插入数据
    @Test
    public void testAddUser()
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDaoImpl",UserDaoImpl.class);

        User user = new User("李四",19);
        userDao.addUser(user);

    }

    // 测试删除用户
    @Test
    public void  testDeleteUser()
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDaoImpl",UserDaoImpl.class);

        userDao.deleteUser(1);
    }

    // 测试修改用户
    @Test
    public void  testUpdateUser()
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDaoImpl",UserDaoImpl.class);
        userDao.updateUser(new User(3,"Gene",18));
    }

    // 根据用户姓名查询
    @Test
    public void  testGetUserByName()
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDaoImpl",UserDaoImpl.class);
        User user  = userDao.getUserByName("Gene");
        System.out.println(user);
    }

    // 查询所有用户
    @Test
    public void  testGetAllUsers()
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDaoImpl",UserDaoImpl.class);
        List<User> allUsers = userDao.getAllUsers();
        allUsers.forEach(System.out::println);
    }

    // 测试使用注解方式实现配置
    @Test
    public void testAnnotationConfig()
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserDao userDao = (UserDao) applicationContext.getBean("userDaoImpl",UserDaoImpl.class);
        List<User> allUsers = userDao.getAllUsers();
        allUsers.forEach(System.out::println);
    }

}