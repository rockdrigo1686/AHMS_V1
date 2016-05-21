/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui;

import com.ahms.boundary.security.MessageBoardBoundary;
import com.ahms.model.entity.MessageBoard;
import com.ahms.model.entity.Users;
import com.ahms.ui.utils.DateLabelFormatter;
import com.ahms.ui.utils.GeneralFunctions;
import java.awt.Font;
import java.util.Calendar;
import java.util.Properties;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author rsoto
 */
public class AddMessage extends javax.swing.JDialog {

    /**
     * Creates new form AddMessage
     */
    private Users mainUser = null;
    
    public AddMessage(java.awt.Frame parent, boolean modal, Users mainUser) {
        super(parent, modal);
        initComponents();
        configDatePickers();
        this.mainUser  = mainUser;
        taMessage.setLineWrap(true);
        taMessage.setWrapStyleWord(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        taMessage = new javax.swing.JTextArea();
        jpFecEntContainerRes = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jbSalir = new javax.swing.JButton();
        jbGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        taMessage.setColumns(20);
        taMessage.setRows(5);
        jScrollPane1.setViewportView(taMessage);

        javax.swing.GroupLayout jpFecEntContainerResLayout = new javax.swing.GroupLayout(jpFecEntContainerRes);
        jpFecEntContainerRes.setLayout(jpFecEntContainerResLayout);
        jpFecEntContainerResLayout.setHorizontalGroup(
            jpFecEntContainerResLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 242, Short.MAX_VALUE)
        );
        jpFecEntContainerResLayout.setVerticalGroup(
            jpFecEntContainerResLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        jLabel1.setText("Fecha:");

        jToolBar1.setRollover(true);

        jbSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/cross.png"))); // NOI18N
        jbSalir.setFocusable(false);
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });
        jToolBar1.add(jbSalir);

        jbGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/disk.png"))); // NOI18N
        jbGuardar.setToolTipText("Guardar");
        jbGuardar.setFocusable(false);
        jbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuardarActionPerformed(evt);
            }
        });
        jToolBar1.add(jbGuardar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jpFecEntContainerRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpFecEntContainerRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarActionPerformed
        if (taMessage.getText() == null || "".equalsIgnoreCase(taMessage.getText())) {
            GeneralFunctions.sendMessage(this, "Error: Mesaje vacio.");
        } else {
            try {
                MessageBoard messageBoard = new MessageBoard();
                MessageBoardBoundary messageBoardBoundary = new MessageBoardBoundary();
                JDatePickerImpl jdpi = (JDatePickerImpl) this.jpFecEntContainerRes.getComponent(0);
                Calendar cal = (Calendar) jdpi.getJFormattedTextField().getValue();
                messageBoard.setMsgUser(this.mainUser);
                messageBoard.setMsgDate(cal.getTime());
                messageBoard.setMsgMessage(taMessage.getText());
                messageBoardBoundary.insert(messageBoard);
                GeneralFunctions.sendMessage(this, "Mensaje Guardado Correctamente.");
                this.dispose();
            } catch (Exception e) {
                throw e;
            }
        }
    }//GEN-LAST:event_jbGuardarActionPerformed
    private void configDatePickers() {
        Calendar calToday = Calendar.getInstance();
        Calendar calTomorrow = Calendar.getInstance();
        calTomorrow.add(Calendar.DATE, 1);

        UtilDateModel modelEntradaRes = new UtilDateModel();
        Properties pEntradaRes = new Properties();
        pEntradaRes.put("text.today", calToday.get(Calendar.DATE));
        pEntradaRes.put("text.month", calToday.get(Calendar.MONTH + 1));
        pEntradaRes.put("text.year", calToday.get(Calendar.YEAR));
        JDatePanelImpl datePanelEntradaRes = new JDatePanelImpl(modelEntradaRes, pEntradaRes);
        JDatePickerImpl datePickerEntradaRes = new JDatePickerImpl(datePanelEntradaRes, new DateLabelFormatter());
        datePickerEntradaRes.setFont(new Font("Arial", Font.PLAIN, 8));
        datePickerEntradaRes.setLocation(0, 0);
        datePickerEntradaRes.setSize(223, 50);
        datePickerEntradaRes.setVisible(true);
        datePickerEntradaRes.setEnabled(true);
        datePickerEntradaRes.getJFormattedTextField().setValue(calToday);
        this.jpFecEntContainerRes.add(datePickerEntradaRes);
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
            java.util.logging.Logger.getLogger(AddMessage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddMessage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddMessage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddMessage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddMessage dialog = new AddMessage(new javax.swing.JFrame(), true, new Users(1));
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JButton jbSalir;
    private javax.swing.JPanel jpFecEntContainerRes;
    private javax.swing.JTextArea taMessage;
    // End of variables declaration//GEN-END:variables
}
