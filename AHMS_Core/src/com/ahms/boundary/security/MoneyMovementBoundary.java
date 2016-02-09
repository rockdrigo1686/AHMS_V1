/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.MoneyMovement;
import com.ahms.model.manager.entity_manager.MoneyMovementEM;
import java.util.List;

/**
 *
 * @author rsoto
 */
public class MoneyMovementBoundary implements AHMSBoundary<MoneyMovement> {
    private MoneyMovementEM moneyMovementEM =null;

    @Override
    public List<MoneyMovement> search(MoneyMovement obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MoneyMovement> searchAll(MoneyMovement obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MoneyMovement find(MoneyMovement obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(MoneyMovement obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(MoneyMovement obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(MoneyMovement obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public List<MoneyMovement> searchByCouId(MoneyMovement obj) { 
         moneyMovementEM = new MoneyMovementEM();
         return moneyMovementEM.searchByCouId(obj);
     }
    
}
