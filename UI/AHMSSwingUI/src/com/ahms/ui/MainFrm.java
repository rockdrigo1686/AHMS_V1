package com.ahms.ui;

import com.ahms.boundary.security.AccountBoundary;
import com.ahms.boundary.security.AccountTransactionsBoundary;
import com.ahms.boundary.security.CustomersBoundary;
import com.ahms.boundary.security.FloorsBoundary;
import com.ahms.boundary.security.MessageBoardBoundary;
import com.ahms.boundary.security.MultiValueBoundary;
import com.ahms.boundary.security.PreferenceDetailBoundary;
import com.ahms.boundary.security.ReservationBoundary;
import com.ahms.boundary.security.RoomTypesBoundary;
import com.ahms.boundary.security.RoomsBoundary;
import com.ahms.model.entity.Account;
import com.ahms.model.entity.AccountTransactions;
import com.ahms.model.entity.CashOut;
import com.ahms.model.entity.Customers;
import com.ahms.model.entity.Floors;
import com.ahms.model.entity.MessageBoard;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.RoomTypes;
import com.ahms.model.entity.Rooms;
import com.ahms.model.entity.Users;
import com.ahms.reports.CancelacionesRp;
import com.ahms.reports.CorteCajaRp;
import com.ahms.reports.OcupacionRp;
import com.ahms.reports.ServiciosRp;
import com.ahms.ui.modules.security.ProfilesFrm;
import com.ahms.ui.modules.security.UsersFrm;
import com.ahms.ui.utils.UIConstants;
import com.ahms.util.MMKeys;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rsoto
 */
public class MainFrm extends javax.swing.JFrame {

    private Customers mainCustomer;
    private Users mainUser = null;
    private Boolean shiftOn = false;
    private RoomsBoundary roomsBounday = null;
    private FloorsBoundary floorsBoundary = null;
    private AccountBoundary accountBoundary = null;
    private AccountTransactionsBoundary accountTransactionsBoundary;
    private MultiValueBoundary multiValueBoundary;
    private RoomTypesBoundary roomTypesBoundary;
    private CustomersBoundary customersBoundary;
    private ReservationBoundary reservationBoundary;
    private CashOut currentShift = null;
    private ArrayList<String> cuartos = new ArrayList<String>();
    private PreferenceDetailBoundary preferenceDetailBoundary = null;

    //QuickRent Instances
    private Rooms quickRentRoomAssigned = null;
    private BigDecimal quickRentSubTotal = BigDecimal.ZERO;
    private BigDecimal quickRentIva = BigDecimal.ZERO;
    private BigDecimal quickRentTotal = BigDecimal.ZERO;
    private BigDecimal quickrentIvaPercent = BigDecimal.ZERO;

    //QuickRes Instances
    private Rooms quickResRoomAssigned = null;

    MainFrm() {
    }

    private void fillData() {

    }

    public Customers getMainCustomer() {
        return mainCustomer;
    }

    public void setMainCustomer(Customers mainCustomer) {
        this.mainCustomer = mainCustomer;
    }

    public Users getMainUser() {
        return mainUser;
    }

    /**
     * @param mainUser the mainUser to set
     */
    public void setMainUser(Users mainUser) {
        this.mainUser = mainUser;
    }

    /**
     * @return the shiftOn
     */
    public Boolean getShiftOn() {
        return shiftOn;
    }

    /**
     * @param shiftOn the shiftOn to set
     */
    public void setShiftOn(Boolean shiftOn) {
        this.shiftOn = shiftOn;
    }

    /**
     * @return the currentShift
     */
    public CashOut getCurrentShift() {
        return currentShift;
    }

    /**
     * @param currentShift the currentShift to set
     */
    public void setCurrentShift(CashOut currentShift) {
        this.currentShift = currentShift;
    }

