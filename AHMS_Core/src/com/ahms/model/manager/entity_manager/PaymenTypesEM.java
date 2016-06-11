/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.PaymentTypes;
import com.ahms.model.manager.AHMSEntityManager;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author jorge
 */
public class PaymenTypesEM extends AHMSEntityManager<PaymentTypes>{
    public List<PaymentTypes> findAll(PaymentTypes payment) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<PaymentTypes> query = em.createNamedQuery("PaymentTypes.findAll", PaymentTypes.class);
            return query.getResultList();
        } catch (Exception e) {
            if (e instanceof NoResultException) {
                return null;
            } else {
                throw e;
            }
        } finally { 
            if (em != null) {
                closeEm();
            }
        }
    }
    
    public List<PaymentTypes> findByPayStatus(PaymentTypes payment) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<PaymentTypes> query = em.createNamedQuery("PaymentTypes.findByPayStatus", PaymentTypes.class);
            query.setParameter("payStatus", payment.getPayStatus());
            return query.getResultList();
        } catch (Exception e) {
            if (e instanceof NoResultException) {
                return null;
            } else {
                throw e;
            }
        } finally { 
            if (em != null) {
                closeEm();
            }
        }
    }
}
