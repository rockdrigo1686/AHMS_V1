/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui.configuracion;


import com.ahms.boundary.entity_boundary.CustomersBoundary;
import com.ahms.boundary.entity_boundary.MultiValueBoundary;
import com.ahms.model.entity.Customers;
import com.ahms.model.entity.MultiValue;
import com.ahms.model.entity.Users;
import com.ahms.ui.utils.FormManager;
import com.ahms.ui.utils.GeneralFunctions;
import com.ahms.ui.utils.JTableDoubleClickListener;
import com.ahms.ui.utils.UIConstants;
import com.ahms.util.MMKeys;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jorge
 */
public class CustomerFrm extends javax.swing.JDialog {

    private CustomersBoundary customersBoundary = null;
    private Users userLogued = null;
    private List<Customers> resultList = null;
    private MultiValueBoundary multiValueBoundary = null;
    private DefaultTableModel tableModel = null;
    private Map<String, Component> formComponentMap = new HashMap<String, Component>();
    private Map<String, Component> buttonMap = new HashMap<String, Component>();
    private FormManager formManager = null;
    
    public CustomerFrm(java.awt.Frame parent, boolean modal, Users loged) {
        super(parent, modal);
        initComponents();
        
        userLogued = new Users(1);
        setResizable(false);
        setTitle("Catálogo de Clientes");
        customersBoundary = new CustomersBoundary();
        multiValueBoundary = new MultiValueBoundary();
        MultiValue multiValue = new MultiValue();
        multiValue.setMvaType(MMKeys.General.GP_KEY);
        loadStatus(multiValueBoundary.findByType(multiValue));
        resultList = searchAll();
        //Creamos mapa de componentes
        formManager = new FormManager();
        formManager.createComponentMaps(this);
        formManager.setDefaultFormStatus();
        fillTable(resultTable);

        resultTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTableDoubleClickListener.addDoubleCLick(e, customersBean, resultList, formManager.getFormComponentMap());
                if (e.getClickCount() == 2) {
                    formManager.updateButtonMenuState(UIConstants.DOUBLE_CLICK);
                    btnPref.setEnabled(false);
                }
            }
        });
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        
    }
    
    private void loadStatus(List<MultiValue> statusList){
        DefaultComboBoxModel model= new DefaultComboBoxModel(statusList.toArray(new MultiValue[statusList.size()]));
        this.cusStatus.setModel(model);
    }
    
    private List<Customers> searchAll() {
        return customersBoundary.searchAll(new Customers());
    }
    
    private void fillTable(JTable resultTable) {
        String col[] = {"ID", "Nombre","Paterno","Materno", "Dirección", "Código Postal", "Estado", "Ciudad", "RFC","Tel"
                ,"Cel", "Correo", "Placas", "Factura", "Preferencial", "Estatus", "Usuario Mod", "Fecha Mod"};

        tableModel = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };

        // The 0 argument is number rows.
        resultList.stream().forEach((next) -> {
            tableModel.addRow(new Object[]{
                next.getCusId(), 
                next.getCusName(),
                next.getCusLst1(),
                next.getCusLst2(), 
                next.getCusAddress(), 
                next.getCusPostalCode(), 
                next.getCusState(), 
                next.getCusCity(), 
                next.getCusRfc(), 
                next.getCusTel(), 
                next.getCusCel(),
                next.getCusEmail(),
                next.getCusCarPlates(), 
                next.getCusInvoice(),
                next.getCusPref(), 
                next.getCusStatus(), 
                next.getCusUsrMod(), 
                next.getCusDteMod()});
        });

        resultTable.setModel(tableModel);
        resultTable.getColumn("ID").setMinWidth(0);
        resultTable.getColumn("ID").setMaxWidth(0);
        resultTable.getColumn("Nombre").setMaxWidth(150);
        resultTable.getColumn("Nombre").setWidth(150);
        resultTable.getColumn("Paterno").setMaxWidth(150);
        resultTable.getColumn("Paterno").setWidth(150);
        resultTable.getColumn("Materno").setMaxWidth(150);
        resultTable.getColumn("Materno").setWidth(150);
        resultTable.getColumn("Dirección").setMaxWidth(150);
        resultTable.getColumn("Dirección").setWidth(150);
        resultTable.getColumn("Código Postal").setMaxWidth(150);
        resultTable.getColumn("Código Postal").setWidth(150);
        resultTable.getColumn("Estado").setMaxWidth(150);
        resultTable.getColumn("Estado").setWidth(150);
        resultTable.getColumn("Ciudad").setMaxWidth(150);
        resultTable.getColumn("Ciudad").setWidth(150);
        resultTable.getColumn("RFC").setMaxWidth(150);
        resultTable.getColumn("RFC").setWidth(150);
        resultTable.getColumn("Tel").setMaxWidth(150);
        resultTable.getColumn("Tel").setWidth(150);
        resultTable.getColumn("Cel").setMaxWidth(150);
        resultTable.getColumn("Cel").setWidth(150);
        resultTable.getColumn("Correo").setMaxWidth(150);
        resultTable.getColumn("Correo").setWidth(150);
        resultTable.getColumn("Placas").setMaxWidth(150);
        resultTable.getColumn("Placas").setWidth(150);
        resultTable.getColumn("Factura").setMaxWidth(150);
        resultTable.getColumn("Factura").setWidth(150);
        resultTable.getColumn("Preferencial").setMaxWidth(150);
        resultTable.getColumn("Preferencial").setWidth(150);
        resultTable.getColumn("Estatus").setMaxWidth(150);
        resultTable.getColumn("Estatus").setWidth(150);
        resultTable.getColumn("Usuario Mod").setMaxWidth(150);
        resultTable.getColumn("Usuario Mod").setWidth(150);
        resultTable.getColumn("Fecha Mod").setMaxWidth(150);
        resultTable.getColumn("Fecha Mod").setWidth(150);
        
        resultTable.setColumnSelectionAllowed(false);
        resultTable.setCellSelectionEnabled(false);
        resultTable.setRowSelectionAllowed(true);
        resultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        customersBean = new com.ahms.model.entity.Customers();
        jToolBar1 = new javax.swing.JToolBar();
        btnLimpiar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        btnPref = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cusName = new javax.swing.JTextField();
        cusLst1 = new javax.swing.JTextField();
        cusLst2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cusAddress = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cusRfc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cusState = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cusCity = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cusPostalCode = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cusTel = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cusCel = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cusCarPlates = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cusEmail = new javax.swing.JTextField();
        cusInvoice = new javax.swing.JCheckBox();
        cusPref = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        cusStatus = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${accountCollection}"), customersBean, org.jdesktop.beansbinding.BeanProperty.create("accountCollection"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusAddress}"), customersBean, org.jdesktop.beansbinding.BeanProperty.create("cusAddress"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusCarPlates}"), customersBean, org.jdesktop.beansbinding.BeanProperty.create("cusCarPlates"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusCel}"), customersBean, org.jdesktop.beansbinding.BeanProperty.create("cusCel"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusCity}"), customersBean, org.jdesktop.beansbinding.BeanProperty.create("cusCity"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusDteMod}"), customersBean, org.jdesktop.beansbinding.BeanProperty.create("cusDteMod"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusEmail}"), customersBean, org.jdesktop.beansbinding.BeanProperty.create("cusEmail"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusId}"), customersBean, org.jdesktop.beansbinding.BeanProperty.create("cusId"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusInvoice}"), customersBean, org.jdesktop.beansbinding.BeanProperty.create("cusInvoice"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusLst1}"), customersBean, org.jdesktop.beansbinding.BeanProperty.create("cusLst1"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusLst2}"), customersBean, org.jdesktop.beansbinding.BeanProperty.create("cusLst2"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusName}"), customersBean, org.jdesktop.beansbinding.BeanProperty.create("cusName"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusPostalCode}"), customersBean, org.jdesktop.beansbinding.BeanProperty.create("cusPostalCode"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusPref}"), customersBean, org.jdesktop.beansbinding.BeanProperty.create("cusPref"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusRfc}"), customersBean, org.jdesktop.beansbinding.BeanProperty.create("cusRfc"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusState}"), customersBean, org.jdesktop.beansbinding.BeanProperty.create("cusState"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusStatus}"), customersBean, org.jdesktop.beansbinding.BeanProperty.create("cusStatus"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusTel}"), customersBean, org.jdesktop.beansbinding.BeanProperty.create("cusTel"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusUsrMod}"), customersBean, org.jdesktop.beansbinding.BeanProperty.create("cusUsrMod"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${reservationCollection}"), customersBean, org.jdesktop.beansbinding.BeanProperty.create("reservationCollection"));
        bindingGroup.addBinding(binding);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        btnPref.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ahms/ui/images/32x32/Star.png"))); // NOI18N
        btnPref.setText("Preferencias");
        btnPref.setEnabled(false);
        btnPref.setFocusable(false);
        btnPref.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnPref.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnPref.setName("btnPref"); // NOI18N
        btnPref.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrefActionPerformed(evt);
            }
        });
        jToolBar1.add(btnPref);

        jLabel1.setText("Nombre:");

        cusName.setName("cusName"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusName}"), cusName, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        cusLst1.setName("cusLst1"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusLst1}"), cusLst1, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        cusLst2.setName("cusLst2"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusLst2}"), cusLst2, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel2.setText("Dirección:");

        cusAddress.setName("cusAddress"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusAddress}"), cusAddress, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel3.setText("RFC:");

        cusRfc.setName("cusRfc"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusRfc}"), cusRfc, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        cusRfc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cusRfcActionPerformed(evt);
            }
        });

        jLabel4.setText("Estado:");

        cusState.setName("cusState"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusState}"), cusState, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel5.setText("Ciudad:");

        cusCity.setName("cusCity"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusCity}"), cusCity, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        cusCity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cusCityActionPerformed(evt);
            }
        });

        jLabel6.setText("C.P.:");

        cusPostalCode.setName("cusPostalCode"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusPostalCode}"), cusPostalCode, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel7.setText("Tel.:");

        cusTel.setToolTipText("");
        cusTel.setName("cusTel"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusTel}"), cusTel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel8.setText("Cel.:");

        cusCel.setName("cusCel"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusCel}"), cusCel, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel9.setText("Placas:");

        cusCarPlates.setName("cusCarPlates"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusCarPlates}"), cusCarPlates, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jLabel10.setText("Correo:");

        cusEmail.setName("cusEmail"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusEmail}"), cusEmail, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        cusInvoice.setText("Factura");
        cusInvoice.setName("cusInvoice"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusInvoice}"), cusInvoice, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        cusInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cusInvoiceActionPerformed(evt);
            }
        });

        cusPref.setText("Preferencial");
        cusPref.setName("cusPref"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusPref}"), cusPref, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        cusPref.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cusPrefActionPerformed(evt);
            }
        });

        jLabel11.setText("Estatus:");

        cusStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cusStatus.setName("cusStatus"); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, customersBean, org.jdesktop.beansbinding.ELProperty.create("${cusStatus}"), cusStatus, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        resultTable.setModel(new javax.swing.table.DefaultTableModel(
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
        resultTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(resultTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cusName, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cusLst1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cusLst2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cusRfc, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cusAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cusState, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cusCity))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cusPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cusTel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cusCel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cusInvoice)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cusPref)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cusStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cusEmail)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cusCarPlates, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cusName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cusLst1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cusLst2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cusRfc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cusAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(cusState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cusCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cusPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cusEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cusCarPlates, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cusTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cusCel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cusInvoice)
                    .addComponent(cusPref)
                    .addComponent(jLabel11)
                    .addComponent(cusStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        formManager.setDefaultFormStatus();
        resultList = searchAll();
        fillTable(resultTable);
        formManager.setDefaultFormStatus();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        resultList = customersBoundary.search(customersBean);
        fillTable(resultTable);
        formManager.updateButtonMenuState(UIConstants.BTN_BUSCAR);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        Customers customer = new Customers();
        customer.setCusName(customersBean.getCusName());
        customer.setCusLst1(customersBean.getCusLst1());
        customer.setCusLst2(customersBean.getCusLst2());
        customer.setCusAddress(customersBean.getCusAddress());
        customer.setCusState(customersBean.getCusState());
        customer.setCusCel(customersBean.getCusCel());
        customer.setCusCity(customersBean.getCusCity());
        customer.setCusEmail(customersBean.getCusEmail());
        customer.setCusPostalCode(customersBean.getCusPostalCode());
        customer.setCusRfc(customersBean.getCusRfc());
        customer.setCusStatus(customersBean.getCusStatus());
        customer.setCusTel(customersBean.getCusTel());
        customer.setCusStatus(customersBean.getCusStatus());
        customer.setCusInvoice(customersBean.getCusInvoice());
        customer.setCusPref(customersBean.getCusPref());
        customer.setCusCarPlates(customersBean.getCusCarPlates());
        customer.setCusUsrMod(userLogued);
        customer.setCusDteMod(new Date());

        if (customersBoundary.insert(customer) == 1) {
            JOptionPane.showMessageDialog(this, UIConstants.SUCCESS_SAVE);
        }
        resultList = searchAll();
        fillTable(resultTable);
        formManager.setDefaultFormStatus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        formManager.updateButtonMenuState(UIConstants.BTN_EDITAR);
         if(cusPref.isSelected()){
            btnPref.setEnabled(true);
        } else {
            btnPref.setEnabled(false);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (customersBean.getCusId()!= null) {
            if (customersBoundary.update(customersBean) == 1) {
                JOptionPane.showMessageDialog(this, UIConstants.SUCCESS_UPDATE);
            }
        }
        resultList = searchAll();
        fillTable(resultTable);
        formManager.setDefaultFormStatus();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int dialogResult = JOptionPane.showConfirmDialog(this, UIConstants.CONFIRM_DELETE, UIConstants.TYPE_WARNING, JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            if (customersBoundary.delete(customersBean) == 1) {
                JOptionPane.showMessageDialog(this, UIConstants.SUCCESS_DELETE);
            }
        }
        resultList = searchAll();
        fillTable(resultTable);
        formManager.updateButtonMenuState(UIConstants.BTN_ELIMINAR);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cusInvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cusInvoiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cusInvoiceActionPerformed

    private void cusRfcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cusRfcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cusRfcActionPerformed

    private void cusCityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cusCityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cusCityActionPerformed

    private void btnPrefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrefActionPerformed
        if(customersBean != null && customersBean.getCusId()  != null){
            SetPreferencesDlg preferences = new SetPreferencesDlg(this, true, customersBean);
            preferences.setVisible(true);
        } else {
            GeneralFunctions.sendMessage(this, "Es necesario guardar el cliente previamente.");
        }        
    }//GEN-LAST:event_btnPrefActionPerformed

    private void cusPrefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cusPrefActionPerformed
        if(cusPref.isSelected()){
            btnPref.setEnabled(true);
        } else {
            btnPref.setEnabled(false);
        }
    }//GEN-LAST:event_cusPrefActionPerformed

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
            java.util.logging.Logger.getLogger(CustomerFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CustomerFrm dialog = new CustomerFrm(new javax.swing.JFrame(), true, null);
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
    private javax.swing.JButton btnPref;
    private javax.swing.JTextField cusAddress;
    private javax.swing.JTextField cusCarPlates;
    private javax.swing.JTextField cusCel;
    private javax.swing.JTextField cusCity;
    private javax.swing.JTextField cusEmail;
    private javax.swing.JCheckBox cusInvoice;
    private javax.swing.JTextField cusLst1;
    private javax.swing.JTextField cusLst2;
    private javax.swing.JTextField cusName;
    private javax.swing.JTextField cusPostalCode;
    private javax.swing.JCheckBox cusPref;
    private javax.swing.JTextField cusRfc;
    private javax.swing.JTextField cusState;
    private javax.swing.JComboBox cusStatus;
    private javax.swing.JTextField cusTel;
    private com.ahms.model.entity.Customers customersBean;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable resultTable;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