    /**
     * Creates new form MainFrm
     *
     * @param mainUser
     * @param currentShift
     */
    public MainFrm(Users mainUser, CashOut currentShift) {
        this.setMainCustomer(new Customers());
        this.mainUser = mainUser;
        this.currentShift = currentShift;

        initComponents();
        setExtendedState(Frame.MAXIMIZED_BOTH);
        roomsBounday = new RoomsBoundary();
        floorsBoundary = new FloorsBoundary();
        accountBoundary = new AccountBoundary();
        accountTransactionsBoundary = new AccountTransactionsBoundary();
        multiValueBoundary = new MultiValueBoundary();
        roomTypesBoundary = new RoomTypesBoundary();
        customersBoundary = new CustomersBoundary();
        reservationBoundary = new ReservationBoundary();
        preferenceDetailBoundary = new PreferenceDetailBoundary();

        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        //setLocationRelativeTo(null);
        //setResizable(false);
        setTitle("AHMS: Advanced Hotel Management System ");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        configGrid(roomsBounday.searchAll(new Rooms()));
        configFloors(floorsBoundary.findAllAvailable());

        MultiValue multiValueDisp = new MultiValue();
        multiValueDisp.setMvaType(MMKeys.Rooms.GP_KEY);
        configDisponibilidad(multiValueBoundary.findByType(multiValueDisp));

        RoomTypes roomTypesActive = new RoomTypes();
        roomTypesActive.setRtyStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.General.STA_ACTIVO_KEY)));
        configTiposCuartos(roomTypesBoundary.findActiveTypes(roomTypesActive));
        
        fillMessageBoard();

        if (!MMKeys.Profiles.ADMI.equals(this.mainUser.getProId().getProCode())
                && !MMKeys.Profiles.ACNT.equals(this.mainUser.getProId().getProCode())) {
            menAdmin.setEnabled(false);
            menConf.setEnabled(false);
        }
        
         Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configGrid(roomsBounday.searchAll(new Rooms()));
            }
        };

        txtRoomNumber.addActionListener(action);
        
    }

    public void clearQuickRentInstance() {
        quickRentRoomAssigned = null;
        quickRentSubTotal = BigDecimal.ZERO;
        quickRentIva = BigDecimal.ZERO;
        quickRentTotal = BigDecimal.ZERO;
        quickrentIvaPercent = BigDecimal.ZERO;

    }

    public void clearQuickResInstance() {
        quickResRoomAssigned = null;

    }

    private void configTiposCuartos(List<RoomTypes> lstRoomTypes) {
        this.jcbTipoCuarto.removeAllItems();
        this.jcbTipoCuarto.addItem(new RoomTypes());
        for(RoomTypes roomType : lstRoomTypes){
            this.jcbTipoCuarto.addItem(roomType);
        }
        this.jcbTipoCuarto.addItemListener((ItemEvent arg0) -> {
            Rooms roomTypes = new Rooms();
            if(this.jcbTipoCuarto.getSelectedIndex() <= 0){
                configGrid(roomsBounday.searchAll(roomTypes));
            } 
            else {
                roomTypes.setRmsBeds((RoomTypes) jcbTipoCuarto.getSelectedItem());
                configGrid(roomsBounday.findByRmsBeds(roomTypes));
            }            
        });
    }

    private void configFloors(List<Floors> lstFloors) {
        this.jcbPisos.removeAllItems();
        this.jcbPisos.addItem(new Floors());
        for(Floors floor : lstFloors){
            this.jcbPisos.addItem(floor);
        }
        this.jcbPisos.addItemListener((ItemEvent arg0) -> {
            if(this.jcbPisos.getSelectedIndex() <= 0){
                configGrid(roomsBounday.searchAll(new Rooms()));
            } else {
                Rooms room = new Rooms();
                room.setFlrId((Floors) this.jcbPisos.getSelectedItem());
                configGrid(roomsBounday.findByFloor(room));
            }            
        });
    }

    private void configDisponibilidad(List<MultiValue> lstMultiValueDisp) {
        this.jcbDisponibilidad.removeAllItems();
        this.jcbDisponibilidad.addItem(new MultiValue());
        for(MultiValue multi : lstMultiValueDisp){
            this.jcbDisponibilidad.addItem(multi);
        }
        this.jcbDisponibilidad.addItemListener((ItemEvent arg0) -> {
            Rooms roomDisp = new Rooms();
            if(jcbDisponibilidad.getSelectedIndex() <= 0){
                configGrid(roomsBounday.searchAll(roomDisp));
            }
            else{
                roomDisp.setRmsStatus((MultiValue) jcbDisponibilidad.getSelectedItem());
                configGrid(roomsBounday.findByRmsStatus(roomDisp));
            }            
        });
    }

    public void configGrid(List<Rooms> rooms) {
        Vector<String> columnNames = new Vector();
        columnNames.add("");
        columnNames.add("#");
        columnNames.add("Descripción");
        columnNames.add("# Camas");
        columnNames.add("Max Ocupantes");
        columnNames.add("Fecha Reservación");
        columnNames.add("Precio P/N");
        columnNames.add("Estado");
        columnNames.add("");

        Vector<Vector> rows = new Vector<>();
        for (Rooms room : rooms) {
            Vector vctRow = new Vector();
            switch (room.getRmsStatus().getMvaKey()) {
                case MMKeys.Rooms.STA_DISPONIBLE_KEY:
                    vctRow.add(new ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack3/green_ball.png")));
                    break;
                case MMKeys.Rooms.STA_OCUPADO_KEY:
                    vctRow.add(new ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack3/red_ball.png")));
                    break;
                case MMKeys.Rooms.STA_RESERVADO_KEY:
                    vctRow.add(new ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack3/blue_ball.png")));
                    break;
                case MMKeys.Rooms.STA_MTO_KEY:
                    vctRow.add(new ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack3/grey_ball.png")));
                    break;
            }
            vctRow.add(room.getRmsNumber());
            vctRow.add(room.getRmsDesc());
            vctRow.add(room.getRmsBeds().getRtyBeds());
            vctRow.add(room.getRmsMaxOcu());
            vctRow.add(room.getRmsDteMod());
            vctRow.add(100);
            vctRow.add(room.getRmsStatus());
            vctRow.add(room.getRmsId());
            rows.add(vctRow);
        }

        DefaultTableModel model = new DefaultTableModel(rows, columnNames) {

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
        //JFrame parent = this;
        jtDashboard.setModel(model);
        jtDashboard.setRowHeight(50);
        jtDashboard.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        jtDashboard.getColumnModel().getColumn(0).setMaxWidth(40);
        jtDashboard.getColumnModel().getColumn(1).setMaxWidth(20);
        jtDashboard.getColumnModel().getColumn(2).setMaxWidth(800);
        jtDashboard.getColumnModel().getColumn(3).setMaxWidth(100);
        jtDashboard.getColumnModel().getColumn(4).setMaxWidth(150);
        jtDashboard.getColumnModel().getColumn(5).setMaxWidth(150);
        jtDashboard.getColumnModel().getColumn(6).setMaxWidth(100);
        jtDashboard.getColumnModel().getColumn(7).setMaxWidth(100);
        jtDashboard.getColumnModel().getColumn(8).setMaxWidth(20);

    }

    private Rooms getRoomFromDashboard(Integer rmsId) {
        Rooms room = null;
        try {
            room = roomsBounday.findByRmsId(new Rooms(rmsId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return room;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jcbPisos = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jcbDisponibilidad = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jcbTipoCuarto = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        txtRoomNumber = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtDashboard = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        messageBoard = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        btnRentDlg = new javax.swing.JButton();
        btnResDlg = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnCheckinDlg = new javax.swing.JButton();
        btnSearchAcc = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btnIniciarTurno = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        menAdmin = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        miProfiles = new javax.swing.JMenuItem();
        miUsers = new javax.swing.JMenuItem();
        menConf = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable3);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(192, 59, 59));

        jPanel7.setBackground(new java.awt.Color(254, 254, 254));

        jLabel1.setText("Piso: ");

        jcbPisos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Disponibilidad:");

        jcbDisponibilidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Tipo:");

        jcbTipoCuarto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Cuarto:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jcbPisos, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbDisponibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbTipoCuarto, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcbPisos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jcbDisponibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jcbTipoCuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtRoomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jtDashboard.setModel(new javax.swing.table.DefaultTableModel(
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
        jtDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtDashboardMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtDashboard);

        messageBoard.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(messageBoard);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jToolBar1.setRollover(true);

        btnRentDlg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/building.png"))); // NOI18N
        btnRentDlg.setToolTipText("Renta de cuartos");
        btnRentDlg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRentDlgActionPerformed(evt);
            }
        });
        jToolBar1.add(btnRentDlg);

        btnResDlg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/calendar.png"))); // NOI18N
        btnResDlg.setToolTipText("Reservaciones");
        btnResDlg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResDlgActionPerformed(evt);
            }
        });
        jToolBar1.add(btnResDlg);
        jToolBar1.add(jSeparator4);

        btnCheckinDlg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/test.png"))); // NOI18N
        btnCheckinDlg.setToolTipText("Check in");
        btnCheckinDlg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckinDlgActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCheckinDlg);

        btnSearchAcc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/search.png"))); // NOI18N
        btnSearchAcc.setToolTipText("Busqueda de Cuentas");
        btnSearchAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchAccActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSearchAcc);

        jToolBar2.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pencil.png"))); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/repeat.png"))); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton2);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack3/Transfer Document.png"))); // NOI18N
        jMenu1.setText("Movimientos");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack3/Donate.png"))); // NOI18N
        jMenuItem3.setText("Movimiento de Efectivo");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack3/Transfer.png"))); // NOI18N
        jMenuItem1.setText("Movimientos de cuartos");
        jMenu1.add(jMenuItem1);

        jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack3/Appointment Urgent.png"))); // NOI18N
        jMenuItem16.setText("Reservaciones");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem16);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack3/History.png"))); // NOI18N
        jMenu2.setText("Turnos");

        btnIniciarTurno.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        btnIniciarTurno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack3/Add Appointment.png"))); // NOI18N
        btnIniciarTurno.setText("Iniciar Turno");
        btnIniciarTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarTurnoActionPerformed(evt);
            }
        });
        jMenu2.add(btnIniciarTurno);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack3/Remove Appointment.png"))); // NOI18N
        jMenuItem2.setText("Cerrar Turno");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        menAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack3/Desktop.png"))); // NOI18N
        menAdmin.setText("Administracion");

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/chart_bar.png"))); // NOI18N
        jMenu4.setText("Reportes");

        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pdf.png"))); // NOI18N
        jMenuItem13.setText("Serivicios");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem13);

        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pdf.png"))); // NOI18N
        jMenuItem14.setText("Ocupacion");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem14);

        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pdf.png"))); // NOI18N
        jMenuItem15.setText("Corte de Caja");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem15);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pdf.png"))); // NOI18N
        jMenuItem4.setText("Cancelaciones");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        menAdmin.add(jMenu4);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/group.png"))); // NOI18N
        jMenu6.setText("Usuarios y Perfiles");

        miProfiles.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        miProfiles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/vcard.png"))); // NOI18N
        miProfiles.setText("Perfiles");
        miProfiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miProfilesActionPerformed(evt);
            }
        });
        jMenu6.add(miProfiles);

        miUsers.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        miUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/user.png"))); // NOI18N
        miUsers.setText("Usuarios");
        miUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miUsersActionPerformed(evt);
            }
        });
        jMenu6.add(miUsers);

        menAdmin.add(jMenu6);

        jMenuBar1.add(menAdmin);

        menConf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack3/Run.png"))); // NOI18N
        menConf.setText("Configuracion");

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/house.png"))); // NOI18N
        jMenu7.setText("Pisos y Cuartos");

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/application_form.png"))); // NOI18N
        jMenuItem6.setText("Pisos");
        jMenu7.add(jMenuItem6);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/application_form.png"))); // NOI18N
        jMenuItem7.setText("Precios");
        jMenu7.add(jMenuItem7);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/application_form.png"))); // NOI18N
        jMenuItem8.setText("Tipos de Cuartos");
        jMenu7.add(jMenuItem8);

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/application_form.png"))); // NOI18N
        jMenuItem9.setText("Cuartos");
        jMenu7.add(jMenuItem9);

        menConf.add(jMenu7);

        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/package.png"))); // NOI18N
        jMenu8.setText("Servicios");

        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/application_form.png"))); // NOI18N
        jMenuItem10.setText("Tipos de Servicios");
        jMenu8.add(jMenuItem10);

        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/application_form.png"))); // NOI18N
        jMenuItem11.setText("Servicios");
        jMenu8.add(jMenuItem11);

        menConf.add(jMenu8);

        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/money.png"))); // NOI18N
        jMenuItem12.setText("Tipos de Pago");
        menConf.add(jMenuItem12);

        jMenuBar1.add(menConf);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarTurnoActionPerformed
        // TODO add your handling code here:
        if (currentShift == null) {
            ShiftFrm shiftFrm = new ShiftFrm(this, true, mainUser);
            shiftFrm.setVisible(true);
        }

    }//GEN-LAST:event_btnIniciarTurnoActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        if (currentShift != null) {
            ShiftEndFrm se = new ShiftEndFrm(this, true, mainUser, currentShift);
            se.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        if (currentShift != null) {
            MoneyMovementFRM mmFrm;
            try {
                mmFrm = new MoneyMovementFRM(this, true, currentShift, mainUser);
                mmFrm.setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(MainFrm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, UIConstants.ERROR_SHIFT_INI);
        }

    }//GEN-LAST:event_jMenuItem3ActionPerformed


    private void miProfilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miProfilesActionPerformed
        // TODO add your handling code here:
        ProfilesFrm profileFrm = new ProfilesFrm();
        profileFrm.setVisible(true);
    }//GEN-LAST:event_miProfilesActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void miUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miUsersActionPerformed
        // TODO add your handling code here:
        UsersFrm userFrm = new UsersFrm();
        userFrm.setVisible(true);
    }//GEN-LAST:event_miUsersActionPerformed

    private void btnRentDlgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRentDlgActionPerformed
        // TODO add your handling code here:
        QuickRentDialog qRentDilg = new QuickRentDialog(this, true, mainCustomer, currentShift);
        qRentDilg.setVisible(true);
    }//GEN-LAST:event_btnRentDlgActionPerformed

    private void btnResDlgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResDlgActionPerformed
        // TODO add your handling code here:
        QuickResDialog qResDlg = new QuickResDialog(this, true, mainCustomer, currentShift);
        qResDlg.setVisible(true);
    }//GEN-LAST:event_btnResDlgActionPerformed

    private void btnSearchAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchAccActionPerformed
        // TODO add your handling code here:
        AccountSearch accSearch = new AccountSearch(this, true, "AC");
        accSearch.setVisible(true);
    }//GEN-LAST:event_btnSearchAccActionPerformed

    private void btnCheckinDlgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckinDlgActionPerformed
        // TODO add your handling code here:
        AccountSearch accSearch = new AccountSearch(this, true, "RS");
        accSearch.setVisible(true);
    }//GEN-LAST:event_btnCheckinDlgActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        CancelacionesRp rep = new CancelacionesRp(this, true);
        rep.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
        ServiciosRp  rep = new ServiciosRp(this, true);
        rep.setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
        OcupacionRp rep = new OcupacionRp(this, true);
        rep.setVisible(true);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:
        CorteCajaRp rep = new CorteCajaRp(this, true);
        rep.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jtDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtDashboardMouseClicked
        int clicks = evt.getClickCount();
        int row = jtDashboard.getSelectedRow();
        MultiValue estatus = (MultiValue) jtDashboard.getValueAt(row, 7);
        if (clicks > 1) { // doble click
            if (estatus.getMvaKey().equals(MMKeys.Rooms.STA_OCUPADO_KEY)) {
                //llamar a agregar servicios
                Rooms roomObj = getRoomFromDashboard(Integer.parseInt(String.valueOf(jtDashboard.getValueAt(row, 8))));
                RoomService roomService = new RoomService(null, true, roomObj, currentShift);
                roomService.setLocationRelativeTo(evt.getComponent().getParent().getParent().getParent());
                roomService.setVisible(true);
            }
        } else {  // 1 click

        }
    }//GEN-LAST:event_jtDashboardMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        fillMessageBoard();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
