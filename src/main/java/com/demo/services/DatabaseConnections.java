package com.demo.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnections {

    public static Connection initializeDatabase() throws SQLException, ClassNotFoundException{

        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";

        String dbName = "organizations";
        String dbUsername = "root";
        String dbPassword = "pas.pas.pas.";

        Class.forName(dbDriver);
        Connection con = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);

        return con;

    }
}
