package cn.org.geneplus.service;

import cn.org.geneplus.config.SpringConfig;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author:quhaichuan
 * @Date:2025/6/17 11:00
 */
public class TransferServiceTest extends TestCase {


    @Test
    public void testTransfer() throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        TransferService transferService = applicationContext.getBean(TransferService.class);
        transferService.transfer("Alice","Bob",150);
    }

}