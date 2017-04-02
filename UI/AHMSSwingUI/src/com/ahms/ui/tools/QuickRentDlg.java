package com.ahms.ui.tools;

import com.ahms.ui.configuracion.CustomerRegFrm;
import com.ahms.boundary.entity_boundary.AccountBoundary;
import com.ahms.boundary.entity_boundary.AccountTransactionsBoundary;
import com.ahms.boundary.entity_boundary.FolioTransactionBoundary;
import com.ahms.boundary.entity_boundary.MultiValueBoundary;
import com.ahms.boundary.entity_boundary.PaymentTypesBoundary;
import com.ahms.boundary.entity_boundary.PreferenceDetailBoundary;
import com.ahms.boundary.entity_boundary.ReservationBoundary;
import com.ahms.boundary.entity_boundary.RoomTypesBoundary;
import com.ahms.boundary.entity_boundary.RoomsBoundary;
import com.ahms.boundary.entity_boundary.ServiceBoundary;
import com.ahms.model.entity.Account;
import com.ahms.model.entity.AccountTransactions;
import com.ahms.model.entity.CashOut;
import com.ahms.model.entity.Customers;
import com.ahms.model.entity.FolioTransaction;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.PaymentTypes;
import com.ahms.model.entity.PreferenceDetail;
import com.ahms.model.entity.Reservation;
import com.ahms.model.entity.RoomTypes;
import com.ahms.model.entity.Services;
import com.ahms.ui.MainFrm;
import com.ahms.ui.utils.GeneralFunctions;
import com.ahms.ui.utils.UIConstants;
import com.ahms.util.MMKeys;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jorge
 */
public class QuickRentDlg extends javax.swing.JDialog {

    //QuickRent Instances
    private com.ahms.model.entity.Rooms quickRentRoomAssigned = null;
    private BigDecimal quickRentSubTotal = BigDecimal.ZERO;
    private BigDecimal quickRentIva = BigDecimal.ZERO;
    private BigDecimal quickRentIsh = BigDecimal.ZERO;
    private BigDecimal quickRentTotal = BigDecimal.ZERO;
    private BigDecimal quickrentIvaPercent = BigDecimal.ZERO;
    private BigDecimal quickrentIshPercent = BigDecimal.ZERO;

    private Customers mainCustomer;
    private RoomsBoundary roomsBounday = null;
    private AccountBoundary accountBoundary = null;
    private AccountTransactionsBoundary accountTransactionsBoundary;
    private MultiValueBoundary multiValueBoundary;
    private RoomTypesBoundary roomTypesBoundary;
    private CashOut currentShift = null;
    private PreferenceDetailBoundary preferenceDetailBoundary = null;
    private ServiceBoundary servicesBoundary = null;

    public HashMap<String, ArrayList<FolioTransaction>> mapPayTypes = new HashMap<String, ArrayList<FolioTransaction>>();
    //public StringBuilder sbCardNumbers = null;
    PreferenceDetail preference = null;

    public boolean totalPaid = false;
    public List<com.ahms.model.entity.Rooms> roomAvailableByTypeLst = null;
    public MainFrm mainForm = null;
    private AccountSearchDlg dlgParent = null;

    private Services perExtra = null;

    private FolioTransactionBoundary folioBoundary = new FolioTransactionBoundary();
    private PaymentTypesBoundary paymentTypesBoundary = new PaymentTypesBoundary();

    public QuickRentDlg(MainFrm parent, boolean modal, Customers mainCustomer, CashOut currentShift) {
        super(parent, modal);
        initComponents();
        mainForm = parent;
        this.mainCustomer = mainCustomer;
        this.currentShift = currentShift;
        jbtnLoadCustomer.setEnabled(false);

        roomsBounday = new RoomsBoundary();
        accountBoundary = new AccountBoundary();
        accountTransactionsBoundary = new AccountTransactionsBoundary();
        multiValueBoundary = new MultiValueBoundary();
        roomTypesBoundary = new RoomTypesBoundary();
        preferenceDetailBoundary = new PreferenceDetailBoundary();
        servicesBoundary = new ServiceBoundary();

        mapPayTypes = new HashMap<String, ArrayList<FolioTransaction>>();
        //sbCardNumbers = new StringBuilder("");

        RoomTypes roomTypesActive = new RoomTypes();
        roomTypesActive.setRtyStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.General.STA_ACTIVO_KEY)));
        configTiposCuartos(roomTypesBoundary.findActiveTypes(roomTypesActive));
        perExtra = servicesBoundary.findByCode("PEX");

    }
