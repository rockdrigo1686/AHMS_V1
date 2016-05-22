/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.ChangeHistory;
import com.ahms.model.manager.AHMSEntityManager;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author rsoto
 */
public class ChangeHistoryEM extends AHMSEntityManager<ChangeHistory> {

    @Override
    public List<ChangeHistory> search(ChangeHistory ch) {
        try {
            if (this.em == null || !this.em.isOpen()) {
                this.createEm();
            }
            TypedQuery<ChangeHistory> query;
            Map<String, Object> paramMap = new HashMap<String, Object>();
            StringBuilder sQuery = new StringBuilder("SELECT p FROM ChangeHistory p WHERE 1=1 ");
            if (ch.getChaUsrCode() != null && !"".equals(ch.getChaUsrCode())) {
                sQuery.append(" AND LOWER(p.chaUsrCode) LIKE :chaUsrCode");
                paramMap.put("chaUsrCode", "%" + ch.getChaUsrCode().toLowerCase() + "%");
            }
            if (ch.getChaUsrAutCode() != null && !"".equals(ch.getChaUsrAutCode())) {
                sQuery.append(" AND LOWER(p.chaUsrAutCode) LIKE :chaUsrAutCode");
                paramMap.put("chaUsrAutCode", "%" + ch.getChaUsrAutCode().toLowerCase() + "%");
            }
            if (ch.getChaDateAux() != null) {

                sQuery.append(" AND p.chaDate BETWEEN :chaDate AND :chaDateAux ");
                paramMap.put("chaDate", ch.getChaDate());
                paramMap.put("chaDateAux", ch.getChaDateAux());
            } else if (ch.getChaDate() !=null) {
                sQuery.append(" AND p.chaDate = :chaDate  ");
                paramMap.put("chaDate", ch.getChaDate());
            }

            System.out.println(sQuery.toString());
            query = this.em.createQuery(sQuery.toString(), (Class<ChangeHistory>) ChangeHistory.class);

            paramMap.keySet().stream().forEach((paramName) -> {

                query.setParameter(paramName, paramMap.get(paramName));
            });

            return query.getResultList();

        } catch (Exception e) {
            if (e instanceof NoResultException) {
                return null;
            } else {
                throw e;
            }
        } finally {
            if (this.em != null) {
                this.closeEm();
            }
        }
    }
    
    public List<ChangeHistory> findMovements(ChangeHistory changeH, Date fecIni, Date fecFin) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            StringBuilder sbQuery = new StringBuilder();
            sbQuery.append(" SELECT t.* FROM change_history a ");
            sbQuery.append(" WHERE a.chaDate BETWEEN :fecIni AND :fecFin ");
            if (changeH != null && changeH.getChaUsrAut()!= null) {
                sbQuery.append(" AND a.chaUsrAut = :atrUser ");
            }
            Query query = em.createNativeQuery(sbQuery.toString(), ChangeHistory.class);
            query.setParameter("fecIni", fecIni);
            query.setParameter("fecFin", fecFin);
            if (changeH != null && changeH.getChaUsrAut() != null) {
                query.setParameter("atrUser", changeH.getChaUsrAut());
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

}
