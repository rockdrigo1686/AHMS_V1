/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui.configuracion;

import com.ahms.boundary.entity_boundary.MultiValueBoundary;
import com.ahms.boundary.entity_boundary.ServiceBoundary;
import com.ahms.boundary.entity_boundary.ServiceTypesBoundary;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.ServiceTypes;
import com.ahms.model.entity.Services;
import com.ahms.model.entity.Users;
import com.ahms.ui.MainFrm;
import com.ahms.ui.utils.FormManager;
import com.ahms.ui.utils.JTableDoubleClickListener;
import com.ahms.ui.utils.UIConstants;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rsoto
 */
public class ServicesFrm extends javax.swing.JDialog {

    private ServiceTypesBoundary serviceTypeBoundary;
    private ServiceBoundary serviceBoundary;
    private MultiValueBoundary MMBoundary = null;
    private List<Services> resultList = null;

    private DefaultTableModel tableModel = null;
    private Map<String, Component> formComponentMap = new HashMap<String, Component>();
    private Map<String, Component> buttonMap = new HashMap<String, Component>();
    private FormManager formManager = null;
    private MainFrm parent;

    /**
     * Creates new form ServicesFrm
     */
    public ServicesFrm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.parent = (MainFrm)parent;
        initComponents();
        setSize(980, 450);
        setLocationRelativeTo(null);
        setResizable(false);

        setTitle("Tipos de Servicios");
        serviceTypeBoundary = new ServiceTypesBoundary();
        serviceBoundary =  new ServiceBoundary();
        MMBoundary = new MultiValueBoundary();

        srvStatus.addItem(new MultiValue());
        for (MultiValue obj : MMBoundary.findByType(new MultiValue(null, null, "GRL", null, null, null))) {
            srvStatus.addItem(obj);
        }

        svtId.addItem(new ServiceTypes());
        ServiceTypes st = new ServiceTypes();
        st.setSvtStatus(new MultiValue("AC"));
        for (ServiceTypes obj : serviceTypeBoundary.searchAll(st)) {
            svtId.addItem(obj);
        }

        resultList = searchAll();
        //Creamos mapa de componentes
        formManager = new FormManager();
        formManager.createComponentMaps(this);
        formManager.setDefaultFormStatus();
        fillTable(resultTable);

        resultTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTableDoubleClickListener.addDoubleCLick(e, service, resultList, formManager.getFormComponentMap());
                if (e.getClickCount() == 2) {
                    formManager.updateButtonMenuState(UIConstants.DOUBLE_CLICK);
                }
            }
        });
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

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

        service = new com.ahms.model.entity.Services();
        jToolBar1 = new javax.swing.JToolBar();
        btnLimpiar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        srvDesc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        srvStatus = new javax.swing.JComboBox();
        srvPrice = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        srvCode = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        srvName = new javax.swing.JTextField();
        svtId = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        srvId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
        jToolBar1.add(jSeparator5);

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
        jToolBar1.add(jSeparator6);

        jLabel1.setText("Clave:");
        jLabel1.setPreferredSize(new java.awt.Dimension(67, 15));

        jLabel2.setText("Descripcion:");

        srvDesc.setName("srvDesc"); // NOI18N

        jLabel3.setText("Estatus:");
        jLabel3.setPreferredSize(new java.awt.Dimension(67, 15));

        srvStatus.setName("srvStatus"); // NOI18N

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, service, org.jdesktop.beansbinding.ELProperty.create("${srvStatus}"), srvStatus, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        srvPrice.setToolTipText("");
        srvPrice.setName("srvPrice"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, service, org.jdesktop.beansbinding.ELProperty.create("${srvPrice}"), srvPrice, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel4.setText("Clave:");
        jLabel4.setPreferredSize(new java.awt.Dimension(67, 15));

        srvCode.setName("srvCode"); // NOI18N

        jLabel5.setText("Nombre:");
        jLabel5.setPreferredSize(new java.awt.Dimension(67, 15));

        srvName.setName("srvName"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, service, org.jdesktop.beansbinding.ELProperty.create("${srvName}"), srvName, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        svtId.setName("svtId"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, service, org.jdesktop.beansbinding.ELProperty.create("${svtId}"), svtId, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jLabel7.setText("Costo:");
        jLabel7.setPreferredSize(new java.awt.Dimension(67, 15));

        srvId.setToolTipText("");
        srvId.setName("srvId"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, service, org.jdesktop.beansbinding.ELProperty.create("${srvId}"), srvId, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 894, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(svtId, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(srvCode, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(srvName, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(srvDesc))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(srvStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(srvPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(srvId))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 894, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 894, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(svtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(srvCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(srvName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(srvStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(srvDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(srvPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(srvId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:

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
        resultList = serviceBoundary.search(service);
        fillTable(resultTable);
        formManager.updateButtonMenuState(UIConstants.BTN_BUSCAR);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        System.out.println("nuevo");
        service.setSrvDteMod(new Date());
        //        profile.setSrvUsrMod(topFrame.getMainUser().getUsrId());
        service.setSrvUsrMod(new Users(1));
        if (serviceBoundary.insert(service) == 1) {
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
        if (service.getSvtId() != null) {
            service.setSrvDteMod(new Date());
            //        profile.setSrvUsrMod(topFrame.getMainUser().getUsrId());
            service.setSrvUsrMod(new Users(1));
            if (serviceBoundary.update(service) == 1) {
                JOptionPane.showMessageDialog(this, UIConstants.SUCCESS_UPDATE);
            }
        }
        resultList = searchAll();
        fillTable(resultTable);
        formManager.setDefaultFormStatus();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private List<Services> searchAll() {
        return serviceBoundary.searchAll(new Services());
    }

    //</editor-fold>
    private void fillTable(JTable resultTable) {
        String col[] = {"ID", "Tipo", "Clave", "Descripcion", "Costo", "Estatus"};

        tableModel = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };

        // The 0 argument is number rows.
        resultList.stream().forEach((next) -> {
            tableModel.addRow(new Object[]{next.getSrvId(),next.getSvtId(), next.getSrvCode(),next.getSrvDesc(),next.getSrvPrice(), next.getSrvStatus()});
        });

        resultTable.setModel(tableModel);
        resultTable.getColumn("ID").setMinWidth(0);
        resultTable.getColumn("ID").setMaxWidth(0);
        resultTable.setColumnSelectionAllowed(false);
        resultTable.setCellSelectionEnabled(false);
        resultTable.setRowSelectionAllowed(true);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        System.out.println("eliminar");
        int dialogResult = JOptionPane.showConfirmDialog(this, UIConstants.CONFIRM_DELETE, UIConstants.TYPE_WARNING, JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            if (serviceBoundary.delete(service) == 1) {
                JOptionPane.showMessageDialog(this, UIConstants.SUCCESS_DELETE);
            }
        }
        resultList = searchAll();
        fillTable(resultTable);
        formManager.updateButtonMenuState(UIConstants.BTN_ELIMINAR);
        formManager.setDefaultFormStatus();
    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServicesFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServicesFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServicesFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServicesFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ServicesFrm dialog = new ServicesFrm(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable resultTable;
    private com.ahms.model.entity.Services service;
    private javax.swing.JTextField srvCode;
    private javax.swing.JTextField srvDesc;
    private javax.swing.JTextField srvId;
    private javax.swing.JTextField srvName;
    private javax.swing.JTextField srvPrice;
    private javax.swing.JComboBox srvStatus;
    private javax.swing.JComboBox svtId;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
