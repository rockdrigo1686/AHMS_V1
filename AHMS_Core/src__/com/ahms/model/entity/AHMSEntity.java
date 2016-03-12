/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.entity;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rsoto
 */
public class AHMSEntity {
    
    public void resetProperties(){
        for(Field field : this.getClass().getDeclaredFields()){
            try {
                field.setAccessible(true);
                field.set(this, null);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(AHMSEntity.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(AHMSEntity.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
