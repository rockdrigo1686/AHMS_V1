/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui;

import com.ahms.boundary.security.AccountTransactionsBoundary;
import com.ahms.boundary.security.ServiceBoundary;
import com.ahms.boundary.security.ServiceTypesBoundary;
import com.ahms.model.entity.AccountTransactions;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.Rooms;
import com.ahms.model.entity.ServiceTypes;
import com.ahms.model.entity.Services;
import com.ahms.util.MMKeys;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rsoto
 */
public class RoomService extends javax.swing.JDialog {

    private Rooms room = null;
    private ServiceTypesBoundary serviceTypesBoundary;
    private AccountTransactionsBoundary accountTransactionsBoundary;
    private ServiceBoundary serviceBoundary;

    /**
     * Creates new form RoomService
     */
    public RoomService(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public RoomService(java.awt.Frame parent, boolean modal, Rooms room) {
        super(parent, modal);
        this.room = room;
        initComponents();
        serviceTypesBoundary = new ServiceTypesBoundary();
        accountTransactionsBoundary = new AccountTransactionsBoundary();
        serviceBoundary = new ServiceBoundary();
        loadServiceTypes(serviceTypesBoundary.searchAll(null));
        idRMS.setText("Cuarto # " + room.getRmsNumber() + " - " + room.getRmsDesc());
        
        loadGrid(accountTransactionsBoundary.findAllByRmsId(room));
        
        
    }
    
    public RoomService(java.awt.Frame parent, boolean modal, Integer rms_id) {
        super(parent, modal);
        this.room = new Rooms();
        this.room.setRmsId(rms_id);
        initComponents();
        idRMS.setText(rms_id.toString());
    }
    
    public void loadGrid(List<AccountTransactions> lstAccountTransactions){
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
            vctRow.add(acctTran.getAtrQuantity());
            vctRow.add(acctTran.getSrvId().getSrvPrice());
            rows.add(vctRow);
        }
        DefaultTableModel model = new DefaultTableModel(rows, columnNames) {
            private static final long serialVersionUID = 1L;
            /*@Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }*/
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
        jtAccountTransactions.getColumnModel().getColumn(3).setMaxWidth(300);
        jtAccountTransactions.getColumnModel().getColumn(4).setMaxWidth(100);
        jtAccountTransactions.getColumnModel().getColumn(5).setMaxWidth(100);
        
        /*jtDashboard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clicks = e.getClickCount();
                int row = jtDashboard.getSelectedRow();
                MultiValue estatus = (MultiValue) jtDashboard.getValueAt(row, 7);
                if(clicks > 1){ // doble click
                    if(estatus.getMvaKey().equals(MMKeys.Rooms.STA_OCUPADO_KEY)){
                        //llamar a agregar servicios
                        Rooms roomObj = getRoomFromDashboard(Integer.parseInt(String.valueOf(jtDashboard.getValueAt(row, 8))));
                        RoomService roomService = new RoomService(null, true, roomObj);
                        roomService.setVisible(true);
                    }
                } else {  // 1 click
                    jbCheckOut.setEnabled(false);
                    if(estatus.getMvaKey().equals(MMKeys.Rooms.STA_OCUPADO_KEY)){
                        jbCheckOut.setEnabled(true);
                    }
                    //jtIdCuarto.setText(String.valueOf(jtDashboard.getValueAt(row, 8)));
                    //jspNumeroPersonas.setModel(new SpinnerNumberModel(1, 1, Integer.parseInt(jtDashboard.getValueAt(row, 4).toString()), 1));
                }                
            }

        });*/
    }
    
    private void loadServiceTypes(List<ServiceTypes> serviceTypes){
        DefaultComboBoxModel model = new DefaultComboBoxModel(serviceTypes.toArray(new ServiceTypes[serviceTypes.size()]));
        jcbServiceTypes.setModel(model);
    }
    
    private void loadServices(List<Services> lstServices){
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jcbServiceTypes = new javax.swing.JComboBox();
        jcbServices = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtxtQuantity = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtAccountTransactions = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        idRMS = new javax.swing.JLabel();

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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/1445772552_add.png"))); // NOI18N
        jButton1.setText("Agregar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbServiceTypes, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addComponent(jcbServices, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtxtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcbServiceTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jcbServices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jtxtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jScrollPane1.setViewportView(jtAccountTransactions);

        jPanel2.setBackground(java.awt.Color.white);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/1445772619_notification_error.png"))); // NOI18N
        jButton2.setText("Cancelar");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/1445772697_diskette.png"))); // NOI18N
        jButton3.setText("Guardar");

        idRMS.setText("jLabel4");
        idRMS.setName("idRMS"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(idRMS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(27, 27, 27)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(idRMS))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbServiceTypesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbServiceTypesActionPerformed
        //load Services
        ServiceTypes typeselected = (ServiceTypes) jcbServiceTypes.getSelectedItem();
        loadServices(serviceBoundary.findAllByServiceType(typeselected));
    }//GEN-LAST:event_jcbServiceTypesActionPerformed

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
    private javax.swing.JLabel idRMS;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jcbServiceTypes;
    private javax.swing.JComboBox jcbServices;
    private javax.swing.JTable jtAccountTransactions;
    private javax.swing.JTextField jtxtQuantity;
    // End of variables declaration//GEN-END:variables
}
