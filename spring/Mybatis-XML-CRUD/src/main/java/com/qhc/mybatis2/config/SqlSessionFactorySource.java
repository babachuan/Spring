package com.qhc.mybatis2.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactorySource {
    public static SqlSessionFactory getSqlSessionFactory() {
        try {
            String sources = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(sources);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            return sqlSessionFactory;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
