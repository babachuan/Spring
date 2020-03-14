package com.qhc.mybatis3.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;

public class DataSouceConfig {
    public static ComboPooledDataSource getDataSouece(){
        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
            dataSource.setUser("root");
            dataSource.setPassword("123456");
            dataSource.setInitialPoolSize(50);
            dataSource.setMaxPoolSize(100);
            return dataSource;
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            return  null;
        }
    }
}
