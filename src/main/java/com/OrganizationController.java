package com;

import com.entities.Organization;
import com.google.gson.Gson;
import com.services.OrganizationService;
import com.util.extractJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.util.extractJson.toJson;


@WebServlet("/organizations")
public class OrganizationController extends HttpServlet{
    private static final OrganizationService organizationService = new OrganizationService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        extractJson toJson = new extractJson();
        Organization organization = toJson(request);

        Organization newOrganization = organizationService.saveOrganization(organization);

        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(newOrganization));
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

        extractJson toJson = new extractJson();
        Organization organization = toJson(request);

        Organization updatedOrganization = organizationService.updateOrg(organization);

        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(updatedOrganization));
    }
}

