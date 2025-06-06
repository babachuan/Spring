package cn.org.geneplus.service.proxy;

import cn.org.geneplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:quhaichuan
 * @Date:2025/6/5 16:09
 */
@Component
public class UserServiceProxy implements UserService {

    @Autowired
    private UserService userService;

    // 需要传入要代理的对象
    public UserServiceProxy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void addUser(String name) {
        System.out.println("添加用户前记录日志");
        userService.addUser(name);
        System.out.println("添加用户后记录日志");

    }

    @Override
    public void deleteUser(String name) {
        System.out.println("删除用户前记录日志");
        userService.deleteUser(name);
        System.out.println("删除用户后记录日志");

    }
}
