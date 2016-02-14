/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.util;

/**
 *
 * @author rsoto
 */
public class MMKeys {
     public static class Rooms{
        
        public final static String GP_KEY = "RMS";
        public final static String STA_DISPONIBLE = "Disponible";
        public final static String STA_DISPONIBLE_KEY = "AV";
        public final static String STA_OCUPADO = "Ocupado";
        public final static String STA_OCUPADO_KEY = "BS";
        public final static String STA_RESERVADO = "Reservado";
        public final static String STA_RESERVADO_KEY = "RS";
        public final static String STA_MTO = "Mantenimiento";
        public final static String STA_MTO_KEY = "MT";
    }
    
   public static class Acounts{
        
        public final static String GP_KEY = "ACT";
        public final static String STA_ABIERTO = "Abierto";
        public final static String STA_ABIERTO_KEY = "ACT_A";
        public final static String STA_CERRADO = "Cerrado";
        public final static String STA_CERRADO_KEY = "ACT_C";
        public final static String STA_MOROSO = "Moroso";
        public final static String STA_MOROSO_KEY = "ACT_P";
    }

    public static class Shift{
        
        public final static String GP_KEY = "SHF";
        public final static String STA_ABIERTO = "Abierto";
        public final static String STA_ABIERTO_KEY = "SHF_O";
        public final static String STA_CERRADO = "Cerrado";
        public final static String STA_CERRADO_KEY = "SHF_E";
    }

   public static class Users{
        
        public final static String GP_KEY = "USR";
        public final static String STA_ACTIVO = "Activo";
        public final static String STA_ACTIVO_KEY = "AC";
        public final static String STA_INACTIVO = "Inactivo";
        public final static String STA_INACTIVO_KEY = "X";
    }

    
    public static class Math{
        
        public final static String GP_KEY = "MTH";
        public final static String IVA_KEY = "IVA";
    }
}
