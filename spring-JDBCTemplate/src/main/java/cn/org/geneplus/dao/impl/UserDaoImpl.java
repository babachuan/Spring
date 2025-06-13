package cn.org.geneplus.dao.impl;

import cn.org.geneplus.dao.UserDao;
import cn.org.geneplus.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:quhaichuan
 * @Date:2025/6/12 11:28
 */
@Repository
public class UserDaoImpl implements UserDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getAge());

    }

    // 删除用户
    @Override
    public void deleteUser(int id) {
        String sql = "DELETE FROM user WHERE id = ?";
        jdbcTemplate.update(sql, id);
        System.out.println("删除了用户：" + id);

    }

    // 更新用户
    @Override
    public void updateUser(User user) {
        String sql = "UPDATE user SET name = ?, age = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getName(), user.getAge(), user.getId());
        System.out.println("更新了用户：" + user);

    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    // 根据用户姓名查找
    @Override
    public User getUserByName(String name) {
        String sql = "SELECT * FROM user WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
}
