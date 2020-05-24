/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.endpoints;

import com.atlantis.DAO.DevicesFacadeLocal;
import com.atlantis.DAO.MetricsFacadeLocal;
import com.atlantis.DAO.UserFacadeLocal;
import com.atlantis.Entity.Devices;
import com.atlantis.Entity.Metrics;
import com.atlantis.Entity.User;
import com.atlantis.Entity.Userdevices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author root
 */
@Path("user")
@RequestScoped
public class UserResource {

    @Context
    private UriInfo context;
    
    @EJB
    private DevicesFacadeLocal devicesFacade;

    @EJB
    private MetricsFacadeLocal metricsFacade;

    @EJB
    private UserFacadeLocal userFacade;
    
    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }

    /**
     * Retrieves representation of an instance of com.atlantis.endpoints.UserResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Metrics> getJson() {
      Devices device = devicesFacade.find(153);
      List<Metrics> metrics = device.getMetricsList();
      return metrics;
    }
    
   @Path("/new")
    @POST
   @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addUser(String idToken) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = decodedToken.getUid();
            String email = decodedToken.getEmail();
            
           
            User user = new User();
            user.setEmail(email);
            user.setToken(uid);
            user.setCreatedAt(new Date());
            
            userFacade.create(user);
            return "Success";
        } catch (FirebaseAuthException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
            return "Failed";
        }

    }

    /**
     * PUT method for updating or creating an instance of UserResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