//CAMBIAR AQUI QUICK RENT DESDE RESERVACIONES 

    public QuickRentDlg(AccountSearchDlg parent, Reservation reservation, CashOut shift, MainFrm mainForm) {
        super(parent, true);
        initComponents();
        this.dlgParent = parent;
        this.mainForm = mainForm;
        this.mainCustomer = reservation.getCusId();
        this.currentShift = shift;

        multiValueBoundary = new MultiValueBoundary();
        preferenceDetailBoundary = new PreferenceDetailBoundary();
        accountBoundary = new AccountBoundary();
        accountTransactionsBoundary = new AccountTransactionsBoundary();
        roomsBounday = new RoomsBoundary();

        //asignar valores de la reservacion
        Calendar calIni = Calendar.getInstance();
        calIni.setTime(reservation.getResFecIni());
        Calendar calFin = Calendar.getInstance();
        calFin.setTime(reservation.getResFecFin());

        dateCsIni.setCurrent(calIni);
        dateCsFin.setCurrent(calFin);
        dateCsIni.setSelectedDate(calIni);
        dateCsFin.setSelectedDate(calFin);

        ReservationBoundary resB = new ReservationBoundary();
        List<Reservation> resList = resB.searchByCusId(reservation);

        jcbQuickRentTipo.setSelectedItem(reservation.getRmsId().getRmsBeds());
        lblCustName.setText(mainCustomer.getFullName());
        lblRfc.setText(mainCustomer.getCusRfc());
        //generar totales de renta
        MultiValue mvIva = multiValueBoundary.findByKey(new MultiValue(MMKeys.Math.IVA_KEY));
        MultiValue mvIsh = multiValueBoundary.findByKey(new MultiValue(MMKeys.Math.ISH_KEY));
        quickrentIvaPercent = new BigDecimal(mvIva.getMvaDescription()).setScale(2, RoundingMode.HALF_UP);
        quickrentIshPercent = new BigDecimal(mvIsh.getMvaDescription()).setScale(2, RoundingMode.HALF_UP);
        int days = GeneralFunctions.getDaysBetweenDates(calIni, calFin);
        //verificar si el Customer tiene tasa preferencial ------------------------------------
        PreferenceDetail preferenceDetail = new PreferenceDetail();
        preferenceDetail.setCusId(mainCustomer);
        preferenceDetail.setRtyId(reservation.getRmsId().getRmsBeds());
        preference = preferenceDetailBoundary.searchByCusId(preferenceDetail);
        // ------------------------------------------------------------------------------------
        quickRentSubTotal = BigDecimal.ZERO;
        quickRentIva = BigDecimal.ZERO;
        quickRentIsh = BigDecimal.ZERO;
        quickRentTotal = BigDecimal.ZERO;
        BigDecimal price = BigDecimal.ZERO;
        BigDecimal totalPerExt = BigDecimal.ZERO;
        int maxPers = 0;
        for (Reservation res : resList) {
            price = preference != null && preference.getPrefId() != null ? preference.getPrefAmount() : res.getRmsId().getRteId().getRtePrice();
            quickRentSubTotal = quickRentSubTotal.add(price.multiply(new BigDecimal(days)).setScale(2, RoundingMode.HALF_UP));
            maxPers += res.getRmsId().getRmsMaxOcu();
        }

//        quickRentIva = quickRentIva.add(quickRentSubTotal.multiply(quickrentIvaPercent).setScale(2, RoundingMode.HALF_EVEN));
//        quickRentIsh = quickRentIsh.add(quickRentSubTotal.multiply(quickrentIshPercent).setScale(2, RoundingMode.HALF_EVEN));
//        quickRentTotal = quickRentSubTotal.add(quickRentIva).add(quickRentIsh).setScale(2, RoundingMode.HALF_UP);
        BigDecimal impuestos = quickrentIvaPercent.add(quickrentIshPercent).add(BigDecimal.ONE);

        Integer numPers = ((Integer) jspNumeroPersonas.getValue()) - maxPers;
        if (numPers > 0) {
            totalPerExt = perExtra.getSrvPrice().multiply(new BigDecimal(numPers));
        }
        quickRentTotal = quickRentSubTotal.add(totalPerExt);

        quickRentIva = quickRentTotal.divide(impuestos, 2, RoundingMode.HALF_UP).multiply(quickrentIvaPercent).setScale(2, RoundingMode.HALF_UP);
        quickRentIsh = quickRentTotal.divide(impuestos, 2, RoundingMode.HALF_UP).multiply(quickrentIshPercent).setScale(2, RoundingMode.HALF_UP);

        BigDecimal rentaSinImpuestos = quickRentSubTotal.divide(impuestos, 2, RoundingMode.HALF_UP);
        lbPrecioCuarto.setText(GeneralFunctions.formatAmount(rentaSinImpuestos));
        jlQRSubTotal.setText(GeneralFunctions.formatAmount(quickRentTotal.divide(impuestos, 2, RoundingMode.HALF_UP)));
        jlQRIVA.setText(GeneralFunctions.formatAmount(quickRentIva));
        lblIsh.setText(GeneralFunctions.formatAmount(quickRentIsh));
        jlQRTotal.setText(GeneralFunctions.formatAmount(quickRentTotal));

        roomAvailableByTypeLst = new ArrayList<com.ahms.model.entity.Rooms>();
        roomAvailableByTypeLst.add(reservation.getRmsId());

        //deshabilitar todos los componentes
        dateCsIni.setEnabled(false);
        dateCsFin.setEnabled(false);
        jspNumeroPersonas.setEnabled(true);
        jcbQuickRentTipo.setEnabled(false);
        jbQRSearchRoom.setEnabled(false);
        jbtnLoadCustomer.setEnabled(false);
        jbQRPagar.setEnabled(true);

    }

    private void configTiposCuartos(List<RoomTypes> lstRoomTypes) {
        jcbQuickRentTipo.removeAllItems();
        RoomTypes newRt = new RoomTypes();
        newRt.setRtyDescription("Seleccionar...");
        jcbQuickRentTipo.addItem(newRt);
        for (RoomTypes roomType : lstRoomTypes) {
            jcbQuickRentTipo.addItem(roomType);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jspNumeroPersonas = new javax.swing.JSpinner();
        jlNumber = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jcbQuickRentTipo = new javax.swing.JComboBox();
        jbQRSearchRoom = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jLabel1 = new javax.swing.JLabel();
        dateCsIni = new datechooser.beans.DateChooserCombo();
        jSplitPane2 = new javax.swing.JSplitPane();
        jLabel2 = new javax.swing.JLabel();
        dateCsFin = new datechooser.beans.DateChooserCombo();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jbtnLoadCustomer = new javax.swing.JButton();
        jlQRTotal = new javax.swing.JLabel();
        jlQRIVA = new javax.swing.JLabel();
        jlQRSubTotal = new javax.swing.JLabel();
        lblCustName = new javax.swing.JLabel();
        lblRfc = new javax.swing.JLabel();
        jbQRPagar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lbPrecioCuarto = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblPerExt = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblIsh = new javax.swing.JLabel();
        label1 = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel10.setText("Personas:");

        jspNumeroPersonas.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));
        jspNumeroPersonas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jspNumeroPersonasStateChanged(evt);
            }
        });

        jlNumber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlNumber.setText("Cuarto(s) disponible(s):");

        jLabel4.setText("Tipo de cuarto:");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jcbQuickRentTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jbQRSearchRoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/16x16/search.png"))); // NOI18N
        jbQRSearchRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbQRSearchRoomActionPerformed(evt);
            }
        });

        jSplitPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jSplitPane1.setDividerSize(0);

        jLabel1.setText("Entrada:    ");
        jSplitPane1.setLeftComponent(jLabel1);
        jSplitPane1.setRightComponent(dateCsIni);

        jSplitPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jSplitPane2.setDividerSize(0);

        jLabel2.setText("Salida:       ");
        jSplitPane2.setLeftComponent(jLabel2);
        jSplitPane2.setRightComponent(dateCsFin);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jlNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbQuickRentTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbQRSearchRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(174, 178, Short.MAX_VALUE)
                        .addComponent(jspNumeroPersonas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jspNumeroPersonas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbQRSearchRoom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcbQuickRentTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addComponent(jlNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel7.setText("Total:");

        jLabel6.setText("IVA:");

        jLabel5.setText("Subtotal:");

        jbtnLoadCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/1445772636_user_manage.png"))); // NOI18N
        jbtnLoadCustomer.setToolTipText("Buscar Cliente");
        jbtnLoadCustomer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtnLoadCustomer.setLabel("Cliente");
        jbtnLoadCustomer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbtnLoadCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLoadCustomerActionPerformed(evt);
            }
        });

        jlQRTotal.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlQRTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlQRTotal.setText("-");

        jlQRIVA.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlQRIVA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlQRIVA.setText("-");

        jlQRSubTotal.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlQRSubTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlQRSubTotal.setText("-");

        lblCustName.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblCustName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCustName.setText("Cliente sin especificar");

        lblRfc.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblRfc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRfc.setText("-");

        jbQRPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/Donate.png"))); // NOI18N
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

        jLabel8.setText("Renta:");

        lbPrecioCuarto.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lbPrecioCuarto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbPrecioCuarto.setText("-");

        jLabel9.setText("Personas Extra");

        lblPerExt.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblPerExt.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPerExt.setText("-");

        jLabel11.setText("ISH:");

        lblIsh.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblIsh.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIsh.setText("-");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 116, Short.MAX_VALUE)
                        .addComponent(jbtnLoadCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbQRPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbPrecioCuarto, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlQRTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlQRIVA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlQRSubTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIsh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPerExt, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblRfc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCustName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lbPrecioCuarto))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblPerExt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlQRSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jlQRIVA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblIsh))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(7, 7, 7))
                    .addComponent(jlQRTotal, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtnLoadCustomer)
                    .addComponent(jbQRPagar)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addComponent(lblCustName, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblRfc, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(190, 190, 190)))
        );

        label1.setAlignment(java.awt.Label.CENTER);
        label1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        label1.setText("RENTAS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbQRPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbQRPagarActionPerformed
        if (mainCustomer != null && mainCustomer.getCusId() != null) {
            try {
                Calendar calEntrada = dateCsIni.getCurrent();
                Calendar calSalida = dateCsFin.getCurrent();

                if (!GeneralFunctions.compareDates(calEntrada, calSalida, true)) {
                    GeneralFunctions.sendMessage(this, UIConstants.ERROR_INVALID_RANGE_DATES);
                    return;
                }

                int numPersonas = (int) jspNumeroPersonas.getValue();

                Account actFind = new Account();
                actFind.setCusId(mainCustomer);
                List<Account> lstAccounts = accountBoundary.findByCusId(actFind);
                Account quickRentAccount = null;
                if (lstAccounts != null & lstAccounts.size() > 0) {
                    quickRentAccount = lstAccounts.get(0);
                }
                if (quickRentAccount != null && quickRentAccount.getActId() != null) {
                    // YA TIENE CUENTA ACTIVA, SOLO ACTUALIZAR
                    quickRentAccount.setActNumPeople(quickRentAccount.getActNumPeople() + numPersonas);
                    quickRentAccount.setActDteMod(new Date());
                    quickRentAccount.setActUsrMod(currentShift.getUsrId());
                    accountBoundary.update(quickRentAccount);
                } else {
                    // NO TIENE CUENTA ACTIVA, APERTURAR
                    quickRentAccount = new Account();
                    quickRentAccount.setActDteMod(Calendar.getInstance().getTime());
                    quickRentAccount.setActUsrMod(currentShift.getUsrId());
                    quickRentAccount.setActFecIni(calEntrada.getTime());
                    quickRentAccount.setActFecFin(calSalida.getTime());
                    quickRentAccount.setActIva(quickrentIvaPercent);
                    quickRentAccount.setActIvaAmt(quickRentIva);
                    quickRentAccount.setActSubtotal(quickRentSubTotal);
                    quickRentAccount.setActTotal(quickRentTotal);
                    quickRentAccount.setActStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.Acounts.STA_ABIERTO_KEY)));
                    quickRentAccount.setAccountTransactionsCollection(null);
                    quickRentAccount.setCusId(mainCustomer);
                    quickRentAccount.setActNumPeople((int) jspNumeroPersonas.getValue());
                    accountBoundary.insert(quickRentAccount);
                }
                //Arreglo que guarda la relacion entre el room y la renta, [indice][0 - room - 1 - atr]
                Object[][] roomsAtrs = new Object[roomAvailableByTypeLst.size()][2];
                int indexRoom = 0;
                for (com.ahms.model.entity.Rooms room : roomAvailableByTypeLst) {
                    //Insertando account transaction
                    AccountTransactions rentTran = new AccountTransactions();
                    rentTran.setAtrDteMod(Calendar.getInstance().getTime());
                    rentTran.setAtrNotes("Renta de Cuarto " + room.getRmsNumber());
                    rentTran.setAtrQuantity(1);
                    rentTran.setAtrStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.AccountsTransactions.STA_PAGADO_KEY)));
                    rentTran.setAtrUsrMod(currentShift.getUsrId());
                    rentTran.setCouId(currentShift);
                    rentTran.setRmsId(room);
                    rentTran.setSrvId(null);
                    rentTran.setActId(quickRentAccount);
                    accountTransactionsBoundary.insert(rentTran);

                    //actualizando el Room
                    room.setRmsStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.Rooms.STA_OCUPADO_KEY)));
                    roomsBounday.update(room);

                    roomsAtrs[indexRoom][0] = room;
                    roomsAtrs[indexRoom][1] = rentTran;
                    indexRoom++;
                }

                //LLamando a paymentModule
                PaymentModuleDlg paymentModule = new PaymentModuleDlg(this, true, quickRentTotal, quickRentAccount);
                paymentModule.setLocationRelativeTo(this);
                paymentModule.setVisible(true);

                if (totalPaid) {
                    //Cuando ya se haya pagado la totalidad del importe, hacer inserciones en folio Transaction
                    BigDecimal payTypeAmount;
                    BigDecimal folioAmount;
                    FolioTransaction fTranFinal;
                    BigDecimal roomsPaid = new BigDecimal(roomsAtrs.length);
                    for (String paytype : mapPayTypes.keySet()) {
                        //Obtener la lista de folio transaction que componen el tipo de pago
                        ArrayList<FolioTransaction> fTranList = mapPayTypes.get(paytype);
                        //iterar lista de tipos de pago
                        for (FolioTransaction fTran : fTranList) {
                            payTypeAmount = fTran.getFtrAmount();
                            //dividir el monto total del tipo de pago / # cuartos rentados (AccountTransacions)
                            folioAmount = payTypeAmount.divide(roomsPaid, 2, RoundingMode.HALF_UP);
                            fTranFinal = null;
                            for (Object[] roomsAtr : roomsAtrs) {
                                AccountTransactions iAtr = (AccountTransactions) roomsAtr[1];
                                //com.ahms.model.entity.Rooms iRoom = (com.ahms.model.entity.Rooms) roomsAtr[0];
                                fTranFinal = new FolioTransaction();
                                fTranFinal.setActId(quickRentAccount);
                                fTranFinal.setAtrId(iAtr);
                                fTranFinal.setCouId(currentShift);
                                fTranFinal.setFtrAmount(folioAmount);
                                fTranFinal.setFtrCardNumber(fTran.getFtrCardNumber());
                                fTranFinal.setFtrDteMod(new Date());
                                fTranFinal.setFtrUsrMod(currentShift.getUsrId());
                                List<PaymentTypes> payList = paymentTypesBoundary.search(new PaymentTypes(paytype));
                                fTranFinal.setPayId(payList.get(0));
                                folioBoundary.insert(fTranFinal);
                            }
                        }
                    }

                    GeneralFunctions.sendMessage(this, "Renta realizada exitosamente.");
                    RoomsBoundary roomsBoundary = new RoomsBoundary();
                    mainForm.configGrid(roomsBoundary.searchAll(new com.ahms.model.entity.Rooms()));
                    this.dispose();
                }

            } catch (Exception e) {
                GeneralFunctions.sendMessage(this, "Ocurrio un error. Por favor contacte con servicio técnico. \n Error: " + e.getMessage());
                //GeneralFunctions.appendTrace(e.getStackTrace());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jbQRPagarActionPerformed

    private void jbQRSearchRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbQRSearchRoomActionPerformed
        try {
            Calendar calEntrada = dateCsIni.getCurrent();
            Calendar calSalida = dateCsFin.getCurrent();

            if (!GeneralFunctions.compareDates(calEntrada, calSalida, true)) {
                GeneralFunctions.sendMessage(this, UIConstants.ERROR_INVALID_RANGE_DATES);
                return;
            }

            RoomTypes tipoSeleccionado = (RoomTypes) jcbQuickRentTipo.getSelectedItem();
            com.ahms.model.entity.Rooms paramRoom = new com.ahms.model.entity.Rooms();
            paramRoom.setRmsBeds(tipoSeleccionado);

            roomAvailableByTypeLst = roomsBounday.findAvailable(paramRoom, calEntrada.getTime(), calSalida.getTime());
            if (roomAvailableByTypeLst != null && roomAvailableByTypeLst.size() > 0) {
                jlNumber.setText("Cuarto(s) disponible(s):");
                MultiRoomSelect roomSel = new MultiRoomSelect(this, true, roomAvailableByTypeLst);
                roomSel.setVisible(true);
                roomAvailableByTypeLst = roomSel.getSelectedRooms();
                for (com.ahms.model.entity.Rooms roomAvailableByType : roomAvailableByTypeLst) {
                    //quickRentRoomAssigned = roomAvailableByType;
                    jlNumber.setText(jlNumber.getText() + "  " + roomAvailableByType.getRmsNumber());
                }
                jbtnLoadCustomer.setEnabled(true);
            } else {
                GeneralFunctions.sendMessage(this, UIConstants.NO_AVAIL_ROOMS + " Favor de revisar las fechas y el tipo de cuarto.");
                jbtnLoadCustomer.setEnabled(false);
            }
        } catch (Exception e) {
            GeneralFunctions.sendMessage(this, "Ocurrio un error al tratar de obtener el cuarto disponible. Por favor contacte al servicio de soporte tecnico.\n Error: " + e.getMessage());
            clearQuickRentInstance();
            //GeneralFunctions.appendTrace(e.getStackTrace());
        }
    }//GEN-LAST:event_jbQRSearchRoomActionPerformed

    private void jbtnLoadCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLoadCustomerActionPerformed
        CustomerRegFrm loadCustomer = new CustomerRegFrm(mainForm, true, mainForm, mainCustomer);
        loadCustomer.setVisible(true);
        mainCustomer = loadCustomer.localCustomer;

        if (mainCustomer != null && mainCustomer.getCusId() != null) {
            //------------------------------------------------        
            lblCustName.setText(mainCustomer.getFullName());
            lblRfc.setText(mainCustomer.getCusRfc());
            //------------------------------------------------
            jbQRPagar.setEnabled(true);
            recalculate();
        }

    }//GEN-LAST:event_jbtnLoadCustomerActionPerformed

    private void recalculate(){
        try {
            RoomTypes tipoSeleccionado = (RoomTypes) jcbQuickRentTipo.getSelectedItem();
            com.ahms.model.entity.Rooms paramRoom = new com.ahms.model.entity.Rooms();
            paramRoom.setRmsBeds(tipoSeleccionado);
            Calendar calEntrada = dateCsIni.getCurrent();
            Calendar calSalida = dateCsFin.getCurrent();
            
            MultiValue mvIva = multiValueBoundary.findByKey(new MultiValue(MMKeys.Math.IVA_KEY));
            MultiValue mvIsh = multiValueBoundary.findByKey(new MultiValue(MMKeys.Math.ISH_KEY));
            quickrentIvaPercent = new BigDecimal(mvIva.getMvaDescription()).setScale(2, RoundingMode.HALF_UP);
            quickrentIshPercent = new BigDecimal(mvIsh.getMvaDescription()).setScale(2, RoundingMode.HALF_UP);

            int days = GeneralFunctions.getDaysBetweenDates(calEntrada, calSalida);
            //verificar si el Customer tiene tasa preferencial ------------------------------------
            PreferenceDetail preferenceDetail = new PreferenceDetail();
            preferenceDetail.setCusId(mainCustomer);
            preferenceDetail.setRtyId((RoomTypes) jcbQuickRentTipo.getSelectedItem());
            preference = preferenceDetailBoundary.searchByCusId(preferenceDetail);
            // ------------------------------------------------------------------------------------
            quickRentSubTotal = BigDecimal.ZERO;
            quickRentIva = BigDecimal.ZERO;
            quickRentIsh = BigDecimal.ZERO;
            quickRentTotal = BigDecimal.ZERO;
            BigDecimal price = BigDecimal.ZERO;
            BigDecimal totalPerExt = BigDecimal.ZERO;

            int maxPers = 0;
            for (com.ahms.model.entity.Rooms room : roomAvailableByTypeLst) {
                maxPers += room.getRmsMaxOcu();
                price = preference != null && preference.getPrefId() != null ? preference.getPrefAmount() : room.getRteId().getRtePrice();
                quickRentSubTotal = quickRentSubTotal.add(price.multiply(new BigDecimal(days)));
            }
            Integer numPers = ((Integer) jspNumeroPersonas.getValue()) - maxPers;// - roomAvailableByTypeLst.size();
            if (numPers > 0) {
                totalPerExt = perExtra.getSrvPrice().multiply(new BigDecimal(numPers));
            }
            quickRentTotal = quickRentSubTotal.add(totalPerExt);
            BigDecimal impuestos = quickrentIvaPercent.add(quickrentIshPercent).add(BigDecimal.ONE);
            quickRentIva = quickRentTotal.divide(impuestos, 2, RoundingMode.HALF_UP).multiply(quickrentIvaPercent).setScale(2, RoundingMode.HALF_UP);
            quickRentIsh = quickRentTotal.divide(impuestos, 2, RoundingMode.HALF_UP).multiply(quickrentIshPercent).setScale(2, RoundingMode.HALF_UP);

            BigDecimal rentaSinImpuestos = quickRentSubTotal.divide(impuestos, 2, RoundingMode.HALF_UP);
            lbPrecioCuarto.setText(GeneralFunctions.formatAmount(rentaSinImpuestos));
            jlQRSubTotal.setText(GeneralFunctions.formatAmount(quickRentTotal.divide(impuestos, 2, RoundingMode.HALF_UP)));
            jlQRIVA.setText(GeneralFunctions.formatAmount(quickRentIva));
            lblIsh.setText(GeneralFunctions.formatAmount(quickRentIsh));
            jlQRTotal.setText(GeneralFunctions.formatAmount(quickRentTotal));
            lblPerExt.setText(GeneralFunctions.formatAmount(totalPerExt.divide(impuestos, 2, RoundingMode.HALF_UP)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jspNumeroPersonasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jspNumeroPersonasStateChanged
        //GeneralFunctions.sendMessage(this, jspNumeroPersonas.getValue().toString());
        //Hacer recalculo de cantidades
        if(mainCustomer.getCusId() != null){
            //GeneralFunctions.sendMessage(this, jspNumeroPersonas.getValue().toString());
            recalculate();
        }
        
    }//GEN-LAST:event_jspNumeroPersonasStateChanged

    public void clearQuickRentInstance() {
        quickRentRoomAssigned = null;
        quickRentSubTotal = BigDecimal.ZERO;
        quickRentIva = BigDecimal.ZERO;
        quickRentTotal = BigDecimal.ZERO;
        quickrentIvaPercent = BigDecimal.ZERO;
        //jlNumber.setVisible(false);
        jbQRPagar.setEnabled(false);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateCsFin;
    private datechooser.beans.DateChooserCombo dateCsIni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JButton jbQRPagar;
    private javax.swing.JButton jbQRSearchRoom;
    private javax.swing.JButton jbtnLoadCustomer;
    private javax.swing.JComboBox jcbQuickRentTipo;
    private javax.swing.JLabel jlNumber;
    private javax.swing.JLabel jlQRIVA;
    private javax.swing.JLabel jlQRSubTotal;
    private javax.swing.JLabel jlQRTotal;
    private javax.swing.JSpinner jspNumeroPersonas;
    private java.awt.Label label1;
    private javax.swing.JLabel lbPrecioCuarto;
    private javax.swing.JLabel lblCustName;
    private javax.swing.JLabel lblIsh;
    private javax.swing.JLabel lblPerExt;
    private javax.swing.JLabel lblRfc;
    // End of variables declaration//GEN-END:variables
}
