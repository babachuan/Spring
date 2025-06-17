package cn.org.geneplus.service;

import cn.org.geneplus.config.SpringConfig;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author:quhaichuan
 * @Date:2025/6/16 17:13
 */
public class TransferServiceTest extends TestCase {

    // 测试转账，没有使用事务
    @Test
    public void testTransfer() throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        TransferService transferService = applicationContext.getBean(TransferService.class);
        transferService.transfer("Alice","Bob",350);
    }

}