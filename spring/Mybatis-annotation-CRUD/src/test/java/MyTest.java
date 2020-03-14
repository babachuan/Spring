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
