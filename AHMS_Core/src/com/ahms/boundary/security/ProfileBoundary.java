/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.boundary.security;

import com.ahms.boundary.AHMSBoundary;
import com.ahms.model.entity.Profiles;
import com.ahms.model.manager.entity_manager.ProfileEM;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rsoto
 */
public class ProfileBoundary implements AHMSBoundary<Profiles> {

    private ProfileEM profileEm = null;

    public ProfileBoundary() {
        profileEm = new ProfileEM();
    }

    @Override
    public List<Profiles> search(Profiles obj) {
        try {
            return profileEm.search(obj);
        } catch (Exception ex) {
            Logger.getLogger(ProfileBoundary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Profiles> searchAll(Profiles obj) {
        return profileEm.searchAll(obj);
    }

    @Override
    public Profiles find(Profiles obj) {
        try {
            return profileEm.find(obj);
        } catch (Exception ex) {
            Logger.getLogger(ProfileBoundary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int insert(Profiles obj) {
         return profileEm.insert(obj);
    }

    @Override
    public int update(Profiles obj) {
         return profileEm.update(obj);
    }

    @Override
    public int delete(Profiles obj) {
         return profileEm.delete(obj);
    }

}
