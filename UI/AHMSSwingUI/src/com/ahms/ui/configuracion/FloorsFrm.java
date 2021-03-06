package com.ahms.ui.configuracion;

import com.ahms.boundary.entity_boundary.FloorsBoundary;
import com.ahms.boundary.entity_boundary.MultiValueBoundary;
import com.ahms.model.entity.Floors;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.Users;
import com.ahms.ui.utils.FormManager;
import com.ahms.ui.utils.GeneralFunctions;
import com.ahms.ui.utils.JTableDoubleClickListener;
import com.ahms.ui.utils.UIConstants;
import com.ahms.util.MMKeys;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jorge
 */
public class FloorsFrm extends javax.swing.JDialog {

    /**
     * Creates new form FloorsCatalog
     */
    
    private FloorsBoundary floorsBoundary = null;
    private Users userLogued = null;
    private List<Floors> resultList = null;
    private MultiValueBoundary multiValueBoundary = null;
    private DefaultTableModel tableModel = null;
    private Map<String, Component> formComponentMap = new HashMap<String, Component>();
    private Map<String, Component> buttonMap = new HashMap<String, Component>();
    private FormManager formManager = null;
    
    public FloorsFrm(java.awt.Frame parent, boolean modal, Users logued) {
        super(parent, modal);
        initComponents();
        
        userLogued = logued;
        setResizable(false);
        setTitle("Catálogo de Pisos");
        floorsBoundary = new FloorsBoundary();
        multiValueBoundary = new MultiValueBoundary();
        MultiValue multiValue = new MultiValue();
        multiValue.setMvaType(MMKeys.General.GP_KEY);
        loadStatus(multiValueBoundary.findByType(multiValue));
        resultList = searchAll();
        //Creamos mapa de componentes
        formManager = new FormManager();
        formManager.createComponentMaps(this);
        formManager.setDefaultFormStatus();
        fillTable(resultTable);

        resultTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTableDoubleClickListener.addDoubleCLick(e, floorsBean, resultList, formManager.getFormComponentMap());
                if (e.getClickCount() == 2) {
                    formManager.updateButtonMenuState(UIConstants.DOUBLE_CLICK);
                }
            }
        });
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    }
    
    private void loadStatus(List<MultiValue> statusList){
        flrStatus.removeAllItems();
        flrStatus.addItem(new MultiValue(null, "Seleccionar ..."));
        for(MultiValue mul:statusList){
            flrStatus.addItem(mul);
        }        
    }
    
    private List<Floors> searchAll() {
        return floorsBoundary.searchAll(new Floors());
    }
    
    private void fillTable(JTable resultTable) {
        String col[] = {"ID", "Código", "Estatus", "Usuario Mod", "Fecha Mod"};

        tableModel = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };

        // The 0 argument is number rows.
        resultList.stream().forEach((next) -> {
            tableModel.addRow(new Object[]{next.getFlrId(), next.getFlrCode(), next.getFlrStatus(), next.getFlrUsrMod(), next.getFlrDteMod()});
        });

        resultTable.setModel(tableModel);
        resultTable.getColumn("ID").setMinWidth(0);
        resultTable.getColumn("ID").setMaxWidth(0);
        resultTable.getColumn("Usuario Mod").setMinWidth(0);
        resultTable.getColumn("Usuario Mod").setMaxWidth(0);
        resultTable.getColumn("Fecha Mod").setMinWidth(0);
        resultTable.getColumn("Fecha Mod").setMaxWidth(0);
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

        floorsBean = new com.ahms.model.entity.Floors();
        jToolBar1 = new javax.swing.JToolBar();
        btnLimpiar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        btnEliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        flrCode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        flrStatus = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, floorsBean, org.jdesktop.beansbinding.ELProperty.create("${flrCode}"), floorsBean, org.jdesktop.beansbinding.BeanProperty.create("flrCode"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, floorsBean, org.jdesktop.beansbinding.ELProperty.create("${flrDteMod}"), floorsBean, org.jdesktop.beansbinding.BeanProperty.create("flrDteMod"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, floorsBean, org.jdesktop.beansbinding.ELProperty.create("${flrId}"), floorsBean, org.jdesktop.beansbinding.BeanProperty.create("flrId"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, floorsBean, org.jdesktop.beansbinding.ELProperty.create("${flrStatus}"), floorsBean, org.jdesktop.beansbinding.BeanProperty.create("flrStatus"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, floorsBean, org.jdesktop.beansbinding.ELProperty.create("${flrUsrMod}"), floorsBean, org.jdesktop.beansbinding.BeanProperty.create("flrUsrMod"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, floorsBean, org.jdesktop.beansbinding.ELProperty.create("${roomsCollection}"), floorsBean, org.jdesktop.beansbinding.BeanProperty.create("roomsCollection"));
        bindingGroup.addBinding(binding);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setMargin(new java.awt.Insets(0, 10, 0, 0));
        jToolBar1.setName("mainToolBar"); // NOI18N

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/1445772664_file.png"))); // NOI18N
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
        jToolBar1.add(jSeparator5);

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

        jLabel1.setText("Codigo de Piso:");

        flrCode.setName("flrCode"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, floorsBean, org.jdesktop.beansbinding.ELProperty.create("${flrCode}"), flrCode, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        flrCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                flrCodeKeyTyped(evt);
            }
        });

        jLabel2.setText("Estatus:");

        flrStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        flrStatus.setName("flrStatus"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, floorsBean, org.jdesktop.beansbinding.ELProperty.create("${flrStatus}"), flrStatus, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        resultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(resultTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(flrCode, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(flrStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(flrCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(flrStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        formManager.setDefaultFormStatus();
        resultList = searchAll();
        fillTable(resultTable);
        formManager.setDefaultFormStatus();
        GeneralFunctions.resetProperties(floorsBean);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        resultList = floorsBoundary.search(floorsBean);
        fillTable(resultTable);
        formManager.updateButtonMenuState(UIConstants.BTN_BUSCAR);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        
        if(this.flrCode.getText().isEmpty()){
            GeneralFunctions.sendMessage(this, "El campo del Código no puede estar vacio. Por favor rectifique.");
            return;
        }
        
        if(flrStatus.getSelectedIndex() == 0){
            GeneralFunctions.sendMessage(this, "Debe seleccionar el estatus del piso. Por favor rectifique.");
            return;
        }
        
        Floors newFlr = new Floors();
        newFlr.setFlrCode(floorsBean.getFlrCode());
        newFlr.setFlrStatus(floorsBean.getFlrStatus());
        newFlr.setFlrUsrMod(userLogued);
        newFlr.setFlrDteMod(new Date());

        if (floorsBoundary.insert(newFlr) == 1) {
            JOptionPane.showMessageDialog(this, UIConstants.SUCCESS_SAVE);
        }
        resultList = searchAll();
        fillTable(resultTable);
        formManager.setDefaultFormStatus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        formManager.updateButtonMenuState(UIConstants.BTN_EDITAR);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        
        if(this.flrCode.getText().isEmpty()){
            GeneralFunctions.sendMessage(this, "El campo del Código no puede estar vacio. Por favor rectifique.");
            return;
        }
        
        if(flrStatus.getSelectedIndex() == 0){
            GeneralFunctions.sendMessage(this, "Debe seleccionar el estatus del piso. Por favor rectifique.");
            return;
        }
        
        if (floorsBean.getFlrId()!= null) {
            if (floorsBoundary.update(floorsBean) == 1) {
                JOptionPane.showMessageDialog(this, UIConstants.SUCCESS_UPDATE);
            }
        }
        resultList = searchAll();
        fillTable(resultTable);
        formManager.setDefaultFormStatus();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int dialogResult = JOptionPane.showConfirmDialog(this, UIConstants.CONFIRM_DELETE, UIConstants.TYPE_WARNING, JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            if (floorsBoundary.delete(floorsBean) == 1) {
                JOptionPane.showMessageDialog(this, UIConstants.SUCCESS_DELETE);
            }
        }
        resultList = searchAll();
        fillTable(resultTable);
        formManager.updateButtonMenuState(UIConstants.BTN_ELIMINAR);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void flrCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_flrCodeKeyTyped
        if(this.flrCode.getText().length() >= 5){
            evt.consume();
        }
    }//GEN-LAST:event_flrCodeKeyTyped

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
            java.util.logging.Logger.getLogger(FloorsFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FloorsFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FloorsFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FloorsFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FloorsFrm dialog = new FloorsFrm(new javax.swing.JFrame(), true, null);
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
    private com.ahms.model.entity.Floors floorsBean;
    private javax.swing.JTextField flrCode;
    private javax.swing.JComboBox flrStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable resultTable;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
