/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.entity_boundary;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.Users;
import com.ahms.model.manager.entity_manager.UserEM;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rsoto
 */
public class UsersBoundary implements AHMSBoundary<Users>{

    private UserEM userEM =null;

    public UsersBoundary() {
        userEM = new UserEM();
    }
    
    
    @Override
    public List<Users> search(Users obj) {
        return userEM.search(obj);
    }

    @Override
    public List<Users> searchAll(Users obj) {
        return userEM.searchAll(obj);
    }

    @Override
    public Users find(Users obj) {
        try {
            return  userEM.find(obj);
        } catch (Exception ex) {
            Logger.getLogger(UsersBoundary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int insert(Users obj) {
        return userEM.insert(obj);
    }

    @Override
    public int update(Users obj) {
        return userEM.update(obj);
    }

    @Override
    public int delete(Users obj) {
        return userEM.delete(obj);
    }
    
}
