/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.ServiceTypes;
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
public class ServiceTypesEM extends AHMSEntityManager<ServiceTypes>{
    
    public List<ServiceTypes> findAll() {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<ServiceTypes> query = em.createNamedQuery("ServiceTypes.findAll", ServiceTypes.class);
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

    @Override
    public List<ServiceTypes> search(ServiceTypes servType) {
        try {
            if (this.em == null || !this.em.isOpen() ) {
                this.createEm();
            }
            TypedQuery<ServiceTypes> query;
            Map<String,Object> paramMap = new HashMap<String,Object>();
            StringBuilder sQuery = new StringBuilder("SELECT p FROM ServiceTypes p WHERE 1=1 ");
            if (servType.getSvtCode() != null && !"".equals(servType.getSvtCode())) {
                sQuery.append(" AND p.svtCode =:svtCode");
                paramMap.put("svtCode",servType.getSvtCode());
            }
            if (servType.getSvtDesc()!= null && !"".equals(servType.getSvtDesc())) {
                sQuery.append(" AND LOWER(p.svtDesc) LIKE :svtDesc");
                paramMap.put("svtDesc","%" + servType.getSvtDesc().toLowerCase() + "%" );
            }
            if (servType.getSvtStatus()!= null && servType.getSvtStatus().getMvaKey()!=null) {
                sQuery.append(" AND p.svtStatus = :svtStatus");
                paramMap.put("svtStatus",servType.getSvtStatus());
            }
            System.out.println(sQuery.toString());
            query = this.em.createQuery(sQuery.toString(), (Class<ServiceTypes>) ServiceTypes.class);
            
            paramMap.keySet().stream().forEach((paramName) -> {
                System.out.println(paramMap.get(paramName) +"33");
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
