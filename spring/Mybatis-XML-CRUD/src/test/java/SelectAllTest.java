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
