package cn.org.geneplus;

import com.alibaba.druid.pool.DruidDataSource;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

/**
 * @Author:quhaichuan
 * @Date:2025/6/4 17:31
 */
public class MainTest extends TestCase {

    @Test
    public void testDruid() throws SQLException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        DruidDataSource dataSource = applicationContext.getBean("dataSource", DruidDataSource.class);
        System.out.println(dataSource);
        // 查询数据库中cities表的所有数据，并逐条打印
        java.sql.Connection connection = dataSource.getConnection();
        java.sql.Statement statement = connection.createStatement();
        java.sql.ResultSet resultSet = statement.executeQuery("select * from cities");
        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("id") + ", Name: " + resultSet.getString("name") );
        }
    }

}