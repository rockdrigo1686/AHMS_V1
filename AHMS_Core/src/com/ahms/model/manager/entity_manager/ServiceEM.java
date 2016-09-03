/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.ServiceTypes;
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
public class ServiceEM extends AHMSEntityManager<Services>{
    public List<Services> findAllByServiceType(ServiceTypes type) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<Services> query = em.createNamedQuery("Services.findAllByServiceType", Services.class);
            query.setParameter("svtId", type);
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
    public Services findByCode(String srvCode) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<Services> query = em.createNamedQuery("Services.findBySrvCode", Services.class);
            query.setParameter("srvCode", srvCode);
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

//    @Override
//    public List<Services> search(Services serv) {
//          try {
//            if (this.em == null || !this.em.isOpen() ) {
//                this.createEm();
//            }
//            TypedQuery<Services> query;
//            Map<String,Object> paramMap = new HashMap<String,Object>();
//            StringBuilder sQuery = new StringBuilder("SELECT p FROM Services p WHERE 1=1 ");
//            if (serv.getSrvCode()!= null && !"".equals(serv.getSrvCode())) {
//                sQuery.append(" AND p.svrCode =:svrCode");
//                paramMap.put("svrCode",serv.getSrvCode());
//            }
//            if (serv.getSrvDesc()!= null && !"".equals(serv.getSrvDesc())) {
//                sQuery.append(" AND LOWER(p.srvDesc) LIKE :srvDesc");
//                paramMap.put("srvDesc","%" + serv.getSrvDesc().toLowerCase() + "%" );
//            }
//            if (serv.getSrvStatus()!= null && serv.getSrvStatus().getMvaKey()!=null) {
//                sQuery.append(" AND p.srvStatus = :srvStatus");
//                paramMap.put("srvStatus",serv.getSrvStatus());
//            }
//            if (serv.getSvtId()!= null && serv.getSvtId().getSvtId()!=null) {
//                sQuery.append(" AND p.svtId = :svtId");
//                paramMap.put("svtId",serv.getSvtId());
//            }
//            System.out.println(sQuery.toString());
//            query = this.em.createQuery(sQuery.toString(), (Class<Services>) Services.class);
//            
//            paramMap.keySet().stream().forEach((paramName) -> {
//                System.out.println(paramMap.get(paramName) +"33");
//                query.setParameter(paramName, paramMap.get(paramName));
//            });
//            
//            return query.getResultList();
//
//        } catch (Exception e) {
//            if (e instanceof NoResultException) {
//                return null;
//            } else {
//                throw e;
//            }
//        } finally {
//            if (this.em != null) {
//                this.closeEm();
//            }
//        }
//    } 
        
}
