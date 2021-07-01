package com.demo;

import com.demo.entities.Organization;
import com.demo.services.OrganizationService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/organizations")
public class OrganizationController extends HttpServlet{

    private static final OrganizationService service = new OrganizationService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        response.setContentType("text/json");
        System.out.println("POST");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Organization updatedOrganization = service.saveUser(id,name);
        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(updatedOrganization));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        System.out.println("GET");
        String id = request.getParameter("id");
        System.out.println(id);
        response.setContentType("text/json");
        System.out.println("id - " + id);
        PrintWriter out = response.getWriter();
        if(id == null){
            out.println(new Gson().toJson(service.getAll()));
        }else{
            out.println(new Gson().toJson(service.getOne(Integer.parseInt((id))-1)));
        }
    }

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

}