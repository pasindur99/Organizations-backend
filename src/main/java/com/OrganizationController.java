package com;

import com.entities.Organization;
import com.exception.BadRequestException;
import com.exception.ErrorResource;
import com.exception.NotFoundException;
import com.services.OrganizationService;
import com.util.ServletUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/organizations")
public class OrganizationController extends HttpServlet {
    private static final OrganizationService organizationService = new OrganizationService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            Organization organization = ServletUtil.expect(Organization.class, request);
            ServletUtil.respond(organizationService.saveOrganization(organization), response);
        } catch (BadRequestException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ErrorResource error = new ErrorResource("Bad Request", e.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
            ServletUtil.respond(error, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            String id = request.getParameter("id");
            if (id == null) {
                ServletUtil.respond(organizationService.getAll(), response);
            } else {
                Organization organization = organizationService.getOne(Integer.parseInt(request.getParameter("id")));
                ServletUtil.respond(organization, response);
            }
        } catch (NotFoundException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ErrorResource error = new ErrorResource("Bad Request", e.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
            ServletUtil.respond(error, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            organizationService.deleteOrg(id);
        } catch (NotFoundException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            ErrorResource error = new ErrorResource("Not found", e.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
            ServletUtil.respond(error, response);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        try {
            Organization organization = ServletUtil.expect(Organization.class, request);
            ServletUtil.respond(organizationService.updateOrg(organization), response);

        } catch (BadRequestException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ErrorResource error = new ErrorResource("Bad Request", e.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
            ServletUtil.respond(error, response);
        } catch (NotFoundException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            ErrorResource error = new ErrorResource("Not found", e.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
            ServletUtil.respond(error, response);
        }
    }
}

