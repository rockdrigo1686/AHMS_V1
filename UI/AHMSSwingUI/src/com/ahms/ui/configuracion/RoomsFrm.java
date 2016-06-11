package com.ahms.ui.configuracion;

import com.ahms.boundary.entity_boundary.FloorsBoundary;
import com.ahms.boundary.entity_boundary.MultiValueBoundary;
import com.ahms.boundary.entity_boundary.RatesBoundary;
import com.ahms.boundary.entity_boundary.RoomTypesBoundary;
import com.ahms.boundary.entity_boundary.RoomsBoundary;
import com.ahms.model.entity.CashOut;
import com.ahms.model.entity.Floors;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.Rates;
import com.ahms.model.entity.RoomTypes;
import com.ahms.model.entity.Rooms;
import com.ahms.ui.MainFrm;
import com.ahms.ui.utils.GeneralFunctions;
import com.ahms.ui.utils.UIConstants;
import com.ahms.util.MMKeys;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class RoomsFrm extends javax.swing.JDialog {

    private RoomsBoundary roomsBoundary;
    private FloorsBoundary floorsBoundary;
    private MultiValueBoundary multiValueBoundary;
    private RatesBoundary ratesBoundary;
    private RoomTypesBoundary roomTypesBoundary;
    private CashOut currentShift;
    private MainFrm parentFrm;
    private Integer roomsIdGlobal = null;
        
    public RoomsFrm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        roomsBoundary = new RoomsBoundary();
        floorsBoundary = new FloorsBoundary();
        multiValueBoundary = new MultiValueBoundary();
        ratesBoundary = new RatesBoundary();
        roomTypesBoundary = new RoomTypesBoundary();
        this.jspRoomMaxOcu.setModel(new SpinnerNumberModel(1, 1, 10, 1));        
        
        loadRooms(roomsBoundary.searchAll(new Rooms()));
        loadFloors(floorsBoundary.searchAll(new Floors()));
        loadRates(ratesBoundary.searchAll(new Rates()));
        loadTypes(roomTypesBoundary.searchAll(new RoomTypes()));
        MultiValue mul = new MultiValue();
        mul.setMvaType(MMKeys.Rooms.GP_KEY);
        loadStatus(multiValueBoundary.findByType(mul));
        
        parentFrm = (MainFrm) parent;
        currentShift = parentFrm.getCurrentShift();
    }
    
    private void loadFloors(List<Floors> lstFloors){
        this.jcbPiso.removeAllItems();
        Floors zeroFlr = new Floors();
        zeroFlr.setFlrCode("Seleccionar ...");
        this.jcbPiso.addItem(zeroFlr);
        for(Floors flr : lstFloors){
            this.jcbPiso.addItem(flr);
        }        
    }
    
    private void loadTypes(List<RoomTypes> lstRoomTypes){
        this.jcbTypes.removeAllItems();
        RoomTypes zeroRmt = new RoomTypes();
        zeroRmt.setRtyDescription("Seleccionar ...");
        this.jcbTypes.addItem(zeroRmt);
        for(RoomTypes rmt : lstRoomTypes){
            this.jcbTypes.addItem(rmt);
        }
    }
    private void loadRates(List<Rates> lstRates){
        this.jcbRate.removeAllItems();
        Rates zeroRte = new Rates();
        zeroRte.setRteDesc("Seleccionar ...");
        this.jcbRate.addItem(zeroRte);
        for(Rates rate : lstRates){
            this.jcbRate.addItem(rate);
        }
    }
    
    private void loadStatus(List<MultiValue> lstStatus){
        this.jcbRoomStatus.removeAllItems();
        MultiValue zeroRte = new MultiValue();
        zeroRte.setMvaDescription("Seleccionar ...");
        this.jcbRoomStatus.addItem(zeroRte);
        for(MultiValue st : lstStatus){
            this.jcbRoomStatus.addItem(st);
        }
    }
    
    private void loadRooms(List<com.ahms.model.entity.Rooms> rooms){
        Vector<String> columnNames = new Vector();
        columnNames.add("Id");
        columnNames.add("Número");
        columnNames.add("Descripción");
        columnNames.add("Piso");
        columnNames.add("Camas");
        columnNames.add("Max Ocupantes");
        columnNames.add("Tipo");
        columnNames.add("Estado");     
        Vector<Vector> rows = new Vector<>();
        for(com.ahms.model.entity.Rooms room : rooms){
            Vector vctRow = new Vector();
            vctRow.add(room);
            vctRow.add(room.getRmsNumber());
            vctRow.add(room.getRmsDesc());
            vctRow.add(room.getFlrId());
            vctRow.add(room.getRmsBeds().getRtyBeds());
            vctRow.add(room.getRmsMaxOcu());
            vctRow.add(room.getRteId().getRteDesc());
            vctRow.add(room.getRmsStatus());
            rows.add(vctRow);
        }
        DefaultTableModel model = new DefaultTableModel(rows, columnNames) {

            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        jtRooms.setModel(model);
        jtRooms.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        btnEliminar.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        rooms1 = new com.ahms.model.entity.Rooms();
        jLabel1 = new javax.swing.JLabel();
        jcbPiso = new javax.swing.JComboBox();
        jlRoomsId = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtxtRoomNuber = new javax.swing.JTextField();
        jtxtRoomDesc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jspRoomMaxOcu = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jcbRate = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtRooms = new javax.swing.JTable();
        jcbRoomStatus = new javax.swing.JComboBox();
        jcbTypes = new javax.swing.JComboBox();
        jToolBar1 = new javax.swing.JToolBar();
        btnLimpiar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rooms1, org.jdesktop.beansbinding.ELProperty.create("${flrId}"), rooms1, org.jdesktop.beansbinding.BeanProperty.create("flrId"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rooms1, org.jdesktop.beansbinding.ELProperty.create("${reservationCollection}"), rooms1, org.jdesktop.beansbinding.BeanProperty.create("reservationCollection"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rooms1, org.jdesktop.beansbinding.ELProperty.create("${rmsBeds}"), rooms1, org.jdesktop.beansbinding.BeanProperty.create("rmsBeds"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rooms1, org.jdesktop.beansbinding.ELProperty.create("${rmsDesc}"), rooms1, org.jdesktop.beansbinding.BeanProperty.create("rmsDesc"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rooms1, org.jdesktop.beansbinding.ELProperty.create("${rmsDteMod}"), rooms1, org.jdesktop.beansbinding.BeanProperty.create("rmsDteMod"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rooms1, org.jdesktop.beansbinding.ELProperty.create("${rmsId}"), rooms1, org.jdesktop.beansbinding.BeanProperty.create("rmsId"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rooms1, org.jdesktop.beansbinding.ELProperty.create("${rmsMaxOcu}"), rooms1, org.jdesktop.beansbinding.BeanProperty.create("rmsMaxOcu"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rooms1, org.jdesktop.beansbinding.ELProperty.create("${rmsNumber}"), rooms1, org.jdesktop.beansbinding.BeanProperty.create("rmsNumber"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rooms1, org.jdesktop.beansbinding.ELProperty.create("${rmsStatus}"), rooms1, org.jdesktop.beansbinding.BeanProperty.create("rmsStatus"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rooms1, org.jdesktop.beansbinding.ELProperty.create("${rmsUsrMod}"), rooms1, org.jdesktop.beansbinding.BeanProperty.create("rmsUsrMod"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rooms1, org.jdesktop.beansbinding.ELProperty.create("${rteId}"), rooms1, org.jdesktop.beansbinding.BeanProperty.create("rteId"));
        bindingGroup.addBinding(binding);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Piso:");

        jcbPiso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rooms1, org.jdesktop.beansbinding.ELProperty.create("${flrId}"), jcbPiso, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jlRoomsId.setEnabled(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rooms1, org.jdesktop.beansbinding.ELProperty.create("${rmsId}"), jlRoomsId, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel2.setText("Numero:");
        jLabel2.setToolTipText("");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rooms1, org.jdesktop.beansbinding.ELProperty.create("${rmsNumber}"), jtxtRoomNuber, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rooms1, org.jdesktop.beansbinding.ELProperty.create("${rmsDesc}"), jtxtRoomDesc, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jtxtRoomDesc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtRoomDescKeyTyped(evt);
            }
        });

        jLabel3.setText("Camas:");

        jLabel4.setText("Max Ocu.:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rooms1, org.jdesktop.beansbinding.ELProperty.create("${rmsMaxOcu}"), jspRoomMaxOcu, org.jdesktop.beansbinding.BeanProperty.create("value"));
        bindingGroup.addBinding(binding);

        jLabel5.setText("Tipo de Cuarto:");

        jcbRate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rooms1, org.jdesktop.beansbinding.ELProperty.create("${rteId}"), jcbRate, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jtRooms.setModel(new javax.swing.table.DefaultTableModel(
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
        jtRooms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtRoomsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtRooms);

        jcbRoomStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rooms1, org.jdesktop.beansbinding.ELProperty.create("${rmsStatus}"), jcbRoomStatus, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jcbTypes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, rooms1, org.jdesktop.beansbinding.ELProperty.create("${rmsBeds}"), jcbTypes, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setMargin(new java.awt.Insets(0, 10, 0, 0));
        jToolBar1.setName("mainToolBar"); // NOI18N

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/1445773254_file.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setFocusable(false);
        btnLimpiar.setName("btLimpiar"); // NOI18N
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnLimpiar);

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/1445772615_file_search.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setFocusable(false);
        btnBuscar.setName("btnBuscar"); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnBuscar);

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/1445772609_file_add.png"))); // NOI18N
        btnNuevo.setText("Agregar");
        btnNuevo.setFocusable(false);
        btnNuevo.setName("btnNuevo"); // NOI18N
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnNuevo);
        jToolBar1.add(jSeparator4);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/1445772617_file_edit.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setFocusable(false);
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEditar.setName("btnEditar"); // NOI18N
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEditar);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/1445772697_diskette.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setFocusable(false);
        btnGuardar.setName("btnGuardar"); // NOI18N
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnGuardar);

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/1445772612_file_delete.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setFocusable(false);
        btnEliminar.setName("btnEliminar"); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEliminar);
        jToolBar1.add(jSeparator5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbPiso, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlRoomsId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtRoomNuber, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtRoomDesc))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspRoomMaxOcu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbRate, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbRoomStatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcbPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlRoomsId)
                    .addComponent(jLabel2)
                    .addComponent(jtxtRoomNuber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtRoomDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcbTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jspRoomMaxOcu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jcbRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbRoomStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtRoomsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtRoomsMouseClicked
        int clicks = evt.getClickCount();
        int row = jtRooms.getSelectedRow();
        if(clicks == 2){ // doble click
           rooms1 = (Rooms) jtRooms.getValueAt(row, 0);
           renderForm();
           btnGuardar.setEnabled(false);
           btnBuscar.setEnabled(false);
           btnNuevo.setEnabled(false);
           btnEliminar.setEnabled(true);
           lockInstance();
        } 
    }//GEN-LAST:event_jtRoomsMouseClicked

    private void cleanInstance(){
        jcbPiso.setEnabled(true);
        jtxtRoomNuber.setEnabled(true);
        jcbPiso.setSelectedIndex(0);
        jlRoomsId.setText("");
        roomsIdGlobal = null;
        jtxtRoomNuber.setText("");
        jtxtRoomDesc.setText("");
        jcbTypes.setSelectedIndex(0);
        jspRoomMaxOcu.setValue(1);        
        jcbRate.setSelectedIndex(0);
        jcbRoomStatus.setSelectedIndex(0);
    }
    
    private boolean validateInstance(){
        try {
            if(jcbPiso.getSelectedIndex() > 0           &&
                !jtxtRoomNuber.getText().isEmpty()       &&
                jcbPiso.getSelectedIndex() > 0           &&
                roomsIdGlobal == null     &&
                !jtxtRoomDesc.getText().trim().isEmpty()  &&
                jcbTypes.getSelectedIndex() > 0          &&
                jcbRate.getSelectedIndex() > 0 &&
                jcbRoomStatus.getSelectedIndex() > 0){
                 return true;
            } 
        } catch (Exception e) {
            GeneralFunctions.appendTrace(e.getStackTrace());
            GeneralFunctions.sendMessage(this, "Ocurrio un error al insertar el registro. Por favor contacte a servicio técnico.\nError: " + e.getMessage());
        }        
        return false;        
    }
    
    
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        cleanInstance();
        btnGuardar.setEnabled(true);
        btnBuscar.setEnabled(true);
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(true);
        unlockInstance();
        this.jlRoomsId.setText("");
        roomsIdGlobal = null;
        loadRooms(roomsBoundary.searchAll(new Rooms()));
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        Rooms newRoom = new Rooms();
        newRoom.setFlrId((Floors) jcbPiso.getSelectedItem());
        newRoom.setRmsBeds((RoomTypes) jcbTypes.getSelectedItem());
        newRoom.setRmsDesc(jtxtRoomDesc.getText());
        newRoom.setRmsDteMod(new java.util.Date());
        newRoom.setRmsId(roomsIdGlobal);
        newRoom.setRmsMaxOcu((int) jspRoomMaxOcu.getValue());
        newRoom.setRmsNumber(jtxtRoomNuber.getText());
        newRoom.setRmsStatus((MultiValue) jcbRoomStatus.getSelectedItem());
        newRoom.setRteId((Rates) jcbRate.getSelectedItem());
        newRoom.setRmsUsrMod(currentShift.getUsrId());
        
        loadRooms(roomsBoundary.search(newRoom));
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

        if(!validateInstance()){
            GeneralFunctions.sendMessage(this, "No puede haber campos vacios. Por favor rectifique.");
            return;
        }
        Rooms newRoom = new Rooms();
        newRoom.setFlrId((Floors) jcbPiso.getSelectedItem());
        newRoom.setRmsBeds((RoomTypes) jcbTypes.getSelectedItem());
        newRoom.setRmsDesc(jtxtRoomDesc.getText());
        newRoom.setRmsDteMod(new java.util.Date());
        newRoom.setRmsMaxOcu((int) jspRoomMaxOcu.getValue());
        newRoom.setRmsNumber(jtxtRoomNuber.getText());
        newRoom.setRmsStatus((MultiValue) jcbRoomStatus.getSelectedItem());
        newRoom.setRteId((Rates) jcbRate.getSelectedItem());
        newRoom.setRmsUsrMod(currentShift.getUsrId());
        roomsBoundary.insert(newRoom);
        GeneralFunctions.sendMessage(this, UIConstants.SUCCESS_SAVE);
        loadRooms(roomsBoundary.searchAll(new Rooms()));
        cleanInstance();
        
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Rooms newRoom = new Rooms();
        newRoom.setFlrId((Floors) jcbPiso.getSelectedItem());
        newRoom.setRmsBeds((RoomTypes) jcbTypes.getSelectedItem());
        newRoom.setRmsDesc(jtxtRoomDesc.getText());
        newRoom.setRmsDteMod(new java.util.Date());
        newRoom.setRmsId(roomsIdGlobal);
        newRoom.setRmsMaxOcu((int) jspRoomMaxOcu.getValue());
        newRoom.setRmsNumber(jtxtRoomNuber.getText());
        newRoom.setRmsStatus((MultiValue) jcbRoomStatus.getSelectedItem());
        newRoom.setRteId((Rates) jcbRate.getSelectedItem());
        newRoom.setRmsUsrMod(currentShift.getUsrId());
        
        roomsBoundary.update(newRoom);
        GeneralFunctions.sendMessage(this, UIConstants.SUCCESS_UPDATE);            
        loadRooms(roomsBoundary.searchAll(new Rooms()));
        cleanInstance();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int dialogResult = JOptionPane.showConfirmDialog(this, UIConstants.CONFIRM_DELETE, UIConstants.TYPE_WARNING, JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            if(roomsBoundary.delete(rooms1) > 0){
                GeneralFunctions.sendMessage(this, UIConstants.SUCCESS_DELETE);
                btnLimpiarActionPerformed(null);
                //cleanInstance();
                //loadRooms(roomsBoundary.searchAll(new Rooms()));
            }
        }        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void jtxtRoomDescKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtRoomDescKeyTyped
         if(jtxtRoomDesc.getText().length() >= 199){
            evt.consume();
        }
    }//GEN-LAST:event_jtxtRoomDescKeyTyped

    private void lockInstance(){
        jcbPiso.setEnabled(false);
        jtxtRoomNuber.setEnabled(false);
        jcbTypes.setEnabled(false);
        jspRoomMaxOcu.setEnabled(false);
        jcbRate.setEnabled(false);
        jcbRoomStatus.setEnabled(false);
        jtxtRoomDesc.setEnabled(false);
    }
    private void unlockInstance(){
        jcbPiso.setEnabled(true);
        jtxtRoomNuber.setEnabled(true);
        jcbTypes.setEnabled(true);
        jspRoomMaxOcu.setEnabled(true);
        jcbRate.setEnabled(true);
        jcbRoomStatus.setEnabled(true);
        jtxtRoomDesc.setEnabled(true);
    }
    
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        btnBuscar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(true);
        unlockInstance();
    }//GEN-LAST:event_btnEditarActionPerformed

   private void renderForm(){
       if(rooms1 != null){
           jcbPiso.setEnabled(false);
           jtxtRoomNuber.setEnabled(false);
           jcbPiso.setSelectedItem(rooms1.getFlrId());
           jlRoomsId.setText(rooms1.getRmsId().toString());
           roomsIdGlobal = rooms1.getRmsId();
           jtxtRoomNuber.setText(rooms1.getRmsNumber());
           jtxtRoomDesc.setText(rooms1.getRmsDesc());
           jcbTypes.setSelectedItem(rooms1.getRmsBeds());
           jspRoomMaxOcu.setValue(rooms1.getRmsMaxOcu());
           jcbRate.setSelectedItem(rooms1.getRteId());
           jcbRoomStatus.setSelectedItem(rooms1.getRmsStatus());
       }
   }
    
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
            java.util.logging.Logger.getLogger(RoomsFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoomsFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoomsFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoomsFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RoomsFrm dialog = new RoomsFrm(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JComboBox jcbPiso;
    private javax.swing.JComboBox jcbRate;
    private javax.swing.JComboBox jcbRoomStatus;
    private javax.swing.JComboBox jcbTypes;
    private javax.swing.JLabel jlRoomsId;
    private javax.swing.JSpinner jspRoomMaxOcu;
    private javax.swing.JTable jtRooms;
    private javax.swing.JTextField jtxtRoomDesc;
    private javax.swing.JTextField jtxtRoomNuber;
    private com.ahms.model.entity.Rooms rooms1;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
