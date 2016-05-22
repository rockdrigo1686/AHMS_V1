/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.boundary.security.MultiValueBoundary;
import com.ahms.model.entity.Account;
import com.ahms.model.entity.AccountTransactions;
import com.ahms.model.entity.Customers;
import com.ahms.model.entity.Guests;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.Rooms;
import com.ahms.model.manager.AHMSEntityManager;
import com.ahms.util.MMKeys;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author jorge
 */
public class AccountTransactionsEM extends AHMSEntityManager<AccountTransactions> {

    public AccountTransactions findByRmsId(AccountTransactions accountTransactions) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            Query query = em.createNativeQuery("SELECT a.* FROM account_transactions a WHERE a.rms_id = ?1 LIMIT 1 ", AccountTransactions.class);
            query.setParameter(1, accountTransactions.getRmsId().getRmsId());
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

    public List<AccountTransactions> findAllByRmsId(Rooms room, Account account) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<AccountTransactions> query = em.createNamedQuery("AccountTransactions.findAllByRmsId", AccountTransactions.class);
            query.setParameter("rmsId", room);
            query.setParameter("actId", account);
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

    public List<AccountTransactions> findByCusId(Customers customer) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            StringBuilder sbQuery = new StringBuilder();
            sbQuery.append(" SELECT t.* FROM account a ");
            sbQuery.append(" INNER JOIN account_transactions t ");
            sbQuery.append(" ON a.act_id = t.act_id ");
            sbQuery.append(" WHERE a.cus_id = ?1 AND a.act_status = ?2 ");
            Query query = em.createNativeQuery(sbQuery.toString(), AccountTransactions.class);
            query.setParameter(1, customer.getCusId());
            query.setParameter(2, MMKeys.Acounts.STA_ABIERTO_KEY);
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

    public List<AccountTransactions> findCancelations(AccountTransactions accountTransactions, Date fecIni, Date fecFin) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            StringBuilder sbQuery = new StringBuilder();
            sbQuery.append(" SELECT t.* FROM account_tramsactions a ");
            sbQuery.append(" WHERE a.atrStatus = :atrStatus AND a.atrDteMod BETWEEN :fecIni AND :fecFin ");
            if (accountTransactions != null && accountTransactions.getAtrUsrMod() != null) {
                sbQuery.append(" AND a.atrUsrMod = :atrUser ");
            }
            Query query = em.createNativeQuery(sbQuery.toString(), AccountTransactions.class);
            query.setParameter("atrStatus", accountTransactions.getAtrStatus());
            query.setParameter("fecIni", fecIni);
            query.setParameter("fecFin", fecFin);
            if (accountTransactions != null && accountTransactions.getAtrUsrMod() != null) {
                query.setParameter("atrUser", accountTransactions.getAtrUsrMod());
            }
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

    public List<AccountTransactions> findRented(AccountTransactions accountTransactions, Date fecIni, Date fecFin) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            StringBuilder sbQuery = new StringBuilder();
             sbQuery.append(" SELECT t.* FROM account_tramsactions a ");
            sbQuery.append(" WHERE a.srv_id IS NULL AND a.atrStatus = :atrStatus AND a.atrDteMod BETWEEN :fecIni AND :fecFin ");
            Query query = em.createNativeQuery(sbQuery.toString(), AccountTransactions.class);
            query.setParameter("atrStatus", accountTransactions.getAtrStatus());
            query.setParameter("fecIni", fecIni);
            query.setParameter("fecFin", fecFin);

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
