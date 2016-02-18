package com.ahms.ui;

import com.ahms.boundary.security.AccountBoundary;
import com.ahms.boundary.security.AccountTransactionsBoundary;
import com.ahms.boundary.security.CustomersBoundary;
import com.ahms.boundary.security.FloorsBoundary;
import com.ahms.boundary.security.MultiValueBoundary;
import com.ahms.boundary.security.RoomTypesBoundary;
import com.ahms.boundary.security.RoomsBoundary;
import com.ahms.model.entity.Account;
import com.ahms.model.entity.AccountTransactions;
import com.ahms.model.entity.CashOut;
import com.ahms.model.entity.Customers;
import com.ahms.model.entity.Floors;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.RoomTypes;
import com.ahms.model.entity.Rooms;
import com.ahms.model.entity.Users;
import com.ahms.ui.utils.DateLabelFormatter;
import com.ahms.ui.utils.GeneralFunctions;
import com.ahms.ui.utils.UIConstants;
import com.ahms.util.MMKeys;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author rsoto
 */
public class MainFrm extends javax.swing.JFrame {

    private Users mainUser = null;
    private Boolean shiftOn = false;
    private RoomsBoundary roomsBounday = null;
    private FloorsBoundary floorsBoundary = null;
    private AccountBoundary accountBoundary = null;
    private AccountTransactionsBoundary accountTransactionsBoundary;
    private MultiValueBoundary multiValueBoundary;
    private RoomTypesBoundary roomTypesBoundary;
    private CustomersBoundary customersBoundary;
    private CashOut currentShift = null;
    private ArrayList<String> cuartos = new ArrayList<String>();
    
    private Rooms quickRentRoomAssigned = null;
    private BigDecimal quickRentSubTotal = BigDecimal.ZERO;
    private BigDecimal quickRentIva = BigDecimal.ZERO;
    private BigDecimal quickRentTotal = BigDecimal.ZERO;
    private BigDecimal quickrentIvaPercent = BigDecimal.ZERO;

