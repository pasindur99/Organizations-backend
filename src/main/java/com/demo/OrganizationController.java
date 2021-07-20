package com.demo;

import com.demo.entities.Organization;
import com.demo.services.DatabaseConnections;
import com.demo.services.OrganizationService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import code.DatabaseConnection;

@WebServlet("/organizations")
public class OrganizationController extends HttpServlet{

    /*
        private static final OrganizationService service = new OrganizationService();
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                IOException {

            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");

            Organization org = new Organization();
            org.setId(id);
            org.setName(name);

            Organization updatedOrganization = service.saveUser(id,name);
            PrintWriter out = response.getWriter();
            out.println(new Gson().toJson(updatedOrganization));

            response.setContentType("text/json");
            System.out.println("POST");
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            Organization updatedOrganization = service.saveUser(id,name);
            PrintWriter out = response.getWriter();
            out.println(new Gson().toJson(updatedOrganization));

        }
         */
    private static final OrganizationService service = new OrganizationService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String id = request.getParameter("id");
        //int Id = Integer.parseInt(request.getParameter("id"));
        Statement stmt = null;
        Connection con = null;

        try {
            con  = DatabaseConnections.initializeDatabase();
            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from organization");

            List<Organization> organizations = new ArrayList<>();
            while (rs.next()) {
                Organization org = new Organization();
                org.setId(rs.getInt("id"));
                org.setName(rs.getString("name"));
                organizations.add(org);
            }

            response.setContentType("text/json");
            PrintWriter out = response.getWriter();
            if(id == null){
                out.println(new Gson().toJson(organizations));
            }else{
                out.println(new Gson().toJson(organizations));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
/*          stmt = con.createStatement();
            // create a table
            stmt.executeUpdate("create table cities (name varchar(50) not null primary key, population int, county varchar(30))");
            // insert a test record
            stmt.executeUpdate("insert into cities values ('myHomeCity', 106769, 'myHomeCounty')");

 */
        /*
        String id = request.getParameter("id");
        int Id = Integer.parseInt(request.getParameter("id"));

        OrgDao service = new OrgDao();
        //Organization org1 = service.getOrg(Id);

        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(service.getOrg(Id)));

        request.setAttribute("organization", org1);

        RequestDispatcher rd = request.getRequestDispatcher("showOrganization.jsp");
        rd.forward(request,response);

//
        System.out.println("GET");
        String id = request.getParameter("id");
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        if(id == null){
            out.println(new Gson().toJson(service.getAll()));
        }else{
            out.println(new Gson().toJson(service.getOne(Integer.parseInt((id))-1)));
        }
    }
     */
/*
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        System.out.println("DELETE");
        int id = Integer.parseInt(request.getParameter("id")) - 1;
        service.remove(id);
    }

    private int retrieveUserid(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo.startsWith("/")){
            pathInfo = pathInfo.substring(1);
        }
        return Integer.parseInt((pathInfo));
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        System.out.println("PUT");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");

        System.out.println("id - " + id);
        Organization updatedOrganization = service.update(id,name);
        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(updatedOrganization));
    }
     */

}