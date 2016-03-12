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
        return (Floors)floorEM.find(obj);
    }

    @Override
    public int insert(Floors obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Floors obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Floors obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
