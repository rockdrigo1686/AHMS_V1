/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.Users;
import com.ahms.model.manager.AHMSEntityManager;
import com.ahms.util.MMKeys;
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
public class UserEM extends AHMSEntityManager<Users> {

    public Users logIn(String user, String password) {
        try {
            if (em == null) {
                createEm();
            }
            TypedQuery<Users> query = em.createNamedQuery("Users.login", Users.class);
            query.setParameter("usrCode", user);
            query.setParameter("usrPwd", password);
            query.setParameter("usrStatus", new MultiValue(MMKeys.General.STA_ACTIVO_KEY));
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
    
    public Users findByPassword(String password){
         try {
            if (em == null) {
                createEm();
            }
           StringBuilder sbQuery = new StringBuilder();
            sbQuery.append(" SELECT a.* FROM users a ");
            sbQuery.append(" JOIN profiles p on p.pro_id = a.pro_id ");
            sbQuery.append(" WHERE u.usr_status = ?1 and u.usr_pwd = ?2 and (p.pro_code = ?3 OR p.pro_code = ?4)");
           int i =1;
            Query query = em.createNativeQuery(sbQuery.toString(), Users.class);
            query.setParameter(i++, MMKeys.General.STA_ACTIVO_KEY);
            query.setParameter(i++, password);
            query.setParameter(i++, MMKeys.Profiles.ADMI);
            query.setParameter(i++, MMKeys.Profiles.MNGR);
           
            return (Users) query.getSingleResult();
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
    public List<Users> search(Users user) {

        try {
            if (this.em == null || !this.em.isOpen() ) {
                this.createEm();
            }
            TypedQuery<Users> query;
            Map<String,Object> paramMap = new HashMap<String,Object>();
            StringBuilder sQuery = new StringBuilder("SELECT p FROM Users p WHERE 1=1 ");
            if (user.getUsrCode()!= null && !"".equals(user.getUsrCode())) {
                sQuery.append(" AND p.usrCode =:usrCode");
                paramMap.put("usrCode",user.getUsrCode());
            }
            if (user.getProId()!= null && !"".equals(user.getProId())) {
                sQuery.append(" AND p.proId = :proId");
                paramMap.put("proId",user.getProId().getProId());
            }
            if (user.getUsrName()!= null && !"".equals(user.getUsrName())) {
                sQuery.append(" AND p.usrName = :usrName");
                paramMap.put("usrName",user.getUsrName());
            }
            if (user.getUsrLst1()!= null && !"".equals(user.getUsrLst1())) {
                sQuery.append(" AND p.usrLst1 = :usrLst1");
                paramMap.put("usrLst1",user.getUsrLst1());
            }
            if (user.getUsrName()!= null && !"".equals(user.getUsrName())) {
                sQuery.append(" AND p.usrLst2 = :usrLst2");
                paramMap.put("usrLst2",user.getUsrName());
            }
            if (user.getUsrStatus()!= null && user.getUsrStatus().getMvaKey()!=null) {
                sQuery.append(" AND p.usrStatus = :usrStatus");
                paramMap.put("usrStatus",user.getUsrStatus().getMvaKey());
            }
            query = this.em.createQuery(sQuery.toString(), (Class<Users>) Users.class);
            
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
