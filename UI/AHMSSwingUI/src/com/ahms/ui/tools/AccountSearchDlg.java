package com.ahms.ui.tools;

import com.ahms.ui.configuracion.CustomerRegFrm;
import com.ahms.boundary.entity_boundary.AccountBoundary;
import com.ahms.boundary.entity_boundary.MultiValueBoundary;
import com.ahms.boundary.entity_boundary.ReservationBoundary;
import com.ahms.boundary.entity_boundary.RoomsBoundary;
import com.ahms.model.entity.Account;
import com.ahms.model.entity.AccountTransactions;
import com.ahms.model.entity.Customers;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.Reservation;
import com.ahms.model.entity.Rooms;
import com.ahms.ui.MainFrm;
import com.ahms.ui.utils.GeneralFunctions;
import com.ahms.ui.utils.UIConstants;
import com.ahms.util.MMKeys;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rsoto
 */
public class AccountSearchDlg extends javax.swing.JDialog {

    private Customers customer = null;
    private AccountBoundary accountB = null;
    private ReservationBoundary resB = null;
    private List<?> resultList = null;
    private List<Rooms> roomList = null;
    private DefaultTableModel tableModel = null;
    private MainFrm topFrame;
    private static final String AC = "AC";
    private static final String RS = "RS";
    private String action = "";

