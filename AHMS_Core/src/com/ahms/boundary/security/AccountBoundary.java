package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.Account;
import com.ahms.model.manager.entity_manager.AccountEM;
import java.util.List;

public class AccountBoundary  implements AHMSBoundary<Account>{
    
    private AccountEM accountEM = null;
    
    public AccountBoundary() {
        accountEM = new AccountEM();
    }

    @Override
    public List<Account> search(Account obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Account> searchAll(Account obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Account find(Account obj) {
        return accountEM.find(obj);
    }
    
    public Account findByRms(Account obj) {
        return accountEM.findByRmsId(obj);
    }

    @Override
    public int insert(Account obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Account obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Account obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
