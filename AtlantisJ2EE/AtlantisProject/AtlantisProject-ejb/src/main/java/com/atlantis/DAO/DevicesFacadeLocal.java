/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.DAO;

import com.atlantis.Entity.Devices;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DevicesFacadeLocal {

    void create(Devices devices);

    void edit(Devices devices);

    void remove(Devices devices);

    Devices find(Object id);
    
    Devices findbyaddress(String macaddr);
    
    List<Devices> findAllbyaddress(String macaddr);
             
    List<Devices> findAll();

    List<Devices> findRange(int[] range);

    int count();
    
}
