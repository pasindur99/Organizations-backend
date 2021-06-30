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



}