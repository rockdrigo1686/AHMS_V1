package com.ahms.model.manager.entity_manager;

import com.ahms.model.entity.AccountService;
import com.ahms.model.manager.AHMSEntityManager;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class AccountServiceEM extends AHMSEntityManager{
    
    public List<AccountService> findByAccount(AccountService accountservice) {
        try {
            if (em == null || !em.isOpen()) {
                createEm();
            }
            TypedQuery<AccountService> query = em.createNamedQuery("Rooms.findByAccount", AccountService.class);
            query.setParameter("actId", accountservice.getActId());
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
    
}