    /**
     * Creates new form AccountSearch
     */
    public AccountSearchDlg(MainFrm parent, boolean modal, String action) {
        super(parent, modal);
        initComponents();
        this.topFrame = parent;
        this.action = action;
        btnCancelRs.setVisible(false);
        if (RS.equalsIgnoreCase(action)) {
            btnCancelRs.setVisible(true);
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

        lblCus = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnCancelRs = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblCus.setFont(new java.awt.Font("Droid Sans", 1, 14)); // NOI18N
        lblCus.setText("Cliente:");
        lblCus.setPreferredSize(new java.awt.Dimension(50, 15));

        resultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(resultTable);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToolBar1.setPreferredSize(new java.awt.Dimension(126, 38));
        jToolBar1.add(jSeparator1);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/Transfer Document.png"))); // NOI18N
        jButton1.setToolTipText("Cambiar cuarto");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/Donate.png"))); // NOI18N
        jButton2.setToolTipText("Pagar Cuenta");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);
        jToolBar1.add(jSeparator2);

        btnCancelRs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/1464157321_mail-delete22.png"))); // NOI18N
        btnCancelRs.setToolTipText("Cancelar Recervación");
        btnCancelRs.setFocusable(false);
        btnCancelRs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelRs.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelRs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelRsActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCancelRs);
        btnCancelRs.getAccessibleContext().setAccessibleDescription("Cancelar Reservación");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/24x24/1445772562_search.png"))); // NOI18N
        jButton3.setToolTipText("Buscar Cliente");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblCus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (AC.equalsIgnoreCase(action)) {
            lauchCheckout();
        } else {
            lauchQuickRent();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (AC.equalsIgnoreCase(action)) {
            Account row = null;
            Rooms room = null;
            List<Account> accountList = (List<Account>) this.resultList;
            if (accountList == null || accountList.size() == 0) {
                GeneralFunctions.sendMessage(this, "No hay cuentas disponibles para trabajar.");
            } else {
                try {
                    if (accountList.size() == 1) {
                        row = accountList.get(0);
                    } else {
                        row = accountList.get(resultTable.convertRowIndexToModel(resultTable.getSelectedRow()));
                    }
                } catch (IndexOutOfBoundsException e) {
                    GeneralFunctions.sendMessage(this, "Favor de seleccionar una cuenta.");
                }

                if (!roomList.isEmpty() && roomList.size() > 1) {
                    SelectRoomDlg select = new SelectRoomDlg(this, true, roomList);
                    select.setVisible(true);
                    room = select.getSelectedRoom();
                } else {
                    room = roomList.get(0);
                }
                if (room != null) {
                    ChangeHistoryDlg chDlg = new ChangeHistoryDlg(this, true, row, room, topFrame.getMainUser());
                    if (chDlg.getFlag()) {
                        chDlg.setVisible(true);
                    } else {
                        chDlg.dispose();
                    }
                }
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        CustomerRegFrm customerReg;
        customerReg = new CustomerRegFrm(this, true, null,topFrame);
        customerReg.setVisible(true);
        customer = customerReg.getCustomer();
        lblCus.setText(lblCus.getText() + " " + customer.getFullName());
        if (AC.equalsIgnoreCase(action)) {
            resultList = searchAccounts(customer);

        } else {
            resultList = searchReservation(customer);
        }

        fillTable();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnCancelRsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelRsActionPerformed
        Reservation res = null;
        List<Reservation> resList = (List<Reservation>) resultList;
        if (resList == null) {
            GeneralFunctions.sendMessage(this, "No hay cuentas disponibles para trabajar.");
        } else {
            try {

                res = resList.get(resultTable.convertRowIndexToModel(resultTable.getSelectedRow()));

            } catch (Exception e) {
                GeneralFunctions.sendMessage(this, "Favor de seleccionar una cuenta.");
            }
            if (res != null) {
                int dialogResult = JOptionPane.showConfirmDialog(this, UIConstants.CONFIRM_DELETE, UIConstants.TYPE_WARNING, JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    ReservationBoundary reservationBoundary = new ReservationBoundary();
                    MultiValueBoundary multiValueBoundary = new MultiValueBoundary();
                    MultiValue mvStatus = multiValueBoundary.findByKey(new MultiValue(MMKeys.General.STA_INACTIVO_KEY));
                    res.setResStatus(mvStatus);
                    if (reservationBoundary.update(res) == 1) {                       
                        RoomsBoundary roomsBoundary = new RoomsBoundary();
                        mvStatus = multiValueBoundary.findByKey(new MultiValue(MMKeys.Rooms.STA_DISPONIBLE_KEY));
                        Rooms room = res.getRmsId();
                        room.setRmsStatus(mvStatus);
                        if(roomsBoundary.update(room) == 1){
                             JOptionPane.showMessageDialog(this, UIConstants.SUCCESS_DELETE);
                        }else{
                            JOptionPane.showMessageDialog(this, UIConstants.ERROR_GEN);
                        }
                    }
                }
            }
        }

    }//GEN-LAST:event_btnCancelRsActionPerformed

    private List<Account> searchAccounts(Customers customer) {
        Account account = new Account();
        this.accountB = new AccountBoundary();
        account.setCusId(customer);
        return accountB.findByCusId(account);
    }

    private List<Reservation> searchReservation(Customers customer) {
        //TODO crear buscar res by customer 
        Reservation res = new Reservation();
        res.setCusId(customer);
        this.resB = new ReservationBoundary();
        return this.resB.searchByCusId(res);
    }

    private void lauchCheckout() {
        Account row = null;
        List<Account> accountList = (List<Account>) resultList;
        if (accountList == null) {
            GeneralFunctions.sendMessage(this, "No hay cuentas disponibles para trabajar.");
        } else {
            try {
                if (accountList.size() == 1) {
                    row = accountList.get(0);
                } else {
                    row = accountList.get(resultTable.convertRowIndexToModel(resultTable.getSelectedRow()));
                }
            } catch (IndexOutOfBoundsException e) {
                GeneralFunctions.sendMessage(this, "Favor de seleccionar una cuenta.");
            }
            if (row != null) {
                CheckOutDlg checkOutForm = new CheckOutDlg(this, true, row);
                checkOutForm.setVisible(rootPaneCheckingEnabled);
            }
        }
    }

    private void lauchQuickRent() {
        Reservation res = null;
        List<Reservation> resList = (List<Reservation>) resultList;
        if (resList == null) {
            GeneralFunctions.sendMessage(this, "No hay cuentas disponibles para trabajar.");
        } else {
            try {

                res = resList.get(resultTable.convertRowIndexToModel(resultTable.getSelectedRow()));

            } catch (Exception e) {
                GeneralFunctions.sendMessage(this, "Favor de seleccionar una cuenta.");
            }
            if (res != null) {
                QuickRentDlg dlg = new QuickRentDlg(this, res, topFrame.getCurrentShift(),topFrame);
                dlg.setVisible(true);
            }
        }
    }

    //</editor-fold>
    public void fillTable() {
        String cDesc = AC.equalsIgnoreCase(action) ? "Total" : "# Cuartos";
        String col[] = {"ID", "Fecha de Entrada", "Fecha de Salida", cDesc, "Estatus"};
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        tableModel = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        if (AC.equalsIgnoreCase(action)) {
            List<Account> accountList = (List<Account>) this.resultList;
            // The 0 argument is number rows.
            accountList.stream().forEach((next) -> {
                tableModel.addRow(new Object[]{next.getActId(), sd.format(next.getActFecIni()), sd.format(next.getActFecFin()), next.getActTotal(), next.getActStatus()});
                roomList = new ArrayList<>();
                for (AccountTransactions at : next.getAccountTransactionsCollection()) {
                    if (at.getSrvId() == null) {
                        roomList.add(at.getRmsId());
                    }
                }
            });
        } else {
            List<Reservation> accountList = (List<Reservation>) this.resultList;
            // The 0 argument is number rows.
            Reservation res =accountList.get(0);
            
                tableModel.addRow(new Object[]{res.getResId(), sd.format(res.getResFecIni()), sd.format(res.getResFecFin()), accountList.size(), res.getResStatus()});

            
        }

        resultTable.setModel(tableModel);
        resultTable.getColumn("ID").setMinWidth(0);
        resultTable.getColumn("ID").setMaxWidth(0);
        resultTable.setColumnSelectionAllowed(false);
        resultTable.setCellSelectionEnabled(false);
        resultTable.setRowSelectionAllowed(true);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    public MainFrm getParent() {
        return this.topFrame;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelRs;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblCus;
    private javax.swing.JTable resultTable;
    // End of variables declaration//GEN-END:variables

}
