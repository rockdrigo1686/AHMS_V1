/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.ServiceTypes;
import com.ahms.model.manager.AHMSEntityManager;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author rsoto
 */
public class ServiceTypesEM extends AHMSEntityManager{
    
    public List<ServiceTypes> findAll() {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<ServiceTypes> query = em.createNamedQuery("ServiceTypes.findAll", ServiceTypes.class);
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
