/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.util;

import com.ahms.model.entity.Users;
import com.ahms.model.manager.AHMSEntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author hrodric
 */
public class ReportsQuery extends AHMSEntityManager<Users> {
    

    public ArrayList<ArrayList<String>> generaReporteocupacion() {
        ArrayList<ArrayList<String>> retList = new  ArrayList<ArrayList<String>> ();
        try {
            if (em == null) {
                createEm();
            }
            StringBuilder sbQuery = new StringBuilder();
            sbQuery.append(" select r.RMS_NUMBER, r.RMS_STATUS, IFNULL(concat(c.CUS_NAME,  ' ', c.CUS_LST_1), ' ' )as NOMBRE, "
                    + " IFNULL(c.ACT_FEC_INI,' '), IFNULL(c.ACT_FEC_FIN,' ') from rooms r"
                    + " left join ("
                    + " select c.CUS_NAME, c.CUS_LST_1, ac.RMS_ID, a.ACT_FEC_INI, a.ACT_FEC_FIN from account_transactions ac "
                    + " join account a on a.ACT_ID =  ac.ACT_ID"
                    + " join customers c on c.CUS_ID = a.CUS_ID"
                    + " where ac.ATR_STATUS = 'PEND') c on c.RMS_ID = r.RMS_ID");
            int i = 1;
            Query query = em.createNativeQuery(sbQuery.toString());
            List resVec =  query.getResultList();
            String[]header = {"Cuarto","Estatus","Nombre","Entrada","Salida"};
            retList.add(new ArrayList<String>(Arrays.asList(header)));
            for (Object object : resVec) {               
                Object[] obA = (Object[])object;
                String[] strA = Arrays.copyOf(obA, obA.length, String[].class);
                ArrayList<String> val;
                val = new ArrayList<String>(Arrays.asList(strA));
                retList.add(val);                
             }
            int x = 0;
        } catch (Exception e) {
            if (e instanceof NoResultException) {
                return null;
            } else {
                throw e;
            }
        } finally {
            if (em != null) {
                closeEm();
            }
        }

        return retList;
    }

}
