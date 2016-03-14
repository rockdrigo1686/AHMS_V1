package com.ahms.ui;

import com.ahms.boundary.security.AccountBoundary;
import com.ahms.boundary.security.AccountTransactionsBoundary;
import com.ahms.boundary.security.MultiValueBoundary;
import com.ahms.boundary.security.ServiceBoundary;
import com.ahms.boundary.security.ServiceTypesBoundary;
import com.ahms.model.entity.Account;
import com.ahms.model.entity.AccountTransactions;
import com.ahms.model.entity.CashOut;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.Rooms;
import com.ahms.model.entity.ServiceTypes;
import com.ahms.model.entity.Services;
import com.ahms.ui.utils.GeneralFunctions;
import com.ahms.ui.utils.UIConstants;
import com.ahms.util.MMKeys;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jorge
 */
public class RoomService extends javax.swing.JDialog {

    //Parent Entities
    private Rooms room = null;
    private Account account = null;
    private CashOut currentshift = null;

    //Necessary Boundaries
    private MultiValueBoundary multiValueBoundary;
    private ServiceTypesBoundary serviceTypesBoundary;
    public AccountBoundary accountBoundary;
    public AccountTransactionsBoundary accountTransactionsBoundary;
    private ServiceBoundary serviceBoundary;

    //Other instances
    List<AccountTransactions> currentGridsource = null;

    public Rooms getGlobalroom() {
        return room;
    }

