/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.entity_boundary;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.ServiceTypes;
import com.ahms.model.manager.entity_manager.ServiceTypesEM;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        return serviceTypesEM.search(obj);
    }

    @Override
    public List<ServiceTypes> searchAll(ServiceTypes obj) {
        return serviceTypesEM.findAll();
    }

    @Override
    public ServiceTypes find(ServiceTypes obj) {
        try {
            return  serviceTypesEM.find(obj);
        } catch (Exception ex) {
            Logger.getLogger(ServiceTypesBoundary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int insert(ServiceTypes obj) {
        return serviceTypesEM.insert(obj);
    }

    @Override
    public int update(ServiceTypes obj) {
        return serviceTypesEM.update(obj);
    }

    @Override
    public int delete(ServiceTypes obj) {
        return serviceTypesEM.delete(obj);
    }
    
}
