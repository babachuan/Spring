package cn.org.geneplus.dao;

import cn.org.geneplus.pojo.User;

import java.util.List;

/**
 * @Author:quhaichuan
 * @Date:2025/6/12 11:27
 */
public interface UserDao {
    void addUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
    User getUserById(int id);
    User getUserByName(String name);
    List<User> getAllUsers();
}
