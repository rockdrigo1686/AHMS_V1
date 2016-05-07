/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.ServiceTypes;
import com.ahms.model.entity.Services;
import com.ahms.model.manager.entity_manager.ServiceEM;
import java.util.List;

/**
 *
 * @author jorge
 */
public class ServiceBoundary implements AHMSBoundary<Services>{
    
    private ServiceEM serviceEM = null;
    
    public ServiceBoundary() {
        serviceEM = new ServiceEM();
    }

    @Override
    public List<Services> search(Services obj) {
        return serviceEM.search(obj);
    }

    @Override
    public List<Services> searchAll(Services obj) {
        return serviceEM.searchAll(obj);
    }
    
    public List<Services> findAllByServiceType(ServiceTypes type) {
        return serviceEM.findAllByServiceType(type);
    }

    @Override
    public Services find(Services obj) {
       return serviceEM.find(obj);
    }

    @Override
    public int insert(Services obj) {
        return serviceEM.insert(obj);
    }

    @Override
    public int update(Services obj) {
        return serviceEM.update(obj);
    }

    @Override
    public int delete(Services obj) {
        return serviceEM.delete(obj);
    }
    
}
