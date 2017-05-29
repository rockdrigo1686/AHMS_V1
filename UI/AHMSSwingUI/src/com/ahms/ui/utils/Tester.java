/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui.utils;

import com.ahms.model.entity.CashOut;
import com.ahms.model.entity.Users;
import com.ahms.ui.administracion.reportes.source.OcupacionRpEngine;
import com.ahms.util.ReportsQuery;
import java.util.ArrayList;

/**
 *
 * @author hrodric
 */
public class Tester {

    public static void main(String[] args) throws Exception {
        ReportsQuery rq = new ReportsQuery();
        ArrayList<ArrayList<String>> source = rq.generaReporteocupacion();
        CashOut cs = new CashOut();
        cs.setUsrId(new Users());
        OcupacionRpEngine oe = new OcupacionRpEngine(source, cs);
        oe.generate();
    }
}
