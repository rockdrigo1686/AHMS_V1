package com.ahms.ui;

import com.ahms.boundary.security.AccountBoundary;
import com.ahms.boundary.security.AccountTransactionsBoundary;
import com.ahms.boundary.security.MultiValueBoundary;
import com.ahms.boundary.security.PreferenceDetailBoundary;
import com.ahms.boundary.security.RoomTypesBoundary;
import com.ahms.boundary.security.RoomsBoundary;
import com.ahms.model.entity.Account;
import com.ahms.model.entity.AccountTransactions;
import com.ahms.model.entity.CashOut;
import com.ahms.model.entity.Customers;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.PreferenceDetail;
import com.ahms.model.entity.RoomTypes;
import com.ahms.ui.utils.DateLabelFormatter;
import com.ahms.ui.utils.GeneralFunctions;
import com.ahms.ui.utils.UIConstants;
import com.ahms.util.MMKeys;
import java.awt.Font;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import javax.swing.SpinnerNumberModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author jorge
 */
public class QuickRentDialog extends javax.swing.JDialog {

    //QuickRent Instances
    private com.ahms.model.entity.Rooms quickRentRoomAssigned = null;
    private BigDecimal quickRentSubTotal = BigDecimal.ZERO;
    private BigDecimal quickRentIva = BigDecimal.ZERO;
    private BigDecimal quickRentTotal = BigDecimal.ZERO;
    private BigDecimal quickrentIvaPercent = BigDecimal.ZERO;
    
    private Customers mainCustomer;
    private RoomsBoundary roomsBounday = null;
    private AccountBoundary accountBoundary = null;
    private AccountTransactionsBoundary accountTransactionsBoundary;
    private MultiValueBoundary multiValueBoundary;
    private RoomTypesBoundary roomTypesBoundary;
    private CashOut currentShift = null;
    private PreferenceDetailBoundary preferenceDetailBoundary = null;
    
    public boolean totalPaid = false;
    
