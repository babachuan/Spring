package cn.org.geneplus.dao;

import cn.org.geneplus.config.SpringConfig;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author:quhaichuan
 * @Date:2025/6/16 13:39
 */
public class UserImporterTest extends TestCase {

    // 测试导入CSV文件
    @Test
    public void testImportFromCSV() throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserImporter userImporter = applicationContext.getBean("userImporter", UserImporter.class);
        userImporter.importFromCSV("src/test/java/User.csv");
//        importer.importFromCSV("D:\\workspace\\spring-JDBCTemplate\\src\\main\\resources\\users.csv");
    }

}