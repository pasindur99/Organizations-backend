package com;

import com.entities.Organization;
import com.google.gson.Gson;
import com.services.OrganizationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/organizations")
public class OrganizationController extends HttpServlet{
    private static final OrganizationService organizationService = new OrganizationService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        StringBuilder builder = new StringBuilder();
        BufferedReader reader = request.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } finally {
            reader.close();
        }

        Gson gson = new Gson();
        Organization newOrganization = gson.fromJson(builder.toString(), Organization.class);

        Organization organization = organizationService.saveOrganization(newOrganization);

        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(organization));
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

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        organizationService.deleteOrg(id);

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        StringBuilder builder = new StringBuilder();
        BufferedReader reader = request.getReader();

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } finally {
            reader.close();
        }

        Gson gson = new Gson();
        Organization updatedOrganization = gson.fromJson(builder.toString(), Organization.class);

        Organization organization = organizationService.updateOrg(updatedOrganization);

        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(organization));
    }
}

