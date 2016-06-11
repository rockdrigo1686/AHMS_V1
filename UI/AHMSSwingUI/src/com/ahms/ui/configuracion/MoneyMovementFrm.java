/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui.configuracion;

import com.ahms.boundary.entity_boundary.MoneyMovementBoundary;
import com.ahms.model.entity.CashOut;
import com.ahms.model.entity.MoneyMovement;
import com.ahms.model.entity.Users;
import com.ahms.ui.MainFrm;
import com.ahms.ui.utils.UIConstants;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rsoto
 */
public class MoneyMovementFrm extends javax.swing.JDialog {

    /**
     * Creates new form MoneyMovementFRM
     */
    Users mainUser = null;
    CashOut currentShift = null;
    MainFrm parent = null;
    DefaultTableModel mmModel = null;

    public MoneyMovementFrm(java.awt.Frame parent, boolean modal, CashOut currentShift, Users mainUser) throws Exception {
        super(parent, modal);
        this.mainUser = mainUser;
        this.currentShift = currentShift;
        this.parent = (MainFrm) parent;
        initComponents();
        String col[] = {"Descripcion", "Salida", "Entrada"};
//        mmModel = new DefaultTableModel(col, 0);
        mmModel = new DefaultTableModel(col, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public Class getColumnClass(int column) {
                return column > 0 ? BigDecimal.class : getValueAt(0, column).getClass();
            }
        };
        tblMoneuMov.setModel(mmModel);
        tblMoneuMov.setRowHeight(50);
        setTableModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btnGuardar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMoneuMov = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jToolBar1.setFloatable(false);
        jToolBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToolBar1.setPreferredSize(new java.awt.Dimension(114, 38));

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/16x16/disk.png"))); // NOI18N
        btnGuardar.setToolTipText("Guardar");
        btnGuardar.setFocusable(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnGuardar);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/16x16/add.png"))); // NOI18N
        jButton1.setToolTipText("Agregar");
        jButton1.setFocusable(false);
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/16x16/remove_minus_sign.png"))); // NOI18N
        jButton2.setToolTipText("Eliminar");
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        tblMoneuMov.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Descripcion", "Salidas", "Salidas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblMoneuMov);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFormattedTextField amtField = new JFormattedTextField(
                NumberFormat.getCurrencyInstance());

        mmModel.addRow(new Object[]{new String(), BigDecimal.ZERO,BigDecimal.ZERO});
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (tblMoneuMov.getSelectedRow() >= 0) {
            mmModel.removeRow(tblMoneuMov.getSelectedRow());
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showOptionDialog(parent, UIConstants.CONFIRM_UPDATE, null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"SI", "NO"}, rootPane);
           if (null != tblMoneuMov.getCellEditor()) {
                // there is an edit in progress
                 tblMoneuMov.getCellEditor().stopCellEditing();
            }
        if (option == 0) {
         
            MoneyMovementBoundary mmBound = new MoneyMovementBoundary();
            MoneyMovement mm = null;
            List<MoneyMovement> mmList = new ArrayList<>();
            Date modDte = new Date();
            for (int i = 0; i < mmModel.getRowCount(); i++) {
                mm = new MoneyMovement();
                mm.setCouId(currentShift);
                mm.setMmoUsrMod(mainUser);
                mm.setMmoDteMod(modDte);
                mm.setMmoDescription((String) mmModel.getValueAt(i, 0));
                mm.setMmoCasOut((BigDecimal) mmModel.getValueAt(i, 1));
                mm.setMmoCasIn((BigDecimal) mmModel.getValueAt(i, 2));
                mmList.add(mm);
            }
            
                mmBound.update(mmList,currentShift);
            
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void setTableModel() throws Exception {

        MoneyMovementBoundary mmBound = new MoneyMovementBoundary();
        MoneyMovement mm = new MoneyMovement();
        mm.setCouId(currentShift);
        List<MoneyMovement> mmList = mmBound.searchByCouId(mm);

        if (mmList != null) {
            mmList.stream().forEach((mmObj) -> {
                mmModel.addRow(new Object[]{mmObj.getMmoDescription(), mmObj.getMmoCasOut(), mmObj.getMmoCasIn()});
            });
        }
    }

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
            java.util.logging.Logger.getLogger(MoneyMovementFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MoneyMovementFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MoneyMovementFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MoneyMovementFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                MoneyMovementFRM dialog = new MoneyMovementFRM(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                MoneyMovementFRM dialog = new MoneyMovementFRM(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblMoneuMov;
    // End of variables declaration//GEN-END:variables

}
