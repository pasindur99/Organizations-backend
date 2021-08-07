package com;

import com.entities.Organization;
import com.services.OrganizationService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//import code.DatabaseConnection;

@WebServlet("/organizations")
public class OrganizationController extends HttpServlet{
    private static final OrganizationService organizationService = new OrganizationService();
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
                IOException {

            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");

            organizationService.saveOrganization(id,name);

//            response.setContentType("text/json");
//            PrintWriter out = response.getWriter();
//            out.println("Successful");
            /*
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

             */

        }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String id = request.getParameter("id");

        if (id == null){
            response.setContentType("text/json");
            PrintWriter out = response.getWriter();
            out.println(new Gson().toJson(organizationService.getAll()));
        }else{
            response.setContentType("text/json");
            PrintWriter out = response.getWriter();
            Organization organization = organizationService.getOne(Integer.parseInt(request.getParameter("id")));

            if(organization != null){
                out.println(new Gson().toJson(organizationService.getOne(Integer.parseInt(request.getParameter("id")))));
            }else{
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }

        }
    }

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