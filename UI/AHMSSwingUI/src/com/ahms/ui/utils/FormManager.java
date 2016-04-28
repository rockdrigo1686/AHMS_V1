/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui.utils;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author rsoto
 */
public class FormManager {

    private Map<String, Component> buttonMap = null;
    private Map<String, Component> formComponentMap = null;

    public FormManager(Map<String, Component> buttonMap, Map<String, Component> formComponentMap) {
        this.buttonMap = buttonMap;
        this.formComponentMap = formComponentMap;
    }

    public FormManager() {

    }

    public void createComponentMaps(JFrame frame) {
        if (buttonMap == null) {
            buttonMap = new HashMap<String, Component>();
        }
        if (formComponentMap == null) {
            formComponentMap = new HashMap<String, Component>();
        }

        for (Component com : frame.getContentPane().getComponents()) {
            if (com.getName() != null) {
                if (com instanceof JToolBar) {
                    for (Component jTbChild : ((JToolBar) com).getComponents()) {
                        if (jTbChild instanceof JButton) {
                            buttonMap.put(jTbChild.getName(), jTbChild);
                        }
                    }
                } else if (com instanceof JScrollPane) {
                   for (Component jSpChild : ((JScrollPane) com).getComponents()) {
                        if (jSpChild instanceof JTable) {
                            buttonMap.put(jSpChild.getName(), jSpChild);
                        }
                    }
                } else {
                    formComponentMap.put(com.getName(), com);
                }
            }
        }
    }
    
    public void createComponentMaps(JDialog frame) {
        if (buttonMap == null) {
            buttonMap = new HashMap<String, Component>();
        }
        if (formComponentMap == null) {
            formComponentMap = new HashMap<String, Component>();
        }

        for (Component com : frame.getContentPane().getComponents()) {
            if (com.getName() != null) {
                if (com instanceof JToolBar) {
                    for (Component jTbChild : ((JToolBar) com).getComponents()) {
                        if (jTbChild instanceof JButton) {
                            buttonMap.put(jTbChild.getName(), jTbChild);
                        }
                    }
                } else if (com instanceof JScrollPane) {
                   for (Component jSpChild : ((JScrollPane) com).getComponents()) {
                        if (jSpChild instanceof JTable) {
                            buttonMap.put(jSpChild.getName(), jSpChild);
                        }
                    }
                } else {
                    formComponentMap.put(com.getName(), com);
                }
            }
        }
    }

    public void setDefaultFormStatus() {
        Component comp = null;
        //Barra de botones
        for (String compName : buttonMap.keySet()) {
            if (UIConstants.BTN_LIMPIAR.equalsIgnoreCase(compName) || UIConstants.BTN_BUSCAR.equalsIgnoreCase(compName) || UIConstants.BTN_NUEVO.equalsIgnoreCase(compName)) {
                comp = buttonMap.get(compName);
                comp.setEnabled(true);
                if (comp != null) {
                    comp.setEnabled(true);
                }
            } else if (UIConstants.BTN_EDITAR.equalsIgnoreCase(compName) || UIConstants.BTN_GUARDAR.equalsIgnoreCase(compName) || UIConstants.BTN_ELIMINAR.equalsIgnoreCase(compName)) {
                comp = buttonMap.get(compName);
                if (comp != null) {
                    comp.setEnabled(false);
                }
            }
        }
        //formulario
//        formComponentMap.values().stream().forEach((com) -> {
            for (Object com : formComponentMap.values()) {
                
            if (com instanceof JTextField) {
                JTextField jTextField = (JTextField) com;
                jTextField.setText("");
                jTextField.setEnabled(true);
            } else if (com instanceof JComboBox) {
                JComboBox jComboBox = (JComboBox) com;
                jComboBox.setSelectedIndex(0);
                jComboBox.setEnabled(true);
            } else if (com instanceof JTable) {
                JTable jTable = (JTable) com;
                jTable.setEnabled(true);
            }
        }
    }

    public void setEditFormStatus() {
        formComponentMap.values().stream().forEach((com) -> {
            if (com instanceof JTextField) {
                JTextField jTextField = (JTextField) com;
                jTextField.setEnabled(true);
            } else if (com instanceof JComboBox) {
                JComboBox jComboBox = (JComboBox) com;
                jComboBox.setEnabled(true);
            } else if (com instanceof JTable) {
                JTable jTable = (JTable) com;
                jTable.setEnabled(false);
            }
        });
    }

