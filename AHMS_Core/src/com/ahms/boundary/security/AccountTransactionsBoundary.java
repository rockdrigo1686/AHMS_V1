package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.AccountTransactions;
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

    @Override
    public int insert(AccountTransactions obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(AccountTransactions obj) {
        return accountTransactionsEM.update(obj);
    }

    @Override
    public int delete(AccountTransactions obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
