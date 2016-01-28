package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.AccountService;
import com.ahms.model.manager.entity_manager.AccountServiceEM;
import java.util.List;

public class AccountServiceBoundary implements AHMSBoundary<AccountService>{

    private AccountServiceEM accountServiceEM = null;
    
    public AccountServiceBoundary() {
        accountServiceEM = new AccountServiceEM();
    }
    
    public List<AccountService> findByAccount(AccountService obj) {
        return accountServiceEM.findByAccount(obj);
    }
    
    @Override
    public List<AccountService> search(AccountService obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AccountService> searchAll(AccountService obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccountService find(AccountService obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(AccountService obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(AccountService obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(AccountService obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
