/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.controler.security;

import com.ahms.boundary.SecurityBoundary;

/**
 *
 * @author rsoto
 */
public class Security {
    
    
    public void login(String usr_code, String usr_pdw){
        SecurityBoundary sb = new SecurityBoundary();
        sb.login(usr_pdw, usr_pdw);
    }
}
