
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.Account;
import com.ahms.model.entity.Customers;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.manager.AHMSEntityManager;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class AccountEM extends AHMSEntityManager{    
    public Account find(Account account) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<Account> query = em.createNamedQuery("Account.findByActId", Account.class);
            query.setParameter("actId", account.getActId());
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
    
    public List<Account> checkAccount(Customers customers) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<Account> query = em.createNamedQuery("Account.checkAccount", Account.class);
            query.setParameter("cusId", customers);            
            query.setParameter("actStatus1", new MultiValue("ACT_A"));
            query.setParameter("actStatus2", new MultiValue("ACT_P"));                                    
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
    
    public List<Account> findByCusId(Account account) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<Account> query = em.createNamedQuery("Account.findByCusId", Account.class);
            query.setParameter("cusId", account.getCusId());
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
