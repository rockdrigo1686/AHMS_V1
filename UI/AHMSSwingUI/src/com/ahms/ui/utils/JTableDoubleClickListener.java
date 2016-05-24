/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui.utils;

import com.ahms.ui.administracion.seguridad.ProfilesFrm;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author rsoto
 */
public class JTableDoubleClickListener {

    public static void addDoubleCLick(MouseEvent e, Object enityBean, List resultList, Map<String, Component> componentMap) {

        if (e.getClickCount() == 2) {
            Object row = null;
            JTable target = (JTable) e.getSource();
            row = resultList.get(target.convertRowIndexToModel(target.getSelectedRow()));

            //mapea campos de la tabla al bean principal y formulario
            for (Field beanField : enityBean.getClass().getDeclaredFields()) {
                try {
                    beanField.setAccessible(true);
                    Field tableField = null;
                    try {
                        tableField = row.getClass().getDeclaredField(beanField.getName());
                        tableField.setAccessible(true);
                        beanField.set(enityBean, tableField.get(row));
                        Component com = componentMap.get(tableField.getName());
                        if (com != null) {
                            if (com instanceof JTextField) {
                                JTextField jTextField = (JTextField) com;
                                if(tableField.get(enityBean) != null){
                                    jTextField.setText(tableField.get(enityBean).toString());
                                }                                
                            }
                            if (com instanceof JComboBox) {
                                JComboBox jComboBox = (JComboBox) com;
                                jComboBox.setSelectedItem(tableField.get(enityBean));                                
                            }
                            
                            if (com instanceof JCheckBox) {
                                JCheckBox jCheckBox = (JCheckBox) com;
                                jCheckBox.setSelected((boolean) tableField.get(enityBean));                                
                            }
                            
                        }
                    } catch (NoSuchFieldException ex) {
                    } catch (SecurityException ex) {
                        Logger.getLogger(JTableDoubleClickListener.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(ProfilesFrm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(ProfilesFrm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
