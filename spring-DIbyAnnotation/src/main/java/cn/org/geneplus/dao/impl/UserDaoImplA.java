package cn.org.geneplus.dao.impl;

import cn.org.geneplus.dao.UserDao;
import org.springframework.stereotype.Repository;

/**
 * @Author:quhaichuan
 * @Date:2025/6/5 10:55
 */
@Repository
public class UserDaoImplA implements UserDao {
    @Override
    public void add() {
        System.out.println("UserDaoImplA add() ...");
    }
}
