/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author rsoto
 */
public class AHMSEntityManager {

    private final String PU_NAME = "AHMSPU";
    protected EntityManagerFactory entityManagerFactory = null;
    protected EntityManager em = null;
    protected EntityTransaction userTransaction = null;

    public void insert(Object obj) {
        try {
            if (this.em == null) {
                this.createEm();
            }
            userTransaction = em.getTransaction();
            userTransaction.begin();
            //insertcode
            em.persist(obj);
            userTransaction.commit();
        } catch (Exception e) {
        } finally {
            if (this.em != null) {
                this.closeEm();
            }

        }

    }

    public void update(Object obj) {
        try {
            if (this.em == null) {
                this.createEm();
            }
            em.merge(obj);
            userTransaction = em.getTransaction();
            userTransaction.begin();
            userTransaction.commit();
        } catch (Exception e) {
        } finally {
            if (this.em != null) {
                this.closeEm();
            }

        }
    }

    public void delete(Object obj) {
        try {
            if (this.em == null) {
                this.createEm();
            }
            userTransaction = em.getTransaction();
            userTransaction.begin();
            em.remove(em.find(obj.getClass(), entityManagerFactory.getPersistenceUnitUtil().getIdentifier(obj)));
            userTransaction.commit();
        } catch (Exception e) {
        } finally {
            if (this.em != null) {
                this.closeEm();
            }

        }
    }

    protected void createEm() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PU_NAME);
        em = entityManagerFactory.createEntityManager();
    }

    protected void closeEm() {
        entityManagerFactory.close();
    }

}