private void fillMessageBoard() {
        String col[] = {"ID", "Mesaje", "Fecha","Usuario"};
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        DefaultTableModel tableModel = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        MessageBoardBoundary messageBoundary = new MessageBoardBoundary();
            List<MessageBoard> messages =  messageBoundary.searchAll(new MessageBoard());
            // The 0 argument is number rows.
            messages.stream().forEach((next) -> {
                tableModel.addRow(new Object[]{next.getMsgId(), next.getMsgMessage(), sd.format(next.getMsgDate()), next.getMsgUser().getFullName()});
            });
        messageBoard.setModel(tableModel);
        messageBoard.getColumn("ID").setMinWidth(0);
        messageBoard.getColumn("ID").setMaxWidth(0);
        messageBoard.setColumnSelectionAllowed(false);
        messageBoard.setCellSelectionEnabled(false);
        messageBoard.setRowSelectionAllowed(true);
        messageBoard.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheckinDlg;
    private javax.swing.JMenuItem btnIniciarTurno;
    private javax.swing.JButton btnRentDlg;
    private javax.swing.JButton btnResDlg;
    private javax.swing.JButton btnSearchAcc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JComboBox jcbDisponibilidad;
    private javax.swing.JComboBox jcbPisos;
    private javax.swing.JComboBox jcbTipoCuarto;
    private javax.swing.JTable jtDashboard;
    private javax.swing.JMenu menAdmin;
    private javax.swing.JMenu menConf;
    private javax.swing.JTable messageBoard;
    private javax.swing.JMenuItem miProfiles;
    private javax.swing.JMenuItem miUsers;
    private javax.swing.JTextField txtRoomNumber;
    // End of variables declaration//GEN-END:variables

    

}
