package cn.org.geneplus.dao;

import cn.org.geneplus.common.BaseDaoImpl;
import cn.org.geneplus.pojo.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:quhaichuan
 * @Date:2025/6/12 15:02
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{


    // 定义表名
    @Override
    protected String getTableName() {
        return "user";
    }

    // 保存用户


    @Override
    public void save(User user) {
        String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getAge());
    }


    // 这是子接口中自定义的方法
    @Override
    public User findByName(String name) {
        String sql = "SELECT * FROM user WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class) , name);
    }

}
