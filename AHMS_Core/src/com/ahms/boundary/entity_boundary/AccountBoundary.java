package com.ahms.boundary.entity_boundary;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.Account;
import com.ahms.model.entity.Customers;
import com.ahms.model.entity.Rooms;
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
    
    public List<Account> findByCusId(Account obj) {
        return accountEM.findByCusId(obj);
    }
    
    @Override
    public int insert(Account obj) {
       return accountEM.insert(obj);
    }

    @Override
    public int update(Account obj) {
        return accountEM.update(obj);
    }

    @Override
    public int delete(Account obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer checkAccount(Customers cusObj) {
        List<Account> list = accountEM.checkAccount(cusObj);
        return list.size();
    }
    
    public Account getActiveAccountByRoom(Rooms room){
        return accountEM.getActiveAccountByRoom(room);
    }

    
    
}