    public RoomService(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public RoomService(java.awt.Frame parent, boolean modal, Rooms room, CashOut currentShift) {
        super(parent, modal);
        this.room = room;
        this.currentshift = currentShift;
        initComponents();
        accountBoundary = new AccountBoundary();
        account = accountBoundary.getActiveAccountByRoom(room);
        serviceTypesBoundary = new ServiceTypesBoundary();
        accountTransactionsBoundary = new AccountTransactionsBoundary();
        multiValueBoundary = new MultiValueBoundary();
        serviceBoundary = new ServiceBoundary();
        loadServiceTypes(serviceTypesBoundary.searchAll(null));
        idRMS.setText("Cuarto # " + room.getRmsNumber() + " - " + room.getRmsDesc());
        loadGrid(accountTransactionsBoundary.findAllByRmsId(room,account));
        ServiceTypes typeselected = (ServiceTypes) jcbServiceTypes.getSelectedItem();
        loadServices(serviceBoundary.findAllByServiceType(typeselected));
    }

    public RoomService(java.awt.Frame parent, boolean modal, Integer rms_id) {
        super(parent, modal);
        this.room = new Rooms();
        this.room.setRmsId(rms_id);
        initComponents();
        idRMS.setText(rms_id.toString());
    }

    public void loadGrid(List<AccountTransactions> lstAccountTransactions) {
        currentGridsource = lstAccountTransactions;
        //Columnas
        Vector<String> columnNames = new Vector();
        columnNames.add("Cuenta");
        columnNames.add("#");
        columnNames.add("Tipo");
        columnNames.add("Servicio");
        columnNames.add("Cantidad");
        columnNames.add("Precio");
        //Renglones
        Vector<Vector> rows = new Vector<>();
        for (AccountTransactions acctTran : lstAccountTransactions) {
            Vector vctRow = new Vector();
            vctRow.add(acctTran.getActId().getActId());
            vctRow.add(acctTran.getAtrId());
            vctRow.add(acctTran.getSrvId().getSvtId());
            vctRow.add(acctTran.getSrvId());
            vctRow.add(acctTran.getAtrQuantity());
            vctRow.add(acctTran.getSrvId().getSrvPrice());
            rows.add(vctRow);
        }
        DefaultTableModel model = new DefaultTableModel(rows, columnNames) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jtAccountTransactions.setModel(model);
        jtAccountTransactions.setRowHeight(30);
        jtAccountTransactions.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        jtAccountTransactions.getColumnModel().getColumn(0).setMaxWidth(80);
        jtAccountTransactions.getColumnModel().getColumn(1).setMaxWidth(50);
        jtAccountTransactions.getColumnModel().getColumn(2).setMaxWidth(200);
        jtAccountTransactions.getColumnModel().getColumn(3).setMaxWidth(400);
        jtAccountTransactions.getColumnModel().getColumn(4).setMaxWidth(100);
        jtAccountTransactions.getColumnModel().getColumn(5).setMaxWidth(100);
    }

    private void loadServiceTypes(List<ServiceTypes> serviceTypes) {
        DefaultComboBoxModel model = new DefaultComboBoxModel(serviceTypes.toArray(new ServiceTypes[serviceTypes.size()]));
        jcbServiceTypes.setModel(model);
    }

    private void loadServices(List<Services> lstServices) {
        DefaultComboBoxModel model = new DefaultComboBoxModel(lstServices.toArray(new Services[lstServices.size()]));
        jcbServices.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpopOptions = new javax.swing.JPopupMenu();
        jmiPagar = new javax.swing.JMenuItem();
        jmiCancelar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jcbServiceTypes = new javax.swing.JComboBox();
        jcbServices = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtxtQuantity = new javax.swing.JTextField();
        jbAddService = new javax.swing.JButton();
        idRMS = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtAccountTransactions = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        btnSalir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        jmiPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/money.png"))); // NOI18N
        jmiPagar.setText("Pagar");
        jmiPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPagarActionPerformed(evt);
            }
        });
        jpopOptions.add(jmiPagar);

        jmiCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/cancel.png"))); // NOI18N
        jmiCancelar.setText("Cancelar");
        jmiCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCancelarActionPerformed(evt);
            }
        });
        jpopOptions.add(jmiCancelar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(java.awt.Color.white);

        jLabel1.setText("Tipo de Servicio");

        jcbServiceTypes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbServiceTypes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbServiceTypesActionPerformed(evt);
            }
        });

        jcbServices.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Servicio");

        jLabel3.setText("Cantidad");

        jtxtQuantity.setText("1");

        jbAddService.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack3/Add.png"))); // NOI18N
        jbAddService.setText("Agregar");
        jbAddService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAddServiceActionPerformed(evt);
            }
        });

        idRMS.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        idRMS.setForeground(new java.awt.Color(183, 6, 6));
        idRMS.setText("roomParent");
        idRMS.setName("idRMS"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbServiceTypes, 0, 208, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbServices, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbAddService, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(idRMS)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(idRMS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbServiceTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jcbServices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jtxtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(jbAddService, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jtAccountTransactions.setModel(new javax.swing.table.DefaultTableModel(
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
        jtAccountTransactions.setComponentPopupMenu(jpopOptions);
        jtAccountTransactions.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jtAccountTransactions);

        jToolBar1.setFloatable(false);
        jToolBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/cross.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setFocusable(false);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSalir);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/disk.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setFocusable(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnGuardar);

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/delete.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setFocusable(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEliminar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 872, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbServiceTypesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbServiceTypesActionPerformed
        //load Services
        ServiceTypes typeselected = (ServiceTypes) jcbServiceTypes.getSelectedItem();
        loadServices(serviceBoundary.findAllByServiceType(typeselected));
    }//GEN-LAST:event_jcbServiceTypesActionPerformed

    private void jbAddServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAddServiceActionPerformed
//verificar cantidad valida
        if (jtxtQuantity.getText().trim().length() <= 0) {
            GeneralFunctions.sendMessage(this, UIConstants.NEW_SERVICE_QTY_NULL);
            return;
        }
        try {
            AccountTransactions newService = new AccountTransactions();
            newService.setActId(account);
            newService.setCouId(currentshift);
            newService.setRmsId(room);
            Services serviceSel = (Services) jcbServices.getSelectedItem();
            newService.setSrvId(serviceSel);
            newService.setAtrQuantity(Integer.parseInt(jtxtQuantity.getText()));
            newService.setAtrDteMod(Calendar.getInstance().getTime());
            newService.setAtrStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.AccountsTransactions.STA_PENDIENTE_KEY)));
            newService.setAtrUsrMod(currentshift.getUsrId());
            newService.setAtrNotes(serviceSel.getSvtId().getSvtDesc() + " - " + serviceSel.getSrvDesc());
            accountTransactionsBoundary.insert(newService);
//            GeneralFunctions.sendMessage(this, UIConstants.NEW_SERVICE_SUCCESS);
            loadGrid(accountTransactionsBoundary.findAllByRmsId(room,account));
        } catch (Exception e) {
            e.printStackTrace();
            GeneralFunctions.sendMessage(this, UIConstants.NEW_SERVICE_ERROR);
        }
    }//GEN-LAST:event_jbAddServiceActionPerformed

    private void cancelService(AccountTransactions serviceToCancel) {
        try {
            serviceToCancel.setAtrStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.AccountsTransactions.STA_CANCELADO_KEY)));
            accountTransactionsBoundary.update(serviceToCancel);
            GeneralFunctions.sendMessage(this, UIConstants.CANCEL_SERVICE_SUCCESS);
            loadGrid(accountTransactionsBoundary.findAllByRmsId(room,account));
        } catch (Exception e) {
            e.printStackTrace();
            GeneralFunctions.sendMessage(this, UIConstants.CANCEL_SERVICE_ERROR);
        }
    }

    private void payService(AccountTransactions serviceToPay) {
        try {
            PaymentModule paymentModule = new PaymentModule(this, true, serviceToPay);
            paymentModule.setLocationRelativeTo(this);
            paymentModule.setVisible(true);

            serviceToPay.setAtrStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.AccountsTransactions.STA_PAGADO_KEY)));
            accountTransactionsBoundary.update(serviceToPay);
            GeneralFunctions.sendMessage(this, UIConstants.PAY_SERVICE_SUCCESS);
            loadGrid(accountTransactionsBoundary.findAllByRmsId(room,account));
        } catch (Exception e) {
            e.printStackTrace();
            GeneralFunctions.sendMessage(this, UIConstants.PAY_SERVICE_ERROR);
        }
    }

    private void jmiCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCancelarActionPerformed
        //cancelar servicio
        Integer atrIdSelected = (Integer) jtAccountTransactions.getModel().getValueAt(jtAccountTransactions.getSelectedRow(), 1);
        for (AccountTransactions iAct : currentGridsource) {
            if (iAct.getAtrId().compareTo(atrIdSelected) == 0) {
                cancelService(iAct);
                return;
            }
        }

    }//GEN-LAST:event_jmiCancelarActionPerformed

    private void jmiPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPagarActionPerformed
        //Pagar Servicio
        Integer atrIdSelected = (Integer) jtAccountTransactions.getModel().getValueAt(jtAccountTransactions.getSelectedRow(), 1);
        for (AccountTransactions iAct : currentGridsource) {
            if (iAct.getAtrId().compareTo(atrIdSelected) == 0) {
                payService(iAct);
                return;
            }
        }

    }//GEN-LAST:event_jmiPagarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
        //this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
      
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(RoomService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(RoomService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(RoomService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(RoomService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                RoomService dialog = new RoomService(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel idRMS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton jbAddService;
    private javax.swing.JComboBox jcbServiceTypes;
    private javax.swing.JComboBox jcbServices;
    private javax.swing.JMenuItem jmiCancelar;
    private javax.swing.JMenuItem jmiPagar;
    private javax.swing.JPopupMenu jpopOptions;
    private javax.swing.JTable jtAccountTransactions;
    private javax.swing.JTextField jtxtQuantity;
    // End of variables declaration//GEN-END:variables
}
