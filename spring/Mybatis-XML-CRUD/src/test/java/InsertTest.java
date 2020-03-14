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
