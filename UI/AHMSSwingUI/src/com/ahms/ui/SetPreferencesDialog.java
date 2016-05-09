package com.ahms.ui;

import com.ahms.boundary.security.FloorsBoundary;
import com.ahms.boundary.security.PreferenceDetailBoundary;
import com.ahms.boundary.security.RoomsBoundary;
import com.ahms.model.entity.Customers;
import com.ahms.model.entity.Floors;
import com.ahms.model.entity.PreferenceDetail;
import com.ahms.ui.utils.GeneralFunctions;
import com.ahms.ui.utils.UIConstants;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jorge
 */
public class SetPreferencesDialog extends javax.swing.JDialog {

    private Customers mainCustomer = null;
    private FloorsBoundary floorsBoundary = null;
    private RoomsBoundary roomsBoundary = null;
    private PreferenceDetailBoundary preferenceDetailBoundary = null;
    private DefaultTableModel tableModel = null;
    private List<PreferenceDetail> resultList = null;
    private PreferenceDetail selectedPreference = null;
    
    public SetPreferencesDialog(java.awt.Frame parent, boolean modal, Customers customer) {
        super(parent, modal);
        initComponents();
        mainCustomer = customer;
        floorsBoundary = new FloorsBoundary();
        roomsBoundary = new RoomsBoundary();
        preferenceDetailBoundary = new PreferenceDetailBoundary();
        configFloors();
        resultList = preferenceDetailBoundary.findPreferences(mainCustomer);
        configTable();
        this.jcbCuartos.removeAllItems();
        
        this.jbtnDel.setEnabled(false);
        this.jbtnNew.setEnabled(true);
        this.jbtnUpd.setEnabled(false);
        
        configRooms((Floors) jcbPisos.getSelectedItem());
        this.jlName.setText(mainCustomer.getFullName());
    }
    
    public SetPreferencesDialog(java.awt.Dialog parent, boolean modal, Customers customer) {
        super(parent, modal);
        initComponents();
        mainCustomer = customer;
        floorsBoundary = new FloorsBoundary();
        roomsBoundary = new RoomsBoundary();
        preferenceDetailBoundary = new PreferenceDetailBoundary();
        configFloors();
        resultList = preferenceDetailBoundary.findPreferences(mainCustomer);
        configTable();
        this.jcbCuartos.removeAllItems();
        
        this.jbtnDel.setEnabled(false);
        this.jbtnNew.setEnabled(true);
        this.jbtnUpd.setEnabled(false);
        
        configRooms((Floors) jcbPisos.getSelectedItem());
        this.jlName.setText(mainCustomer.getFullName());
    }
    
    private void configFloors(){
        List<Floors> lstFloors = floorsBoundary.searchAll(new Floors());
        DefaultComboBoxModel model = new DefaultComboBoxModel(lstFloors.toArray(new Floors[lstFloors.size()]));
        this.jcbPisos.setModel(model);
    }
    
    private void configRooms(Floors floor){
        com.ahms.model.entity.Rooms room = new com.ahms.model.entity.Rooms();
        room.setFlrId(floor);
        List<com.ahms.model.entity.Rooms> lstRooms = roomsBoundary.findByFloor(room);
        DefaultComboBoxModel model = new DefaultComboBoxModel(lstRooms.toArray(new com.ahms.model.entity.Rooms[lstRooms.size()]));
        this.jcbCuartos.setModel(model);
    }
    
    private void configTable(){
        
        String col[] = {"ROW","PREF_ID", "CUS_ID","RMS_ID","Cuarto", "Monto"};

        tableModel = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };

        // The 0 argument is number rows.
        resultList.stream().forEach((next) -> {
            tableModel.addRow(new Object[]{
                next,
                next.getPrefId(), 
                next.getCusId(),
                next.getRmsId(),
                next.getRmsId().getRmsDesc(), 
                next.getPrefAmount()});
        });

