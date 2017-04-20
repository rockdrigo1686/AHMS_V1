package com.ahms.ui.tools;

import com.ahms.boundary.entity_boundary.AccountBoundary;
import com.ahms.boundary.entity_boundary.AccountTransactionsBoundary;
import com.ahms.boundary.entity_boundary.CashOutBoundary;
import com.ahms.boundary.entity_boundary.FolioTransactionBoundary;
import com.ahms.boundary.entity_boundary.MultiValueBoundary;
import com.ahms.boundary.entity_boundary.PaymentTypesBoundary;
import com.ahms.boundary.entity_boundary.RoomsBoundary;
import com.ahms.model.entity.Account;
import com.ahms.model.entity.AccountTransactions;
import com.ahms.model.entity.CashOut;
import com.ahms.model.entity.Customers;
import com.ahms.model.entity.FolioTransaction;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.PaymentTypes;
import com.ahms.ui.utils.GeneralFunctions;
import com.ahms.util.MMKeys;
import java.awt.Color;
import java.awt.Component;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jorge
 */
public class CheckOutDlg extends javax.swing.JDialog {

    public BigDecimal totalAccount = BigDecimal.ZERO;
    public BigDecimal totalPending = BigDecimal.ZERO;
    public BigDecimal total = BigDecimal.ZERO;
    public BigDecimal subtotal = BigDecimal.ZERO;
    public BigDecimal totalIVA = BigDecimal.ZERO;
    public BigDecimal totalISH = BigDecimal.ZERO;
    public BigDecimal totalPagado = BigDecimal.ZERO;
    public BigDecimal totalOriginal = BigDecimal.ZERO;
    public BigDecimal totalCambio = BigDecimal.ZERO;
    private Customers customerGlobal = null;
    private Account account= null;;
    private AccountBoundary accountBoundary= null;;
    private RoomsBoundary roomsBoundary= null;;
    private AccountTransactionsBoundary accountTransactionsBoundary= null;;
    private MultiValueBoundary multiValueBoundary= null;;
    private JDialog parentFrame = null;;
    private Map<Integer,Integer> mapInactiveRows = null;
    
    public HashMap<String, ArrayList<FolioTransaction>> mapPayTypes = null;
    public StringBuilder sbCardNumbers = null;
    
    private CashOut currentShift;
    private CashOutBoundary cashOutBoundary;
    private FolioTransactionBoundary folioBoundary = new FolioTransactionBoundary();
    private PaymentTypesBoundary paymentTypesBoundary = new PaymentTypesBoundary();

    public CheckOutDlg(JDialog parent, boolean modal, Account account) {
        super(parent, modal);
        initComponents();
        mapPayTypes = new HashMap<String, ArrayList<FolioTransaction>>();
        mapInactiveRows = new HashMap<Integer,Integer>();
        parentFrame =  parent;
        accountBoundary = new AccountBoundary();
        roomsBoundary = new RoomsBoundary();
        accountTransactionsBoundary = new AccountTransactionsBoundary();
        multiValueBoundary = new MultiValueBoundary();
        jlCambio.setVisible(false);
        jlMontoCambio.setVisible(false);
        this.account = account;
        customerGlobal = account.getCusId();
        jlNombre.setText(customerGlobal.getFullName()+ " - " + customerGlobal.getCusRfc());
        
        cashOutBoundary = new CashOutBoundary();
        currentShift = cashOutBoundary.getCurrentShift();
        sbCardNumbers = new StringBuilder("");
        generateGridSimple(customerGlobal);
    }

    public void regenerateTotals() {
        jlSubtotal.setText("$" + subtotal.toString());
        jlIVA.setText("$" + totalIVA.toString());
        jlISH.setText("$" + totalISH.toString());
        jlTotal.setText("$" + totalOriginal.toString());
        jlPagado.setText("$" + totalPagado.toString());
        jlPendiente.setText("$" + totalPending.toString());
        if (totalPending.compareTo(BigDecimal.ZERO)<=0 ) {
            activeAccountClose();
        }
    }

    public void activeAccountClose() {
        jbPay.setEnabled(false);
        jbCloseAccount.setEnabled(true);
    }

    public void setCambio() {
        jlCambio.setVisible(true);
        jlMontoCambio.setVisible(true);
        jlMontoCambio.setText("$" + totalCambio.toString());
    }

