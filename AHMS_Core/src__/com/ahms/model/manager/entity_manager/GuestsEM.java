/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.Account;
import com.ahms.model.entity.AccountTransactions;
import com.ahms.model.entity.Guests;
import com.ahms.model.manager.AHMSEntityManager;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Marcos
 */
public class GuestsEM extends AHMSEntityManager {
    public List<Guests> findByAtrId(AccountTransactions accountTransactions) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<Guests> query = em.createNamedQuery("Guests.findByAtrId", Guests.class);
            query.setParameter("atrId", accountTransactions);
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
