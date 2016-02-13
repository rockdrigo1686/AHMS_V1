/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui.utils;

/**
 *
 * @author rsoto
 */
public class UIConstants {

    
    // Estatus
     public static final String STATUS_OPEN = "Abierto";
     public static final String STATUS_PAID = "Pagado";
     public static final String STATUS_CLOSED = "Cerrado";
     public static final String STATUS_PEDING = "Pendiente";
     public static final String STATUS_DELAYED = "Moroso";
    
    //controlles de tarea
    public static final String BTN_LIMPIAR = "btnLimpiar";
    public static final String BTN_NUEVO = "btnNuevo";
    public static final String BTN_BUSCAR = "btnBuscar";
    public static final String BTN_EDITAR = "btnEditar";
    public static final String BTN_GUARDAR = "btnGuardar";
    public static final String BTN_ELIMINAR = "btnEliminar";
    public static final String DOUBLE_CLICK = "DC";

    //Estatus cuartos
    public static final String ROOM_STA_AVA = "Disponible";
    public static final String ROOM_STA_RE = "Reservado";
    public static final String ROOM_STA_TAK = "Ocupado";
    public static final String ROOM_STA_MNT = "Mantenimiento";
    
    //mensajes
    public static final String TYPE_WARNING = "Advertencia.";
    public static final String TYPE_MESAGE = "Mensaje.";
    public static final String CONFIRM_DELETE = "Esta seguro que desea eiminar el registro?";
    public static final String CONFIRM_UPDATE = "Esta seguro que desea guardar los cambios?";
    public static final String CONFIRM_SHIFT_END= "Esta seguro que desea cerrar el turno?";
    public static final String CONFIRM_EXIT = "Esta seguro que desea salir?";
    public static final String SUCCESS_SAVE = "Registro creado exitosamente.";
    public static final String SUCCESS_UPDATE = "Registro guardado exitosamente.";
    public static final String SUCCESS_DELETE = "Registro eliminado exitosamente.";
    public static final String SHIFT_ENDED =  "Turno Cerrado.";
    public static final String NOT_ENOUGH_ROOMS =   "No se encontraron suficientes cuartos disponibles, favor de verificar.";
    public static final String NO_AVAIL_ROOMS =   "No se encontraron cuartos disponibles.";
    
    //Errores
    public static final String ERROR_EMPY_AMOUNT = "Error: El monto inicial no puede estar vacio, favor de capturar.";
    public static final String ERROR_SHIFT_INI = "Error: Turno no iniciado.";
    public static final String ERROR_AUT_CODE = "Error: Clave de autorizacion incorrecta.";
    public static final String ERROR_LOGIN = "Error: Clave de usuario o contraseña incorrecta.";
    public static final String ERROR_SAVE = "Error: No se pudo guardar el registro.";
    
    //CashOut
    public static final String COU_AMOUNT_WARNING = "Advertencia: El monto inicial no puede estar vacio, favor de capturar.";
    public static final String COU_AMOUNT_ERROR = "Error: Turno no iniciado.";
    public static final String SHIFT_STA_INI = "Abierto";
    public static final String SHIFT_STA_CLS = "Cerrado";

    //variables
     public static final String PAYMENT_TYPE_CS ="Efectivo";
     public static final String PAYMENT_TYPE_CC ="Tarjeta Credito";
     public static final String PAYMENT_TYPE_DC ="Tarjeta Debito";
    
}
