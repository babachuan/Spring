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
