package cn.org.geneplus.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @Author:quhaichuan
 * @Date:2025/6/17 10:23
 */
@Service
public class LogService {


    @Transactional(propagation = Propagation.REQUIRED)
    public void saveLog(String content,String label) {
        // 测试当前线程是否在事务中
        boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();


        System.out.println("保存日志：" + content);
        System.out.println("["+label+"] 当前事务存在：{"+isActive +"}");
        if(content.contains("异常")){

            throw new RuntimeException("保存日志异常");
        }
    }
}
