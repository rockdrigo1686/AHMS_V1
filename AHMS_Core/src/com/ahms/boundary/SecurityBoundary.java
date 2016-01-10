/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary;

import com.ahms.model.entity.Users;
import com.ahms.model.manager.entity_manager.ModuleEM;
import com.ahms.model.manager.entity_manager.PrivilegesEM;
import com.ahms.model.manager.entity_manager.ProfileEM;
import com.ahms.model.manager.entity_manager.TaskEM;
import com.ahms.model.manager.entity_manager.UserEM;

/**
 *
 * @author rsoto
 */
public class SecurityBoundary {
    
    ProfileEM profileEntityManager;
    UserEM userEntityManager;
    TaskEM taskEntityManager;
    ModuleEM moduleEntityManager;
    PrivilegesEM privilegesEntityManager;
    
    
    //<editor-fold desc="proceso de login" defaultstate="collapsed">
    
    public Users login(String user, String password){
        userEntityManager = new UserEM();
        return userEntityManager.logIn(user, password);
    }
    
    //</editor-fold>
    
    
    
    
}
