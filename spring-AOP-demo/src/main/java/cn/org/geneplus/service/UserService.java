package cn.org.geneplus.service;

import org.springframework.stereotype.Component;

/**
 * @Author:quhaichuan
 * @Date:2025/6/10 11:09
 */
@Component
public class UserService {
    public String register(String username, String password){
        System.out.println("【业务逻辑】执行用户注册，账号："+username+"，密码："+ password);
        return username+"注册成功";
    }


    public String login(String username, String password){
        System.out.println("【业务逻辑】执行用户登录，账号："+username+"，密码："+ password);
        return username+"登录成功";
    }
}
