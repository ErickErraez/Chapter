package com.library.utils;

import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {

    private static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private DatabaseUtils() {
        // Private constructor to prevent instantiation
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void startH2Server() throws SQLException {
        Server.createTcpServer("-tcpAllowOthers").start();
    }
}