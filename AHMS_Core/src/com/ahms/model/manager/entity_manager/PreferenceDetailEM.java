/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.PreferenceDetail;
import com.ahms.model.manager.AHMSEntityManager;
import java.util.List;
import javax.persistence.NoResultException;
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
            query.setParameter("rmsId", obj.getRmsId());
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
