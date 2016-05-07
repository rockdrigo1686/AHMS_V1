/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(PreferenceDetail obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(PreferenceDetail obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public PreferenceDetail searchByCusId(PreferenceDetail obj) { 
         preferenceDetailEM = new PreferenceDetailEM();
         return preferenceDetailEM.searchByCusId(obj);
     }
    
}