        resultTable.setModel(tableModel);
        resultTable.getColumn("ROW").setMinWidth(0);
        resultTable.getColumn("ROW").setMaxWidth(0);
        resultTable.getColumn("PREF_ID").setMinWidth(0);
        resultTable.getColumn("PREF_ID").setMaxWidth(0);
        resultTable.getColumn("CUS_ID").setMaxWidth(0);
        resultTable.getColumn("CUS_ID").setMinWidth(0);
        resultTable.getColumn("RMS_ID").setMinWidth(0);
        resultTable.getColumn("RMS_ID").setMaxWidth(0);        
        resultTable.getColumn("Cuarto").setMaxWidth(180);
        resultTable.getColumn("Cuarto").setMinWidth(180);
        resultTable.getColumn("Monto").setMaxWidth(180);
        resultTable.getColumn("Monto").setMinWidth(180);
        resultTable.setColumnSelectionAllowed(false);
        resultTable.setCellSelectionEnabled(false);
        resultTable.setRowSelectionAllowed(true);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);        
        resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jcbPisos = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jcbCuartos = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jtxtMonto = new javax.swing.JTextField();
        jbtnUpd = new javax.swing.JButton();
        jbtnNew = new javax.swing.JButton();
        jbtnDel = new javax.swing.JButton();
        jbtnClr = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jlName.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlName.setText("JORGE ACLFONSO CASTAÑEDA GUTIERREZ");

        jLabel2.setText("Piso:");

        jcbPisos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbPisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPisosActionPerformed(evt);
            }
        });

        jLabel3.setText("Cuarto:");

        jcbCuartos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
        resultTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resultTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(resultTable);
        resultTable.getAccessibleContext().setAccessibleName("resultTable");

        jLabel4.setText("Monto:");

        jbtnUpd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/1445771624_1.png"))); // NOI18N
        jbtnUpd.setToolTipText("ACTUALIZAR");
        jbtnUpd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnUpdActionPerformed(evt);
            }
        });

        jbtnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/1445771614_7.png"))); // NOI18N
        jbtnNew.setToolTipText("NUEVO");
        jbtnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNewActionPerformed(evt);
            }
        });

        jbtnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/1445771618_17.png"))); // NOI18N
        jbtnDel.setToolTipText("ELIMINAR");
        jbtnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDelActionPerformed(evt);
            }
        });

        jbtnClr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/1445771577_12.png"))); // NOI18N
        jbtnClr.setToolTipText("LIMPIAR");
        jbtnClr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnClrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jlName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbPisos, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbCuartos, 0, 122, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(3, 3, 3)
                        .addComponent(jtxtMonto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnClr)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnUpd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnDel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbPisos)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcbCuartos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jbtnUpd, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                        .addComponent(jbtnDel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnClr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbtnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtxtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addContainerGap())
        );

        jlName.getAccessibleContext().setAccessibleName("jlCustomer");
        jcbPisos.getAccessibleContext().setAccessibleName("jcbPisos");
        jcbCuartos.getAccessibleContext().setAccessibleName("jcbCuartos");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbPisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPisosActionPerformed
        configRooms((Floors) jcbPisos.getSelectedItem());
    }//GEN-LAST:event_jcbPisosActionPerformed

    private void jbtnClrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnClrActionPerformed
        this.jcbPisos.setSelectedIndex(0);
        this.jtxtMonto.setText("");
        this.jbtnNew.setEnabled(true);
        this.jbtnDel.setEnabled(false);
        this.jbtnUpd.setEnabled(false);
    }//GEN-LAST:event_jbtnClrActionPerformed

    private void jbtnUpdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUpdActionPerformed
        if(selectedPreference != null){
            selectedPreference.setPrefAmount(new BigDecimal(this.jtxtMonto.getText()));
            selectedPreference.setRmsId((com.ahms.model.entity.Rooms) this.jcbCuartos.getSelectedItem());
            selectedPreference.setCusId(mainCustomer);
            if(preferenceDetailBoundary.update(selectedPreference) > 0){
                GeneralFunctions.sendMessage(this, UIConstants.SUCCESS_UPDATE);
                resultList = preferenceDetailBoundary.findPreferences(mainCustomer);
                configTable();
                this.jbtnNew.setEnabled(true);
                this.jbtnDel.setEnabled(false);
                this.jbtnUpd.setEnabled(false);
            } else {
                GeneralFunctions.sendMessage(this, UIConstants.ERROR_SAVE);
            }
        } else {
            GeneralFunctions.sendMessage(this, "Seleccione primero un registro a modificar.");
        }
    }//GEN-LAST:event_jbtnUpdActionPerformed

    private void jbtnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNewActionPerformed
        PreferenceDetail newPreferenceDetail = new PreferenceDetail();
        newPreferenceDetail.setCusId(mainCustomer);
        newPreferenceDetail.setPrefAmount(new BigDecimal(this.jtxtMonto.getText()));
        newPreferenceDetail.setRmsId((com.ahms.model.entity.Rooms) this.jcbCuartos.getSelectedItem());
        if(preferenceDetailBoundary.insert(newPreferenceDetail) > 0){
            GeneralFunctions.sendMessage(this, UIConstants.SUCCESS_SAVE);
            resultList = preferenceDetailBoundary.findPreferences(mainCustomer);
            configTable();
        } else {
            GeneralFunctions.sendMessage(this, UIConstants.ERROR_SAVE);
        }
    }//GEN-LAST:event_jbtnNewActionPerformed

    private void jbtnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDelActionPerformed
        if(selectedPreference != null){
            int dialogResult = JOptionPane.showConfirmDialog(this, UIConstants.CONFIRM_DELETE, UIConstants.TYPE_WARNING, JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                if(preferenceDetailBoundary.delete(selectedPreference) > 0){
                    resultList = preferenceDetailBoundary.findPreferences(mainCustomer);
                    configTable();
                    GeneralFunctions.sendMessage(this, UIConstants.SUCCESS_DELETE);
                } else {
                    GeneralFunctions.sendMessage(this, UIConstants.ERROR_SAVE);
                }
            }            
        } else {
            GeneralFunctions.sendMessage(this, "Seleccione primero un registro a eliminar.");
        }
    }//GEN-LAST:event_jbtnDelActionPerformed

    private void resultTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultTableMouseClicked
        int clicks = evt.getClickCount();
        int row = resultTable.getSelectedRow();
        if (clicks > 1) { // doble click
            selectedPreference = (PreferenceDetail) resultTable.getValueAt(row, 0);
            this.jbtnDel.setEnabled(true);
            this.jbtnNew.setEnabled(false);
            this.jbtnUpd.setEnabled(true);
            
            this.jtxtMonto.setText(selectedPreference.getPrefAmount().toString());
            this.jcbPisos.setSelectedItem(selectedPreference.getRmsId().getFlrId());
            this.jcbCuartos.setSelectedItem(selectedPreference.getRmsId());            
        }
    }//GEN-LAST:event_resultTableMouseClicked

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
            java.util.logging.Logger.getLogger(SetPreferencesDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SetPreferencesDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SetPreferencesDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SetPreferencesDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SetPreferencesDialog dialog = new SetPreferencesDialog(new javax.swing.JFrame(), true, new Customers(1));
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnClr;
    private javax.swing.JButton jbtnDel;
    private javax.swing.JButton jbtnNew;
    private javax.swing.JButton jbtnUpd;
    private javax.swing.JComboBox jcbCuartos;
    private javax.swing.JComboBox jcbPisos;
    private javax.swing.JLabel jlName;
    private javax.swing.JTextField jtxtMonto;
    private javax.swing.JTable resultTable;
    // End of variables declaration//GEN-END:variables
}
