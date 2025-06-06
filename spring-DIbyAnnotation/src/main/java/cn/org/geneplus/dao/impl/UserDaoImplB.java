package cn.org.geneplus.dao.impl;

import cn.org.geneplus.dao.UserDao;
import org.springframework.stereotype.Repository;

/**
 * @Author:quhaichuan
 * @Date:2025/6/5 11:01
 */
@Repository
public class UserDaoImplB implements UserDao {
    @Override
    public void add() {
        System.out.println("UserDaoImplB add() ...");
    }
}
