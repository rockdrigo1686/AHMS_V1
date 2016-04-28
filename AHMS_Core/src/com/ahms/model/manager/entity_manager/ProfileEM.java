/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.Profiles;
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
public class ProfileEM extends AHMSEntityManager<Profiles> {

    @Override
    public List<Profiles> search(Profiles profile) {

        try {
            if (this.em == null || !this.em.isOpen() ) {
                this.createEm();
            }
            TypedQuery<Profiles> query;
            Map<String,Object> paramMap = new HashMap<String,Object>();
            StringBuilder sQuery = new StringBuilder("SELECT p FROM Profiles p WHERE 1=1 ");
            if (profile.getProCode() != null && !"".equals(profile.getProCode())) {
                sQuery.append(" AND p.proCode =:proCode");
                paramMap.put("proCode",profile.getProCode());
            }
            if (profile.getProName() != null && !"".equals(profile.getProName())) {
                sQuery.append(" AND p.proName = :proName");
                paramMap.put("proName",profile.getProName());
            }
            if (profile.getProStatus()!= null && profile.getProStatus().getMvaKey()!=null) {
                sQuery.append(" AND p.proStatus = :proStatus");
                paramMap.put("proStatus",profile.getProStatus());
            }
            query = this.em.createQuery(sQuery.toString(), (Class<Profiles>) Profiles.class);
            
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
