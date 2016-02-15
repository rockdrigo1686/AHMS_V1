/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.AccountTransactions;
import com.ahms.model.manager.AHMSEntityManager;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author jorge
 */
public class AccountTransactionsEM  extends AHMSEntityManager {    
    public AccountTransactions findByRmsId(AccountTransactions accountTransactions) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<AccountTransactions> query = em.createNamedQuery("AccountTransactions.findByRmsId", AccountTransactions.class);
            query.setParameter("rmsId", accountTransactions.getRmsId());
            return query.getSingleResult();
        } catch (Exception e) {
            if (e instanceof NoResultException) {
                return null;
            } else {
                throw e;
            }
        } finally { 
            if (em != null) {
                closeEm();
            }
        }
    }

    public List<AccountTransactions> findRentsByActId(AccountTransactions accountTransactions) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<AccountTransactions> query = em.createNamedQuery("AccountTransactions.findRentsByActId", AccountTransactions.class);
            query.setParameter("actId", accountTransactions.getActId());
            return query.getResultList();
        } catch (Exception e) {
            if (e instanceof NoResultException) {
                return null;
            } else {
                throw e;
            }
        } finally {
            if (em != null) {
                closeEm();
            }
        }
        
    }
}
