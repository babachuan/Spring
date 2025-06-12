package cn.org.geneplus.service;

import cn.org.geneplus.annotation.LogOperation;
import org.springframework.stereotype.Component;

/**
 * @Author:quhaichuan
 * @Date:2025/6/10 18:13
 */
@Component
public class UserService {

    @LogOperation("注册操作")
    public String register(String username, String password){
        System.out.println("【业务逻辑】执行用户注册，账号：" + username + "，密码：" + password);
        return username + "注册成功";
    }

    @LogOperation("登录操作")
    public String login(String username, String password){
        System.out.println("【业务逻辑】执行用户登录，账号：" + username + "，密码：" + password);
        return username + "登录成功";
    }
}
