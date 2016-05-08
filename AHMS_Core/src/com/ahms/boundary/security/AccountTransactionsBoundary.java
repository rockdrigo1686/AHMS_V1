package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.Account;
import com.ahms.model.entity.AccountTransactions;
import com.ahms.model.entity.Customers;
import com.ahms.model.entity.Guests;
import com.ahms.model.entity.Rooms;
import com.ahms.model.manager.entity_manager.AccountTransactionsEM;
import java.util.List;

/**
 *
 * @author jorge
 */
public class AccountTransactionsBoundary implements AHMSBoundary<AccountTransactions> {

     private AccountTransactionsEM accountTransactionsEM = null;
    
    public AccountTransactionsBoundary() {
        accountTransactionsEM = new AccountTransactionsEM();
    }
    
    public AccountTransactions findByRmsId(AccountTransactions obj) {
        return accountTransactionsEM.findByRmsId(obj);
    }
    
    @Override
    public List<AccountTransactions> search(AccountTransactions obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AccountTransactions> searchAll(AccountTransactions obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccountTransactions find(AccountTransactions obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<AccountTransactions> findByCusId(Customers customer) {
        return accountTransactionsEM.findByCusId(customer);
    }
    
    public List<AccountTransactions> findAllByRmsId(Rooms room, Account account) {
        return accountTransactionsEM.findAllByRmsId(room, account);
    }

    @Override
    public int insert(AccountTransactions obj) {
        return accountTransactionsEM.insert(obj);
    }

    @Override
    public int update(AccountTransactions obj) {
        return accountTransactionsEM.update(obj);
    }

    @Override
    public int delete(AccountTransactions obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<AccountTransactions> findRentsByActId(AccountTransactions obj) {
        return accountTransactionsEM.findRentsByActId(obj);
    }
    
    public Integer updateGuests(List<Guests> guestsList, AccountTransactions obj) {
        return accountTransactionsEM.updateGuests(guestsList,obj);
    }
}
