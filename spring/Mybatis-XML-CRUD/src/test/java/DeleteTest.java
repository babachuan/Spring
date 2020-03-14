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
