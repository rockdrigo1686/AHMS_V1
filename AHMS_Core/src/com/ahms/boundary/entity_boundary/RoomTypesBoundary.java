/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.entity_boundary;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.RoomTypes;
import com.ahms.model.manager.entity_manager.RoomTypesEM;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class RoomTypesBoundary implements AHMSBoundary<RoomTypes>{

    private RoomTypesEM roomTypesEM = null;

    public RoomTypesBoundary() {
        roomTypesEM = new RoomTypesEM();
    }
    
    public List<RoomTypes> findActiveTypes(RoomTypes obj) {
        return roomTypesEM.findActiveTypes(obj);
    }
    
    @Override
    public List<RoomTypes> search(RoomTypes obj) {
        try {
            return roomTypesEM.search(obj);
        } catch (Exception ex) {
            Logger.getLogger(RoomTypesBoundary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<RoomTypes> searchAll(RoomTypes obj) {
        return roomTypesEM.searchAll(obj);
    }

    @Override
    public RoomTypes find(RoomTypes obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(RoomTypes obj) {
        return roomTypesEM.insert(obj);
    }

    @Override
    public int update(RoomTypes obj) {
        return roomTypesEM.update(obj);
    }

    @Override
    public int delete(RoomTypes obj) {
        return roomTypesEM.delete(obj);
    }
    
}
