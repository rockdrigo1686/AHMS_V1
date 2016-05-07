/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.ChangeHistory;
import com.ahms.model.entity.Services;
import com.ahms.model.manager.AHMSEntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
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

}
