/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui;

import com.ahms.boundary.security.AccountBoundary;
import com.ahms.boundary.security.AccountTransactionsBoundary;
import com.ahms.boundary.security.CustomersBoundary;
import com.ahms.boundary.security.GuestsBoundary;
import com.ahms.model.entity.Account;
import com.ahms.model.entity.AccountTransactions;
import com.ahms.model.entity.Guests;
import com.ahms.model.entity.Rooms;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;
import javax.swing.CellRendererPane;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Marcos
 */
public class GuestFrm extends javax.swing.JDialog {

//    AccountBoundary actBoundary;
    Account act;
    AccountTransactions accountTransactions;
    GuestsBoundary guestsBoundary; 
    AccountBoundary accountBoundary;
    AccountTransactionsBoundary accountTransactionsBoundary;
    List<Guests> guestsList;
    List<AccountTransactions> accountTransactionsList;
    DefaultTableModel defaultTableModel;
    
    /**
     * Creates new form GuestFrm
     * @param parent
     * @param modal
     * @param account
     */
    public GuestFrm(java.awt.Frame parent, boolean modal, Account account) {
        super(parent, modal);
        guestsBoundary = new GuestsBoundary();
        accountTransactionsBoundary = new AccountTransactionsBoundary();
        act = account;//Aqui se asignaria el valor del parent account
        accountTransactions = new AccountTransactions();
        accountTransactions.setActId(act);
        initComponents();            
        fillGrid();
//        act.setGuestsCollection(guestsList);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Visitantes");
        setAlwaysOnTop(true);

        jPanel1.setBackground(java.awt.Color.white);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addContainerGap())
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/1445772660_home.png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);
        jButton1.getAccessibleContext().setAccessibleName("Salir");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/1445772556_delete.png"))); // NOI18N
        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/1445772697_diskette.png"))); // NOI18N
        jButton4.setText("Guardar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(jTable1.getSelectedRow()>-1) {
            int resp = JOptionPane.showConfirmDialog(this,"¿Estás Seguro que deseas limpiar el registro seleccionado?", "Limpiar registro", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            
            if(resp==JOptionPane.YES_OPTION) { 
                defaultTableModel.setValueAt("", jTable1.getSelectedRow(), 1);
                defaultTableModel.setValueAt("", jTable1.getSelectedRow(), 2);
                defaultTableModel.setValueAt("", jTable1.getSelectedRow(), 3);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Es necesario seleccionar un registro en la tabla.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
//        jTable1.();
        
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(GuestFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuestFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuestFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuestFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AccountBoundary accountBoundary= new AccountBoundary();
                Account account = new Account(1);
                account = accountBoundary.find(account);
                GuestFrm dialog = new GuestFrm(new javax.swing.JFrame(), true, account);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

    private void fillGrid() {
        
        Vector<String> columnNames = new Vector();
        columnNames.add("Cuarto");
        columnNames.add("Nombre");
        columnNames.add("Ap. Paterno");
        columnNames.add("Ap. Materno");        

        Vector<Vector> rows = new Vector<>();
        accountTransactionsList = accountTransactionsBoundary.findRentsByActId(accountTransactions);
        if(accountTransactionsList!=null&&accountTransactionsList.size()>0) {
            
            for(AccountTransactions atrObj:accountTransactionsList) {
                atrObj.setGuestsCollection(guestsBoundary.findByAtrId(atrObj));
                int gstReg = 0;
                if(atrObj.getGuestsCollection() != null && atrObj.getGuestsCollection().size()>0) {
                    gstReg = atrObj.getGuestsCollection().size();
                    for (Guests guests : atrObj.getGuestsCollection()) {
                        Vector vctRow = new Vector();
                        vctRow.add(atrObj.getRmsId().getRmsNumber());
                        vctRow.add(guests.getGstName());
                        vctRow.add(guests.getGstLst1());
                        vctRow.add(guests.getGstLst2());
                        rows.add(vctRow);//3840787890
                    }
                }
                while (gstReg<atrObj.getRmsId().getRmsMaxOcu()) {
                    Vector vctRow = new Vector();
                        vctRow.add(atrObj.getRmsId().getRmsNumber());
                        vctRow.add("");
                        vctRow.add("");
                        vctRow.add("");
                        rows.add(vctRow);
                        gstReg++;
                }
            }
        }
        defaultTableModel = new DefaultTableModel(rows, columnNames) {

            private static final long serialVersionUID = 1L;

            @Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column>0;
            }
        };
        jTable1.setModel(defaultTableModel);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(60);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(200);
        jTable1.getColumnModel().getColumn(2).setMaxWidth(200);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(150);
        
        jTable1.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                int clicks = e.getClickCount();
                if(clicks>1) {
                    int row = jTable1.getSelectedRow();
                    // Remover renglon
                }
            }

        });
    }
}
