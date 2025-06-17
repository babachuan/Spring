package cn.org.geneplus.dao;

import cn.org.geneplus.pojo.User;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * @Author:quhaichuan
 * @Date:2025/6/16 13:32
 */
@Component
public class UserImporter {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void importFromCSV(String csvFilePath) throws Exception {
        // 使用openCSV解析CSV文件为User对象列表，没有加编码
//        List<User> users = new CsvToBeanBuilder<User>(new FileReader(csvFilePath))
//                .withType(User.class)
//                .withIgnoreLeadingWhiteSpace(true)
//                .build()
//                .parse();

        // 使用openCSV解析CSV文件为User对象列表，使用UTF-8编码

            
            List<User> users = new CsvToBeanBuilder<User>( new InputStreamReader(new FileInputStream(csvFilePath), StandardCharsets.UTF_8))
                    .withType(User.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();

        users.forEach(System.out::println);

        // 构建批量参数
        List<Object[]> batchArgs = users.stream()
                .map(user -> new Object[]{user.getName(), user.getAge()})
                .toList();

        // 执行批量插入
        String sql = "INSERT INTO user(name,age) VALUES (?, ?)";
        int[] result = jdbcTemplate.batchUpdate(sql, batchArgs);
        System.out.println("批量插入用户成功，影响行数：" + Arrays.stream(result).sum());
    }
}
