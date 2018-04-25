package com.tanyetao.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {
    private static String JDBC_DRIVER;
    private static String DB_URL;
    private static String USER;
    private static String PASSWORD;

    static {
        Properties props = new Properties();
        InputStream is = ConnectionFactory.class.getResourceAsStream("JDBCConfig.properties");
        try {
            props.load(is);
            JDBC_DRIVER = props.getProperty("JDBC_DRIVER");
            DB_URL = props.getProperty("DB_URL");
            USER = props.getProperty("USER");
            PASSWORD = props.getProperty("PASSWORD");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
