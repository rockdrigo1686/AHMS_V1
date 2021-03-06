
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.Account;
import com.ahms.model.entity.Customers;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.Rooms;
import com.ahms.model.manager.AHMSEntityManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class AccountEM extends AHMSEntityManager<Account>{    
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
            query.setParameter("actStatus",new MultiValue("ACT_A"));
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
    
    

    public Account getActiveAccountByRoom(Rooms room) {
           try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            StringBuilder qr = new StringBuilder();
            qr.append("select distinct ac.*, r.rms_id from rooms r ");
            qr.append(" join account_transactions  ae on ae.rms_id = r.rms_id");
            qr.append(" join account ac on ac.act_id = ae.act_id ");
            qr.append(" where r.rms_id = ? and ac.act_status = 'ACT_A'");
            Query query = em.createNativeQuery(qr.toString(), Account.class);
            query.setParameter(1, room.getRmsId());
            return (Account) query.getSingleResult();
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
    public List<Account> findServices(Account account, Date fecIni, Date fecFin) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            StringBuilder sbQuery = new StringBuilder();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            sbQuery.append(" Select DISTINCT t.* from account t join account_transactions a on a.act_id = t.act_id");
            sbQuery.append(" WHERE a.srv_id IS NOT NULL AND a.atr_status = ?1 AND a.atr_dte_mod BETWEEN ?2 AND ?3 ");
            Query query = em.createNativeQuery(sbQuery.toString(), Account.class);
            query.setParameter(1, account.getActStatus().getMvaKey());
          
            query.setParameter(2, df.format(fecIni));
            query.setParameter(3, df.format(fecFin));

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
