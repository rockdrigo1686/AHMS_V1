package com.ahms.ui;

import com.ahms.boundary.security.MultiValueBoundary;
import com.ahms.boundary.security.ReservationBoundary;
import com.ahms.boundary.security.RoomTypesBoundary;
import com.ahms.boundary.security.RoomsBoundary;
import com.ahms.model.entity.CashOut;
import com.ahms.model.entity.Customers;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.Reservation;
import com.ahms.model.entity.RoomTypes;
import com.ahms.ui.utils.DateLabelFormatter;
import com.ahms.ui.utils.GeneralFunctions;
import com.ahms.ui.utils.UIConstants;
import com.ahms.util.MMKeys;
import java.awt.Font;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import javax.swing.DefaultComboBoxModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class QuickResDialog extends javax.swing.JDialog {

    //QuickRes Instances
    private com.ahms.model.entity.Rooms quickResRoomAssigned = null;
    private Customers mainCustomer;
    private RoomsBoundary roomsBounday = null;
    private MultiValueBoundary multiValueBoundary;
    private RoomTypesBoundary roomTypesBoundary;
    private CashOut currentShift = null;
    
    private ReservationBoundary reservationBoundary;
    
    public QuickResDialog(java.awt.Frame parent, boolean modal, Customers mainCustomer, CashOut currentShift) {
        super(parent, modal);
        initComponents();
        this.mainCustomer = mainCustomer;
        this.currentShift = currentShift;
        
        roomsBounday = new RoomsBoundary();
        multiValueBoundary = new MultiValueBoundary();
        roomTypesBoundary = new RoomTypesBoundary();
        reservationBoundary = new ReservationBoundary();
        jbQuickResReserve.setEnabled(false);
        jbtnLoadCustomer.setEnabled(false);
        jlQuickResRoomNumber.setText("-");
        configDatePickers();
        RoomTypes roomTypesActive = new RoomTypes();
        roomTypesActive.setRtyStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.General.STA_ACTIVO_KEY)));
        configTiposCuartos(roomTypesBoundary.findActiveTypes(roomTypesActive));
        
    }
    
    public void clearQuickResInstance() {
        quickResRoomAssigned = null;
        jlQuickResRoomNumber.setVisible(false);
        jbQuickResReserve.setEnabled(false);

    }

    private void configTiposCuartos(List<RoomTypes> lstRoomTypes) {
        DefaultComboBoxModel modelQuickRes = new DefaultComboBoxModel(lstRoomTypes.toArray(new RoomTypes[lstRoomTypes.size()]));
        this.jcbQuickResTipoCuarto.setModel(modelQuickRes);
    }
    
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

        UtilDateModel modelSalidaRes = new UtilDateModel();
        Properties psalidaRes = new Properties();
        psalidaRes.put("text.today", calToday.get(Calendar.DATE));
        psalidaRes.put("text.month", calToday.get(Calendar.MONTH + 1));
        psalidaRes.put("text.year", calToday.get(Calendar.YEAR));
        JDatePanelImpl datePanelSalidaRes = new JDatePanelImpl(modelSalidaRes, psalidaRes);
        JDatePickerImpl datePickerSalidaRes = new JDatePickerImpl(datePanelSalidaRes, new DateLabelFormatter());
        datePickerSalidaRes.setFont(new Font("Arial", Font.PLAIN, 8));
        datePickerSalidaRes.setLocation(0, 0);
        datePickerSalidaRes.setSize(223, 50);
        datePickerSalidaRes.setVisible(true);
        datePickerSalidaRes.setEnabled(true);
        datePickerSalidaRes.getJFormattedTextField().setValue(calTomorrow);
        this.jpFecSalContainerRes.add(datePickerSalidaRes);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlCusName = new javax.swing.JLabel();
        jlRFC = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jpFecEntContainerRes = new javax.swing.JPanel();
        jpFecSalContainerRes = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jcbQuickResTipoCuarto = new javax.swing.JComboBox();
        jlQuickResRoomNumber = new javax.swing.JLabel();
        jbQuickResSearch = new javax.swing.JButton();
        jbQuickResReserve = new javax.swing.JButton();
        jbtnLoadCustomer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jlCusName.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlCusName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlCusName.setText("Cliente sin especificar");

        jlRFC.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlRFC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlRFC.setText("-");

        jLabel14.setText("Entrada:");

        jLabel15.setText("Salida:");

        jpFecEntContainerRes.setMaximumSize(new java.awt.Dimension(223, 50));

        javax.swing.GroupLayout jpFecEntContainerResLayout = new javax.swing.GroupLayout(jpFecEntContainerRes);
        jpFecEntContainerRes.setLayout(jpFecEntContainerResLayout);
        jpFecEntContainerResLayout.setHorizontalGroup(
            jpFecEntContainerResLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 223, Short.MAX_VALUE)
        );
        jpFecEntContainerResLayout.setVerticalGroup(
            jpFecEntContainerResLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        jpFecSalContainerRes.setMaximumSize(new java.awt.Dimension(223, 50));

        javax.swing.GroupLayout jpFecSalContainerResLayout = new javax.swing.GroupLayout(jpFecSalContainerRes);
        jpFecSalContainerRes.setLayout(jpFecSalContainerResLayout);
        jpFecSalContainerResLayout.setHorizontalGroup(
            jpFecSalContainerResLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpFecSalContainerResLayout.setVerticalGroup(
            jpFecSalContainerResLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 33, Short.MAX_VALUE)
        );

        jLabel16.setText("Tipo de Cuarto:");

        jLabel12.setText("Numero de Cuarto:");

        jcbQuickResTipoCuarto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jlQuickResRoomNumber.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlQuickResRoomNumber.setForeground(new java.awt.Color(232, 43, 43));
        jlQuickResRoomNumber.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlQuickResRoomNumber.setText("RoomNmbr");
        jlQuickResRoomNumber.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jbQuickResSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/find.png"))); // NOI18N
        jbQuickResSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbQuickResSearchActionPerformed(evt);
            }
        });

        jbQuickResReserve.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/1445772617_file_edit.png"))); // NOI18N
        jbQuickResReserve.setText("Reservar");
        jbQuickResReserve.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbQuickResReserve.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jbQuickResReserve.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbQuickResReserve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbQuickResReserveActionPerformed(evt);
            }
        });

        jbtnLoadCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/1445772636_user_manage.png"))); // NOI18N
        jbtnLoadCustomer.setText("Cargar Cliente");
        jbtnLoadCustomer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtnLoadCustomer.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jbtnLoadCustomer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbtnLoadCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLoadCustomerActionPerformed(evt);
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
                        .addGap(56, 56, 56)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jlCusName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlRFC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jbtnLoadCustomer)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbQuickResReserve, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jlQuickResRoomNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14)
                                .addComponent(jLabel15))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jpFecEntContainerRes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jpFecSalContainerRes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jcbQuickResTipoCuarto, 0, 142, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbQuickResSearch))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlCusName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlRFC)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpFecEntContainerRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpFecSalContainerRes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(jcbQuickResTipoCuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jbQuickResSearch))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlQuickResRoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbQuickResReserve, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnLoadCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbQuickResReserveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbQuickResReserveActionPerformed
        JDatePickerImpl fEntrada = (JDatePickerImpl) this.jpFecEntContainerRes.getComponent(0);
        JDatePickerImpl fSalida = (JDatePickerImpl) this.jpFecSalContainerRes.getComponent(0);
        Calendar calEntrada = (Calendar) fEntrada.getJFormattedTextField().getValue();
        Calendar calSalida = (Calendar) fSalida.getJFormattedTextField().getValue();

        //Actualizando el estatus del cuarto
        quickResRoomAssigned.setRmsStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.Rooms.STA_RESERVADO_KEY)));
        roomsBounday.update(quickResRoomAssigned);

        //Reservando cuarto
        Reservation reservation = new Reservation();
        reservation.setCusId(mainCustomer);
        reservation.setResDteMod(Calendar.getInstance().getTime());
        reservation.setResUsrMod(currentShift.getUsrId());
        reservation.setResStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.General.STA_ACTIVO_KEY)));
        reservation.setRmsId(quickResRoomAssigned);
        reservation.setResFecIni(calEntrada.getTime());
        reservation.setResFecFin(calSalida.getTime());
        reservationBoundary.insert(reservation);
        GeneralFunctions.sendMessage(this, UIConstants.RESERVATION_OK + " Cuarto reservado: " + quickResRoomAssigned.getRmsNumber());

        //configGrid(roomsBounday.searchAll(new Rooms()));
        clearQuickResInstance();
    }//GEN-LAST:event_jbQuickResReserveActionPerformed

    private void jbQuickResSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbQuickResSearchActionPerformed
        try {
            RoomTypes tipoSeleccionado = (RoomTypes) jcbQuickResTipoCuarto.getSelectedItem();
            com.ahms.model.entity.Rooms paramRoom = new com.ahms.model.entity.Rooms();
            paramRoom.setRmsBeds(tipoSeleccionado);
            JDatePickerImpl fEntrada = (JDatePickerImpl) this.jpFecEntContainerRes.getComponent(0);
            JDatePickerImpl fSalida = (JDatePickerImpl) this.jpFecSalContainerRes.getComponent(0);
            Calendar calEntrada = (Calendar) fEntrada.getJFormattedTextField().getValue();
            Calendar calSalida = (Calendar) fSalida.getJFormattedTextField().getValue();
            com.ahms.model.entity.Rooms roomAvailableByType = roomsBounday.findAvailable(paramRoom, calEntrada.getTime(), calSalida.getTime());
            if (roomAvailableByType != null) {
                quickResRoomAssigned = roomAvailableByType;
                jlQuickResRoomNumber.setText(roomAvailableByType.getRmsNumber());
                jlQuickResRoomNumber.setVisible(true);
                //jbQuickResReserve.setEnabled(true);
                jbtnLoadCustomer.setEnabled(true);
            } else {
                jbtnLoadCustomer.setEnabled(false);
                jbQuickResReserve.setEnabled(false);
                GeneralFunctions.sendMessage(this, UIConstants.NO_AVAIL_ROOMS);
            }
        } catch (Exception e) {
            GeneralFunctions.sendMessage(this, "Ocurrio un error al tratar de obtener el cuarto disponible. Por favor contacte al servicio de soporte tecnico.\n Error: " + e.getMessage());
            //clearQuickRentInstance();
            e.printStackTrace();
        }
    }//GEN-LAST:event_jbQuickResSearchActionPerformed

    private void jbtnLoadCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLoadCustomerActionPerformed
        CustomerReg loadCustomer = new CustomerReg(this, true, mainCustomer);
        loadCustomer.setVisible(true);
        mainCustomer = loadCustomer.localCustomer;
        if(mainCustomer != null && mainCustomer.getCusId() != null){
            jbQuickResReserve.setEnabled(true);
            jlCusName.setText(mainCustomer.getFullName());
            jlRFC.setText(mainCustomer.getCusRfc());
        }
    }//GEN-LAST:event_jbtnLoadCustomerActionPerformed

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
            java.util.logging.Logger.getLogger(QuickResDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuickResDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuickResDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuickResDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QuickResDialog dialog = new QuickResDialog(new MainFrm(), true, new Customers(1), new CashOut(1));
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
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JButton jbQuickResReserve;
    private javax.swing.JButton jbQuickResSearch;
    private javax.swing.JButton jbtnLoadCustomer;
    private javax.swing.JComboBox jcbQuickResTipoCuarto;
    private javax.swing.JLabel jlCusName;
    private javax.swing.JLabel jlQuickResRoomNumber;
    private javax.swing.JLabel jlRFC;
    private javax.swing.JPanel jpFecEntContainerRes;
    private javax.swing.JPanel jpFecSalContainerRes;
    // End of variables declaration//GEN-END:variables
}
