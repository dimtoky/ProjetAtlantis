/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.DAO;

import com.atlantis.Entity.Metrics;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface MetricsFacadeLocal {

    void create(Metrics metrics);

    void edit(Metrics metrics);

    void remove(Metrics metrics);

    Metrics find(Object id);

    List<Metrics> findAll();

    List<Metrics> findRange(int[] range);

    int count();
    
}
