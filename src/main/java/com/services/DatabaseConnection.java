package com.services;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection;

    public static Connection getConnection() {

        if (connection == null){
            initializeDatabase();
        }
        return connection;
    }
    
    private static void initializeDatabase() {

        String dbDriver = Driver.class.getName();
        String dbURL = "jdbc:mysql://localhost:3306/";
        String dbName = "org";
        String dbUsername = "siri";
        String dbPassword = "siri123";

        try {
            Class.forName(dbDriver);
            connection = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
            System.exit(0);
        }
    }
}

