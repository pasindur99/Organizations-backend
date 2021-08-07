package com.services;

import com.entities.Organization;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrganizationService {
    //private static List<Organization> orgs = new ArrayList<>();

//    public Organization saveUser(int id, String name){
//        Organization org = new Organization();
//        orgs.add(org);
//        return org;
//    }

    public List<Organization> getAll(){

        List<Organization> organizations = new ArrayList<>();

        try {
            Connection connection  = DatabaseConnections.getConnection();
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from organization");

            while (rs.next()) {
                Organization org = new Organization();
                org.setId(rs.getInt("id"));
                org.setName(rs.getString("name"));
                organizations.add(org);
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return organizations;
    }

    public Organization getOne(int id){
        Organization org = null;
        try {
            Connection connection  = DatabaseConnections.getConnection();
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from organization where id = " + id);

            if (rs.next()){
                org = new Organization();
                org.setId(rs.getInt("id"));
                org.setName(rs.getString("name"));
            }

        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
        return org;
    }

    public void saveOrganization(int id, String name){

        try{
            Connection connection = DatabaseConnections.getConnection();

            PreparedStatement statement = connection.prepareStatement("insert into orgizations values(?,?)");
            statement.setInt(1,id);
            statement.setString(2,name);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
/*
ResultSet rs = statement.executeQuery("update organization set name =" + name + "where id = " + id);
 */