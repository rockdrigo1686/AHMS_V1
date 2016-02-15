package com.ahms.ui;

import com.ahms.boundary.security.AccountBoundary;
import com.ahms.boundary.security.AccountTransactionsBoundary;
import com.ahms.boundary.security.CustomersBoundary;
import com.ahms.boundary.security.RoomsBoundary;
import com.ahms.model.entity.Account;
import com.ahms.model.entity.AccountTransactions;
import com.ahms.model.entity.Customers;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.Rooms;
import com.ahms.ui.utils.UIConstants;
import com.ahms.util.MMKeys;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jorge
 */
public class CheckOutForm extends javax.swing.JDialog {

    public BigDecimal totalAccount = BigDecimal.ZERO;
    public BigDecimal totalPending = BigDecimal.ZERO;
    public BigDecimal total        = BigDecimal.ZERO;
    public BigDecimal totalIVA     = BigDecimal.ZERO;
    public BigDecimal totalPagado  = BigDecimal.ZERO; 
    public BigDecimal totalOriginal= BigDecimal.ZERO; 
    public BigDecimal totalCambio  = BigDecimal.ZERO; 
    private Account acct = null;
    private final Customers customerGlobal;
    private final AccountBoundary accountBoundary;
    private final RoomsBoundary roomsBoundary;
    private final AccountTransactionsBoundary accountTransactionsBoundary;
    private final MainFrm parentFrame;
    
    public CheckOutForm(java.awt.Frame parent, boolean modal, Customers customer) {
        super(parent, modal);
        initComponents();
        parentFrame = (MainFrm) parent;
        accountBoundary = new AccountBoundary();
        roomsBoundary = new RoomsBoundary();
        accountTransactionsBoundary = new AccountTransactionsBoundary();
        jlCambio.setVisible(false);
        jlMontoCambio.setVisible(false);
        
        customerGlobal = customer;
        jlNombre.setText(customerGlobal.getCusName() + " " + customerGlobal.getCusLst1() + " " + customerGlobal.getCusLst2() + " - " + customerGlobal.getCusRfc());
        
        generateGridSimple(customerGlobal);
    }
    
    public void regenerateTotals(){
        jlSubtotal.setText("$" +total.toString());
        jlIVA.setText("$" +totalIVA.toString());
        jlTotal.setText("$" +totalOriginal.toString());
        jlPagado.setText("$" +totalPagado.toString());
        jlPendiente.setText("$" +totalPending.toString());
    }
    
    public void activeAccountClose(){
        jbPay.setEnabled(false);
        jbCloseAccount.setEnabled(true);
    }
    
    public void setCambio(){
        jlCambio.setVisible(true);
        jlMontoCambio.setVisible(true);
        jlMontoCambio.setText("$" + totalCambio.toString());
    }
    
    private void generateGridSimple(Customers customer){
        try {
            
            Vector<String> vctColumns = new Vector<>();
            vctColumns.add("Cuarto");
            vctColumns.add("Cantidad");
            vctColumns.add("Descripción");
            vctColumns.add("Costo");
            
            total = BigDecimal.ZERO;
            
            Vector<Vector> rows = new Vector<>();
            for(Account account : customer.getAccountCollection()){
                for(AccountTransactions a  : account.getAccountTransactionsCollection()){
                    if(!a.getAtrStatus().getMvaKey().equals(MMKeys.AccountsTransactions.STA_PENDIENTE_KEY)){continue;} //para discriminar los servicios que ya estan pagados o cancelados
                    Vector<Object> vctRow = new Vector<>();
                    vctRow.add(a.getRmsId().getRmsNumber());
                    vctRow.add(a.getAtrQuantity());
                    vctRow.add(a.getSrvId().getSrvDesc());
                    vctRow.add((a.getAtrQuantity() * a.getSrvId().getSrvPrice().doubleValue()));
                    rows.add(vctRow);
                    
                    total = total.add(new BigDecimal((a.getAtrQuantity() * a.getSrvId().getSrvPrice().doubleValue())));
                }
            }
            
            
            totalIVA = total.multiply(new BigDecimal(0.16));
            totalAccount = totalPending = total.add(totalIVA);
            
            total = total.setScale(2, RoundingMode.HALF_EVEN);
            totalIVA = totalIVA.setScale(2, RoundingMode.HALF_EVEN);
            totalAccount = totalAccount.setScale(2, RoundingMode.HALF_EVEN);
            totalPending = totalPending.setScale(2, RoundingMode.HALF_EVEN);
            totalPagado = totalPagado.setScale(2, RoundingMode.HALF_EVEN);
            
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
                                    
        } catch (Exception e) {
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
        jToolBar1 = new javax.swing.JToolBar();
        jbSalir = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jpCabecera.setBackground(java.awt.Color.white);

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

        jToolBar1.setFloatable(false);
        jToolBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jbSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/img/1445771618_17.png"))); // NOI18N
        jbSalir.setText("Salir");
        jbSalir.setFocusable(false);
        jbSalir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });
        jToolBar1.add(jbSalir);

        jPanel1.setBackground(java.awt.Color.white);

        jLabel11.setText("Subtotal:");

        jLabel10.setText("IVA(16%):");

        jbPay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack3/Donate.png"))); // NOI18N
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

        jbCloseAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/accept.png"))); // NOI18N
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel1))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jlTotal)
                            .addComponent(jLabel3)
                            .addComponent(jlPendiente))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jscpDetalle)
                    .addComponent(jpCabecera, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jscpDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPayActionPerformed
        //abrir PaymentModule
        for(Account acc : customerGlobal.getAccountCollection()){
            acct = acc;
            break;
        }
        PaymentModule paymentModule = new PaymentModule(this, true, totalPending, acct);
        paymentModule.setLocationRelativeTo(this);
        paymentModule.setVisible(true);
    }//GEN-LAST:event_jbPayActionPerformed

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jbCloseAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCloseAccountActionPerformed
        //Poner Disponible el Cuarto
        Integer roomNumber = 0;
        for(Account account : customerGlobal.getAccountCollection()){
            for(AccountTransactions tran : account.getAccountTransactionsCollection()){
                if(roomNumber == 0 || !roomNumber.equals(Integer.parseInt(tran.getRmsId().getRmsNumber()))){
                    roomNumber = Integer.parseInt(tran.getRmsId().getRmsNumber());
                    tran.getRmsId().setRmsStatus(new MultiValue(MMKeys.Rooms.STA_DISPONIBLE_KEY));
                    roomsBoundary.update(tran.getRmsId());
                }
                tran.setAtrStatus(new MultiValue(MMKeys.AccountsTransactions.STA_PAGADO_KEY));
                accountTransactionsBoundary.update(tran);
            }
        }
        //Actualizar montos de la cuenta y cerrarla
        acct.setActIva(new BigDecimal(0.16));
        acct.setActIvaAmt(totalIVA);
        acct.setActSubtotal(total);
        acct.setActTotal(totalAccount);
        acct.setActStatus(new MultiValue(MMKeys.Acounts.STA_CERRADO_KEY));
        accountBoundary.update(acct);
        //Cerrar dialog y actualizar el dashboard
        parentFrame.configGrid(roomsBoundary.searchAll(new Rooms()));
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
            java.util.logging.Logger.getLogger(CheckOutForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckOutForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckOutForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckOutForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CheckOutForm dialog = new CheckOutForm(new javax.swing.JFrame(), true, null);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton jbCloseAccount;
    private javax.swing.JButton jbPay;
    private javax.swing.JButton jbSalir;
    private javax.swing.JLabel jlCambio;
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
