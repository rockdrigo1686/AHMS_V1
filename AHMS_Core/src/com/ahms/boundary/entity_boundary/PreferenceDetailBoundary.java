/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.entity_boundary;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.Customers;
import com.ahms.model.entity.PreferenceDetail;
import com.ahms.model.manager.entity_manager.PreferenceDetailEM;
import java.util.List;

/**
 *
 * @author jorge
 */
public class PreferenceDetailBoundary implements AHMSBoundary<PreferenceDetail>  {
    private PreferenceDetailEM preferenceDetailEM =null;
    @Override
    public List<PreferenceDetail> search(PreferenceDetail obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PreferenceDetail> searchAll(PreferenceDetail obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PreferenceDetail find(PreferenceDetail obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(PreferenceDetail obj) {
        return preferenceDetailEM.insert(obj);
    }

    @Override
    public int update(PreferenceDetail obj) {
        return preferenceDetailEM.update(obj);
    }

    @Override
    public int delete(PreferenceDetail obj) {
        return preferenceDetailEM.delete(obj);
    }
    
    public PreferenceDetail searchByCusId(PreferenceDetail obj) { 
         preferenceDetailEM = new PreferenceDetailEM();
         return preferenceDetailEM.searchByCusId(obj);
    }
    
    public List<PreferenceDetail> findPreferences(Customers customer){
        preferenceDetailEM = new PreferenceDetailEM();
        return preferenceDetailEM.findByCustomer(customer);
    }
    
}
