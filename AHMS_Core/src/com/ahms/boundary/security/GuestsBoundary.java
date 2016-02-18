/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.Account;
import com.ahms.model.entity.AccountTransactions;
import com.ahms.model.entity.Guests;
import com.ahms.model.manager.entity_manager.GuestsEM;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class GuestsBoundary implements AHMSBoundary<Guests>{
    private GuestsEM guestsEM = null;
    
    public GuestsBoundary() {
        guestsEM = new GuestsEM();
    }
    @Override
    public List<Guests> search(Guests obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Guests> searchAll(Guests obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Guests find(Guests obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int insert(Guests obj) {
        return guestsEM.insert(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Guests obj) {
        return guestsEM.delete(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Guests obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Guests> findByAtrId(AccountTransactions obj) {
        return guestsEM.findByAtrId(obj);
    }
    
}
