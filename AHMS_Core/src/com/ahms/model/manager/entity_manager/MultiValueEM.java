/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.MultiValue;
import com.ahms.model.manager.AHMSEntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author jorge
 */
public class MultiValueEM extends AHMSEntityManager {
    
    public MultiValue findByKey(MultiValue multivalue) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<MultiValue> query = em.createNamedQuery("MultiValue.findByMvaKey", MultiValue.class);
            query.setParameter("mvaKey", multivalue.getMvaKey());
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
