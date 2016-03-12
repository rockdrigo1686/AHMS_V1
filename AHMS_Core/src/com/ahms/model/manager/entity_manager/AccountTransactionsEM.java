/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.boundary.security.MultiValueBoundary;
import com.ahms.model.entity.AccountTransactions;
import com.ahms.model.entity.Guests;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.Rooms;
import com.ahms.model.manager.AHMSEntityManager;
import com.ahms.util.MMKeys;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author jorge
 */
public class AccountTransactionsEM extends AHMSEntityManager {

    public AccountTransactions findByRmsId(AccountTransactions accountTransactions) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            Query query = em.createNativeQuery("SELECT a.* FROM account_transactions a WHERE a.rms_id = " + accountTransactions.getRmsId().getRmsId().toString() + " LIMIT 1 ", AccountTransactions.class);
            return (AccountTransactions) query.getSingleResult();
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
    
    public List<AccountTransactions> findAllByRmsId(Rooms room) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<AccountTransactions> query = em.createNamedQuery("AccountTransactions.findAllByRmsId", AccountTransactions.class);
            query.setParameter("rmsId", room);
            MultiValueBoundary mlb = new MultiValueBoundary();
            query.setParameter("atrStatus", mlb.findByKey(new MultiValue(MMKeys.AccountsTransactions.STA_PENDIENTE_KEY)));
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

    public Integer updateGuests(List<Guests> mmList, AccountTransactions accountTransactions) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            em.getTransaction().begin();
            this.deletByActId(accountTransactions);
            mmList.stream().forEach((obj) -> {
                em.persist(obj);
            });
            em.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            if (e instanceof NoResultException) {
                if (em != null) {
                    em.getTransaction().rollback();
                }
                return null;
            } else {
                throw e;
            }
        } finally {
            if (em != null && em.isOpen()) {
                closeEm();
            }
        }
    }

    public Integer deletByActId(AccountTransactions obj) {

        try {

            TypedQuery<AccountTransactions> query = em.createNamedQuery("AccountTransactions.deleteByActId", AccountTransactions.class);
            query.setParameter("actId", obj.getActId());
            Integer rslt = query.executeUpdate();
            return rslt;
        } catch (Exception e) {
            if (e instanceof NoResultException) {
                return null;
            } else {
                throw e;
            }
        }
    }
}
