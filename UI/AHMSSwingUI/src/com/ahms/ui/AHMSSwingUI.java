/*
 * To change this license header, choos e License Headers in Project Properties.
 * To change this template file, choose Tools | Templates 
 * and open the template in the editor.    
 */   
package com.ahms.ui; 
       
import javax.swing.UIManager;      
import javax.swing.UnsupportedLookAndFeelException; 
   
/** 
 * 
 * @author rsoto 
 */
public class AHMSSwingUI { 

    /**
     * @param args the command line arguments 
     */  
    public static void main(String[] args) {
        // TODO code application logic here
        LoginFrm login = new LoginFrm();
        login.setVisible(true);

        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            // handle exception
        }
        // handle exception
        // handle exception
        // handle exception
        

    }

}
