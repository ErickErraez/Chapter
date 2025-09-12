package com.library.patterns.singleton;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private DataSource dataSource;

    private DatabaseConnection() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        ds.setUser("sa");
        ds.setPassword("");
        this.dataSource = ds;
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}