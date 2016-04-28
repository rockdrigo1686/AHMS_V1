/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.Users;
import com.ahms.model.manager.entity_manager.UserEM;
import java.util.List;

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
        return  userEM.find(obj);
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
