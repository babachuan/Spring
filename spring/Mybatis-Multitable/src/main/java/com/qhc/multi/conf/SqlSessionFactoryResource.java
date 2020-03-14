package com.qhc.multi.conf;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryResource {
    public static SqlSessionFactory getSqlSessionFactory(){
        try {
            String source="mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(source);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            return sqlSessionFactory;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
