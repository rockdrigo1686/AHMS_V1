/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.ServiceTypes;
import com.ahms.model.manager.entity_manager.ServiceTypesEM;
import java.util.List;

/**
 *
 * @author jorge
 */
public class ServiceTypesBoundary implements AHMSBoundary<ServiceTypes>{
    
    private ServiceTypesEM serviceTypesEM = null;

    public ServiceTypesBoundary() {
        serviceTypesEM = new ServiceTypesEM();
    }

    @Override
    public List<ServiceTypes> search(ServiceTypes obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ServiceTypes> searchAll(ServiceTypes obj) {
        return serviceTypesEM.findAll();
    }

    @Override
    public ServiceTypes find(ServiceTypes obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(ServiceTypes obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(ServiceTypes obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(ServiceTypes obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
