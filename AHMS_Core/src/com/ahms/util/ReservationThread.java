/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.util;

import com.ahms.boundary.security.ReservationBoundary;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rsoto
 */
public class ReservationThread implements Runnable {
    private boolean flag = false;
    private ReservationBoundary rBoundary;
    @Override
    public void run() {
        rBoundary = new ReservationBoundary();
        flag =true;
       while (flag){
           try {
               checkReservations();
               Thread.sleep(1000);
           } catch (InterruptedException ex) {
               Logger.getLogger(ReservationThread.class.getName()).log(Level.SEVERE, null, ex);
                flag = false;
           }
       }
    }


    private void checkReservations() {
        System.out.println("print reservations");
//        rBoundary.checkReservations(new Date());
    }

    void terminate() {
       this.flag = false;
    }
    
}
