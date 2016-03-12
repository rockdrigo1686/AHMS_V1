/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.CashOut;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.manager.AHMSEntityManager;
import com.ahms.util.MMKeys;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author rsoto
 */
public class CashOutEM extends AHMSEntityManager {

    public CashOut getCurrentShift() {
        try {
            if (em == null) {
                createEm();
            }
            TypedQuery<CashOut> query = em.createNamedQuery("CashOut.findByCouStatus", CashOut.class);
            query.setParameter("couStatus", new MultiValue(MMKeys.Shift.STA_ABIERTO_KEY));
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
}
