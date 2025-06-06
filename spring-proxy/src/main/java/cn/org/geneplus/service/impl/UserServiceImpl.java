package cn.org.geneplus.service.impl;

import cn.org.geneplus.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @Author:quhaichuan
 * @Date:2025/6/5 16:07
 */
@Component
public class UserServiceImpl implements UserService {
    @Override
    public void addUser(String name) {
        System.out.println("添加用户"+ name);

    }

    @Override
    public void deleteUser(String name) {
        System.out.println("删除用户"+ name);
    }
}
