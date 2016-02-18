/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.Floors;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.manager.AHMSEntityManager;
import com.ahms.util.MMKeys;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author rsoto
 */
public class FloorEM extends AHMSEntityManager{
    public List<Floors> findAllAvailable() {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<Floors> query = em.createNamedQuery("Floors.findAllAvailable", Floors.class);
            query.setParameter("flrStatus", new MultiValue(MMKeys.Rooms.STA_DISPONIBLE_KEY));
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
