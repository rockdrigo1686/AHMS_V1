package com.ahms.ui.tools;

import com.ahms.ui.configuracion.CustomerRegFrm;
import com.ahms.boundary.entity_boundary.MultiValueBoundary;
import com.ahms.boundary.entity_boundary.ReservationBoundary;
import com.ahms.boundary.entity_boundary.RoomTypesBoundary;
import com.ahms.boundary.entity_boundary.RoomsBoundary;
import com.ahms.model.entity.CashOut;
import com.ahms.model.entity.Customers;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.Reservation;
import com.ahms.model.entity.RoomTypes;
import com.ahms.ui.MainFrm;
import com.ahms.ui.utils.DateLabelFormatter;
import com.ahms.ui.utils.GeneralFunctions;
import com.ahms.ui.utils.UIConstants;
import com.ahms.util.MMKeys;
import java.awt.Font;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import javax.swing.DefaultComboBoxModel;

public class QuickResDlg extends javax.swing.JDialog {

    //QuickRes Instances
    private com.ahms.model.entity.Rooms quickResRoomAssigned = null;
    private Customers mainCustomer;
    private RoomsBoundary roomsBounday = null;
    private MultiValueBoundary multiValueBoundary;
    private RoomTypesBoundary roomTypesBoundary;
    private CashOut currentShift = null;

    private ReservationBoundary reservationBoundary;
    public List<com.ahms.model.entity.Rooms> roomAvailableByTypeLst = null;
    private MainFrm parentFrm = null;

