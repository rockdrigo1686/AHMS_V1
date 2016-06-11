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
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            return paymentTypesEM.search(obj);
        } catch (Exception ex) {
            Logger.getLogger(PaymentTypesBoundary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<PaymentTypes> searchAll(PaymentTypes obj) {
        return paymentTypesEM.findAll(obj);        
    }
    
    public List<PaymentTypes> findByPayStatus(PaymentTypes obj) {
        return paymentTypesEM.findByPayStatus(obj);        
    }

    @Override
    public PaymentTypes find(PaymentTypes obj) {
        try {
            return paymentTypesEM.find(obj);
        } catch (Exception ex) {
            Logger.getLogger(PaymentTypesBoundary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int insert(PaymentTypes obj) {
       return paymentTypesEM.insert(obj);
    }

    @Override
    public int update(PaymentTypes obj) {
        return paymentTypesEM.update(obj);
    }

    @Override
    public int delete(PaymentTypes obj) {
        return paymentTypesEM.delete(obj);
    }
    
}
