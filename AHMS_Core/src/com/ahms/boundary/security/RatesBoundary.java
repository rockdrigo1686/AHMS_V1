/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.Rates;
import com.ahms.model.manager.entity_manager.RatesEM;
import java.util.List;

/**
 *
 * @author jorge
 */
public class RatesBoundary implements AHMSBoundary<Rates>{
    
    private RatesEM ratesEm = null;

    public RatesBoundary() {
        ratesEm = new RatesEM();
    }

    @Override
    public List<Rates> search(Rates obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rates> searchAll(Rates obj) {
        return ratesEm.searchAll(obj);
    }

    @Override
    public Rates find(Rates obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(Rates obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Rates obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Rates obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
