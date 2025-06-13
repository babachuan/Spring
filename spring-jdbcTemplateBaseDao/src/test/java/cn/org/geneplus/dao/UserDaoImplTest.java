package cn.org.geneplus.dao;

import cn.org.geneplus.config.SpringConfig;
import cn.org.geneplus.pojo.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * @Author:quhaichuan
 * @Date:2025/6/12 15:11
 */
public class UserDaoImplTest extends TestCase {

    // 通过公共抽象方法的子类UserDaoImpl来测试
    @Test
    public void testBaseDao() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserDao userDao = applicationContext.getBean(UserDao.class);
        userDao.save(new User("张三", 20));

        // 通过调用BaseDaoImple抽象类中的findAll()方法来测试，这个方法在UserDao中没有实现
        List<User> list = userDao.findAll();
        list.forEach(System.out::println);
    }

    // 异常测试：测试抽象类update()方法，这里应该报错提醒：java.lang.UnsupportedOperationException: 请在子类中实现 update()
    @Test
    public void testUpdate() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserDao userDao = applicationContext.getBean(UserDao.class);
        userDao.update(new User(4,"张三丰", 28));
    }


}