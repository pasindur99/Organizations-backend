package com.demo;

import com.demo.entities.Organization;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/organizations")
public class DemoServlet extends HttpServlet {

    static List<Organization> orgs = new ArrayList<>();
    static {
        Organization org = new Organization(1,"Adoptee");
        Organization org2 = new Organization(2,"Adoptee2");
        orgs.add(org);
        orgs.add(org2);
        orgs.add(new Organization(3,"Adoptee3"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println(id);
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        if(id == null){
            out.println(new Gson().toJson(orgs));
        }else{
            out.println(new Gson().toJson(orgs.get(Integer.parseInt((id))-1)));
        }
    }


}