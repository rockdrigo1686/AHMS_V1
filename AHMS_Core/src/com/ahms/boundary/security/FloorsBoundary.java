/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.Floors;
import com.ahms.model.manager.entity_manager.FloorEM;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jorge
 */
public class FloorsBoundary implements AHMSBoundary<Floors>{
    
    private FloorEM floorEM = null;
    
    public FloorsBoundary() {
        floorEM = new FloorEM();
    }

    @Override
    public List<Floors> search(Floors obj) {
        return floorEM.searchAll(obj);
    }
    
    public List<Floors> searchByFloor(Floors obj) {
        return floorEM.searchAll(obj);
    }

    @Override
    public List<Floors> searchAll(Floors obj) {
        return floorEM.searchAll(obj);
    }

    public List<Floors> findAllAvailable() {
        return floorEM.findAllAvailable();
    }
    
    @Override
    public Floors find(Floors obj) {
        try {
            return (Floors)floorEM.find(obj);
        } catch (Exception ex) {
            Logger.getLogger(FloorsBoundary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int insert(Floors obj) {
        return floorEM.insert(obj);
    }

    @Override
    public int update(Floors obj) {
        return floorEM.update(obj);
    }

    @Override
    public int delete(Floors obj) {
        return floorEM.delete(obj);
    }
    
}