    private int serviciosPendientesDePagar = 0;
    private void generateGridSimple(Customers customer) {
        try {

            mapInactiveRows = new HashMap<Integer,Integer>();
            
            Vector<String> vctColumns = new Vector<>();
            vctColumns.add("Cuarto");
            vctColumns.add("Cantidad");
            vctColumns.add("Descripción");
            vctColumns.add("Costo");

            total = BigDecimal.ZERO;
            
            Vector<Vector> rows = new Vector<>();
            List<AccountTransactions> resultList = accountTransactionsBoundary.findByCusId(customer);
            Integer contador = 0;
            serviciosPendientesDePagar = 0;
            for (AccountTransactions a : resultList) {
                Vector<Object> vctRow = new Vector<>();
                vctRow.add(a.getRmsId().getRmsNumber());
                vctRow.add(a.getAtrQuantity());
                vctRow.add(a.getSrvId() != null ? a.getSrvId().getSrvDesc() : a.getAtrNotes());
                vctRow.add(a.getSrvId() != null ? (a.getAtrQuantity() * a.getSrvId().getSrvPrice().doubleValue()) : "");
                rows.add(vctRow);

                if (a.getAtrStatus().getMvaKey().equals(MMKeys.AccountsTransactions.STA_PENDIENTE_KEY)) {
                    serviciosPendientesDePagar ++;
                    //solo sumar al total los que estan pendientes por pagar
                    total = total.add(new BigDecimal((a.getAtrQuantity() * a.getSrvId().getSrvPrice().doubleValue())));
                } else {
                    mapInactiveRows.put(contador, contador);
                }
                contador++;
            }

            MultiValue mvIva = multiValueBoundary.findByKey(new MultiValue(MMKeys.Math.IVA_KEY));
            MultiValue mvIsh = multiValueBoundary.findByKey(new MultiValue(MMKeys.Math.ISH_KEY));
            BigDecimal quickrentIvaPercent = new BigDecimal(mvIva.getMvaDescription()).setScale(2, RoundingMode.HALF_UP);
            BigDecimal quickrentIshPercent = new BigDecimal(mvIsh.getMvaDescription()).setScale(2, RoundingMode.HALF_UP);
            BigDecimal impuestos = quickrentIvaPercent.add(quickrentIshPercent).add(BigDecimal.ONE);
            
            totalIVA = total.divide(impuestos, 2, RoundingMode.HALF_UP).multiply(quickrentIvaPercent).setScale(2, RoundingMode.HALF_UP);
            totalISH = total.divide(impuestos, 2, RoundingMode.HALF_UP).multiply(quickrentIshPercent).setScale(2, RoundingMode.HALF_UP);
            //totalAccount = totalPending = total.add(totalIVA);
            totalAccount = totalPending = total;

            total = total.setScale(2, RoundingMode.HALF_UP);
            totalIVA = totalIVA.setScale(2, RoundingMode.HALF_UP);
            totalAccount = totalAccount.setScale(2, RoundingMode.HALF_UP);
            totalPending = totalPending.setScale(2, RoundingMode.HALF_UP);
            totalPagado = totalPagado.setScale(2, RoundingMode.HALF_UP);
            subtotal = total.subtract(totalIVA).subtract(totalISH).setScale(2, RoundingMode.HALF_UP);
            totalOriginal = totalAccount;
            //vinculando al UI
            regenerateTotals();

            DefaultTableModel model = new DefaultTableModel(rows, vctColumns) {
                private static final long serialVersionUID = 1L;

                @Override
                public Class getColumnClass(int column) {
                    return getValueAt(0, column).getClass();
                }

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
                
            };
            jtCheckoutDetalle.setModel(model);
            jtCheckoutDetalle.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
            jtCheckoutDetalle.getColumnModel().getColumn(0).setMaxWidth(100);
            jtCheckoutDetalle.getColumnModel().getColumn(1).setMaxWidth(100);
            jtCheckoutDetalle.getColumnModel().getColumn(2).setMaxWidth(600);
            jtCheckoutDetalle.getColumnModel().getColumn(3).setMaxWidth(100);
            jtCheckoutDetalle.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
            {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
                {
                    final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    if(mapInactiveRows.containsKey(row)){
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(new Color(249, 87, 87));
                    }
                    return c;
                }
            });
            
        } catch (Exception e) {
            throw e;
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

        jpCabecera = new javax.swing.JPanel();
        jlNombre = new javax.swing.JLabel();
        jscpDetalle = new javax.swing.JScrollPane();
        jtCheckoutDetalle = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jbPay = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jlSubtotal = new javax.swing.JLabel();
        jlIVA = new javax.swing.JLabel();
        jlTotal = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jlPagado = new javax.swing.JLabel();
        jlPendiente = new javax.swing.JLabel();
        jbCloseAccount = new javax.swing.JButton();
        jlCambio = new javax.swing.JLabel();
        jlMontoCambio = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jlISH = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jlNombre.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlNombre.setText("Jorge  Alfonso Castañeda Gutierrez");

        javax.swing.GroupLayout jpCabeceraLayout = new javax.swing.GroupLayout(jpCabecera);
        jpCabecera.setLayout(jpCabeceraLayout);
        jpCabeceraLayout.setHorizontalGroup(
            jpCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCabeceraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpCabeceraLayout.setVerticalGroup(
            jpCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCabeceraLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jlNombre)
                .addContainerGap())
        );

