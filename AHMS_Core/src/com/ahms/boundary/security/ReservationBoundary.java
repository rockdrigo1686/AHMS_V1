/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.Reservation;
import com.ahms.model.entity.Rooms;
import com.ahms.model.manager.entity_manager.ReservationEM;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jorge
 */
public class ReservationBoundary implements AHMSBoundary<Reservation>{

    private ReservationEM reservationEM = null;
    
    public ReservationBoundary() {
        reservationEM = new ReservationEM();
    }
    
    @Override
    public List<Reservation> search(Reservation obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> searchAll(Reservation obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reservation find(Reservation obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(Reservation obj) {
        return reservationEM.insert(obj);
    }

    @Override
    public int update(Reservation obj) {
        return reservationEM.update(obj);
    }

    @Override
    public int delete(Reservation obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Reservation> checkReservations(Date date,MultiValue status) {
        return reservationEM.checkReservations(date, status);
    }
    
    public List<Reservation> findReservationByRoom(Rooms room, MultiValue status,Date date){
        return reservationEM.findReservationByRoom(room, status, date);
    }
    
}
