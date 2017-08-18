/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui.administracion.reportes;

import com.ahms.boundary.entity_boundary.AccountTransactionsBoundary;
import com.ahms.boundary.entity_boundary.MultiValueBoundary;
import com.ahms.boundary.entity_boundary.UsersBoundary;
import com.ahms.model.entity.AccountTransactions;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.Users;
//import com.ahms.ui.administracion.reportes.entity.Header;
//import com.ahms.ui.administracion.reportes.entity.ReqUser;
//import com.ahms.ui.administracion.reportes.entity.cancelaciones.Cancelacion;
//import com.ahms.ui.administracion.reportes.entity.cancelaciones.CancelacionRep;
import com.ahms.ui.utils.FOPEngine;
import com.ahms.ui.utils.GeneralFunctions;
import com.ahms.ui.utils.UIConstants;
import com.ahms.ui.utils.XmlMarshaler;
import com.ahms.util.MMKeys;
import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author rsoto
 */
public class CancelacionesRp extends javax.swing.JDialog {

    /**
     * Creates new form CancelacionesRp
     */
    private AccountTransactionsBoundary accountTransactionsBoundary = null;
    private MultiValueBoundary multiValueBoundary = null;

    public CancelacionesRp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        accountTransactionsBoundary = new AccountTransactionsBoundary();
        multiValueBoundary = new MultiValueBoundary();

        UsersBoundary userBoundary = new UsersBoundary();
        List<Users> list = userBoundary.searchAll(new Users());
        for (Users list1 : list) {
            cbUsers.addItem(list1);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbUsers = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        dateIni = new datechooser.beans.DateChooserCombo();
        dateFin = new datechooser.beans.DateChooserCombo();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte de Cancelaciones");
        setResizable(false);

        jLabel14.setText("Fecha de inicio:");

        jLabel15.setText("Fecha Final:");

        jLabel1.setText("Usuario:");

        cbUsers.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/48x48/pie_chart.png"))); // NOI18N
        jButton1.setText("Generar ");
        jButton1.setPreferredSize(new java.awt.Dimension(152, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        dateIni.setCalendarPreferredSize(new java.awt.Dimension(250, 200));
        dateIni.setLocale(new java.util.Locale("es", "MX", ""));

        dateFin.setCalendarPreferredSize(new java.awt.Dimension(250, 200));
        dateFin.setLocale(new java.util.Locale("es", "MX", ""));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateIni, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateFin, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        Date date = new Date();
//        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy_hh:mm");
//        String fileOut = "./reports/RPT_CANCELACION_" + df.format(date) + ".pdf";
//        try {
//            SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
//            AccountTransactions accountTransactions = new AccountTransactions();
//            Calendar calEntrada = dateIni.getCurrent();
//            Calendar calSalida = dateFin.getCurrent();
//            if (GeneralFunctions.compareDates(calEntrada, calSalida,false)) {
//                XmlMarshaler marshaler = new XmlMarshaler(UIConstants.REPORTE_CANCELACIONES_XML_LINUX);
//            AccountTransactionsBoundary atb = new AccountTransactionsBoundary();
//            MultiValueBoundary mvb = new MultiValueBoundary();
//            accountTransactions.setAtrStatus(mvb.findByKey(new MultiValue(MMKeys.AccountsTransactions.STA_CANCELADO_KEY)));
//            if (cbUsers.getSelectedIndex() != 0) {
//                accountTransactions.setAtrUsrMod((Users) cbUsers.getSelectedItem());
//            }
//            List<AccountTransactions> cancelations = atb.findCancelations(accountTransactions, calEntrada.getTime(), calSalida.getTime());
//            if (cancelations != null && cancelations.size()>0) {
//                CancelacionRep rep = mapEntity(cancelations);
//                rep.setHeader(new Header(dateF.format(calEntrada.getTime()), dateF.format(calSalida.getTime()), dateF.format(date)));
//                int response = marshaler.parseObject(rep);
//                if (response > 0) {
//                    FOPEngine.convertToPDF(UIConstants.REPORTE_CANCELACIONES_XSL_LINUX, UIConstants.REPORTE_CANCELACIONES_XML_LINUX, fileOut);
//                    File myFile = new File(fileOut);
//                    Desktop.getDesktop().open(myFile);
//                }
//            }else{
//                GeneralFunctions.sendMessage(this, UIConstants.ERROR_EMPTY_REPORT);
//            }
//            }else{
//                GeneralFunctions.sendMessage(this,UIConstants.ERROR_INVALID_RANGE_DATES);
//            }
//            
//        } catch (Exception ex) {
//            Logger.getLogger(CancelacionesRp.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jButton1ActionPerformed

//    private CancelacionRep mapEntity(List<AccountTransactions> cancelations) {
//        CancelacionRep rep = null;
//        Map<String, ReqUser> userMap = new HashMap<String, ReqUser>();
//        List<Cancelacion> canList = null;
//        Cancelacion cancelacion = null;
//        ReqUser user = null;
//        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//        try {
//            for (AccountTransactions cancelation : cancelations) {
//                user = userMap.get(cancelation.getAtrUsrMod().getUsrCode());
//                if (user == null) {
//                    user = new ReqUser(cancelation.getAtrUsrMod().getUsrCode() + " | " + cancelation.getAtrUsrMod().getFullName());
//                    canList = new ArrayList<Cancelacion>();
//                    cancelacion = new Cancelacion(df.format(cancelation.getAtrDteMod()), cancelation.getSrvId().getSrvDesc());
//                    canList.add(cancelacion);
//                    user.setCancelation(canList);
//                    userMap.put(cancelation.getAtrUsrMod().getUsrCode(), user);
//                } else {
//                    canList = user.getCancelation();
//                    if (canList == null) {
//                        canList = new ArrayList<Cancelacion>();
//                    }
//                    cancelacion = new Cancelacion(df.format(cancelation.getAtrDteMod()), cancelation.getSrvId().getSrvDesc());
//                    canList.add(cancelacion);
//                }
//            }
//            rep = new CancelacionRep();
//            List<ReqUser> userList = new ArrayList<ReqUser>(userMap.values());
//            rep.setUser(userList);
//        } catch (Exception e) {
//            e.printStackTrace();
////            System.out.println("error: " + e.getMessage());
//            GeneralFunctions.appendTrace(e.getStackTrace());
//        }
//        return rep;
//    }

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
            java.util.logging.Logger.getLogger(CancelacionesRp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CancelacionesRp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CancelacionesRp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CancelacionesRp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CancelacionesRp dialog = new CancelacionesRp(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox cbUsers;
    private datechooser.beans.DateChooserCombo dateFin;
    private datechooser.beans.DateChooserCombo dateIni;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    // End of variables declaration//GEN-END:variables

}
