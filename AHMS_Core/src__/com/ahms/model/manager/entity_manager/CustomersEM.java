/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.Customers;
import com.ahms.model.manager.AHMSEntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author jorge
 */
public class CustomersEM extends AHMSEntityManager{
    public Customers find(Customers customers) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<Customers> query = em.createNamedQuery("Customers.findByCusId", Customers.class);
            query.setParameter("cusId", customers.getCusId());
            return query.getSingleResult();
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
