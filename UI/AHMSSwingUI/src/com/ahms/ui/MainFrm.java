package com.ahms.ui;

import com.ahms.boundary.security.FloorsBoundary;
import com.ahms.boundary.security.RoomsBoundary;
import com.ahms.model.entity.Floors;
import com.ahms.model.entity.Rooms;
import com.ahms.model.entity.Users;
import com.ahms.ui.utils.DateLabelFormatter;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
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

    private ArrayList<String> cuartos = new ArrayList<String>();

    private void fillData() {

    }

    public Users getMainUser() {
        return mainUser;
    }

    public void setMainUser(Users mainUser) {
        this.mainUser = mainUser;
    }

    /**
     * Creates new form MainFrm
     */
    public MainFrm() {
        initComponents();
        roomsBounday = new RoomsBoundary();
        floorsBoundary = new FloorsBoundary();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("AHMS: Advanced Hotel Management System ");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        configDatePickers();
        configGrid(roomsBounday.searchAll(new Rooms()));
        configFloors(floorsBoundary.searchAll(new Floors()));
    }

    private void configFloors(List<Floors> lstFloors) {
        /*ArrayList<ComboBoxItem> items = new ArrayList<>();
         if(lstFloors != null && lstFloors.size() > 0){
         for (Floors lstFloor : lstFloors) {
         if(lstFloor.getFlrStatus().trim().toUpperCase().equals("ACTIVO")){
         ComboBoxItem item = new ComboBoxItem();
         item.setId(lstFloor.getFlrId());
         item.setValue(lstFloor.getFlrCode());
         items.add(item);
         }
         }
         }        
         DefaultComboBoxModel model = new DefaultComboBoxModel(items.toArray(new ComboBoxItem[items.size()]));
         */
        ArrayList<String> items = new ArrayList<>();
        if (lstFloors != null && lstFloors.size() > 0) {
            for (Floors lstFloor : lstFloors) {
                if (lstFloor.getFlrStatus().trim().toUpperCase().equals("ACTIVO")) {
                    items.add(lstFloor.getFlrId().toString());
                }
            }
        }
        DefaultComboBoxModel model = new DefaultComboBoxModel(items.toArray(new String[items.size()]));
        this.jcbPisos.setModel(model);

    }

    private void configGrid(List<Rooms> rooms) {
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
            switch (room.getRmsStatus()) {
                case "Disponible":
                    vctRow.add(new ImageIcon(getClass().getResource("/com/ahms/ui/resources/img/bedG.png")));
                    break;
                case "Ocupado":
                    vctRow.add(new ImageIcon(getClass().getResource("/com/ahms/ui/resources/img/bedR.png")));
                    break;
                case "Reservado":
                    vctRow.add(new ImageIcon(getClass().getResource("/com/ahms/ui/resources/img/bedB.png")));
                    break;
            }
            vctRow.add(room.getRmsNumber());
            vctRow.add(room.getRmsDesc());
            vctRow.add(room.getRmsBeds());
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
                if (clicks > 1) {
                    RoomService roomServiceDlg = new RoomService(parent,true, (Integer) jtDashboard.getValueAt(row, 8));
                    roomServiceDlg.setVisible(true);
                    roomServiceDlg.setAlwaysOnTop(true);
                    
                } else {
                    //if(){}   agregar validacion con el estatus
                    jtIdCuarto.setText(String.valueOf(jtDashboard.getValueAt(row, 8)));
                    jspNumeroPersonas.setModel(new SpinnerNumberModel(1, 1, Integer.parseInt(jtDashboard.getValueAt(row, 4).toString()), 1));
                }
            }

        });
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
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jcbPisos = new javax.swing.JComboBox();
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
        jbBuscarCliente = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jtSubtotal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jtIdCuarto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jpFecEntContainer = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jpFecSalContainer = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jspNumeroPersonas = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        jcbTipoPago = new javax.swing.JComboBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTurno)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlTurno)
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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jcbPisos, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(627, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcbPisos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jScrollPane1)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane5.addTab("Cuartos", jPanel4);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 880, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 437, Short.MAX_VALUE)
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

        jbBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/resources/1445772770_search-80px.png"))); // NOI18N
        jbBuscarCliente.setText("Buscar Cliente");
        jbBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarClienteActionPerformed(evt);
            }
        });

        jLabel6.setText("Subtotal:");

        jButton1.setText("Rentar");

        jLabel7.setText("Id Cuarto:");

        jtIdCuarto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtIdCuarto.setEnabled(false);

        jLabel8.setText("Entrada:");

        javax.swing.GroupLayout jpFecEntContainerLayout = new javax.swing.GroupLayout(jpFecEntContainer);
        jpFecEntContainer.setLayout(jpFecEntContainerLayout);
        jpFecEntContainerLayout.setHorizontalGroup(
            jpFecEntContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpFecEntContainerLayout.setVerticalGroup(
            jpFecEntContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
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

        jLabel11.setText("Tipo de Pago:");

        jcbTipoPago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Efectivo", "T. Crédito", "T. Débito" }));

        javax.swing.GroupLayout jpRegistroRapidoLayout = new javax.swing.GroupLayout(jpRegistroRapido);
        jpRegistroRapido.setLayout(jpRegistroRapidoLayout);
        jpRegistroRapidoLayout.setHorizontalGroup(
            jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbBuscarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                        .addGap(0, 51, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jtIdCuarto))
                    .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                        .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpFecSalContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpFecEntContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jspNumeroPersonas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                        .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlRegistroNombre)
                            .addComponent(jlRegistroPaterno)
                            .addComponent(jlRegistroMaterno)
                            .addComponent(jlRegistroRfc))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(27, 27, 27)
                        .addComponent(jcbTipoPago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpRegistroRapidoLayout.setVerticalGroup(
            jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jbBuscarCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlRegistroNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlRegistroPaterno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlRegistroMaterno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlRegistroRfc)
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtIdCuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpFecEntContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel9))
                    .addGroup(jpRegistroRapidoLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jpFecSalContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jspNumeroPersonas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jcbTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpRegistroRapidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(3, 3, 3))
        );

        jTabbedPane1.addTab("Registro", jpRegistroRapido);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTabbedPane5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
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
        
        
        CustomerReg customerReg = new CustomerReg(this,true);
        customerReg.setVisible(true);
    }//GEN-LAST:event_jbBuscarClienteActionPerformed

    private void jcbPisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPisosActionPerformed
        Rooms room = new Rooms();
        Floors floor = new Floors();
        floor.setFlrId(Integer.parseInt(this.jcbPisos.getSelectedItem().toString()));
        room.setFlrId(floor);
        configGrid(roomsBounday.findByFloor(room));
    }//GEN-LAST:event_jcbPisosActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenuItem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JButton jbBuscarCliente;
    private javax.swing.JComboBox jcbPisos;
    private javax.swing.JComboBox jcbTipoPago;
    private javax.swing.JLabel jlRegistroMaterno;
    private javax.swing.JLabel jlRegistroNombre;
    private javax.swing.JLabel jlRegistroPaterno;
    private javax.swing.JLabel jlRegistroRfc;
    private javax.swing.JLabel jlTurno;
    private javax.swing.JPanel jpFecEntContainer;
    private javax.swing.JPanel jpFecSalContainer;
    private javax.swing.JPanel jpRegistroRapido;
    private javax.swing.JSpinner jspNumeroPersonas;
    private javax.swing.JTable jtDashboard;
    private javax.swing.JTextField jtIdCuarto;
    private javax.swing.JTextField jtSubtotal;
    // End of variables declaration//GEN-END:variables
}
