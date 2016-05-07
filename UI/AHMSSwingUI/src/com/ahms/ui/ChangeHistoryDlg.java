/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui;

import com.ahms.boundary.security.ChangeHistoryBoundary;
import com.ahms.boundary.security.RoomsBoundary;
import com.ahms.model.entity.Account;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.RoomTypes;
import com.ahms.model.entity.Rooms;
import com.ahms.model.entity.Users;
import com.ahms.ui.utils.UIConstants;
import com.ahms.util.MMKeys;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author rsoto
 */
public class ChangeHistoryDlg extends javax.swing.JDialog {

    
    private MainFrm parent;
    
    
    /**
     * Creates new form Change
     */
    public ChangeHistoryDlg(java.awt.Frame parent, boolean modal, Account account, Rooms room) {
        super(parent, modal);
        initComponents();
        this.parent  = (MainFrm) parent;
        changeHistory.setChaUsr(this.parent.getMainUser());
        changeHistory.setActId(account);
        changeHistory.setChaRmB(room);
        
        lblCustomer.setText(account.getCusId().getCusName());
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        lblDate.setText(sf.format(new Date()));
        lblRoomA.setText(room.getRmsNumber() +": " + room.getRmsDesc());
        btnSaveChange.setEnabled(false);
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

        changeHistory = new com.ahms.model.entity.ChangeHistory();
        lblCustomer = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblRoom = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDesc = new javax.swing.JTextArea();
        lblRoomA = new javax.swing.JLabel();
        jbQRSearchRoom = new javax.swing.JButton();
        btnSaveChange = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblCustomer.setText("jLabel1");

        lblDate.setText("jLabel1");

        lblRoom.setText("Cuarto: ");

        jLabel4.setText("Motivo del cambio:");

        txtDesc.setColumns(20);
        txtDesc.setRows(5);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, changeHistory, org.jdesktop.beansbinding.ELProperty.create("${chaDescc}"), txtDesc, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(txtDesc);

        lblRoomA.setText("Nuevo Cuarto:");

        jbQRSearchRoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/find.png"))); // NOI18N
        jbQRSearchRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbQRSearchRoomActionPerformed(evt);
            }
        });

        btnSaveChange.setText("jButton1");
        btnSaveChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveChangeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblRoomA, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jbQRSearchRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSaveChange))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCustomer)
                                .addGap(290, 290, 290)
                                .addComponent(lblDate))
                            .addComponent(jLabel4)
                            .addComponent(lblRoom)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCustomer)
                    .addComponent(lblDate))
                .addGap(18, 18, 18)
                .addComponent(lblRoom)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSaveChange)
                    .addComponent(lblRoomA)
                    .addComponent(jbQRSearchRoom))
                .addGap(31, 31, 31))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbQRSearchRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbQRSearchRoomActionPerformed
        RoomTypes tipoSeleccionado = changeHistory.getChaRmB().getRmsBeds();
        Rooms paramRoom = new Rooms();
        paramRoom.setRmsBeds(tipoSeleccionado);
        RoomsBoundary roomsBoundary = new RoomsBoundary();
        List<Rooms> roomsAvailableByType = roomsBoundary.findByRmsBeds(paramRoom);
        for(Rooms room : roomsAvailableByType){
            if(room.getRmsStatus().getMvaKey().equals(MMKeys.Rooms.STA_DISPONIBLE_KEY)){
                changeHistory.setChaRmA(room);
                lblRoomA.setText(lblRoomA.getText()+ " "+room.getRmsNumber());
               
                btnSaveChange.setEnabled(true);
                                //generar totales de renta
                
            }
        }
        JOptionPane.showMessageDialog(this, UIConstants.NO_AVAIL_ROOMS);
    }//GEN-LAST:event_jbQRSearchRoomActionPerformed

    private void btnSaveChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveChangeActionPerformed
        // TODO add your handling code here:
        CancelationPrompt cp = new CancelationPrompt(parent, true);
        cp.setVisible(true);
        boolean response  =cp.getAutorization();
        Users autUser = cp.getAutUser();
        if (response && (txtDesc.getText()!=null && !"".equals(txtDesc.getText()))) {
            ChangeHistoryBoundary  chb = new ChangeHistoryBoundary();
            RoomsBoundary rmb = new RoomsBoundary();
            changeHistory.setChaUsrAut(autUser);
            changeHistory.getChaRmA().setRmsStatus(new MultiValue(MMKeys.Rooms.STA_OCUPADO_KEY));
            changeHistory.getChaRmB().setRmsStatus(new MultiValue(MMKeys.Rooms.STA_DISPONIBLE_KEY));
            changeHistory.setChaDescc(txtDesc.getText());
            changeHistory.setChaDate(new Date());
            chb.insert(changeHistory);
            rmb.insert(changeHistory.getChaRmA());
            rmb.insert(changeHistory.getChaRmB());
            
             JOptionPane.showMessageDialog(this, UIConstants.SUCCESS_SAVE);
        }
    }//GEN-LAST:event_btnSaveChangeActionPerformed

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
            java.util.logging.Logger.getLogger(ChangeHistoryDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangeHistoryDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangeHistoryDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangeHistoryDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChangeHistoryDlg dialog = new ChangeHistoryDlg(new MainFrm(), true, new Account(1), new Rooms(1));
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
    private javax.swing.JButton btnSaveChange;
    private com.ahms.model.entity.ChangeHistory changeHistory;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbQRSearchRoom;
    private javax.swing.JLabel lblCustomer;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblRoom;
    private javax.swing.JLabel lblRoomA;
    private javax.swing.JTextArea txtDesc;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}