    public void lockForm() {
        formComponentMap.values().stream().forEach((com) -> {
            if (com instanceof JTextField) {
                JTextField jTextField = (JTextField) com;
                jTextField.setEnabled(false);
            } else if (com instanceof JComboBox) {
                JComboBox jComboBox = (JComboBox) com;
                jComboBox.setEnabled(false);
            } else if (com instanceof JTable) {
                JTable jTable = (JTable) com;
                jTable.setEnabled(true);
            }
        });
    }

    public void updateButtonMenuState(String btnName) {
        Component comp = null;
        switch (btnName) {
            case UIConstants.BTN_LIMPIAR:
            case UIConstants.BTN_BUSCAR:
            case UIConstants.BTN_GUARDAR:
            case UIConstants.BTN_NUEVO:
            case UIConstants.BTN_ELIMINAR:
                for (String compName : buttonMap.keySet()) {
                    if (UIConstants.BTN_LIMPIAR.equalsIgnoreCase(compName) || UIConstants.BTN_BUSCAR.equalsIgnoreCase(compName) || UIConstants.BTN_NUEVO.equalsIgnoreCase(compName)) {
                        comp = buttonMap.get(compName);
                        comp.setEnabled(true);
                        if (comp != null) {
                            comp.setEnabled(true);
                        }
                    } else if (UIConstants.BTN_EDITAR.equalsIgnoreCase(compName) || UIConstants.BTN_GUARDAR.equalsIgnoreCase(compName) || UIConstants.BTN_ELIMINAR.equalsIgnoreCase(compName)) {
                        comp = buttonMap.get(compName);
                        if (comp != null) {
                            comp.setEnabled(false);
                        }
                    }
                }
                break;
            case UIConstants.BTN_EDITAR:
                for (String compName : buttonMap.keySet()) {
                    if (UIConstants.BTN_LIMPIAR.equalsIgnoreCase(compName) || UIConstants.BTN_GUARDAR.equalsIgnoreCase(compName)) {
                        comp = buttonMap.get(compName);
                        if (comp != null) {
                            comp.setEnabled(true);
                        }
                    } else if (UIConstants.BTN_EDITAR.equalsIgnoreCase(compName) || UIConstants.BTN_NUEVO.equalsIgnoreCase(compName)
                            || UIConstants.BTN_ELIMINAR.equalsIgnoreCase(compName) || UIConstants.BTN_BUSCAR.equalsIgnoreCase(compName)) {
                        comp = buttonMap.get(compName);
                        if (comp != null) {
                            comp.setEnabled(false);
                        }
                    }
                }
                setEditFormStatus();
                break;
            case UIConstants.DOUBLE_CLICK:
                for (String compName : buttonMap.keySet()) {
                    if (UIConstants.BTN_ELIMINAR.equalsIgnoreCase(compName) || UIConstants.BTN_NUEVO.equalsIgnoreCase(compName)
                            || UIConstants.BTN_EDITAR.equalsIgnoreCase(compName) || UIConstants.BTN_LIMPIAR.equalsIgnoreCase(compName)
                            || UIConstants.BTN_BUSCAR.equalsIgnoreCase(compName)) {
                        comp = buttonMap.get(compName);
                        if (comp != null) {
                            comp.setEnabled(true);
                        }
                    } else if (UIConstants.BTN_GUARDAR.equalsIgnoreCase(compName)) {
                        comp = buttonMap.get(compName);
                        if (comp != null) {
                            comp.setEnabled(false);
                        }
                    }
                }
                lockForm();
                break;

        }

    }

    public Map<String, Component> getButtonMap() {
        return buttonMap;
    }

    public void setButtonMap(Map<String, Component> buttonMap) {
        this.buttonMap = buttonMap;
    }

    public Map<String, Component> getFormComponentMap() {
        return formComponentMap;
    }

    public void setFormComponentMap(Map<String, Component> formComponentMap) {
        this.formComponentMap = formComponentMap;
    }

}
