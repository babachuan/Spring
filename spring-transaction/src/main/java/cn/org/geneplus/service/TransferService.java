package cn.org.geneplus.service;

import cn.org.geneplus.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author:quhaichuan
 * @Date:2025/6/16 16:54
 */
@Service
public class TransferService {

    @Autowired
    private AccountDao accountDao;

    @Transactional
    public void transfer(String from, String to, double amount){

        accountDao.updateBalance(from, -amount);
        // 模拟失败
        if(amount > 300){
            throw new RuntimeException("转账金额过大");
        }

        accountDao.updateBalance(to, amount);
    }

}
