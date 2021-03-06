/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui.administracion.reportes;

import com.ahms.boundary.entity_boundary.AccountBoundary;
import com.ahms.boundary.entity_boundary.MultiValueBoundary;
import com.ahms.model.entity.Account;
import com.ahms.model.entity.AccountTransactions;
import com.ahms.model.entity.FolioTransaction;
import com.ahms.model.entity.MultiValue;
//import com.ahms.ui.administracion.reportes.entity.Header;
//import com.ahms.ui.administracion.reportes.entity.servicios.Servicio;
//import com.ahms.ui.administracion.reportes.entity.servicios.ServicioRep;
//import com.ahms.ui.administracion.reportes.entity.servicios.TipoPago;
//import com.ahms.ui.administracion.reportes.entity.servicios.TipoServicio;
import com.ahms.ui.utils.FOPEngine;
import com.ahms.ui.utils.GeneralFunctions;
import com.ahms.ui.utils.UIConstants;
import com.ahms.ui.utils.XmlMarshaler;
import com.ahms.util.MMKeys;
import java.awt.Desktop;
import java.io.File;
import java.math.BigDecimal;
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
public class ServiciosRp extends javax.swing.JDialog {

    /**
     * Creates new form ServiciosRp
     */
    public ServiciosRp(java.awt.Frame parent, boolean modal) {
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
        dateChooserFecEnt = new datechooser.beans.DateChooserCombo();
        dateChooserFecFin = new datechooser.beans.DateChooserCombo();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte de Servicios");
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

        dateChooserFecEnt.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Noto Sans", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(49, 55, 57),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Noto Sans", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(49, 55, 57),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Noto Sans", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Noto Sans", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Noto Sans", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(49, 55, 57),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Noto Sans", java.awt.Font.PLAIN, 12),
                    new java.awt.Color(49, 55, 57),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    dateChooserFecEnt.setLocale(new java.util.Locale("es", "MX", ""));
    dateChooserFecEnt.setShowOneMonth(true);

    dateChooserFecFin.setCalendarPreferredSize(new java.awt.Dimension(250, 200));
    dateChooserFecFin.setLocale(new java.util.Locale("es", "MX", ""));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dateChooserFecEnt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dateChooserFecFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dateChooserFecEnt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(dateChooserFecFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        Date date = new Date();
//        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy_hh:mm");
//        String fileOut = "./reports/RPT_SERVICIOS_" + df.format(date) + ".pdf";
//        try {
//            Calendar calEntrada = dateChooserFecEnt.getCurrent();
//            Calendar calSalida = dateChooserFecFin.getCurrent();
//            if (GeneralFunctions.compareDates(calEntrada, calSalida,false)) {
//                SimpleDateFormat dateF = new SimpleDateFormat("dd/MM/yyyy");
//                XmlMarshaler marshaler = new XmlMarshaler(UIConstants.REPORTE_SERVICIOS_XML_LINUX);
//                AccountBoundary acb = new AccountBoundary();
//                MultiValueBoundary mvb = new MultiValueBoundary();
//                Account acct = new Account();
//                acct.setActStatus(mvb.findByKey(new MultiValue(MMKeys.AccountsTransactions.STA_PAGADO_KEY)));
//                List<Account> list = acb.findServices(acct, calEntrada.getTime(), calSalida.getTime());
//                if (list != null && list.size() > 0) {
//                    ServicioRep rep = mapEntity(list);
//                    rep.setHeader(new Header(dateF.format(calEntrada.getTime()), dateF.format(calSalida.getTime()), df.format(date)));
//                    int response = marshaler.parseObject(rep);
//                    if (response > 0) {
//                        FOPEngine.convertToPDF(UIConstants.REPORTE_SERVICIOS_XSL_LINUX, UIConstants.REPORTE_SERVICIOS_XML_LINUX, fileOut);
//                        File myFile = new File(fileOut);
//                        Desktop.getDesktop().open(myFile);
//                        GeneralFunctions.sendMessage(this, "Reporte de Servicios generado correctamente.");
//                    } else {
//                        GeneralFunctions.sendMessage(this, "No se pudo generar el Reporte de Servicios.");
//                    }
//
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

//    private ServicioRep mapEntity(List<Account> list) {
//        ServicioRep rep = new ServicioRep();
//        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
//        List<TipoPago> tpl = new ArrayList<TipoPago>();
//        List<TipoServicio> tsl = new ArrayList<TipoServicio>();
//        Map<String, TipoPago> tpMap = new HashMap<String, TipoPago>();
//        Map<String, List<Servicio>> tsMap = new HashMap<String, List<Servicio>>();
//        for (Account at : list) {
//            for (FolioTransaction ft : at.getFolioTransactionCollection()) {
//                TipoPago tpago = tpMap.get(ft.getPayId().getPayCode());
//                if (tpago == null) {
//                    tpMap.put(ft.getPayId().getPayCode(), new TipoPago(ft.getPayId().getPayDesc(), ft.getFtrAmount()));
//                } else {
//                    tpago.setTotal(tpago.getTotal().add(ft.getFtrAmount()));
//                }
//            }
//            for (AccountTransactions act : at.getAccountTransactionsCollection()) {
//                if (act.getSrvId() != null) {
//                    BigDecimal ammt = act.getSrvId().getSrvPrice().multiply(new BigDecimal(act.getAtrQuantity()));
//                    if (MMKeys.AccountsTransactions.STA_PAGADO_KEY.equalsIgnoreCase(act.getAtrStatus().getMvaKey())) {
//                        List<Servicio> sl = tsMap.get(act.getSrvId().getSvtId().getSvtDesc());
//                        if (sl == null) {
//                            sl = new ArrayList<Servicio>();
//                            sl.add(new Servicio(act.getSrvId().getSrvDesc(), act.getAtrQuantity(), ammt.toString()));
//                            tsMap.put(act.getSrvId().getSvtId().getSvtDesc(), sl);
//                        } else {
//                            sl.add(new Servicio(act.getSrvId().getSrvDesc(), act.getAtrQuantity(), ammt.toString()));
//                        }
//                    }
//                }
//            }
//        }
//
//        for (TipoPago entry : tpMap.values()) {
//            tpl.add(entry);
//        }
//        for (String key : tsMap.keySet()) {
//            tsl.add(new TipoServicio(key, tsMap.get(key)));
//        }
//        rep.setPymentType(tpl);
//        rep.setServiceType(tsl);
//
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
            java.util.logging.Logger.getLogger(ServiciosRp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServiciosRp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServiciosRp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServiciosRp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ServiciosRp dialog = new ServiciosRp(new javax.swing.JFrame(), true);
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
    private datechooser.beans.DateChooserCombo dateChooserFecEnt;
    private datechooser.beans.DateChooserCombo dateChooserFecFin;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    // End of variables declaration//GEN-END:variables

}
