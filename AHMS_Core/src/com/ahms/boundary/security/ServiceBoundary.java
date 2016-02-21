/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.ServiceTypes;
import com.ahms.model.entity.Services;
import com.ahms.model.manager.entity_manager.AccountEM;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Services> searchAll(Services obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Services> findAllByServiceType(ServiceTypes type) {
        return serviceEM.findAllByServiceType(type);
    }

    @Override
    public Services find(Services obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(Services obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Services obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Services obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
