package com.demo.services;
import com.demo.entities.Organization;

import java.util.ArrayList;
import java.util.List;

public class OrganizationService {
    private static List<Organization> orgs = new ArrayList<>();
    static {
        Organization org = new Organization(1,"Adoptee");
        Organization org2 = new Organization(2,"Adoptee2");
        orgs.add(org);
        orgs.add(org2);
        orgs.add(new Organization(3,"Adoptee3"));
    }

    public Organization saveUser(int id, String name){
        Organization org = new Organization(id,name);
        orgs.add(org);
        return org;
    }

    public List<Organization> getAll(){
        return orgs;
    }

    public Organization getOne(int id){
        return orgs.get(id);
    }

    public void remove(int id){
        orgs.remove(id);
    }

    public Organization update(int id, String name){

        Organization organization = orgs.get(id);
        organization.setName(name);

        return organization;
    }
}