    public QuickRentDialog(MainFrm parent, boolean modal, Customers mainCustomer, CashOut currentShift) {
        super(parent, modal);
        initComponents();
        this.mainCustomer = mainCustomer;
        this.currentShift = currentShift;
        
        jbtnLoadCustomer.setEnabled(false);
        
        roomsBounday = new RoomsBoundary();
        accountBoundary = new AccountBoundary();
        accountTransactionsBoundary = new AccountTransactionsBoundary();
        multiValueBoundary = new MultiValueBoundary();
        roomTypesBoundary = new RoomTypesBoundary();
        preferenceDetailBoundary = new PreferenceDetailBoundary();

        jlQRRoomNumber.setText("-");
        configDatePickers();
        RoomTypes roomTypesActive = new RoomTypes();
        roomTypesActive.setRtyStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.General.STA_ACTIVO_KEY)));
        configTiposCuartos(roomTypesBoundary.findActiveTypes(roomTypesActive));

    }
    
    private void configTiposCuartos(List<RoomTypes> lstRoomTypes) {
        jcbQuickRentTipo.removeAllItems();
        jcbQuickRentTipo.addItem(new RoomTypes());
        for (RoomTypes roomType : lstRoomTypes) {
            jcbQuickRentTipo.addItem(roomType);
        }
    }
    
    private void configDatePickers() {

        Calendar calToday = Calendar.getInstance();
        Calendar calTomorrow = Calendar.getInstance();
        calTomorrow.add(Calendar.DATE, 1);

        UtilDateModel modelEntrada = new UtilDateModel();
        Properties pEntrada = new Properties();
        pEntrada.put("text.today", calToday.get(Calendar.DATE));
        pEntrada.put("text.month", calToday.get(Calendar.MONTH + 1));
        pEntrada.put("text.year", calToday.get(Calendar.YEAR));
        JDatePanelImpl datePanelEntrada = new JDatePanelImpl(modelEntrada, pEntrada);
        JDatePickerImpl datePickerEntrada = new JDatePickerImpl(datePanelEntrada, new DateLabelFormatter());
        datePickerEntrada.setFont(new Font("Arial", Font.PLAIN, 8));
        datePickerEntrada.setLocation(0, 0);
        datePickerEntrada.setSize(223, 50);
        datePickerEntrada.setVisible(true);
        datePickerEntrada.setEnabled(true);
        datePickerEntrada.getJFormattedTextField().setValue(calToday);
        this.jpFecEntContainer.add(datePickerEntrada);
        
        UtilDateModel modelSalida = new UtilDateModel();
        Properties pSalida = new Properties();
        pSalida.put("text.today", calTomorrow.get(Calendar.DATE));
        pSalida.put("text.month", calTomorrow.get(Calendar.MONTH + 1));
        pSalida.put("text.year", calTomorrow.get(Calendar.YEAR));
        JDatePanelImpl datePanelSalida = new JDatePanelImpl(modelSalida, pSalida);
        JDatePickerImpl datePickerSalida = new JDatePickerImpl(datePanelSalida, new DateLabelFormatter());
        datePanelSalida.setFont(new Font("Arial", Font.PLAIN, 8));
        datePickerSalida.setLocation(0, 0);
        datePickerSalida.setSize(223, 50);
        datePickerSalida.setVisible(true);
        datePickerSalida.setEnabled(true);
        datePickerSalida.getJFormattedTextField().setValue(calTomorrow);
        this.jpFecSalContainer.add(datePickerSalida);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();
        lblCustName = new javax.swing.JLabel();
        lblRfc = new javax.swing.JLabel();
        jbtnLoadCustomer = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jlQRSubTotal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jlQRIVA = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jlQRTotal = new javax.swing.JLabel();
        jbQRPagar = new javax.swing.JButton();
        panel2 = new java.awt.Panel();
        jLabel4 = new javax.swing.JLabel();
        jcbQuickRentTipo = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jlQRRoomNumber = new javax.swing.JLabel();
        jspNumeroPersonas = new javax.swing.JSpinner();
        jbQRSearchRoom = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jLabel1 = new javax.swing.JLabel();
        jpFecEntContainer = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jLabel2 = new javax.swing.JLabel();
        jpFecSalContainer = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        panel4 = new java.awt.Panel();
        label1 = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        lblCustName.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblCustName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCustName.setText("Cliente sin especificar");

        lblRfc.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblRfc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRfc.setText("-");

        jbtnLoadCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/1445772636_user_manage.png"))); // NOI18N
        jbtnLoadCustomer.setToolTipText("Buscar Cliente");
        jbtnLoadCustomer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtnLoadCustomer.setLabel("Cliente");
        jbtnLoadCustomer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbtnLoadCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLoadCustomerActionPerformed(evt);
            }
        });

        jLabel5.setText("Subtotal:");

        jlQRSubTotal.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlQRSubTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlQRSubTotal.setText("-");

        jLabel6.setText("IVA:");

        jlQRIVA.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlQRIVA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlQRIVA.setText("-");

        jLabel7.setText("Total:");

        jlQRTotal.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlQRTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlQRTotal.setText("-");

        jbQRPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack3/Donate.png"))); // NOI18N
        jbQRPagar.setText("Pagar");
        jbQRPagar.setEnabled(false);
        jbQRPagar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbQRPagar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jbQRPagar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbQRPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbQRPagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblRfc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                        .addComponent(jbtnLoadCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbQRPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jlQRTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlQRIVA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlQRSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(lblCustName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCustName, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRfc, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlQRSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlQRIVA)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlQRTotal)
                    .addComponent(jLabel7))
                .addGap(10, 10, 10)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbQRPagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnLoadCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panel2.setForeground(new java.awt.Color(1, 1, 1));

        jLabel4.setText("Tipo de cuarto:");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jcbQuickRentTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Número de cuarto:");

        jLabel10.setText("Personas:");

        jlQRRoomNumber.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlQRRoomNumber.setForeground(new java.awt.Color(232, 43, 43));
        jlQRRoomNumber.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlQRRoomNumber.setText("RoomNmbr");
        jlQRRoomNumber.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jbQRSearchRoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/find.png"))); // NOI18N
        jbQRSearchRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbQRSearchRoomActionPerformed(evt);
            }
        });

        jSplitPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jSplitPane1.setDividerSize(0);

        jLabel1.setText("Entrada:    ");
        jSplitPane1.setLeftComponent(jLabel1);

        jpFecEntContainer.setMaximumSize(new java.awt.Dimension(223, 50));
        jpFecEntContainer.setPreferredSize(new java.awt.Dimension(223, 50));

        javax.swing.GroupLayout jpFecEntContainerLayout = new javax.swing.GroupLayout(jpFecEntContainer);
        jpFecEntContainer.setLayout(jpFecEntContainerLayout);
        jpFecEntContainerLayout.setHorizontalGroup(
            jpFecEntContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpFecEntContainerLayout.setVerticalGroup(
            jpFecEntContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 33, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(jpFecEntContainer);

        jSplitPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jSplitPane2.setDividerSize(0);

        jLabel2.setText("Salida:       ");
        jSplitPane2.setLeftComponent(jLabel2);

        jpFecSalContainer.setMaximumSize(new java.awt.Dimension(223, 50));
        jpFecSalContainer.setPreferredSize(new java.awt.Dimension(223, 50));

        javax.swing.GroupLayout jpFecSalContainerLayout = new javax.swing.GroupLayout(jpFecSalContainer);
        jpFecSalContainer.setLayout(jpFecSalContainerLayout);
        jpFecSalContainerLayout.setHorizontalGroup(
            jpFecSalContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 217, Short.MAX_VALUE)
        );
        jpFecSalContainerLayout.setVerticalGroup(
            jpFecSalContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 33, Short.MAX_VALUE)
        );

        jSplitPane2.setRightComponent(jpFecSalContainer);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSplitPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jspNumeroPersonas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel2Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jlQRRoomNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jcbQuickRentTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbQRSearchRoom))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(panel2Layout.createSequentialGroup()
                        .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSplitPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jcbQuickRentTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jbQRSearchRoom))
                        .addGap(13, 13, 13)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlQRRoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jspNumeroPersonas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        label1.setAlignment(java.awt.Label.CENTER);
        label1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        label1.setText("RENTAS");

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbQRPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbQRPagarActionPerformed
        if(mainCustomer != null && mainCustomer.getCusId() != null){
            try {
                //aperturando la cuenta
                JDatePickerImpl fEntrada = (JDatePickerImpl) this.jpFecEntContainer.getComponent(0);
                JDatePickerImpl fSalida = (JDatePickerImpl) this.jpFecSalContainer.getComponent(0);
                Calendar calEntrada = (Calendar) fEntrada.getJFormattedTextField().getValue();
                Calendar calSalida = (Calendar) fSalida.getJFormattedTextField().getValue();
                Account quickRentAccount = new Account();
                quickRentAccount.setActDteMod(Calendar.getInstance().getTime());
                quickRentAccount.setActUsrMod(currentShift.getUsrId());
                //quickRentAccount.setActUsrMod(new Users(1));
                quickRentAccount.setActFecIni(calEntrada.getTime());
                quickRentAccount.setActFecFin(calSalida.getTime());
                quickRentAccount.setActIva(quickrentIvaPercent);
                quickRentAccount.setActIvaAmt(quickRentIva);
                quickRentAccount.setActSubtotal(quickRentSubTotal);
                quickRentAccount.setActTotal(quickRentTotal);
                quickRentAccount.setActStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.Acounts.STA_ABIERTO_KEY)));
                quickRentAccount.setAccountTransactionsCollection(null);
                quickRentAccount.setCusId(mainCustomer);
                accountBoundary.insert(quickRentAccount);

                //Insertando account transaction
                AccountTransactions rentTran = new AccountTransactions();
                rentTran.setAtrDteMod(Calendar.getInstance().getTime());
                rentTran.setAtrNotes("Renta de Cuarto " + quickRentRoomAssigned.getRmsNumber());
                rentTran.setAtrQuantity(1);
                rentTran.setAtrStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.AccountsTransactions.STA_PAGADO_KEY)));
                rentTran.setAtrUsrMod(currentShift.getUsrId());
                //rentTran.setAtrUsrMod(new Users(1));
                rentTran.setCouId(currentShift);
                rentTran.setRmsId(quickRentRoomAssigned);
                rentTran.setSrvId(null);
                rentTran.setActId(quickRentAccount);
                accountTransactionsBoundary.insert(rentTran);

                //LLamando a paymentModule
                PaymentModule paymentModule = new PaymentModule(this, true, quickRentTotal, quickRentAccount);
                paymentModule.setLocationRelativeTo(this);
                paymentModule.setVisible(true);
                
                //actualizando el Room
                quickRentRoomAssigned.setRmsStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.Rooms.STA_OCUPADO_KEY)));
                roomsBounday.update(quickRentRoomAssigned);
                
                if(totalPaid){
                    GeneralFunctions.sendMessage(this, "Renta realizada exitosamente.");
                    this.dispose();
                }
                
            } catch (Exception e) {
                GeneralFunctions.sendMessage(this, "Ocurrio un error. Por favor contacte con servicio técnico. \n Error: " + e.getMessage());
            }            
        }        
    }//GEN-LAST:event_jbQRPagarActionPerformed

    private void jbQRSearchRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbQRSearchRoomActionPerformed
        try {
            RoomTypes tipoSeleccionado = (RoomTypes) jcbQuickRentTipo.getSelectedItem();
            com.ahms.model.entity.Rooms paramRoom = new com.ahms.model.entity.Rooms();
            paramRoom.setRmsBeds(tipoSeleccionado);
            JDatePickerImpl fEntrada = (JDatePickerImpl) this.jpFecEntContainer.getComponent(0);
            JDatePickerImpl fSalida = (JDatePickerImpl) this.jpFecSalContainer.getComponent(0);
            Calendar calEntrada = (Calendar) fEntrada.getJFormattedTextField().getValue();
            Calendar calSalida = (Calendar) fSalida.getJFormattedTextField().getValue();
            com.ahms.model.entity.Rooms roomAvailableByType = roomsBounday.findAvailable(paramRoom, calEntrada.getTime(), calSalida.getTime());
            if (roomAvailableByType != null) {
                quickRentRoomAssigned = roomAvailableByType;
                jlQRRoomNumber.setText(roomAvailableByType.getRmsNumber());
                jlQRRoomNumber.setVisible(true);                
                jbtnLoadCustomer.setEnabled(true);
            } else {
                GeneralFunctions.sendMessage(this, UIConstants.NO_AVAIL_ROOMS);
                jbtnLoadCustomer.setEnabled(false);
            }
        } catch (Exception e) {
            GeneralFunctions.sendMessage(this, "Ocurrio un error al tratar de obtener el cuarto disponible. Por favor contacte al servicio de soporte tecnico.\n Error: " + e.getMessage());
            clearQuickRentInstance();
            e.printStackTrace();
        }
    }//GEN-LAST:event_jbQRSearchRoomActionPerformed

    private void jbtnLoadCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLoadCustomerActionPerformed
        CustomerReg loadCustomer = new CustomerReg(this, true, mainCustomer);
        loadCustomer.setVisible(true);
        mainCustomer = loadCustomer.localCustomer;
        
        if(mainCustomer != null && mainCustomer.getCusId() != null){
            //------------------------------------------------        
            lblCustName.setText(mainCustomer.getFullName());
            lblRfc.setText(mainCustomer.getCusRfc());
            //------------------------------------------------
            RoomTypes tipoSeleccionado = (RoomTypes) jcbQuickRentTipo.getSelectedItem();
            com.ahms.model.entity.Rooms paramRoom = new com.ahms.model.entity.Rooms();
            paramRoom.setRmsBeds(tipoSeleccionado);
            JDatePickerImpl fEntrada = (JDatePickerImpl) this.jpFecEntContainer.getComponent(0);
            JDatePickerImpl fSalida = (JDatePickerImpl) this.jpFecSalContainer.getComponent(0);
            Calendar calEntrada = (Calendar) fEntrada.getJFormattedTextField().getValue();
            Calendar calSalida = (Calendar) fSalida.getJFormattedTextField().getValue();

            jbQRPagar.setEnabled(true);
            jspNumeroPersonas.setModel(new SpinnerNumberModel(1, 1, quickRentRoomAssigned.getRmsMaxOcu(), 1));
            //generar totales de renta
            MultiValue mvIva = multiValueBoundary.findByKey(new MultiValue(MMKeys.Math.IVA_KEY));
            quickrentIvaPercent = new BigDecimal(mvIva.getMvaDescription()).setScale(2, RoundingMode.HALF_EVEN);

            long days = GeneralFunctions.getDaysBetweenDates(calEntrada, calSalida) + 1;
            //verificar si el Customer tiene tasa preferencial ------------------------------------
            PreferenceDetail preferenceDetail = new PreferenceDetail();
            preferenceDetail.setCusId(mainCustomer);
            preferenceDetail.setRmsId(quickRentRoomAssigned);
            PreferenceDetail preference = preferenceDetailBoundary.searchByCusId(preferenceDetail);
            // ------------------------------------------------------------------------------------
            BigDecimal price = preference != null && preference.getPrefId() != null ? preference.getPrefAmount() : quickRentRoomAssigned.getRteId().getRtePrice();
            quickRentSubTotal = price.multiply(new BigDecimal(days)).setScale(2, RoundingMode.HALF_EVEN);
            quickRentIva = quickRentSubTotal.multiply(quickrentIvaPercent).setScale(2, RoundingMode.HALF_EVEN);
            quickRentTotal = quickRentSubTotal.add(quickRentIva).setScale(2, RoundingMode.HALF_EVEN);

            jlQRSubTotal.setText(quickRentSubTotal.toString());
            jlQRIVA.setText(quickRentIva.toString());
            jlQRTotal.setText(quickRentTotal.toString());
        }
         
    }//GEN-LAST:event_jbtnLoadCustomerActionPerformed

    public void clearQuickRentInstance() {
        quickRentRoomAssigned = null;
        quickRentSubTotal = BigDecimal.ZERO;
        quickRentIva = BigDecimal.ZERO;
        quickRentTotal = BigDecimal.ZERO;
        quickrentIvaPercent = BigDecimal.ZERO;
        jlQRRoomNumber.setVisible(false);
        jbQRPagar.setEnabled(false);

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
            java.util.logging.Logger.getLogger(QuickRentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuickRentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuickRentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuickRentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QuickRentDialog dialog = new QuickRentDialog(new MainFrm(), true, new Customers(1), new CashOut(1));
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JButton jbQRPagar;
    private javax.swing.JButton jbQRSearchRoom;
    private javax.swing.JButton jbtnLoadCustomer;
    private javax.swing.JComboBox jcbQuickRentTipo;
    private javax.swing.JLabel jlQRIVA;
    private javax.swing.JLabel jlQRRoomNumber;
    private javax.swing.JLabel jlQRSubTotal;
    private javax.swing.JLabel jlQRTotal;
    private javax.swing.JPanel jpFecEntContainer;
    private javax.swing.JPanel jpFecSalContainer;
    private javax.swing.JSpinner jspNumeroPersonas;
    private java.awt.Label label1;
    private javax.swing.JLabel lblCustName;
    private javax.swing.JLabel lblRfc;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private java.awt.Panel panel4;
    // End of variables declaration//GEN-END:variables
}
