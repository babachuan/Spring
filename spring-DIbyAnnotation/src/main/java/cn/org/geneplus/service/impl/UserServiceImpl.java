package cn.org.geneplus.service.impl;

import cn.org.geneplus.dao.UserDao;
import cn.org.geneplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @Author:quhaichuan
 * @Date:2025/6/5 11:02
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier(value = "userDaoImplA")
    private UserDao userDao;
    @Override
    public void add() {
        userDao.add();
        System.out.println("UserServiceImpl.add() ...");

    }
}
