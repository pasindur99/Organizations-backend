package com.services;

import com.entities.Organization;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrganizationService {

    public List<Organization> getAll(){

        List<Organization> organizations = new ArrayList<>();

        try {
            Connection connection  = DatabaseConnection.getConnection();
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
            Connection connection  = DatabaseConnection.getConnection();
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

    public Organization saveOrganization(String name){
        Organization organization = null;
        try{
            Connection connection = DatabaseConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO organization (name) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1,name);
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    organization = getOne(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating organisation failed, no ID obtained.");
                }
            }
            statement.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return organization;
    }

    public void deleteOrg (int id){

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM organization WHERE id = ?");
            statement.setInt(1,id);
            statement.executeUpdate();
            statement.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Organization updateOrg (int id , String name) {
        Organization organization = null;

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE organization SET name = ? WHERE id = ? ");
            statement.setString(1,name);
            statement.setInt(2,id);
            statement.executeUpdate();
            statement.close();
            organization = getOne(id);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return organization;
    }
}

