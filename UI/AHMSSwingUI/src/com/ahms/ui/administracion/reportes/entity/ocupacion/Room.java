/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui.administracion.reportes.entity.ocupacion;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author rsoto
 */
public class Room {

    private String rmsNumber;
    private String rmtType;
    private String cusNum;
    private String actFecIni;
    private String actFecFin;

    public Room(String rmsNumber, String rmtType, String cusNum, String actFecIni, String actFecFin) {
        this.rmsNumber = rmsNumber;
        this.rmtType = rmtType;
        this.cusNum = cusNum;
        this.actFecIni = actFecIni;
        this.actFecFin = actFecFin;
    }

    
    
    public String getRmsNumber() {
        return rmsNumber;
    }

    @XmlAttribute
    public void setRmsNumber(String rmsNumber) {
        this.rmsNumber = rmsNumber;
    }

    public String getRmtType() {
        return rmtType;
    }

    @XmlAttribute
    public void setRmtType(String rmtType) {
        this.rmtType = rmtType;
    }

    public String getCusNum() {
        return cusNum;
    }

    @XmlAttribute
    public void setCusNum(String cusNum) {
        this.cusNum = cusNum;
    }

    public String getActFecIni() {
        return actFecIni;
    }

    @XmlAttribute
    public void setActFecIni(String actFecIni) {
        this.actFecIni = actFecIni;
    }

    public String getActFecFin() {
        return actFecFin;
    }

    @XmlAttribute
    public void setActFecFin(String actFecFin) {
        this.actFecFin = actFecFin;
    }

}
