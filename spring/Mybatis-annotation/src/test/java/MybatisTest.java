import com.qhc.mybatis.MybatisConfiguartion;
import com.qhc.mybatis.User;
import com.qhc.mybatis.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;


public class MybatisTest {
    @Test
    public void test() {
        MybatisConfiguartion mybatisConfiguartion = new MybatisConfiguartion();
        SqlSessionFactory sqlSessionFactory = mybatisConfiguartion.getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.selectUser(4);
        System.out.println(user);
    }
}
