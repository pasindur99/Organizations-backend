/*
1. import -- > java.sql
2. load and register the driver -- > com.mysql.jdbc.Driver
3. create connection
4. create a statement
5. execute the query
7. close
 */
package com.services;
import com.entities.Organization;

import java.sql.*;

public class OrgDao {

    public Organization getOrg(int id){

        Organization org = new Organization();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/organizations","root","pas.pas.pas.");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from organization");
            if(rs.next()){
                org.setId(rs.getInt("id"));
                org.setName(rs.getString("name"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return org;

    }
/*
    public int addOrganiaztion(Organization org) throws ClassNotFoundException{

        String INSERT_SQL = "INSERT INTO organization" + "(id , name )";

        int result = 0;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/mysql_database?useSSL=false", "root", "pas.pas.pas.");

             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)){

            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, org.getName());

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    private void printSQLException(SQLException ex) {

        for(Throwable e: ex){
            if (e instanceof SQLException){

                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

 */

}