/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.CashOut;
import com.ahms.model.entity.MoneyMovement;
import com.ahms.model.manager.AHMSEntityManager;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author rsoto
 */
public class MoneyMovementEM extends AHMSEntityManager<MoneyMovement> {

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

    public Integer deletByCouId(CashOut obj) {

        try {
          
            TypedQuery<MoneyMovement> query = em.createNamedQuery("MoneyMovement.deleteByCouId", MoneyMovement.class);
            query.setParameter("couId", obj);
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

    public Integer update(List<MoneyMovement> mmList) {
        try {
            if (em == null) {
                createEm();
            }
            em.getTransaction().begin();
            this.deletByCouId(mmList.get(0).getCouId());
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
            if (em != null || em.isOpen()) {
                closeEm();
            }
        }
    }

}