    public QuickResDlg(MainFrm parent, boolean modal, Customers mainCustomer, CashOut currentShift) {
        super(parent, modal);
        initComponents();
        parentFrm = parent;
        this.mainCustomer = mainCustomer;
        this.currentShift = currentShift;

        roomsBounday = new RoomsBoundary();
        multiValueBoundary = new MultiValueBoundary();
        roomTypesBoundary = new RoomTypesBoundary();
        reservationBoundary = new ReservationBoundary();
        jbQuickResReserve.setEnabled(false);
        jbtnLoadCustomer.setEnabled(false);
        RoomTypes roomTypesActive = new RoomTypes();
        roomTypesActive.setRtyStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.General.STA_ACTIVO_KEY)));
        configTiposCuartos(roomTypesBoundary.findActiveTypes(roomTypesActive));

    }

    public void clearQuickResInstance() {
        quickResRoomAssigned = null;
        jbQuickResReserve.setEnabled(false);
    }

    private void configTiposCuartos(List<RoomTypes> lstRoomTypes) {
        DefaultComboBoxModel modelQuickRes = new DefaultComboBoxModel(lstRoomTypes.toArray(new RoomTypes[lstRoomTypes.size()]));
        this.jcbQuickResTipoCuarto.setModel(modelQuickRes);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlCusName = new javax.swing.JLabel();
        jlRFC = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jlCuartos = new javax.swing.JLabel();
        jcbQuickResTipoCuarto = new javax.swing.JComboBox();
        jbQuickResSearch = new javax.swing.JButton();
        jbQuickResReserve = new javax.swing.JButton();
        jbtnLoadCustomer = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jspNumRooms = new javax.swing.JSpinner();
        jToolBar2 = new javax.swing.JToolBar();
        jButton5 = new javax.swing.JButton();
        dateCsIni = new datechooser.beans.DateChooserCombo();
        dateCsFin = new datechooser.beans.DateChooserCombo();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jlCusName.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlCusName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlCusName.setText("Cliente sin especificar");

        jlRFC.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlRFC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlRFC.setText("-");

        jLabel14.setText("Entrada:");

        jLabel15.setText("Salida:");

        jLabel16.setText("Tipo de Cuarto:");

        jlCuartos.setText("Cuarto(s) asigando(s):");

        jcbQuickResTipoCuarto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jbQuickResSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/16x16/search.png"))); // NOI18N
        jbQuickResSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbQuickResSearchActionPerformed(evt);
            }
        });

        jbQuickResReserve.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/1445772617_file_edit.png"))); // NOI18N
        jbQuickResReserve.setText("Reservar");
        jbQuickResReserve.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbQuickResReserve.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jbQuickResReserve.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbQuickResReserve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbQuickResReserveActionPerformed(evt);
            }
        });

        jbtnLoadCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/1445772636_user_manage.png"))); // NOI18N
        jbtnLoadCustomer.setText("Cargar Cliente");
        jbtnLoadCustomer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtnLoadCustomer.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jbtnLoadCustomer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbtnLoadCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLoadCustomerActionPerformed(evt);
            }
        });

        jLabel1.setText("Número de Cuartos:");

        jspNumRooms.setModel(new javax.swing.SpinnerNumberModel(1, 1, 6, 1));

        jToolBar2.setBackground(java.awt.Color.white);
        jToolBar2.setFloatable(false);
        jToolBar2.setPreferredSize(new java.awt.Dimension(30, 38));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/16x16/cross.png"))); // NOI18N
        jButton5.setFocusable(false);
        jButton5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton5);

        dateCsIni.setLocale(new java.util.Locale("es", "MX", ""));

        dateCsFin.setLocale(new java.util.Locale("es", "MX", ""));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtnLoadCustomer)
                        .addGap(18, 18, 18)
                        .addComponent(jbQuickResReserve, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 14, Short.MAX_VALUE)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jspNumRooms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(dateCsIni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dateCsFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlCusName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jlRFC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jlCuartos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jcbQuickResTipoCuarto, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jbQuickResSearch)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(2, 2, 2)))
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlCusName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlRFC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateCsIni, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateCsFin, javax.swing.GroupLayout.PREFERRED_SIZE, 29, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jspNumRooms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateCsFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jspNumRooms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jcbQuickResTipoCuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbQuickResSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlCuartos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbQuickResReserve, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnLoadCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbQuickResReserveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbQuickResReserveActionPerformed
        Calendar calEntrada = dateCsIni.getCurrent();
        Calendar calSalida = dateCsFin.getCurrent();
        
        if(!GeneralFunctions.compareDates(calEntrada, calSalida, true)){
            GeneralFunctions.sendMessage(this, UIConstants.ERROR_INVALID_RANGE_DATES);
            return;
        }

        for (com.ahms.model.entity.Rooms room : roomAvailableByTypeLst) {
            //Actualizando el estatus del cuarto
            room.setRmsStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.Rooms.STA_RESERVADO_KEY)));
            roomsBounday.update(room);

            //Reservando cuarto
            Reservation reservation = new Reservation();
            reservation.setCusId(mainCustomer);
            reservation.setResDteMod(Calendar.getInstance().getTime());
            reservation.setResUsrMod(currentShift.getUsrId());
            reservation.setResStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.General.STA_ACTIVO_KEY)));
            reservation.setRmsId(room);
            reservation.setResFecIni(calEntrada.getTime());
            reservation.setResFecFin(calSalida.getTime());
            reservationBoundary.insert(reservation);
        }
        GeneralFunctions.sendMessage(this, UIConstants.RESERVATION_OK);
        RoomsBoundary roomsBoundary = new RoomsBoundary();
        parentFrm.configGrid(roomsBoundary.searchAll(new com.ahms.model.entity.Rooms()));
        this.dispose();
        //clearQuickResInstance();
    }//GEN-LAST:event_jbQuickResReserveActionPerformed

    private void jbQuickResSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbQuickResSearchActionPerformed
        try {
            
            Calendar calEntrada = dateCsIni.getCurrent();
            Calendar calSalida = dateCsFin.getCurrent();
            
            RoomTypes tipoSeleccionado = (RoomTypes) jcbQuickResTipoCuarto.getSelectedItem();
            com.ahms.model.entity.Rooms paramRoom = new com.ahms.model.entity.Rooms();
            paramRoom.setRmsBeds(tipoSeleccionado);
            

            int numRooms = (int) jspNumRooms.getValue();
            roomAvailableByTypeLst = roomsBounday.findAvailable(paramRoom, calEntrada.getTime(), calSalida.getTime(), numRooms);
            if (roomAvailableByTypeLst != null && roomAvailableByTypeLst.size() > 0) {
                jlCuartos.setText("Cuarto(s) disponible(s):");
                for (com.ahms.model.entity.Rooms room : roomAvailableByTypeLst) {
                    jlCuartos.setText(jlCuartos.getText() + "  " + room.getRmsNumber());
                }
                jbtnLoadCustomer.setEnabled(true);
            } else {
                jbtnLoadCustomer.setEnabled(false);
                jbQuickResReserve.setEnabled(false);
                GeneralFunctions.sendMessage(this, UIConstants.NO_AVAIL_ROOMS + " Favor de revisar las fechas y el tipo de cuarto.");
            }
        } catch (Exception e) {
            GeneralFunctions.sendMessage(this, "Ocurrio un error al tratar de obtener el cuarto disponible. Por favor contacte al servicio de soporte tecnico.\n Error: " + e.getMessage());
            //clearQuickRentInstance();
            GeneralFunctions.appendTrace(e.getStackTrace());
        }
    }//GEN-LAST:event_jbQuickResSearchActionPerformed

    private void jbtnLoadCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLoadCustomerActionPerformed
        CustomerRegFrm loadCustomer = new CustomerRegFrm(this, true, mainCustomer);
        loadCustomer.setVisible(true);
        mainCustomer = loadCustomer.localCustomer;
        if (mainCustomer != null && mainCustomer.getCusId() != null) {
            jbQuickResReserve.setEnabled(true);
            jlCusName.setText(mainCustomer.getFullName());
            jlRFC.setText(mainCustomer.getCusRfc());
        }
    }//GEN-LAST:event_jbtnLoadCustomerActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateCsFin;
    private datechooser.beans.DateChooserCombo dateCsIni;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JButton jbQuickResReserve;
    private javax.swing.JButton jbQuickResSearch;
    private javax.swing.JButton jbtnLoadCustomer;
    private javax.swing.JComboBox jcbQuickResTipoCuarto;
    private javax.swing.JLabel jlCuartos;
    private javax.swing.JLabel jlCusName;
    private javax.swing.JLabel jlRFC;
    private javax.swing.JSpinner jspNumRooms;
    // End of variables declaration//GEN-END:variables
}
