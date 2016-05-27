/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui.tools;

import com.ahms.boundary.entity_boundary.CashOutBoundary;
import com.ahms.boundary.entity_boundary.FolioTransactionBoundary;
import com.ahms.boundary.entity_boundary.PaymentTypesBoundary;
import com.ahms.model.entity.Account;
import com.ahms.model.entity.AccountTransactions;
import com.ahms.model.entity.CashOut;
import com.ahms.model.entity.FolioTransaction;
import com.ahms.model.entity.PaymentTypes;
import com.ahms.ui.utils.GeneralFunctions;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jorge
 */
public class PaymentModuleDlg extends javax.swing.JDialog {

    private BigDecimal importeTotal = BigDecimal.ZERO;
    private BigDecimal importePagado = BigDecimal.ZERO;
    private BigDecimal importeSobrante = BigDecimal.ZERO;
    private BigDecimal importeRestante = BigDecimal.ZERO;
    private PaymentTypesBoundary paymentTypesBoundary;
    private CheckOutDlg parentDialog = null;
    private RoomServiceSelectionDlg payServiceDialog = null;
    private QuickRentDlg parentQuickRent = null;
    private Account account;
    private CashOut currentShift;
    private CashOutBoundary cashOutBoundary;
    private String cardNumber = "";
    private String folio = "";
    private FolioTransactionBoundary folioTransactionBoundary;
    
    public boolean isPaid = false;
    
