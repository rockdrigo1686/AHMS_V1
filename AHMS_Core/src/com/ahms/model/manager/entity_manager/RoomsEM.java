/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.Rooms;
import com.ahms.model.manager.AHMSEntityManager;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author rsoto
 */
public class RoomsEM extends AHMSEntityManager{
    public List<Rooms> findByFloor(Rooms rooms) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<Rooms> query = em.createNamedQuery("Rooms.findByFlrId", Rooms.class);
            query.setParameter("flrId", rooms.getFlrId());
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
    
    public List<Rooms> findByRmsStatus(Rooms rooms) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<Rooms> query = em.createNamedQuery("Rooms.findByRmsStatus", Rooms.class);
            query.setParameter("rmsStatus", rooms.getRmsStatus());
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
    
    public List<Rooms> findAvailableByAmmount(Integer limit) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            Query query = em.createNativeQuery("SELECT r.* FROM rooms r WHERE r.rms_status = 'Disponible' LIMIT " + limit, Rooms.class);
            query.setParameter("limit", limit);
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