        jtCheckoutDetalle.setModel(new javax.swing.table.DefaultTableModel(
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
        jscpDetalle.setViewportView(jtCheckoutDetalle);

        jLabel11.setText("Subtotal:");

        jLabel10.setText("IVA(16%):");

        jbPay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/Donate.png"))); // NOI18N
        jbPay.setText("Pagar");
        jbPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPayActionPerformed(evt);
            }
        });

        jLabel1.setText("Total:");

        jlSubtotal.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlSubtotal.setText("subtotal");

        jlIVA.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlIVA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlIVA.setText("iva");

        jlTotal.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlTotal.setText("total");

        jLabel2.setText("Total Pagado:");

        jLabel3.setText("Por pagar:");

        jlPagado.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlPagado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlPagado.setText("pagado");

        jlPendiente.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlPendiente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlPendiente.setText("pagado");

        jbCloseAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/1464156151_Ok.png"))); // NOI18N
        jbCloseAccount.setText("Cerrar Cuenta");
        jbCloseAccount.setEnabled(false);
        jbCloseAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCloseAccountActionPerformed(evt);
            }
        });

        jlCambio.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlCambio.setForeground(new java.awt.Color(176, 1, 1));
        jlCambio.setText("Cambio:");

        jlMontoCambio.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlMontoCambio.setForeground(new java.awt.Color(233, 2, 2));
        jlMontoCambio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlMontoCambio.setText("pagado");

        jLabel5.setText("ISH(2%):");

        jlISH.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlISH.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlISH.setText("ish");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlISH, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jlCambio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlMontoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlPagado, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(jbPay, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbCloseAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbPay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbCloseAccount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jlSubtotal)
                            .addComponent(jLabel2)
                            .addComponent(jlPagado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jlIVA)
                            .addComponent(jlCambio)
                            .addComponent(jlMontoCambio))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jlPendiente))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jlISH, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlTotal, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );

        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jscpDetalle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addComponent(jpCabecera, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jscpDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPayActionPerformed
        //abrir PaymentModule       
        PaymentModuleDlg paymentModule = new PaymentModuleDlg(this, true, totalPending, account);
        paymentModule.setLocationRelativeTo(this);
        paymentModule.setVisible(true);
    }//GEN-LAST:event_jbPayActionPerformed

    private void jbCloseAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCloseAccountActionPerformed
        
        try {
            /*PaymentTypes payment = null;
            if(mapPayTypes.size() > 1){
                PaymentTypesBoundary payBoundary = new PaymentTypesBoundary();
                List<PaymentTypes> payList = payBoundary.search(new PaymentTypes(MMKeys.Payments.MIX));
                payment = payList.get(0);
            } else {
                for(PaymentTypes payType : mapPayTypes.values()){
                    payment = payType;
                }
            }

            String cardNumbers = sbCardNumbers.toString();
            cardNumbers = cardNumbers.trim().length() > 0 ? cardNumbers.substring(0, cardNumbers.length()-1) : "";*/

            //Cuando ya se haya pagado la totalidad del importe, hacer inserciones en folio Transaction
            BigDecimal payTypeAmount;
            BigDecimal folioAmount;
            FolioTransaction fTranFinal;
            BigDecimal pendientes = new BigDecimal(serviciosPendientesDePagar);
            for(String paytype : mapPayTypes.keySet()){
                //Obtener la lista de folio transaction que componen el tipo de pago
                ArrayList<FolioTransaction> fTranList = mapPayTypes.get(paytype);
                //iterar lista de tipos de pago
                for(FolioTransaction fTran : fTranList){
                    payTypeAmount = fTran.getFtrAmount();
                    //dividir el monto total del tipo de pago / # cuartos rentados (AccountTransacions)
                    folioAmount = payTypeAmount.divide(pendientes,2,RoundingMode.HALF_UP);
                    fTranFinal = null;
                    
                    for(AccountTransactions actTran : account.getAccountTransactionsCollection()){
                        if(actTran.getAtrStatus().getMvaKey().equals(MMKeys.AccountsTransactions.STA_PENDIENTE_KEY)){
                            fTranFinal = new FolioTransaction();
                            fTranFinal.setActId(account);
                            fTranFinal.setAtrId(actTran);
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
            }
            
            /*FolioTransactionBoundary folioBoundary = new FolioTransactionBoundary();
            for(AccountTransactions actTran : account.getAccountTransactionsCollection()){
                if(actTran.getAtrStatus().getMvaKey().equals(MMKeys.AccountsTransactions.STA_PENDIENTE_KEY)){
                    FolioTransaction iFolio = new FolioTransaction();
                    iFolio.setActId(account);
                    iFolio.setAtrId(actTran);
                    iFolio.setCouId(currentShift);
                    iFolio.setFtrAmount(actTran.getSrvId().getSrvPrice());
                    iFolio.setFtrDteMod(new Date());
                    iFolio.setFtrUsrMod(currentShift.getUsrId());
                    iFolio.setPayId(payment);
                    iFolio.setFtrCardNumber(cardNumbers);
                    folioBoundary.insert(iFolio);
                }
            }*/

            //Poner Disponible el Cuarto
            Integer roomNumber = 0;
            for (Account account : customerGlobal.getAccountCollection()) {
                for (AccountTransactions tran : account.getAccountTransactionsCollection()) {
                    if (roomNumber == 0 || !roomNumber.equals(Integer.parseInt(tran.getRmsId().getRmsNumber()))) {
                        roomNumber = Integer.parseInt(tran.getRmsId().getRmsNumber());
                        tran.getRmsId().setRmsStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.Rooms.STA_DISPONIBLE_KEY)));
                        roomsBoundary.update(tran.getRmsId());
                    }
                    tran.setAtrStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.AccountsTransactions.STA_PAGADO_KEY)));
                    accountTransactionsBoundary.update(tran);
                }
            }
            //Actualizar montos de la cuenta y cerrarla
            account.setActIva(new BigDecimal(0.16));
            account.setActIvaAmt(totalIVA);
            account.setActSubtotal(total);
            account.setActTotal(totalAccount);
            account.setActStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.Acounts.STA_CERRADO_KEY)));
            accountBoundary.update(account);
            
            GeneralFunctions.sendMessage(this, "Cuenta cerrada satisfactoriamente.");
            
        } catch (Exception e) {
            //GeneralFunctions.appendTrace(e.getStackTrace());
            GeneralFunctions.sendMessage(this, "Ocurrio un error al cerrar la cuenta. Por favor contacte a servicio técnico. \nError:" + e.getMessage());
        }
        //Cerrar dialog
        this.dispose();
    }//GEN-LAST:event_jbCloseAccountActionPerformed

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
            java.util.logging.Logger.getLogger(CheckOutDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckOutDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckOutDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckOutDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CheckOutDlg dialog = new CheckOutDlg(new JDialog(), true, null);
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbCloseAccount;
    private javax.swing.JButton jbPay;
    private javax.swing.JLabel jlCambio;
    private javax.swing.JLabel jlISH;
    private javax.swing.JLabel jlIVA;
    private javax.swing.JLabel jlMontoCambio;
    private javax.swing.JLabel jlNombre;
    private javax.swing.JLabel jlPagado;
    private javax.swing.JLabel jlPendiente;
    private javax.swing.JLabel jlSubtotal;
    private javax.swing.JLabel jlTotal;
    private javax.swing.JPanel jpCabecera;
    private javax.swing.JScrollPane jscpDetalle;
    private javax.swing.JTable jtCheckoutDetalle;
    // End of variables declaration//GEN-END:variables
}
