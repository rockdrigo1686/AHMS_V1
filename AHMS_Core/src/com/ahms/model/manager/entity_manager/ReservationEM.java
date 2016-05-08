/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.Reservation;
import com.ahms.model.entity.Rooms;
import com.ahms.model.manager.AHMSEntityManager;
import com.ahms.util.MMKeys;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author jorge
 */
public class ReservationEM extends AHMSEntityManager<Reservation> {

    public List<Reservation> checkReservations(Date date, MultiValue status) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }

            TypedQuery<Reservation> query = em.createNamedQuery("Reservation.checkReservation", Reservation.class);
            query.setParameter("date", date);
            query.setParameter("status", status);
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

    public List<Reservation> findReservationByRoom(Rooms room, MultiValue status, Date date) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<Reservation> query = em.createNamedQuery("Reservation.findByRmsId", Reservation.class);
            query.setParameter("rmsId", room);
            query.setParameter("status", status);
            query.setParameter("date", date);
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
