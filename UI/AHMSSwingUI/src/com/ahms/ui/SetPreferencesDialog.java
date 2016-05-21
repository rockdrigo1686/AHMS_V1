package com.ahms.ui;

import com.ahms.boundary.security.FloorsBoundary;
import com.ahms.boundary.security.PreferenceDetailBoundary;
import com.ahms.boundary.security.RoomTypesBoundary;
import com.ahms.boundary.security.RoomsBoundary;
import com.ahms.model.entity.Customers;
import com.ahms.model.entity.Floors;
import com.ahms.model.entity.PreferenceDetail;
import com.ahms.model.entity.RoomTypes;
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
    private RoomTypesBoundary roomTypesBoundary = null;
    private PreferenceDetailBoundary preferenceDetailBoundary = null;
    private DefaultTableModel tableModel = null;
    private List<PreferenceDetail> resultList = null;
    private PreferenceDetail selectedPreference = null;
    
    public SetPreferencesDialog(java.awt.Frame parent, boolean modal, Customers customer) {
        super(parent, modal);
        initComponents();
        mainCustomer = customer;
        roomTypesBoundary = new RoomTypesBoundary();
        preferenceDetailBoundary = new PreferenceDetailBoundary();
        resultList = preferenceDetailBoundary.findPreferences(mainCustomer);
        configTable();
        this.jcbCuartos.removeAllItems();
        
        this.jbtnDel.setEnabled(false);
        this.jbtnNew.setEnabled(true);
        this.jbtnUpd.setEnabled(false);
        
        configRooms();
        this.jlName.setText(mainCustomer.getFullName());
    }
    
    public SetPreferencesDialog(java.awt.Dialog parent, boolean modal, Customers customer) {
        super(parent, modal);
        initComponents();
        mainCustomer = customer;
        roomTypesBoundary = new RoomTypesBoundary();
        preferenceDetailBoundary = new PreferenceDetailBoundary();
        resultList = preferenceDetailBoundary.findPreferences(mainCustomer);
        configTable();
        this.jcbCuartos.removeAllItems();
        
        this.jbtnDel.setEnabled(false);
        this.jbtnNew.setEnabled(true);
        this.jbtnUpd.setEnabled(false);
        
        configRooms();
        this.jlName.setText(mainCustomer.getFullName());
    }
    
    
    private void configRooms(){
        List<RoomTypes> types = roomTypesBoundary.searchAll(new RoomTypes());
        jcbCuartos.removeAllItems();
        RoomTypes blankRoomType = new RoomTypes();
        blankRoomType.setRtyDescription("Seleccionar...");
        jcbCuartos.addItem(blankRoomType);
        for(RoomTypes type : types){
            jcbCuartos.addItem(type);
        }        
    }
    
    private void configTable(){
        
        String col[] = {"ROW","PREF_ID", "CUS_ID","RTY_ID","Tipo", "Monto"};

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
                next.getRtyId(),
                next.getRtyId().getRtyDescription(), 
                next.getPrefAmount()});
        });

        resultTable.setModel(tableModel);
        resultTable.getColumn("ROW").setMinWidth(0);
        resultTable.getColumn("ROW").setMaxWidth(0);
        resultTable.getColumn("PREF_ID").setMinWidth(0);
        resultTable.getColumn("PREF_ID").setMaxWidth(0);
        resultTable.getColumn("CUS_ID").setMaxWidth(0);
        resultTable.getColumn("CUS_ID").setMinWidth(0);
        resultTable.getColumn("RTY_ID").setMinWidth(0);
        resultTable.getColumn("RTY_ID").setMaxWidth(0);        
        resultTable.getColumn("Tipo").setMaxWidth(180);
        resultTable.getColumn("Tipo").setMinWidth(180);
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
        jLabel3 = new javax.swing.JLabel();
        jcbCuartos = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jtxtMonto = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        jbtnClr = new javax.swing.JButton();
        jbtnUpd = new javax.swing.JButton();
        jbtnNew = new javax.swing.JButton();
        jbtnDel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jlName.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlName.setText("JORGE ACLFONSO CASTAÑEDA GUTIERREZ");

        jLabel3.setText("Tipo:");

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

        jToolBar1.setRollover(true);

        jbtnClr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/META-INF/1445772664_file.png"))); // NOI18N
        jbtnClr.setText("Limpiar");
        jbtnClr.setToolTipText("LIMPIAR");
        jbtnClr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnClrActionPerformed(evt);
            }
        });
        jToolBar1.add(jbtnClr);

        jbtnUpd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/META-INF/1445772617_file_edit.png"))); // NOI18N
        jbtnUpd.setText("Actualizar");
        jbtnUpd.setToolTipText("ACTUALIZAR");
        jbtnUpd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnUpdActionPerformed(evt);
            }
        });
        jToolBar1.add(jbtnUpd);

        jbtnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/META-INF/1445772609_file_add.png"))); // NOI18N
        jbtnNew.setText("Nuevo");
        jbtnNew.setToolTipText("NUEVO");
        jbtnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNewActionPerformed(evt);
            }
        });
        jToolBar1.add(jbtnNew);

        jbtnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/META-INF/1445772612_file_delete.png"))); // NOI18N
        jbtnDel.setText("Eliminar");
        jbtnDel.setToolTipText("ELIMINAR");
        jbtnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDelActionPerformed(evt);
            }
        });
        jToolBar1.add(jbtnDel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbCuartos, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtMonto))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jlName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbCuartos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jtxtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jlName.getAccessibleContext().setAccessibleName("jlCustomer");
        jcbCuartos.getAccessibleContext().setAccessibleName("jcbCuartos");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnClrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnClrActionPerformed
        this.jcbCuartos.setSelectedIndex(0);
        this.jtxtMonto.setText("");
        this.jbtnNew.setEnabled(true);
        this.jbtnDel.setEnabled(false);
        this.jbtnUpd.setEnabled(false);
    }//GEN-LAST:event_jbtnClrActionPerformed

    private void jbtnUpdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUpdActionPerformed
        if(selectedPreference != null){
            selectedPreference.setPrefAmount(new BigDecimal(this.jtxtMonto.getText()));
            selectedPreference.setRtyId((RoomTypes) this.jcbCuartos.getSelectedItem());
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
        newPreferenceDetail.setRtyId((RoomTypes) this.jcbCuartos.getSelectedItem());
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
            this.jcbCuartos.setSelectedItem(selectedPreference.getRtyId());
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton jbtnClr;
    private javax.swing.JButton jbtnDel;
    private javax.swing.JButton jbtnNew;
    private javax.swing.JButton jbtnUpd;
    private javax.swing.JComboBox jcbCuartos;
    private javax.swing.JLabel jlName;
    private javax.swing.JTextField jtxtMonto;
    private javax.swing.JTable resultTable;
    // End of variables declaration//GEN-END:variables
}
