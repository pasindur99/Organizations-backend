package com.services;

import com.entities.Organization;
import com.exception.NotFoundException;

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

    public Organization getOne(int id) throws NotFoundException {
        try {
            Connection connection  = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from organization where id = " + id);
            if (rs.next()){
                Organization organization = new Organization();
                organization.setId(rs.getInt("id"));
                organization.setName(rs.getString("name"));
                return organization;
            } else {
                throw new NotFoundException("Organization doesn't exists for id:"+ id);
            }
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    public Organization saveOrganization(Organization organization){
        try{
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO organization (name) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,organization.getName());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    organization = getOne(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating organisation failed, no ID obtained.");
                }
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            statement.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return organization;
    }

    public void deleteOrg (int id) throws NotFoundException {
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
            throw new NotFoundException("Organization not found for id:" + id);
        }
    }

    public Organization updateOrg (Organization organization) throws NotFoundException {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE organization SET name = ? WHERE id = ? ");
            statement.setString(1, organization.getName());
            statement.setInt(2,organization.getId());
            statement.executeUpdate();
            statement.close();
            organization = getOne(organization.getId());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return organization;
    }
}

