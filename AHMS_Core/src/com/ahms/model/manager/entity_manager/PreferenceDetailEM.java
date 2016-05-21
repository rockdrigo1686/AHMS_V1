/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.Customers;
import com.ahms.model.entity.PreferenceDetail;
import com.ahms.model.manager.AHMSEntityManager;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author jorge
 */
public class PreferenceDetailEM  extends AHMSEntityManager{
    public PreferenceDetail searchByCusId(PreferenceDetail obj) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<PreferenceDetail> query = em.createNamedQuery("PreferenceDetail.findByCus", PreferenceDetail.class);
            query.setParameter("cusId", obj.getCusId());
            query.setParameter("rtyId", obj.getRtyId());
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
    
    public List<PreferenceDetail> findByCustomer(Customers customer) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(" select * from preference_detail where 1=1 ");
            if(customer != null){sb.append(" and cus_id = ?1 ");}
            Query query = em.createNativeQuery(sb.toString(), PreferenceDetail.class);
            if(customer != null){query.setParameter(1, customer.getCusId());}
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
