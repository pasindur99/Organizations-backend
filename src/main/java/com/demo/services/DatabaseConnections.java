package com.demo.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnections {

    private static Connection connection;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        if (connection == null){
            initializeDatabase();
        }
        return connection;
    }
    
    private static void initializeDatabase() throws SQLException, ClassNotFoundException{

        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";

        String dbName = System.getenv("DB_NAME");
        String dbUsername = System.getenv("DB_USERNAME");
        String dbPassword = System.getenv("DB_PASSWORD");

        Class.forName(dbDriver);
        connection = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
    }
}
