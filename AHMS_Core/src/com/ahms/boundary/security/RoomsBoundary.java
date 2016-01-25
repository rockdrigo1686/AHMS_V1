/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.Rooms;
import com.ahms.model.manager.entity_manager.RoomsEM;
import java.util.List;

/**
 *
 * @author jorge
 */
public class RoomsBoundary implements AHMSBoundary<Rooms>{
    
     private RoomsEM roomsEm = null;

    public RoomsBoundary() {
        roomsEm = new RoomsEM();
    }


    @Override
    public List<Rooms> search(Rooms obj) {
        return roomsEm.search(obj);
    }

    @Override
    public List<Rooms> searchAll(Rooms obj) {
        return roomsEm.searchAll(obj);
    }

    @Override
    public Rooms find(Rooms obj) {
        return (Rooms)roomsEm.find(obj);
    }

    @Override
    public int insert(Rooms obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Rooms obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Rooms obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