    public PaymentModuleDlg(RoomServiceSelectionDlg parent, boolean modal, AccountTransactions serviceToPay){
        super(parent, modal);
        payServiceDialog = parent;
        initComponents();
        paymentTypesBoundary = new PaymentTypesBoundary();
        cashOutBoundary = new CashOutBoundary();
        jlCambio.setVisible(false);
        jtCardNumber.setVisible(false);
        cargarTiposPago();
        
        importeTotal = serviceToPay.getSrvId().getSrvPrice().setScale(2, RoundingMode.HALF_EVEN);
        jlTotal.setText("$ "+ importeTotal.toString());
        folioTransactionBoundary = new FolioTransactionBoundary();
        
        account = serviceToPay.getActId();
        //Obteniendo TUERNO        
        currentShift = cashOutBoundary.getCurrentShift();
        jtCardNumber.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                if (jtCardNumber.getText().length()== 4){
                    e.consume();
                }            
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
            });
    }
    
    
    public PaymentModuleDlg(CheckOutDlg parent, boolean modal, BigDecimal total, Account _account) {
        super(parent, modal);
        initComponents();
        account = _account;
        parentDialog = parent;
        paymentTypesBoundary = new PaymentTypesBoundary();
        cashOutBoundary = new CashOutBoundary();
        jlCambio.setVisible(false);
        jtCardNumber.setVisible(false);
        cargarTiposPago();
        
        importeTotal = total.setScale(2,RoundingMode.HALF_EVEN);
        jlTotal.setText("$ "+ importeTotal.toString());
        
        folioTransactionBoundary = new FolioTransactionBoundary();
        //Obteniendo TUERNO
        currentShift = cashOutBoundary.getCurrentShift();
        jtCardNumber.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                if (jtCardNumber.getText().length()== 4){
                    e.consume();
                }            
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
            });
        
    }
    
    public PaymentModuleDlg(QuickRentDlg parent, boolean modal, BigDecimal total, Account _account) {
        super(parent, modal);
        initComponents();
        account = _account;
        parentQuickRent = parent;
        paymentTypesBoundary = new PaymentTypesBoundary();
        cashOutBoundary = new CashOutBoundary();
        jlCambio.setVisible(false);
        jtCardNumber.setVisible(false);
        cargarTiposPago();
        
        importeTotal = total.setScale(2,RoundingMode.HALF_EVEN);
        jlTotal.setText("$ "+ importeTotal.toString());
        
        folioTransactionBoundary = new FolioTransactionBoundary();
        //Obteniendo TUERNO
        currentShift = cashOutBoundary.getCurrentShift();
        jtCardNumber.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                if (jtCardNumber.getText().length()== 4){
                    e.consume();
                }            
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
            });
        
    }
    
    private void cargarTiposPago() {
        jcbTipoPago.removeAllItems();
        List<PaymentTypes> payments = paymentTypesBoundary.searchAll(new PaymentTypes());
        for(PaymentTypes payment : payments){
            jcbTipoPago.addItem(payment);
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

        jlTotal = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jcbTipoPago = new javax.swing.JComboBox();
        jtImporteRecibido = new javax.swing.JTextField();
        jbPago = new javax.swing.JButton();
        jlCambio = new javax.swing.JLabel();
        jtCardNumber = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jlTotal.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTotal.setText("total cuenta");

        jLabel1.setText("Tipo pago:");

        jLabel2.setText("Importe recibido:");

        jcbTipoPago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbTipoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTipoPagoActionPerformed(evt);
            }
        });

        jtImporteRecibido.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jbPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/16x16/money_dollar.png"))); // NOI18N
        jbPago.setText("Procesar pago");
        jbPago.setToolTipText("");
        jbPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPagoActionPerformed(evt);
            }
        });

        jlCambio.setText("Tarjeta:");

        jtCardNumber.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/16x16/cross.png"))); // NOI18N
        jButton1.setToolTipText("Salir");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtImporteRecibido))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbTipoPago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jlCambio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtCardNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbPago)))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jlTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcbTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtImporteRecibido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbPago)
                    .addComponent(jlCambio)
                    .addComponent(jtCardNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPagoActionPerformed
        PaymentTypes selectedPayment = (PaymentTypes) jcbTipoPago.getSelectedItem();
        
        if(parentDialog != null){ // si fue llamado desde el checkout
            importePagado = new BigDecimal(jtImporteRecibido.getText()).setScale(2, RoundingMode.HALF_EVEN);
            //validaciones
            int importeValida = importePagado.compareTo(importeTotal);
            if(importeValida >= 0) // importe pagado > importe total, hay que dar feria
            {
                importeSobrante = importePagado.subtract(importeTotal).setScale(2,RoundingMode.HALF_EVEN);
                parentDialog.totalCambio = importeSobrante;
                parentDialog.totalPending = BigDecimal.ZERO;
                parentDialog.totalPagado = parentDialog.totalOriginal;
                if(importeValida > 0){
                    parentDialog.setCambio();
                }                     
                parentDialog.regenerateTotals();
                //activar boton de cerrar cuenta
                parentDialog.activeAccountClose();

                //TRANSACCIONES CORRESPONDIENTES
                FolioTransaction folioTransaction = null;            
                if(selectedPayment.getPayId() == 1){ // EFECTIVO
                    folioTransaction = new FolioTransaction();
                    folioTransaction.setActId(account);
                    folioTransaction.setCouId(currentShift);
                    folioTransaction.setPayId(selectedPayment);
                    folioTransaction.setFtrAmount(importeTotal);
                    folioTransaction.setFtrDteMod(new Date());
                    folioTransaction.setFtrUsrMod(selectedPayment.getPayUsrMod());
                } else { // TARJETAs
                    folioTransaction = new FolioTransaction();
                    folioTransaction.setActId(account);
                    folioTransaction.setCouId(currentShift);
                    folioTransaction.setPayId(selectedPayment);
                    folioTransaction.setFtrAmount(importeTotal);
                    //folioTransaction.setFtrCardNumber("Cuenta: " + account.getActId() + " Tarjeta: " + jtCardNumber.getText());
                    folioTransaction.setFtrCardNumber(jtCardNumber.getText());
                    folioTransaction.setFtrFolio(folio);  
                    folioTransaction.setFtrDteMod(new Date());
                    folioTransaction.setFtrUsrMod(selectedPayment.getPayUsrMod());
                }
                folioTransactionBoundary.insert(folioTransaction);
                //----------------------------------
            } else { //si el importe pagado es menor o igual al importe total actualizar montos en checkout
                importeRestante = importeTotal.subtract(importePagado).setScale(2,RoundingMode.UP);
                //actualizar montos en el checkout
                parentDialog.totalPagado = parentDialog.totalPagado.add(importePagado);
                parentDialog.totalPending = parentDialog.totalOriginal.subtract(parentDialog.totalPagado).setScale(2, RoundingMode.UP);
                parentDialog.regenerateTotals();

                //TRANSACCIONES CORRESPONDIENTES ---
                FolioTransaction folioTransaction = null;            
                if(selectedPayment.getPayId() == 1){ // EFECTIVO
                    folioTransaction = new FolioTransaction();
                    folioTransaction.setActId(account);
                    folioTransaction.setCouId(currentShift);
                    folioTransaction.setPayId(selectedPayment);
                    folioTransaction.setFtrAmount(importePagado);
                    folioTransaction.setFtrDteMod(new Date());
                    folioTransaction.setFtrUsrMod(selectedPayment.getPayUsrMod());
                } else { // TARJETAs
                    folioTransaction = new FolioTransaction();
                    folioTransaction.setActId(account);
                    folioTransaction.setCouId(currentShift);
                    folioTransaction.setPayId(selectedPayment);
                    folioTransaction.setFtrAmount(importePagado);
                    //folioTransaction.setFtrCardNumber("Cuenta: " + account.getActId() + " Tarjeta: " + jtCardNumber.getText());
                    folioTransaction.setFtrCardNumber(jtCardNumber.getText());
                    folioTransaction.setFtrFolio(folio);                
                    folioTransaction.setFtrDteMod(new Date());
                    folioTransaction.setFtrUsrMod(selectedPayment.getPayUsrMod());
                }
                folioTransactionBoundary.insert(folioTransaction);
                //----------------------------------           
            }
            isPaid = true;
        } else if(parentQuickRent != null){ // si fue llamado desde un mainForm
            importePagado = new BigDecimal(jtImporteRecibido.getText()).setScale(2, RoundingMode.HALF_EVEN);
            int importeValida = importePagado.compareTo(importeTotal);
            if(importeValida >= 0) // importe pagado > importe total, hay que dar feria
            {
                importeSobrante = importePagado.subtract(importeTotal).setScale(2,RoundingMode.HALF_EVEN);
                //TRANSACCIONES CORRESPONDIENTES
                FolioTransaction folioTransaction = null;            
                if(selectedPayment.getPayId() == 1){ // EFECTIVO
                    folioTransaction = new FolioTransaction();
                    folioTransaction.setActId(account);
                    folioTransaction.setCouId(currentShift);
                    folioTransaction.setPayId(selectedPayment);
                    folioTransaction.setFtrAmount(importeTotal);
                    folioTransaction.setFtrDteMod(new Date());
                    folioTransaction.setFtrUsrMod(selectedPayment.getPayUsrMod());
                } else { // TARJETAs
                    folioTransaction = new FolioTransaction();
                    folioTransaction.setActId(account);
                    folioTransaction.setCouId(currentShift);
                    folioTransaction.setPayId(selectedPayment);
                    folioTransaction.setFtrAmount(importeTotal);
                    //folioTransaction.setFtrCardNumber("Cuenta: " + account.getActId() + " Tarjeta: " + jtCardNumber.getText());
                    folioTransaction.setFtrCardNumber(jtCardNumber.getText());
                    folioTransaction.setFtrFolio(folio);  
                    folioTransaction.setFtrDteMod(new Date());
                    folioTransaction.setFtrUsrMod(selectedPayment.getPayUsrMod());
                }
                folioTransactionBoundary.insert(folioTransaction); 
                GeneralFunctions.sendMessage(this, "Renta realizada exitosamente. Monto sobrante: $ " + importeSobrante.toString());
                //JOptionPane.showMessageDialog(this, "Renta realizada exitosamente. Monto sobrante: $ " + importeSobrante.toString());
            } else {  //es menor el importe pagado que el importe total, insertar en folio transaction y limpiar la forma
                importeRestante = importeTotal.subtract(importePagado).setScale(2,RoundingMode.UP);
                //TRANSACCIONES CORRESPONDIENTES
                FolioTransaction folioTransaction = null;            
                if(selectedPayment.getPayId() == 1){ // EFECTIVO
                    folioTransaction = new FolioTransaction();
                    folioTransaction.setActId(account);
                    folioTransaction.setCouId(currentShift);
                    folioTransaction.setPayId(selectedPayment);
                    folioTransaction.setFtrAmount(importePagado);
                    folioTransaction.setFtrDteMod(new Date());
                    folioTransaction.setFtrUsrMod(selectedPayment.getPayUsrMod());
                } else { // TARJETAs
                    folioTransaction = new FolioTransaction();
                    folioTransaction.setActId(account);
                    folioTransaction.setCouId(currentShift);
                    folioTransaction.setPayId(selectedPayment);
                    folioTransaction.setFtrAmount(importePagado);
                    //folioTransaction.setFtrCardNumber("Cuenta: " + account.getActId() + " Tarjeta: " + jtCardNumber.getText());
                    folioTransaction.setFtrCardNumber(jtCardNumber.getText());
                    folioTransaction.setFtrFolio(folio);  
                    folioTransaction.setFtrDteMod(new Date());
                    folioTransaction.setFtrUsrMod(selectedPayment.getPayUsrMod());
                }
                folioTransactionBoundary.insert(folioTransaction); 
                GeneralFunctions.sendMessage(this, "Pago parcial realizado exitosamente. Monto Pagado: $ " + importePagado.toString() + "  |  Importe Restante: $ " + importeRestante.toString());
                //JOptionPane.showMessageDialog(this, "Pago parcial realizado exitosamente. Monto Pagado: $ " + importePagado.toString() + "  |  Importe Restante: $ " + importeRestante.toString());
                //limpiar la forma sin hacer el dispose
                importeTotal = importeRestante;
                jlTotal.setText("$ " + importeTotal.toString());
                return;
            }
            //limpiar quickRent y actualizar grid en MainForm
            parentQuickRent.totalPaid = true;
            isPaid = true;
        } else if(payServiceDialog != null){ //fue llamado para pago de 1 servicio
            importePagado = new BigDecimal(jtImporteRecibido.getText()).setScale(2, RoundingMode.HALF_EVEN);
            int importeValida = importePagado.compareTo(importeTotal);
            if(importeValida >= 0) // importe pagado > importe total, hay que dar feria
            {
                importeSobrante = importePagado.subtract(importeTotal).setScale(2,RoundingMode.HALF_EVEN);
                //TRANSACCIONES CORRESPONDIENTES
                FolioTransaction folioTransaction = null;            
                if(selectedPayment.getPayId() == 1){ // EFECTIVO
                    folioTransaction = new FolioTransaction();
                    folioTransaction.setActId(account);
                    folioTransaction.setCouId(currentShift);
                    folioTransaction.setPayId(selectedPayment);
                    folioTransaction.setFtrAmount(importeTotal);
                    folioTransaction.setFtrDteMod(new Date());
                    folioTransaction.setFtrUsrMod(selectedPayment.getPayUsrMod());
                } else { // TARJETAs
                    folioTransaction = new FolioTransaction();
                    folioTransaction.setActId(account);
                    folioTransaction.setCouId(currentShift);
                    folioTransaction.setPayId(selectedPayment);
                    folioTransaction.setFtrAmount(importeTotal);
                    //folioTransaction.setFtrCardNumber("Cuenta: " + account.getActId() + " Tarjeta: " + jtCardNumber.getText());
                    folioTransaction.setFtrCardNumber(jtCardNumber.getText());
                    folioTransaction.setFtrFolio(folio);  
                    folioTransaction.setFtrDteMod(new Date());
                    folioTransaction.setFtrUsrMod(selectedPayment.getPayUsrMod());
                }
                folioTransactionBoundary.insert(folioTransaction); 
                GeneralFunctions.sendMessage(this, "Servicio pagado exitosamente. Monto sobrante: $ " + importeSobrante.toString());
                //JOptionPane.showMessageDialog(this, "Renta realizada exitosamente. Monto sobrante: $ " + importeSobrante.toString());
            } else {  //es menor el importe pagado que el importe total, insertar en folio transaction y limpiar la forma
                importeRestante = importeTotal.subtract(importePagado).setScale(2,RoundingMode.UP);
                //TRANSACCIONES CORRESPONDIENTES
                FolioTransaction folioTransaction = null;            
                if(selectedPayment.getPayId() == 1){ // EFECTIVO
                    folioTransaction = new FolioTransaction();
                    folioTransaction.setActId(account);
                    folioTransaction.setCouId(currentShift);
                    folioTransaction.setPayId(selectedPayment);
                    folioTransaction.setFtrAmount(importePagado);
                    folioTransaction.setFtrDteMod(new Date());
                    folioTransaction.setFtrUsrMod(selectedPayment.getPayUsrMod());
                } else { // TARJETAs
                    folioTransaction = new FolioTransaction();
                    folioTransaction.setActId(account);
                    folioTransaction.setCouId(currentShift);
                    folioTransaction.setPayId(selectedPayment);
                    folioTransaction.setFtrAmount(importePagado);
                    //folioTransaction.setFtrCardNumber("Cuenta: " + account.getActId() + " Tarjeta: " + jtCardNumber.getText());
                    folioTransaction.setFtrCardNumber(jtCardNumber.getText());
                    folioTransaction.setFtrFolio(folio);  
                    folioTransaction.setFtrDteMod(new Date());
                    folioTransaction.setFtrUsrMod(selectedPayment.getPayUsrMod());
                }
                folioTransactionBoundary.insert(folioTransaction); 
                GeneralFunctions.sendMessage(this, "Pago parcial realizado exitosamente. Monto Pagado: $ " + importePagado.toString() + "  |  Importe Restante: $ " + importeRestante.toString());
                //JOptionPane.showMessageDialog(this, "Pago parcial realizado exitosamente. Monto Pagado: $ " + importePagado.toString() + "  |  Importe Restante: $ " + importeRestante.toString());
                //limpiar la forma sin hacer el dispose
                importeTotal = importeRestante;
                jlTotal.setText("$ " + importeTotal.toString());
                return;
            }   
            isPaid = true;
        } else {
            isPaid = false;
        }
        this.dispose();
    }//GEN-LAST:event_jbPagoActionPerformed

    private void jcbTipoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTipoPagoActionPerformed
        try {
            PaymentTypes type = (PaymentTypes) jcbTipoPago.getSelectedItem();
            if(type.getPayId().equals(1)){
                jlCambio.setVisible(false);
                jtCardNumber.setVisible(false);
                jtImporteRecibido.setText("");
                //jtImporteRecibido.setEnabled(true);
            } else {
                jlCambio.setVisible(true);
                jtCardNumber.setVisible(true);
                jtImporteRecibido.setText(importeTotal.toString());
                //jtImporteRecibido.setEnabled(false);
            }
        } catch (Exception e) {
        }            
    }//GEN-LAST:event_jcbTipoPagoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(PaymentModuleDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaymentModuleDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaymentModuleDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaymentModuleDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PaymentModuleDlg dialog = new PaymentModuleDlg(new CheckOutDlg(null, true, null), true, new BigDecimal(4500), null);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton jbPago;
    private javax.swing.JComboBox jcbTipoPago;
    private javax.swing.JLabel jlCambio;
    private javax.swing.JLabel jlTotal;
    private javax.swing.JTextField jtCardNumber;
    private javax.swing.JTextField jtImporteRecibido;
    // End of variables declaration//GEN-END:variables
}