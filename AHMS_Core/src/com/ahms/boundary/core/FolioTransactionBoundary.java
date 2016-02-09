/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.core;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.FolioTransaction;
import com.ahms.model.manager.entity_manager.FolioTransactionEM;
import java.util.List;

/**
 *
 * @author rsoto
 */
public class FolioTransactionBoundary implements AHMSBoundary<FolioTransaction> {

    FolioTransactionEM folioTransactionEM = null;

    @Override
    public List<FolioTransaction> search(FolioTransaction obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<FolioTransaction> searchAll(FolioTransaction obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FolioTransaction find(FolioTransaction obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(FolioTransaction obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(FolioTransaction obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(FolioTransaction obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<FolioTransaction> searchByCouId(FolioTransaction obj) {
        folioTransactionEM = new FolioTransactionEM();
        return folioTransactionEM.searchByCouId(obj);
    }

}
