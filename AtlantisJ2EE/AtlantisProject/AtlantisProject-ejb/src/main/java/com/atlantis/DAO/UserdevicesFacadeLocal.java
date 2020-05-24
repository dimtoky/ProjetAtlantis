/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.DAO;

import com.atlantis.Entity.Userdevices;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface UserdevicesFacadeLocal {

    void create(Userdevices userdevices);

    void edit(Userdevices userdevices);

    void remove(Userdevices userdevices);

    Userdevices find(Object id);

    List<Userdevices> findAll();

    List<Userdevices> findRange(int[] range);

    int count();
    
}
