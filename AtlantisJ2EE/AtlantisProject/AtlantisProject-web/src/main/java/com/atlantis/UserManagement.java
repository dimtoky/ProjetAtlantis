/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis;

import com.atlantis.DAO.DevicesFacadeLocal;
import com.atlantis.DAO.UserFacadeLocal;
import com.atlantis.Entity.Devices;
import com.atlantis.Entity.User;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import com.atlantis.queues.RepInfoqueueLocal;

/**
 *
 * @author dimtoky
 */
@Named(value = "userManagement")
@RequestScoped
public class UserManagement {

    @EJB
    private DevicesFacadeLocal devicesFacade;


    @EJB
    private RepInfoqueueLocal repqueue;

    @EJB
    private UserFacadeLocal userFacade;
   
    
    
    /**
     * Creates a new instance of UserManagement
     */
    public UserManagement() {
        
    }
    
    public List<User> getUsers(){
        return userFacade.findAll();   
    }
    
     public User getUser(int id){
        return userFacade.find(id);   
    }
     
     public List<Devices> getDevices(){
        return devicesFacade.findAll();   
    }
     
     
     public String test(){
        // List<User> test = userFacade.findAll(); 
        // List<Devices>  testdevice = devicesFacade.findAll();  
         System.out.println("test");
        // System.out.println(test);
        // System.out.println(testdevice);
         repqueue.sendMessage("{ \"deviceType\" : \"Thermal Sensor\", \"deviceAddress\" : \"00:0a:94:9d:68:16\" }");
         return "test";
         
     }
     
     
     
     
}
