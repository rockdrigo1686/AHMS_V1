/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.entity_boundary;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.PaymentTypes;
import com.ahms.model.manager.entity_manager.PaymenTypesEM;
import java.util.List;

/**
 *
 * @author jorge
 */
public class PaymentTypesBoundary implements AHMSBoundary<PaymentTypes>{
    
    private PaymenTypesEM paymentTypesEM = null;
    
    public PaymentTypesBoundary() {
        paymentTypesEM = new PaymenTypesEM();
    }

    @Override
    public List<PaymentTypes> search(PaymentTypes obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PaymentTypes> searchAll(PaymentTypes obj) {
        return paymentTypesEM.findAll(obj);        
    }

    @Override
    public PaymentTypes find(PaymentTypes obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(PaymentTypes obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(PaymentTypes obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(PaymentTypes obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
