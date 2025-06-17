package cn.org.geneplus.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Author:quhaichuan
 * @Date:2025/6/16 16:54
 */
@Repository
public class AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @param name
     * @param amount
     * 转账的通用方法:
     */
    public void updateBalance(String name,double amount){
        String sql = "UPDATE account SET balance = balance + ? WHERE name = ?";
        jdbcTemplate.update(sql,amount,name);
    }

}