    private void fillData() {

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
        this.mainUser = mainUser;
        this.currentShift = currentShift;
        initComponents();
        roomsBounday = new RoomsBoundary();
        floorsBoundary = new FloorsBoundary();
        accountBoundary = new AccountBoundary();
        accountTransactionsBoundary = new AccountTransactionsBoundary();
        multiValueBoundary = new MultiValueBoundary();
        roomTypesBoundary = new RoomTypesBoundary();
        customersBoundary = new CustomersBoundary();
        
        jlQRRoomNumber.setVisible(false);
        jbCheckOut.setEnabled(false);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("AHMS: Advanced Hotel Management System ");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        configDatePickers();
        configGrid(roomsBounday.searchAll(new Rooms()));
        configFloors(floorsBoundary.findAllAvailable());
        
        MultiValue multiValueDisp = new MultiValue();
        multiValueDisp.setMvaType(MMKeys.Rooms.GP_KEY);
        configDisponibilidad(multiValueBoundary.findByType(multiValueDisp));
        
        RoomTypes roomTypesActive = new RoomTypes();
        roomTypesActive.setRtyStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.General.STA_ACTIVO_KEY)));
        configTiposCuartos(roomTypesBoundary.findActiveTypes(roomTypesActive));
    }
    
    public void clearQuickRentInstance(){
        quickRentRoomAssigned = null;
        quickRentSubTotal = BigDecimal.ZERO;
        quickRentIva = BigDecimal.ZERO;
        quickRentTotal = BigDecimal.ZERO;
        quickrentIvaPercent = BigDecimal.ZERO;
        jlQRRoomNumber.setVisible(false);
        jbQRAddGuests.setEnabled(false);
        jbQRPagar.setEnabled(false);
        
    }

    private void configTiposCuartos(List<RoomTypes> lstRoomTypes){
        DefaultComboBoxModel model2= new DefaultComboBoxModel(lstRoomTypes.toArray(new RoomTypes[lstRoomTypes.size()]));
        this.jcbQuickRentTipo.setModel(model2);
        DefaultComboBoxModel model = new DefaultComboBoxModel(lstRoomTypes.toArray(new RoomTypes[lstRoomTypes.size()]));
        this.jcbTipoCuarto.setModel(model);
    }
    
    private void configFloors(List<Floors> lstFloors) {
        DefaultComboBoxModel model = new DefaultComboBoxModel(lstFloors.toArray(new Floors[lstFloors.size()]));
        this.jcbPisos.setModel(model);

    }
    
    private void configDisponibilidad(List<MultiValue> lstMultiValueDisp){
        DefaultComboBoxModel model = new DefaultComboBoxModel(lstMultiValueDisp.toArray(new MultiValue[lstMultiValueDisp.size()]));
        this.jcbDisponibilidad.setModel(model);
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
        JFrame parent = this;
        jtDashboard.setModel(model);
        jtDashboard.setRowHeight(50);
        jtDashboard.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        jtDashboard.getColumnModel().getColumn(0).setMaxWidth(40);
        jtDashboard.getColumnModel().getColumn(1).setMaxWidth(20);
        jtDashboard.getColumnModel().getColumn(2).setMaxWidth(300);
        jtDashboard.getColumnModel().getColumn(3).setMaxWidth(100);
        jtDashboard.getColumnModel().getColumn(4).setMaxWidth(150);
        jtDashboard.getColumnModel().getColumn(5).setMaxWidth(150);
        jtDashboard.getColumnModel().getColumn(6).setMaxWidth(100);
        jtDashboard.getColumnModel().getColumn(7).setMaxWidth(100);
        jtDashboard.getColumnModel().getColumn(8).setMaxWidth(20);
        jtDashboard.addMouseListener(new MouseAdapter() {
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

        });
    }

    private Rooms getRoomFromDashboard(Integer rmsId){
        Rooms room= null;
        try {
            
        } catch (Exception e) {
        }
        return room;
    }
    
    private void configDatePickers() {

        Calendar calToday = Calendar.getInstance();

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
        datePickerEntrada.getJFormattedTextField().setValue(Calendar.getInstance());
        this.jpFecEntContainer.add(datePickerEntrada);

        UtilDateModel modelSalida = new UtilDateModel();
        Properties pSalida = new Properties();
        pSalida.put("text.today", calToday.get(Calendar.DATE));
        pSalida.put("text.month", calToday.get(Calendar.MONTH + 1));
        pSalida.put("text.year", calToday.get(Calendar.YEAR));
        JDatePanelImpl datePanelSalida = new JDatePanelImpl(modelSalida, pSalida);
        JDatePickerImpl datePickerSalida = new JDatePickerImpl(datePanelSalida, new DateLabelFormatter());
        datePanelSalida.setFont(new Font("Arial", Font.PLAIN, 8));
        datePickerSalida.setLocation(0, 0);
        datePickerSalida.setSize(223, 50);
        datePickerSalida.setVisible(true);
        datePickerSalida.setEnabled(true);
        datePickerSalida.getJFormattedTextField().setValue(Calendar.getInstance());
        this.jpFecSalContainer.add(datePickerSalida);

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
        jPanel1 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jlTurno = new javax.swing.JLabel();
        jbBuscarCliente = new javax.swing.JButton();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jcbPisos = new javax.swing.JComboBox();
        jbCheckOut = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jcbDisponibilidad = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jcbTipoCuarto = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtDashboard = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpRegistroRapido = new javax.swing.JPanel();
        jlRegistroNombre = new javax.swing.JLabel();
        jlRegistroPaterno = new javax.swing.JLabel();
        jlRegistroMaterno = new javax.swing.JLabel();
        jlRegistroRfc = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jpFecEntContainer = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jpFecSalContainer = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jspNumeroPersonas = new javax.swing.JSpinner();
        jbQRAddGuests = new javax.swing.JButton();
        jbQRPagar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jcbQuickRentTipo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jlQRSubTotal = new javax.swing.JLabel();
        jlQRIVA = new javax.swing.JLabel();
        jlQRTotal = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jbQRSearchRoom = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jlQRRoomNumber = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jpRegistroRapido1 = new javax.swing.JPanel();
        jlRegistroNombre1 = new javax.swing.JLabel();
        jlRegistroPaterno1 = new javax.swing.JLabel();
        jlRegistroMaterno1 = new javax.swing.JLabel();
        jlRegistroRfc1 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jtSubtotal1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jtIdCuarto1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jpFecEntContainer1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jpFecSalContainer1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jspNumeroPersonas1 = new javax.swing.JSpinner();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        btnIniciarTurno = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

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

        jPanel1.setBackground(java.awt.SystemColor.controlLtHighlight);
        jPanel1.setToolTipText("");

        jlTurno.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlTurno.setText("Turno no iniciado");

        jbBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/1445772770_search-80px.png"))); // NOI18N
        jbBuscarCliente.setText("Buscar Cliente");
        jbBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTurno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlTurno)
                    .addComponent(jbBuscarCliente))
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(254, 254, 254));

        jLabel1.setText("Piso: ");

        jcbPisos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbPisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPisosActionPerformed(evt);
            }
        });

        jbCheckOut.setText("CheckOut");
        jbCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCheckOutActionPerformed(evt);
            }
        });

        jLabel2.setText("Disponibilidad:");

        jcbDisponibilidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbDisponibilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDisponibilidadActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipo:");

        jcbTipoCuarto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbTipoCuarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTipoCuartoActionPerformed(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbCheckOut)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcbPisos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbCheckOut)
                    .addComponent(jLabel2)
                    .addComponent(jcbDisponibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jcbTipoCuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        jScrollPane1.setViewportView(jtDashboard);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane5.addTab("Cuartos", jPanel4);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 987, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 477, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("tools", jPanel6);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jlRegistroNombre.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlRegistroNombre.setText("Jorge Alfonso");

        jlRegistroPaterno.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlRegistroPaterno.setText("Castañeda");

        jlRegistroMaterno.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlRegistroMaterno.setText("Gutierrez");

        jlRegistroRfc.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlRegistroRfc.setText("CAGJ860711BZ6");

        jLabel8.setText("Entrada:");

        javax.swing.GroupLayout jpFecEntContainerLayout = new javax.swing.GroupLayout(jpFecEntContainer);
        jpFecEntContainer.setLayout(jpFecEntContainerLayout);
        jpFecEntContainerLayout.setHorizontalGroup(
            jpFecEntContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpFecEntContainerLayout.setVerticalGroup(
            jpFecEntContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        jLabel9.setText("Salida:");

        javax.swing.GroupLayout jpFecSalContainerLayout = new javax.swing.GroupLayout(jpFecSalContainer);
        jpFecSalContainer.setLayout(jpFecSalContainerLayout);
        jpFecSalContainerLayout.setHorizontalGroup(
            jpFecSalContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpFecSalContainerLayout.setVerticalGroup(
            jpFecSalContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 33, Short.MAX_VALUE)
        );

        jLabel10.setText("Numero de Personas:");

        jbQRAddGuests.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack4/user_male_edit.png"))); // NOI18N
        jbQRAddGuests.setText("Inquilinos");
        jbQRAddGuests.setEnabled(false);
        jbQRAddGuests.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbQRAddGuests.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jbQRAddGuests.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

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

        jLabel4.setText("Tipo de cuarto:");

        jcbQuickRentTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbQuickRentTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbQuickRentTipoActionPerformed(evt);
            }
        });

        jLabel5.setText("Subtotal:");

        jLabel6.setText("IVA:");

        jLabel7.setText("Total:");

        jlQRSubTotal.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlQRSubTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlQRSubTotal.setText("subtotal");

        jlQRIVA.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlQRIVA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlQRIVA.setText("iva");

        jlQRTotal.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlQRTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlQRTotal.setText("total");

        jbQRSearchRoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack2/find.png"))); // NOI18N
        jbQRSearchRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbQRSearchRoomActionPerformed(evt);
            }
        });

        jLabel11.setText("Numero de Cuarto:");

        jlQRRoomNumber.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlQRRoomNumber.setForeground(new java.awt.Color(232, 43, 43));
        jlQRRoomNumber.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jlQRRoomNumber.setText("RoomNmbr");
        jlQRRoomNumber.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jpRegistroRapidoLayout = new javax.swing.GroupLayout(jpRegistroRapido);
        jpRegistroRapido.setLayout(jpRegistroRapidoLayout);
        jpRegistroRapidoLayout.setHorizontalGroup(
            jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpFecEntContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(27, 27, 27)
                        .addComponent(jpFecSalContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlQRIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlQRSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator6)
                    .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlQRRoomNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlQRTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jspNumeroPersonas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                        .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                                .addComponent(jbQRAddGuests, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbQRPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlRegistroRfc)
                            .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                                .addComponent(jlRegistroNombre)
                                .addGap(18, 18, 18)
                                .addComponent(jlRegistroPaterno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jlRegistroMaterno))
                            .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jcbQuickRentTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbQRSearchRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpRegistroRapidoLayout.setVerticalGroup(
            jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlRegistroNombre)
                    .addComponent(jlRegistroPaterno)
                    .addComponent(jlRegistroMaterno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlRegistroRfc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpFecEntContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9))
                    .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jpFecSalContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                        .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jcbQuickRentTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jlQRRoomNumber)))
                    .addComponent(jbQRSearchRoom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jspNumeroPersonas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jlQRSubTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jlQRIVA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jlQRTotal))
                .addGap(18, 18, 18)
                .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbQRAddGuests, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbQRPagar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Check in ", jpRegistroRapido);

        jlRegistroNombre1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlRegistroNombre1.setText("Jorge Alfonso");

        jlRegistroPaterno1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlRegistroPaterno1.setText("Castañeda");

        jlRegistroMaterno1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlRegistroMaterno1.setText("Gutierrez");

        jlRegistroRfc1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jlRegistroRfc1.setText("CAGJ860711BZ6");

        jLabel12.setText("Subtotal:");

        jButton2.setText("Reservar");

        jtIdCuarto1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtIdCuarto1.setEnabled(false);

        jLabel14.setText("Entrada:");

        javax.swing.GroupLayout jpFecEntContainer1Layout = new javax.swing.GroupLayout(jpFecEntContainer1);
        jpFecEntContainer1.setLayout(jpFecEntContainer1Layout);
        jpFecEntContainer1Layout.setHorizontalGroup(
            jpFecEntContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpFecEntContainer1Layout.setVerticalGroup(
            jpFecEntContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel15.setText("Salida:");

        javax.swing.GroupLayout jpFecSalContainer1Layout = new javax.swing.GroupLayout(jpFecSalContainer1);
        jpFecSalContainer1.setLayout(jpFecSalContainer1Layout);
        jpFecSalContainer1Layout.setHorizontalGroup(
            jpFecSalContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpFecSalContainer1Layout.setVerticalGroup(
            jpFecSalContainer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 33, Short.MAX_VALUE)
        );

        jLabel16.setText("Numero de Cuartos:");

        javax.swing.GroupLayout jpRegistroRapido1Layout = new javax.swing.GroupLayout(jpRegistroRapido1);
        jpRegistroRapido1.setLayout(jpRegistroRapido1Layout);
        jpRegistroRapido1Layout.setHorizontalGroup(
            jpRegistroRapido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRegistroRapido1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpRegistroRapido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpRegistroRapido1Layout.createSequentialGroup()
                        .addGroup(jpRegistroRapido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpRegistroRapido1Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(jtIdCuarto1))
                            .addGroup(jpRegistroRapido1Layout.createSequentialGroup()
                                .addGroup(jpRegistroRapido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpRegistroRapido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jpFecSalContainer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jpFecEntContainer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jpRegistroRapido1Layout.createSequentialGroup()
                                .addGroup(jpRegistroRapido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlRegistroNombre1)
                                    .addComponent(jlRegistroPaterno1)
                                    .addComponent(jlRegistroMaterno1)
                                    .addComponent(jlRegistroRfc1))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRegistroRapido1Layout.createSequentialGroup()
                                .addGap(0, 65, Short.MAX_VALUE)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtSubtotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addGap(14, 14, 14)))
                        .addContainerGap())
                    .addGroup(jpRegistroRapido1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jspNumeroPersonas1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        jpRegistroRapido1Layout.setVerticalGroup(
            jpRegistroRapido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRegistroRapido1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jlRegistroNombre1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlRegistroPaterno1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlRegistroMaterno1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlRegistroRfc1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtIdCuarto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpRegistroRapido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpFecEntContainer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGroup(jpRegistroRapido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpRegistroRapido1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel15))
                    .addGroup(jpRegistroRapido1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jpFecSalContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jpRegistroRapido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jspNumeroPersonas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpRegistroRapido1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jtSubtotal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 333, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jpRegistroRapido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 477, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jpRegistroRapido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Reservaciones", jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTabbedPane5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane5)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
                .addContainerGap())
        );

        jTabbedPane5.getAccessibleContext().setAccessibleName("Cuartos");

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/pack3/Transfer.png"))); // NOI18N
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

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/1445771639_2.png"))); // NOI18N
        jMenu3.setText("jMenu3");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/1445771614_7.png"))); // NOI18N
        jMenuItem1.setText("RoomService");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem4.setText("jMenuItem4");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarClienteActionPerformed

        CustomerReg customerReg = new CustomerReg(this, true);
        customerReg.setVisible(true);
    }//GEN-LAST:event_jbBuscarClienteActionPerformed

    private void jcbPisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPisosActionPerformed
        Rooms room = new Rooms();
        room.setFlrId((Floors)this.jcbPisos.getSelectedItem());
        configGrid(roomsBounday.findByFloor(room));
    }//GEN-LAST:event_jcbPisosActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jbCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCheckOutActionPerformed
        Integer roomSelected = Integer.parseInt(String.valueOf(jtDashboard.getModel().getValueAt(jtDashboard.getSelectedRow(), 1)));
        AccountTransactions acctTran = new AccountTransactions();
        acctTran.setRmsId(new Rooms(roomSelected));
        
        AccountTransactions accountTran = accountTransactionsBoundary.findByRmsId(acctTran);
        Account account = accountBoundary.find(new Account(accountTran.getActId().getActId()));
        
        JDialog dialogCheckout = new CheckOutForm(this, true, account.getCusId());
        dialogCheckout.setLocationRelativeTo(this);
        dialogCheckout.setVisible(true);
    }//GEN-LAST:event_jbCheckOutActionPerformed

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

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        CancelationPrompt cp = new CancelationPrompt(this, true);
        cp.setVisible(true);
        boolean response  =cp.getAutorization();
        System.out.println(" response : " + response);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jcbDisponibilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDisponibilidadActionPerformed
        
        Rooms roomDisp = new Rooms();
        roomDisp.setRmsStatus((MultiValue) jcbDisponibilidad.getSelectedItem());
        configGrid(roomsBounday.findByRmsStatus(roomDisp));
    }//GEN-LAST:event_jcbDisponibilidadActionPerformed

    private void jcbTipoCuartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTipoCuartoActionPerformed
        
        Rooms roomTypes = new Rooms();
        roomTypes.setRmsBeds((RoomTypes) jcbTipoCuarto.getSelectedItem());
        configGrid(roomsBounday.findByRmsBeds(roomTypes));
    }//GEN-LAST:event_jcbTipoCuartoActionPerformed

    private void jcbQuickRentTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbQuickRentTipoActionPerformed
        //actualizar el numero de personas segun el tipo de cuarto
        RoomTypes tipoSeleccionado = (RoomTypes) jcbQuickRentTipo.getSelectedItem();
        System.out.println(tipoSeleccionado.getRoomsCollection().size());
    }//GEN-LAST:event_jcbQuickRentTipoActionPerformed

    private void jbQRSearchRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbQRSearchRoomActionPerformed
        RoomTypes tipoSeleccionado = (RoomTypes) jcbQuickRentTipo.getSelectedItem();
        for(Rooms room : tipoSeleccionado.getRoomsCollection()){
            if(room.getRmsStatus().getMvaKey().equals(MMKeys.Rooms.STA_DISPONIBLE_KEY)){
                quickRentRoomAssigned = room;
                jlQRRoomNumber.setText(room.getRmsNumber());
                jlQRRoomNumber.setVisible(true);
                jbQRAddGuests.setEnabled(true);
                jbQRPagar.setEnabled(true);
                jspNumeroPersonas.setModel(new SpinnerNumberModel(1, 1, quickRentRoomAssigned.getRmsMaxOcu(), 1));
                //generar totales de renta
                MultiValue mvIva = multiValueBoundary.findByKey(new MultiValue(MMKeys.Math.IVA_KEY));
                quickrentIvaPercent = new BigDecimal(mvIva.getMvaDescription()).setScale(2, RoundingMode.HALF_EVEN);
                
                JDatePickerImpl fEntrada = (JDatePickerImpl) this.jpFecEntContainer.getComponent(0);
                JDatePickerImpl fSalida = (JDatePickerImpl) this.jpFecSalContainer.getComponent(0);
                Calendar calEntrada = (Calendar) fEntrada.getJFormattedTextField().getValue();
                Calendar calSalida = (Calendar) fSalida.getJFormattedTextField().getValue();
                long days = GeneralFunctions.getDaysBetweenDates(calEntrada, calSalida) + 1;
                //JOptionPane.showMessageDialog(this,days);
                quickRentSubTotal = quickRentRoomAssigned.getRteId().getRtePrice().multiply(new BigDecimal(days)).setScale(2, RoundingMode.HALF_EVEN);
                quickRentIva = quickRentSubTotal.multiply(quickrentIvaPercent).setScale(2, RoundingMode.HALF_EVEN);
                quickRentTotal = quickRentSubTotal.add(quickRentIva).setScale(2, RoundingMode.HALF_EVEN);
                
                jlQRSubTotal.setText(quickRentSubTotal.toString());
                jlQRIVA.setText(quickRentIva.toString());
                jlQRTotal.setText(quickRentTotal.toString());                
                return;
            }            
        }
        JOptionPane.showMessageDialog(this, UIConstants.NO_AVAIL_ROOMS);
    }//GEN-LAST:event_jbQRSearchRoomActionPerformed

    private void jbQRPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbQRPagarActionPerformed
        //actualizando el Room
        quickRentRoomAssigned.setRmsStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.Rooms.STA_OCUPADO_KEY)));
        roomsBounday.update(quickRentRoomAssigned);
        //aperturando la cuenta
        JDatePickerImpl fEntrada = (JDatePickerImpl) this.jpFecEntContainer.getComponent(0);
        JDatePickerImpl fSalida = (JDatePickerImpl) this.jpFecSalContainer.getComponent(0);
        Calendar calEntrada = (Calendar) fEntrada.getJFormattedTextField().getValue();
        Calendar calSalida = (Calendar) fSalida.getJFormattedTextField().getValue();
        Account quickRentAccount = new Account();
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
        quickRentAccount.setCusId(customersBoundary.find(new Customers(1)));        
        accountBoundary.insert(quickRentAccount);
        
        //Insertando account transaction
        
        AccountTransactions rentTran = new AccountTransactions();
        rentTran.setAtrDteMod(Calendar.getInstance().getTime());
        rentTran.setAtrNotes("Renta de Cuarto " + quickRentRoomAssigned.getRmsNumber());
        rentTran.setAtrQuantity(1);
        rentTran.setAtrStatus(multiValueBoundary.findByKey(new MultiValue(MMKeys.AccountsTransactions.STA_PAGADO_KEY)));
        rentTran.setAtrUsrMod(currentShift.getUsrId());
        rentTran.setCouId(currentShift);
        rentTran.setRmsId(quickRentRoomAssigned);
        rentTran.setSrvId(null);
        rentTran.setActId(accountBoundary.findLastAccountInserted());
        accountTransactionsBoundary.insert(rentTran);
        
        //LLamando a paymentModule
        PaymentModule paymentModule = new PaymentModule(this, true, quickRentTotal, quickRentAccount);
        paymentModule.setLocationRelativeTo(this);
        paymentModule.setVisible(true);
    }//GEN-LAST:event_jbQRPagarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnIniciarTurno;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JButton jbBuscarCliente;
    private javax.swing.JButton jbCheckOut;
    private javax.swing.JButton jbQRAddGuests;
    private javax.swing.JButton jbQRPagar;
    private javax.swing.JButton jbQRSearchRoom;
    private javax.swing.JComboBox jcbDisponibilidad;
    private javax.swing.JComboBox jcbPisos;
    private javax.swing.JComboBox jcbQuickRentTipo;
    private javax.swing.JComboBox jcbTipoCuarto;
    private javax.swing.JLabel jlQRIVA;
    private javax.swing.JLabel jlQRRoomNumber;
    private javax.swing.JLabel jlQRSubTotal;
    private javax.swing.JLabel jlQRTotal;
    private javax.swing.JLabel jlRegistroMaterno;
    private javax.swing.JLabel jlRegistroMaterno1;
    private javax.swing.JLabel jlRegistroNombre;
    private javax.swing.JLabel jlRegistroNombre1;
    private javax.swing.JLabel jlRegistroPaterno;
    private javax.swing.JLabel jlRegistroPaterno1;
    private javax.swing.JLabel jlRegistroRfc;
    private javax.swing.JLabel jlRegistroRfc1;
    private javax.swing.JLabel jlTurno;
    private javax.swing.JPanel jpFecEntContainer;
    private javax.swing.JPanel jpFecEntContainer1;
    private javax.swing.JPanel jpFecSalContainer;
    private javax.swing.JPanel jpFecSalContainer1;
    private javax.swing.JPanel jpRegistroRapido;
    private javax.swing.JPanel jpRegistroRapido1;
    private javax.swing.JSpinner jspNumeroPersonas;
    private javax.swing.JSpinner jspNumeroPersonas1;
    private javax.swing.JTable jtDashboard;
    private javax.swing.JTextField jtIdCuarto1;
    private javax.swing.JTextField jtSubtotal1;
    // End of variables declaration//GEN-END:variables

}
