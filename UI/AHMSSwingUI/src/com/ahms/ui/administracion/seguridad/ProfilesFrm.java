/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui.administracion.seguridad;

import com.ahms.boundary.entity_boundary.MultiValueBoundary;
import com.ahms.boundary.entity_boundary.ProfileBoundary;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.Profiles;
import com.ahms.model.entity.Users;
import com.ahms.ui.MainFrm;
import com.ahms.ui.utils.JTableDoubleClickListener;
import com.ahms.ui.utils.FormManager;
import com.ahms.ui.utils.UIConstants;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rsoto
 */
public class ProfilesFrm extends javax.swing.JFrame {

    private List<Profiles> resultList = null;
    private ProfileBoundary profileBoundary = null;
    private MultiValueBoundary MMBoundary = null;
    private DefaultTableModel tableModel = null;
    private Map<String, Component> formComponentMap = new HashMap<String, Component>();
    private Map<String, Component> buttonMap = new HashMap<String, Component>();
    private FormManager formManager = null;
    private MainFrm topFrame;

    /**
     * Creates new form ProfilesFrm
     */
    public ProfilesFrm() {

        initComponents();
        topFrame = (MainFrm) SwingUtilities.getWindowAncestor(this);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Profiles");
        profileBoundary = new ProfileBoundary();
        MMBoundary = new MultiValueBoundary();
        resultList = searchAll();
        //Creamos mapa de componentes
        proStatus.addItem(new MultiValue());
        for (MultiValue obj : MMBoundary.findByType(new MultiValue(null, null, "GRL", null, null, null))) {
            proStatus.addItem(obj);
        }

        formManager = new FormManager();
        formManager.createComponentMaps(this);
        formManager.setDefaultFormStatus();
        fillTable(resultTable);

        resultTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTableDoubleClickListener.addDoubleCLick(e, profile, resultList, formManager.getFormComponentMap());
                if (e.getClickCount() == 2) {
                    formManager.updateButtonMenuState(UIConstants.DOUBLE_CLICK);
                }
            }
        });
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    }

    //<editor-fold defaultstate="collapsed" desc=" operaciones de tarea ">
    private List<Profiles> searchAll() {
        return profileBoundary.searchAll(new Profiles());
    }

    //</editor-fold>
    private void fillTable(JTable resultTable) {
        String col[] = {"ID", "Clave", "Nombre", "Estatus"};

        tableModel = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };

        
        // The 0 argument is number rows.
        
        resultList.stream().forEach((next) -> {
            tableModel.addRow(new Object[]{next.getProId(), next.getProCode(), next.getProName(), next.getProStatus()});
        });

        resultTable.setModel(tableModel);
        resultTable.getColumn("ID").setMinWidth(0);
        resultTable.getColumn("ID").setMaxWidth(0);
        resultTable.setColumnSelectionAllowed(false);
        resultTable.setCellSelectionEnabled(false);
        resultTable.setRowSelectionAllowed(true);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jSeparator2 = new javax.swing.JSeparator();
        profile = new com.ahms.model.entity.Profiles();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        proCode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        proName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        proStatus = new javax.swing.JComboBox();
        proId = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jToolBar1 = new javax.swing.JToolBar();
        btnLimpiar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, profile, org.jdesktop.beansbinding.ELProperty.create("${proCode}"), profile, org.jdesktop.beansbinding.BeanProperty.create("proCode"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, profile, org.jdesktop.beansbinding.ELProperty.create("${proDteMod}"), profile, org.jdesktop.beansbinding.BeanProperty.create("proDteMod"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, profile, org.jdesktop.beansbinding.ELProperty.create("${proId}"), profile, org.jdesktop.beansbinding.BeanProperty.create("proId"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, profile, org.jdesktop.beansbinding.ELProperty.create("${proName}"), profile, org.jdesktop.beansbinding.BeanProperty.create("proName"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, profile, org.jdesktop.beansbinding.ELProperty.create("${proStatus}"), profile, org.jdesktop.beansbinding.BeanProperty.create("proStatus"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, profile, org.jdesktop.beansbinding.ELProperty.create("${proUsrMod}"), profile, org.jdesktop.beansbinding.BeanProperty.create("proUsrMod"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, profile, org.jdesktop.beansbinding.ELProperty.create("${usersCollection}"), profile, org.jdesktop.beansbinding.BeanProperty.create("usersCollection"));
        bindingGroup.addBinding(binding);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(700, 500));

        jLabel1.setText("Clave:");

        proCode.setName("proCode"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, profile, org.jdesktop.beansbinding.ELProperty.create("${proCode}"), proCode, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel2.setText("Nombre:");

        proName.setName("proName"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, profile, org.jdesktop.beansbinding.ELProperty.create("${proName}"), proName, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel3.setText("Estatus:");

        proStatus.setName("proStatus"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, profile, org.jdesktop.beansbinding.ELProperty.create("${proStatus}"), proStatus, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        proId.setToolTipText("");
        proId.setName("proId"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, profile, org.jdesktop.beansbinding.ELProperty.create("${proId}"), proId, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setMargin(new java.awt.Insets(0, 10, 0, 0));
        jToolBar1.setName("mainToolBar"); // NOI18N

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/1445773254_file.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setFocusable(false);
        btnLimpiar.setName("btLimpiar"); // NOI18N
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnLimpiar);

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/1445772615_file_search.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setFocusable(false);
        btnBuscar.setName("btnBuscar"); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnBuscar);

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/1445772609_file_add.png"))); // NOI18N
        btnNuevo.setText("Agregar");
        btnNuevo.setFocusable(false);
        btnNuevo.setName("btnNuevo"); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnNuevo);
        jToolBar1.add(jSeparator4);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/1445772617_file_edit.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setFocusable(false);
        btnEditar.setName("btnEditar"); // NOI18N
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEditar);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/1445772697_diskette.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setFocusable(false);
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnGuardar);

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/1445772612_file_delete.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setFocusable(false);
        btnEliminar.setName("btnEliminar"); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEliminar);
        jToolBar1.add(jSeparator5);

        jScrollPane1.setName("trSp"); // NOI18N

        resultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave", "Nombre", "Estatus", "Fecha Mod", "Usuario Mod"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        resultTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        resultTable.setName("resultTable"); // NOI18N
        resultTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(resultTable);
        resultTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (resultTable.getColumnModel().getColumnCount() > 0) {
            resultTable.getColumnModel().getColumn(0).setResizable(false);
            resultTable.getColumnModel().getColumn(1).setResizable(false);
            resultTable.getColumnModel().getColumn(2).setResizable(false);
            resultTable.getColumnModel().getColumn(3).setResizable(false);
            resultTable.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(proCode, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(proName, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(proStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(proId, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 95, Short.MAX_VALUE))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(proCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(proName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(proStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(proId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
//<editor-fold defaultstate="collapsed" desc=" accion de botones ">


    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        System.out.println(profile.getProCode());

        formManager.setDefaultFormStatus();
        /*try {
         profile.resetProperties();
         } catch (IllegalArgumentException ex) {
         Logger.getLogger(ProfilesFrm.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IllegalAccessException ex) {
         Logger.getLogger(ProfilesFrm.class.getName()).log(Level.SEVERE, null, ex);
         }*/
        resultList = searchAll();
        fillTable(resultTable);
        formManager.setDefaultFormStatus();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        System.out.println(profile.getProStatus());
        resultList = profileBoundary.search(profile);
        fillTable(resultTable);
        formManager.updateButtonMenuState(UIConstants.BTN_BUSCAR);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        System.out.println("nuevo");
        profile.setProDteMod(new Date());
//        profile.setProUsrMod(topFrame.getMainUser().getUsrId());
        profile.setProUsrMod(new Users(1));
        if (profileBoundary.insert(profile) == 1) {
            JOptionPane.showMessageDialog(this, UIConstants.SUCCESS_SAVE);
        }
        resultList = searchAll();
        fillTable(resultTable);
        formManager.setDefaultFormStatus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        System.out.println("editar");
        formManager.updateButtonMenuState(UIConstants.BTN_EDITAR);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        System.out.println("guardar");
        if (profile.getProId() != null) {
            profile.setProDteMod(new Date());
//            profile.setProUsrMod(topFrame.getMainUser().getUsrId());
            profile.setProUsrMod(new Users(1));
            
            if (profileBoundary.update(profile) == 1) {
                JOptionPane.showMessageDialog(this, UIConstants.SUCCESS_UPDATE);
            }
        }
        resultList = searchAll();
        fillTable(resultTable);
        formManager.setDefaultFormStatus();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        System.out.println("eliminar");
        int dialogResult = JOptionPane.showConfirmDialog(this, UIConstants.CONFIRM_DELETE, UIConstants.TYPE_WARNING, JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            if (profileBoundary.delete(profile) == 1) {
                JOptionPane.showMessageDialog(this, UIConstants.SUCCESS_DELETE);
            }
        }
        resultList = searchAll();
        fillTable(resultTable);
        formManager.updateButtonMenuState(UIConstants.BTN_ELIMINAR);
        formManager.setDefaultFormStatus();
    }//GEN-LAST:event_btnEliminarActionPerformed

//</editor-fold>
    /**/
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ClassNotFoundException {
        try {
            /* Set the Nimbus look and feel */
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
             * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
             */
//        try {
////            javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
////            javax.swing.UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
////            javax.swing.UIManager.setLookAndFeel("com.pagosoft.plaf.PgsLookAndFeel");
////            javax.swing.UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel");
////
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                System.out.println(info.getName());
//                if ("Metal".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    MetalLookAndFeel.setCurrentTheme(new OceanTheme());
//                    break;
//                }
//            }
//            
////            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
////                if ("Nimbus".equals(info.getName())) {
////                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
////                    break;
////                }
////            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ProfilesFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ProfilesFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ProfilesFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ProfilesFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        //</editor-fold>
        } catch (InstantiationException ex) {
            Logger.getLogger(ProfilesFrm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ProfilesFrm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ProfilesFrm.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProfilesFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField proCode;
    private javax.swing.JTextField proId;
    private javax.swing.JTextField proName;
    private javax.swing.JComboBox proStatus;
    private com.ahms.model.entity.Profiles profile;
    private javax.swing.JTable resultTable;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}