/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.CashOut;
import com.ahms.model.manager.entity_manager.CashOutEM;
import java.util.List;

/**
 *
 * @author jorge
 */
public class CashOutBoundary implements AHMSBoundary<CashOut>{
    private CashOutEM cashOutEM = null;

    public CashOutBoundary() {
        cashOutEM = new CashOutEM();
    }

    @Override
    public List<CashOut> search(CashOut obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CashOut> searchAll(CashOut obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CashOut find(CashOut obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public CashOut getCurrentShift(CashOut obj){
        return cashOutEM.getCurrentShift();
    }

    @Override
    public int insert(CashOut obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(CashOut obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(CashOut obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
