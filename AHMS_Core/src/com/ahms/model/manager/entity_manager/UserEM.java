/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.Users;
import com.ahms.model.manager.AHMSEntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author rsoto
 */
public class UserEM extends AHMSEntityManager {

    public Users logIn(String user, String password) {
        try {
            if (em == null) {
                createEm();
            }
            TypedQuery<Users> query = em.createNamedQuery("Users.login", Users.class);
            query.setParameter("usrCode", user);
            query.setParameter("usrPwd", password);
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
