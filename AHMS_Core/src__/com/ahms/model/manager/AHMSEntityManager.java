/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author rsoto
 * @param <T>
 */
public class AHMSEntityManager<T> {

    private final String PU_NAME = "AHMSPU";
    protected EntityManagerFactory entityManagerFactory = null;
    protected EntityManager em = null;
    protected EntityTransaction userTransaction = null;

    protected void createEm() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PU_NAME);
        em = entityManagerFactory.createEntityManager();
    }

    protected void closeEm() {
        em.close();
    }

    public List<T> search(T obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<T> searchAll(T obj) {
        try {
            if (this.em == null || !this.em.isOpen()) {
                this.createEm();
            }
            TypedQuery<T> query;
            query = em.createNamedQuery(obj.getClass().getSimpleName() + ".findAll", (Class<T>) Object.class);
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

    public T find(T obj) {
        try {
            if (this.em == null || !this.em.isOpen()) {
                this.createEm();
            }
            TypedQuery<T> query;
            query = em.createNamedQuery(obj.getClass().getSimpleName() + ".find", (Class<T>) Object.class);
            query.setParameter("id", entityManagerFactory.getPersistenceUnitUtil().getIdentifier(obj));
            return query.getSingleResult();

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

    public int insert(T obj) {
        try {
            if (this.em == null || !this.em.isOpen()) {
                this.createEm();
            }
            userTransaction = em.getTransaction();
            userTransaction.begin();
            //insertcode
            em.persist(obj);
            userTransaction.commit();
            return 1;
        } catch (Exception e) {
            throw e;
        } finally {
            if (this.em != null) {
                this.closeEm();
            }

        }
    }

    public int update(T obj) {
        try {
            if (this.em == null || !this.em.isOpen()) {
                this.createEm();
            }
            em.merge(obj);
            userTransaction = em.getTransaction();
            userTransaction.begin();
            userTransaction.commit();
            return 1;
        } catch (Exception e) {
            throw e;
        } finally {
            if (this.em != null) {
                this.closeEm();
            }

        }
    }

    public int delete(T obj) {
        try {
            if (this.em == null || !this.em.isOpen()) {
                this.createEm();
            }
            userTransaction = em.getTransaction();
            userTransaction.begin();
            em.remove(em.find(obj.getClass(), entityManagerFactory.getPersistenceUnitUtil().getIdentifier(obj)));
            userTransaction.commit();
            return 1;
        } catch (Exception e) {
            throw e;
        } finally {
            if (this.em != null) {
                this.closeEm();
            }

        }
    }
}
