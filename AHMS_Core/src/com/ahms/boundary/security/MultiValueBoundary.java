/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.manager.entity_manager.MultiValueEM;
import java.util.List;

/**
 *
 * @author jorge
 */
public class MultiValueBoundary implements AHMSBoundary<MultiValue>{
    
    private MultiValueEM multiValueEM = null;
    
    public MultiValueBoundary() {
        multiValueEM = new MultiValueEM();
    }

    @Override
    public List<MultiValue> search(MultiValue obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MultiValue> searchAll(MultiValue obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MultiValue find(MultiValue obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public MultiValue findByKey(MultiValue obj) {
        return multiValueEM.findByKey(obj);
    }

    @Override
    public int insert(MultiValue obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(MultiValue obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(MultiValue obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
