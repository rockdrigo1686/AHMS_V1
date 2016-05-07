/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.ChangeHistory;
import com.ahms.model.manager.entity_manager.ChangeHistoryEM;
import java.util.List;

/**
 *
 * @author rsoto
 */
public class ChangeHistoryBoundary implements AHMSBoundary<ChangeHistory>{
    
    private ChangeHistoryEM changeHistoryEm = null; 

    public ChangeHistoryBoundary() {
        changeHistoryEm = new ChangeHistoryEM();
    }

    @Override
    public List<ChangeHistory> search(ChangeHistory obj) {
        return changeHistoryEm.search(obj);
    }

    @Override
    public List<ChangeHistory> searchAll(ChangeHistory obj) {
       return  changeHistoryEm.searchAll(obj);
    }

    @Override
    public ChangeHistory find(ChangeHistory obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(ChangeHistory obj) {
       return changeHistoryEm .insert(obj);
    }

    @Override
    public int update(ChangeHistory obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(ChangeHistory obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
