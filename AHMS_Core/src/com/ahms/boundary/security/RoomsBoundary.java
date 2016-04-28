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

    public List<Rooms> findByFloor(Rooms room) {
        return roomsEm.findByFloor(room);
    }
    
    public List<Rooms> findByRmsStatus(Rooms room) {
        return roomsEm.findByRmsStatus(room);
    }
    
    public List<Rooms> findByRmsBeds(Rooms room) {
        return roomsEm.findByRmsBeds(room);
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
    
    public Rooms findByRmsId(Rooms obj) {
        return roomsEm.findByRmsId(obj);
    }

    @Override
    public int insert(Rooms obj) {
        return roomsEm.insert(obj);
    }

    @Override
    public int update(Rooms obj) {
        return roomsEm.update(obj);
    }

    @Override
    public int delete(Rooms obj) {
        return roomsEm.delete(obj);
    }
    
     public List<Rooms> findAvailableByAmmount(Integer limit) {
          return roomsEm.findAvailableByAmmount(limit);
     }
    
}
