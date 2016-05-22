package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.ErrorTrace;
import com.ahms.model.manager.entity_manager.AccountEM;
import com.ahms.model.manager.entity_manager.ErrorTraceEM;
import java.util.List;

/**
 *
 * @author jorge
 */
public class ErrorTraceBoundary implements AHMSBoundary<ErrorTrace>{

    private ErrorTraceEM errorTraceEM = null;
    
    public ErrorTraceBoundary() {
        errorTraceEM = new ErrorTraceEM();
    }
    
    @Override
    public List<ErrorTrace> search(ErrorTrace obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ErrorTrace> searchAll(ErrorTrace obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ErrorTrace find(ErrorTrace obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(ErrorTrace obj) {
        return errorTraceEM.insert(obj);
    }

    @Override
    public int update(ErrorTrace obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(ErrorTrace obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
