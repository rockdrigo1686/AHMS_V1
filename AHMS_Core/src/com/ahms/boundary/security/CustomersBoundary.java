/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.Customers;
import com.ahms.model.manager.entity_manager.CustomersEM;
import java.util.List;

/**
 *
 * @author jorge
 */
public class CustomersBoundary implements AHMSBoundary<Customers> {

    private CustomersEM customersEM = null;
    
    public CustomersBoundary() {
        customersEM = new CustomersEM();
    }
    @Override
    public List<Customers> search(Customers obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Customers> searchAll(Customers obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customers find(Customers obj) {
        return customersEM.find(obj);
    }

    @Override
    public int insert(Customers obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Customers obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Customers obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
