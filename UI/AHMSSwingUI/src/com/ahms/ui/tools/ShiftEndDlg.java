/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui.tools;

import com.ahms.boundary.entity_boundary.CashOutBoundary;
import com.ahms.boundary.entity_boundary.FolioTransactionBoundary;
import com.ahms.boundary.entity_boundary.MoneyMovementBoundary;
import com.ahms.boundary.entity_boundary.MultiValueBoundary;
import com.ahms.model.entity.CashOut;
import com.ahms.model.entity.FolioTransaction;
import com.ahms.model.entity.MoneyMovement;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.Users;
import com.ahms.ui.MainFrm;
import com.ahms.ui.utils.UIConstants;
import com.ahms.util.MMKeys;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rsoto
 */
public class ShiftEndDlg extends javax.swing.JDialog {

    /**
     * Creates new form ShiftEndFrm
     */
    MainFrm parent = null;
    Users mainUser = null;
    CashOut currentShift = null;
    double saldoFinal = 0.0;

    public ShiftEndDlg(MainFrm parent, boolean modal, Users mainUser, CashOut currentShift) {
        super(parent, modal);
        this.mainUser = mainUser;
        this.parent = parent;
        this.currentShift = currentShift;
        initComponents();

        loadBalance();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblClave = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblSaldoIni = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblSaldoFin = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblCash = new javax.swing.JLabel();
        lblCards = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblOuts = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblCash = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblTc = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblOut = new javax.swing.JTable();
        btnEndShift = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        lblNombre.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        lblNombre.setText("nombre");

        lblClave.setFont(new java.awt.Font("Droid Sans", 1, 12)); // NOI18N
        lblClave.setText("clave");

        jLabel1.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        jLabel1.setText("Saldo inicial:");

        lblSaldoIni.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        lblSaldoIni.setText("monto ini");

        jLabel3.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        jLabel3.setText("Saldo final:");

        lblSaldoFin.setFont(new java.awt.Font("Droid Sans", 1, 18)); // NOI18N
        lblSaldoFin.setText("monto fin");

        jLabel5.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        jLabel5.setText("Efectivo:");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblCash.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        lblCash.setText("jLabel1");

        lblCards.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        lblCards.setText("jLabel1");

        jLabel8.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        jLabel8.setText("Tarjetas:");

        lblOuts.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        lblOuts.setText("jLabel1");

        jLabel10.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        jLabel10.setText("Salidas:");

        tblCash.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Descripcion", "Monto"
            }
        ));
        tblCash.setPreferredSize(new java.awt.Dimension(400, 72));
        jScrollPane4.setViewportView(tblCash);
        if (tblCash.getColumnModel().getColumnCount() > 0) {
            tblCash.getColumnModel().getColumn(0).setResizable(false);
            tblCash.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblCash.getColumnModel().getColumn(1).setResizable(false);
            tblCash.getColumnModel().getColumn(1).setPreferredWidth(50);
        }

        jScrollPane1.setViewportView(jScrollPane4);

        tblTc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Descripcion", "Monto"
            }
        ));
        jScrollPane9.setViewportView(tblTc);
        if (tblTc.getColumnModel().getColumnCount() > 0) {
            tblTc.getColumnModel().getColumn(0).setResizable(false);
            tblTc.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblTc.getColumnModel().getColumn(1).setResizable(false);
            tblTc.getColumnModel().getColumn(1).setPreferredWidth(50);
        }

        jScrollPane8.setViewportView(jScrollPane9);

        tblOut.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Descripcion", "Monto"
            }
        ));
        jScrollPane11.setViewportView(tblOut);
        if (tblOut.getColumnModel().getColumnCount() > 0) {
            tblOut.getColumnModel().getColumn(0).setResizable(false);
            tblOut.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblOut.getColumnModel().getColumn(1).setResizable(false);
            tblOut.getColumnModel().getColumn(1).setPreferredWidth(50);
        }

        jScrollPane10.setViewportView(jScrollPane11);

        btnEndShift.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/Remove Appointment.png"))); // NOI18N
        btnEndShift.setText("Cerrar Turno");
        btnEndShift.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEndShiftActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCash))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombre)
                                    .addComponent(lblSaldoIni))))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCards))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblSaldoFin)))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblOuts)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnEndShift, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(23, 23, 23))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblClave)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre)
                            .addComponent(lblClave))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSaldoIni)
                            .addComponent(jLabel3)
                            .addComponent(lblSaldoFin)
                            .addComponent(btnEndShift, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(lblCards)
                    .addComponent(lblCash)
                    .addComponent(lblOuts))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jToolBar1.setBackground(java.awt.Color.white);
        jToolBar1.setFloatable(false);
        jToolBar1.setPreferredSize(new java.awt.Dimension(30, 38));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/16x16/cross.png"))); // NOI18N
        jButton4.setFocusable(false);
        jButton4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(449, 449, 449)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addGap(449, 449, 449)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(163, 163, 163)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(109, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEndShiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEndShiftActionPerformed
        // TODO add your handling code here:

        int option = JOptionPane.showOptionDialog(parent, UIConstants.CONFIRM_SHIFT_END, null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"SI", "NO"}, rootPane);
        if (option == 0) {
            MultiValueBoundary mvBoundary =  new  MultiValueBoundary();
            currentShift.setCouDteEnd(new Date());
            currentShift.setCouMonEnd(new BigDecimal(saldoFinal));
            currentShift.setCouStatus(mvBoundary.findByKey(new MultiValue(MMKeys.Shift.STA_CERRADO_KEY)));
            CashOutBoundary coBoundary = new CashOutBoundary();
            coBoundary.update(currentShift);
            parent.setCurrentShift(null);
            parent.changeFormState(false);
            JOptionPane.showMessageDialog(this,UIConstants.SHIFT_ENDED);
            this.dispose();
        }

    }//GEN-LAST:event_btnEndShiftActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(ShiftEndDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShiftEndDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShiftEndDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShiftEndDlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                ShiftEndFrm dialog = new ShiftEndFrm(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEndShift;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblCards;
    private javax.swing.JLabel lblCash;
    private javax.swing.JLabel lblClave;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblOuts;
    private javax.swing.JLabel lblSaldoFin;
    private javax.swing.JLabel lblSaldoIni;
    private javax.swing.JTable tblCash;
    private javax.swing.JTable tblOut;
    private javax.swing.JTable tblTc;
    // End of variables declaration//GEN-END:variables

    private void loadBalance() {
        List<FolioTransaction> folioTransactionList = null;
        List<MoneyMovement> moneyMovementList = null;
        FolioTransactionBoundary ftBoundary = new FolioTransactionBoundary();
        MoneyMovementBoundary mmBoundary = new MoneyMovementBoundary();

        String col[] = {"Descripcion", "Monto"};

        DefaultTableModel tcModel = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        DefaultTableModel outModel = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        DefaultTableModel csModel = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };

        double total = 0.0;
        double totalTc = 0.0;
        double totalCs =  0.0;
        double salidas =  0.0;

        //traemos las transacciones
        FolioTransaction ft = new FolioTransaction();
        ft.setCouId(currentShift);
        folioTransactionList = ftBoundary.searchByCouId(ft);
        if (folioTransactionList != null) {
//            Map<String, List<FolioTransaction>> ftMap = new HashMap<>();
//            ftMap.put(UIConstants.PAYMENT_TYPE_CC, new ArrayList<FolioTransaction>());
//            ftMap.put(UIConstants.PAYMENT_TYPE_DC, new ArrayList<FolioTransaction>());
//            ftMap.put(UIConstants.PAYMENT_TYPE_CS, new ArrayList<FolioTransaction>());
            
            
            for (FolioTransaction ftObj : folioTransactionList) {
                total+=ftObj.getFtrAmount().doubleValue();
                if (UIConstants.PAYMENT_TYPE_CS.equalsIgnoreCase(ftObj.getPayId().getPayDesc())) {
                    totalCs+=ftObj.getFtrAmount().doubleValue();
                    csModel.addRow(new  Object[]{"tarjeta: " + ftObj.getFtrCardNumber() + " Folio: " + ftObj.getFtrFolio(), ftObj.getFtrAmount()});
                } else {
                    totalTc+=ftObj.getFtrAmount().doubleValue();
                    tcModel.addRow(new Object[]{"tarjeta: " + ftObj.getFtrCardNumber() + " Folio: " + ftObj.getFtrFolio(), ftObj.getFtrAmount()});
                }
            }
        }
        //traemos las entradas y salidas extras
        MoneyMovement mm = new MoneyMovement();
        mm.setCouId(currentShift);
        moneyMovementList = mmBoundary.searchByCouId(mm);
        if (moneyMovementList != null) {
            for (MoneyMovement mmObj : moneyMovementList) {
                salidas+= mmObj.getMmoCasIn().doubleValue()- mmObj.getMmoCasOut().doubleValue();
                outModel.addRow(new Object[]{mmObj.getMmoDescription(), mmObj.getMmoCasIn().subtract(mmObj.getMmoCasOut())});
            }
        }

        DecimalFormat df = new DecimalFormat("$##,##0.00");
        saldoFinal = total + currentShift.getCouAmoIni().doubleValue() + salidas;

        lblSaldoIni.setText(df.format(currentShift.getCouAmoIni()));
        lblCards.setText(df.format(totalTc));
        lblCash.setText(df.format(totalCs));
        lblSaldoFin.setText(df.format(saldoFinal));
        lblOuts.setText(df.format(salidas));
        lblNombre.setText(mainUser.getFullName());
        lblClave.setText(mainUser.getUsrCode());

        tblCash.setModel(csModel);
        tblTc.setModel(tcModel);
        tblOut.setModel(outModel);

        tblCash.getColumn("Descripcion").setMaxWidth(250);
        tblCash.getColumn("Monto").setMaxWidth(120);
        tblTc.getColumn("Descripcion").setMaxWidth(250);
        tblTc.getColumn("Monto").setMaxWidth(120);
        tblOut.getColumn("Descripcion").setMaxWidth(250);
        tblOut.getColumn("Monto").setMaxWidth(120);
        tblCash.getColumn("Descripcion").setWidth(250);
        tblCash.getColumn("Monto").setWidth(120);
        tblTc.getColumn("Descripcion").setWidth(250);
        tblTc.getColumn("Monto").setWidth(120);
        tblOut.getColumn("Descripcion").setWidth(120);
        tblOut.getColumn("Monto").setWidth(120);
    }
}
