/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.MoneyMovement;
import com.ahms.model.manager.AHMSEntityManager;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author rsoto
 */
public class MoneyMovementEM extends AHMSEntityManager<MoneyMovement>{

    public List<MoneyMovement> searchByCouId(MoneyMovement obj) {
         try {
            if (em == null) {
                createEm();
            }
            TypedQuery<MoneyMovement> query = em.createNamedQuery("MoneyMovement.findByCouId", MoneyMovement.class);
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
