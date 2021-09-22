package com.services;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        if (connection == null){
            initializeDatabase();
        }
        return connection;
    }
    
    private static void initializeDatabase() throws SQLException, ClassNotFoundException{

        String dbDriver = Driver.class.getName();
        String dbURL = "jdbc:mysql://localhost:3306/";

        String dbName = "org";
        String dbUsername = "siri";
        String dbPassword = "siri123";

        Class.forName(dbDriver);
        connection = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
    }
}

