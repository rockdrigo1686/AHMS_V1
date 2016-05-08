/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.util;

import com.ahms.boundary.security.MultiValueBoundary;
import com.ahms.boundary.security.ReservationBoundary;
import com.ahms.boundary.security.RoomsBoundary;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.Reservation;
import com.ahms.model.entity.Rooms;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rsoto
 */
public class ReservationThread implements Runnable {
    private boolean flag = false;
    private ReservationBoundary reserB;
    private RoomsBoundary roomsB;
    private MultiValueBoundary mmB;
    @Override
    public void run() {
        reserB = new ReservationBoundary();
        roomsB = new RoomsBoundary();
        mmB = new MultiValueBoundary();
        flag =true;
       while (flag){
           try {
               checkReservations();
               Thread.sleep(2000);
           } catch (InterruptedException ex) {
               Logger.getLogger(ReservationThread.class.getName()).log(Level.SEVERE, null, ex);
                flag = false;
           }
       }
    }


    private void checkReservations() {
        System.out.println("print reservations");
        List<Reservation> reservations;
        Date date = new Date();
        MultiValue genStatus = mmB.findByKey(new MultiValue(MMKeys.General.STA_ACTIVO_KEY));
        MultiValue genStatusIn = mmB.findByKey(new MultiValue(MMKeys.General.STA_INACTIVO_KEY));
        reservations = reserB.checkReservations(date, genStatus);
        Rooms room = null;
        System.out.println("Reservaciones encontradas: " + reservations.size());
        for (Reservation reservation : reservations) {
            reservation.setResStatus(genStatusIn);
            System.out.println("actualizando estatus de reservaciones");
            reserB.update(reservation);
            room = reservation.getRmsId();
            List<Reservation> reservationList = reserB.findReservationByRoom(room,genStatus,date);
            if (reservationList == null || reservationList.isEmpty()) {
                room.setRmsStatus(mmB.findByKey(new MultiValue(MMKeys.Rooms.STA_DISPONIBLE_KEY)));
                System.out.println("actualizando estatus de cuartos");
                roomsB.update(room);
            }
            
        }
    }

    public void terminate() {
       this.flag = false;
    }
    
}
