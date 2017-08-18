/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui.administracion.reportes;

import com.ahms.boundary.entity_boundary.AccountTransactionsBoundary;
import com.ahms.boundary.entity_boundary.MultiValueBoundary;
import com.ahms.model.entity.AccountTransactions;
import com.ahms.model.entity.MultiValue;
//import com.ahms.ui.administracion.reportes.entity.Header;
//import com.ahms.ui.administracion.reportes.entity.ocupacion.OcupacionRep;
//import com.ahms.ui.administracion.reportes.entity.ocupacion.Rent;
//import com.ahms.ui.administracion.reportes.entity.ocupacion.Room;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rsoto
 */
public class OcupacionRp extends javax.swing.JDialog {

    /**
     * Creates new form OcupacionRp
     */
    public OcupacionRp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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
        jButton1 = new javax.swing.JButton();
        fecIni = new datechooser.beans.DateChooserCombo();
        fecFin = new datechooser.beans.DateChooserCombo();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte de Ocupación");
        setModal(true);
        setResizable(false);

        jLabel14.setText("Fecha de inicio:");

        jLabel15.setText("Fecha Final:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/48x48/pie_chart.png"))); // NOI18N
        jButton1.setText("Generar ");
        jButton1.setPreferredSize(new java.awt.Dimension(152, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        fecIni.setCalendarPreferredSize(new java.awt.Dimension(250, 200));
        fecIni.setLocale(new java.util.Locale("es", "MX", ""));

        fecFin.setLocale(new java.util.Locale("es", "MX", ""));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fecFin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fecIni, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fecIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        String fileOut = "/home/jorge/AHMS_FILES/RPT_OCUPACION.pdf";


//        Date date = new Date();
//        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy_hh:mm");
//        String fileOut = "./reports/RPT_OCUPACION_" + df.format(date) + ".pdf";
//        try {
//            Calendar calEntrada = fecIni.getCurrent();
//            Calendar calSalida = fecFin.getCurrent();
//            if (GeneralFunctions.compareDates(calEntrada,calSalida,false )) {
//                XmlMarshaler marshaler = new XmlMarshaler(UIConstants.REPORTE_OCUPACION_XML_LINUX);
//                AccountTransactionsBoundary acb = new AccountTransactionsBoundary();
//                SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
//                MultiValueBoundary mvb = new MultiValueBoundary();
//                AccountTransactions acct = new AccountTransactions();
//                acct.setAtrStatus(mvb.findByKey(new MultiValue(MMKeys.AccountsTransactions.STA_PAGADO_KEY)));
//                List<AccountTransactions> list = acb.findRented(acct, calEntrada.getTime(), calSalida.getTime());
//                if (list != null && list.size() > 0) {
//                    OcupacionRep rep = mapEntity(list);
//                    rep.setHeader(new Header(dateF.format(calEntrada.getTime()), dateF.format(calSalida.getTime()), dateF.format(date)));
//                    int response = marshaler.parseObject(rep);
//                    if (response > 0) {
//                        FOPEngine.convertToPDF(UIConstants.REPORTE_OCUPACION_XSL , UIConstants.REPORTE_OCUPACION_XML, fileOut);
//                        File myFile = new File(fileOut);
//                        Desktop.getDesktop().open(myFile);
//                        this.dispose();
//                        GeneralFunctions.sendMessage(this, "Reporte de ocupacion generado correctamente.");
//                    } else {
//                        GeneralFunctions.sendMessage(this, "No se pudo generar el Reporte de ocupacion.");
//                    }
//                } else {
//                    GeneralFunctions.sendMessage(this, UIConstants.ERROR_EMPTY_REPORT);
//                }
//            } else {
//                GeneralFunctions.sendMessage(this, UIConstants.ERROR_INVALID_RANGE_DATES);
//            }
//
//        } catch (Exception ex) {
//            Logger.getLogger(CancelacionesRp.class.getName()).log(Level.SEVERE, null, ex);
//            GeneralFunctions.sendMessage(this, "Ocurrio un error al generar el reporte.\nContacte con su servicio técnico.\nError: " + ex.getMessage());
//        }
    }//GEN-LAST:event_jButton1ActionPerformed

//    private OcupacionRep mapEntity(List<AccountTransactions> list) {
//        OcupacionRep rep = new OcupacionRep();
//        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
//        Rent rent = new Rent();
//        List<Room> roomL = new ArrayList<Room>();
//        for (AccountTransactions at : list) {
//            String numPeople = String.valueOf(at.getActId().getActNumPeople());
//            String fecIni = df.format(at.getActId().getActFecIni());
//            String fecFin = df.format(at.getActId().getActFecFin());
//            roomL.add(new Room(at.getRmsId().getRmsNumber(), at.getRmsId().getRmsBeds().getRtyDescription(), numPeople, fecIni, fecFin));
//        }
//        rent.setRoom(roomL);
//        rep.setRent(rent);
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
            java.util.logging.Logger.getLogger(OcupacionRp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OcupacionRp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OcupacionRp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OcupacionRp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                OcupacionRp dialog = new OcupacionRp(new javax.swing.JFrame(), true);
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
    private datechooser.beans.DateChooserCombo fecFin;
    private datechooser.beans.DateChooserCombo fecIni;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    // End of variables declaration//GEN-END:variables

}
