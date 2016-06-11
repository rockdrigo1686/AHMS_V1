/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager;

import com.ahms.model.entity.MultiValue;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public List<T> search(T obj) throws Exception {
        try {
            if (this.em == null || !this.em.isOpen()) {
                this.createEm();
            }

            Package pack = obj.getClass().getPackage();
            String className = pack.getName() + "." + obj.getClass().getSimpleName();
            Class<?> klass = Class.forName(className);
            TypedQuery<?> query;
            Map<String, Object> paramMap = new HashMap<String, Object>();
            StringBuilder sQuery = new StringBuilder("SELECT t FROM ");
            sQuery.append(klass.getName() + " t WHERE 1=1 ");
            for (Field f : klass.getDeclaredFields()) {
                f.setAccessible(true);
                for (Annotation a : f.getAnnotations()) {
                    if ("javax.persistence.Column".equals(a.annotationType().getName()) || "javax.persistence.JoinColumn".equals(a.annotationType().getName())) {
                        if (f.get(obj) != null) {
                            if (f.get(obj) instanceof MultiValue) {
                                MultiValue mv = (MultiValue) f.get(obj);
                                if (mv.getMvaKey() == null) {
                                  continue;
                                }
                            }
                            sQuery.append(" AND t.").append(f.getName()).append("= :").append(f.getName());
                            paramMap.put(f.getName(), f.get(obj));

                        }

                    }
                }
            }

            query = this.em.createQuery(sQuery.toString(), klass.getClass());

            paramMap.keySet().stream().forEach((paramName) -> {
                query.setParameter(paramName, paramMap.get(paramName));
            });

            return (List<T>) query.getResultList();

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

    public T find(T obj) throws Exception {
        try {

            if (this.em == null || !this.em.isOpen()) {
                this.createEm();
            }
            Field idField = null;
            Package pack = obj.getClass().getPackage();
            boolean found = false;
            String className = pack.getName() + "." + obj.getClass().getSimpleName();
            Class<?> klass = Class.forName(className);

            for (Field f : klass.getDeclaredFields()) {
                for (Annotation a : f.getAnnotations()) {
                    if ("@javax.persistence.Id()".equals(a.toString())) {
                        found = !found;
                        break;
                    }
                }
                if (found) {
                    idField = f;
                    break;
                }
            }
            String idName = idField.getName().substring(0, 1).toUpperCase() + idField.getName().substring(1);
            TypedQuery<T> query;
            query = em.createNamedQuery(obj.getClass().getSimpleName() + ".findBy" + idName, (Class<T>) Object.class);
            query.setParameter(idField.getName(), entityManagerFactory.getPersistenceUnitUtil().getIdentifier(obj));
            return query.getSingleResult();

        } catch (ClassNotFoundException | SecurityException e) {
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
