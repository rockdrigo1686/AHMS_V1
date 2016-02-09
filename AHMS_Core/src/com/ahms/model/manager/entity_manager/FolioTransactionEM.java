/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.FolioTransaction;
import com.ahms.model.manager.AHMSEntityManager;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author rsoto
 */
public class FolioTransactionEM extends AHMSEntityManager<FolioTransaction>{
    
    public List<FolioTransaction> searchByCouId(FolioTransaction obj) {
         try {
            if (em == null) {
                createEm();
            }
            TypedQuery<FolioTransaction> query = em.createNamedQuery("FolioTransaction.findByCouId", FolioTransaction.class);
            query.setParameter("couId", obj.getCouId());
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
