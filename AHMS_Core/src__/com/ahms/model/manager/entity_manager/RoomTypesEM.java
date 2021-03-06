/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.RoomTypes;
import com.ahms.model.manager.AHMSEntityManager;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author jorge
 */
public class RoomTypesEM extends AHMSEntityManager{
    
    public List<RoomTypes> findActiveTypes(RoomTypes roomTypes) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<RoomTypes> query = em.createNamedQuery("RoomTypes.findActiveTypes", RoomTypes.class);
            query.setParameter("rtyStatus", roomTypes.getRtyStatus());
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
