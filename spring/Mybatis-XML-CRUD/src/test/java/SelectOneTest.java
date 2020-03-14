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
