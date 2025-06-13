package cn.org.geneplus.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @Author:quhaichuan
 * @Date:2025/6/12 13:31
 * 基于注解的方式实现spring- jdbcTemplate配置
 */
@Configurable
@ComponentScan(basePackages = "cn.org.geneplus")
@PropertySource("classpath:db.properties")  // 读取数据库配置
public class SpringConfig {

    @Value("${jdbc.driver}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;




    // 配置数据源，这里使用德鲁伊的数据源
    @Bean
    public DataSource dataSource(){
        // 如果是使用spring自带的数据源，则使用下面的代码
        //DriverManagerDataSource ds = new DriverManagerDataSource();

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    // 创建jdbcTemplate
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}
