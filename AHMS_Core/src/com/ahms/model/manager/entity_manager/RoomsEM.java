/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.Rooms;
import com.ahms.model.manager.AHMSEntityManager;
import com.ahms.util.MMKeys;
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
public class RoomsEM extends AHMSEntityManager {

    public Rooms findByRmsId(Rooms room) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<Rooms> query = em.createNamedQuery("Rooms.findByRmsId", Rooms.class);
            query.setParameter("rmsId", room.getRmsId());
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
    
    public Rooms findByRmsNumber(Rooms room) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<Rooms> query = em.createNamedQuery("Rooms.findByRmsNumber", Rooms.class);
            query.setParameter("rmsNumber", room.getRmsNumber());
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

    public List<Rooms> findByFloor(Rooms rooms) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<Rooms> query = em.createNamedQuery("Rooms.findByFlrId", Rooms.class);
            query.setParameter("flrId", rooms.getFlrId());
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

    public List<Rooms> findByRmsStatus(Rooms rooms) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<Rooms> query = em.createNamedQuery("Rooms.findByRmsStatus", Rooms.class);
            query.setParameter("rmsStatus", rooms.getRmsStatus());
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

    public List<Rooms> findByRmsBeds(Rooms rooms) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<Rooms> query = em.createNamedQuery("Rooms.findByRmsBeds", Rooms.class);
            query.setParameter("rmsBeds", rooms.getRmsBeds());
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

    public List<Rooms> findAvailable(Rooms rooms, Date fecIni, Date fecFin, int numRooms) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("select a.* from rooms a");
            sb.append(" left join reservation r on r.rms_id = a.rms_id and r.res_status = ?3 ");
            sb.append(" where a.rms_beds = ?6 ");
            sb.append(" and a.rms_status = ?4 ");
            sb.append(" or (a.rms_status = ?5 ");
            sb.append(" AND ( (?1 < r.res_fec_fin   AND ?2 < r.res_fec_ini) or  ?1 > r.res_fec_fin) ) LIMIT ?7 ");
            
            Query query = em.createNativeQuery(sb.toString(), Rooms.class);
            query.setParameter(1, fecIni);
            query.setParameter(2, fecFin);
            query.setParameter(3, MMKeys.General.STA_ACTIVO_KEY);
            query.setParameter(4, MMKeys.Rooms.STA_DISPONIBLE_KEY);
            query.setParameter(5, MMKeys.Rooms.STA_RESERVADO_KEY);
            query.setParameter(6, rooms.getRmsBeds().getRtyId());
            query.setParameter(7, numRooms);
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

    public List<Rooms> findAvailableByAmmount(Integer limit) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            Query query = em.createNativeQuery("SELECT r.* FROM rooms r WHERE r.rms_status = '" + MMKeys.Rooms.STA_DISPONIBLE_KEY + "' LIMIT " + limit, Rooms.class);
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

    public List<Rooms> search(Rooms room) {
        try {
            if (this.em == null || !this.em.isOpen()) {
                this.createEm();
            }
            TypedQuery<Rooms> query;
            Map<String, Object> paramMap = new HashMap<String, Object>();
            StringBuilder sQuery = new StringBuilder("SELECT r FROM Rooms r WHERE 1=1 ");
            if (room.getRmsId() != null) {
                sQuery.append(" AND r.rmsNumber =:rmsNumber");
                paramMap.put("rmsNumber", room.getRmsNumber());
            }
            if (room.getFlrId() != null) {
                sQuery.append(" AND r.flrId = :flrId");
                paramMap.put("flrId", room.getFlrId());
            }
            if (room.getRmsStatus() != null && !"".equals(room.getRmsStatus())) {
                sQuery.append(" AND r.rmsStatus = :rmsStatus");
                paramMap.put("rmsStatus", room.getRmsStatus());
            }
            query = this.em.createQuery(sQuery.toString(), (Class<Rooms>) Rooms.class);

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
