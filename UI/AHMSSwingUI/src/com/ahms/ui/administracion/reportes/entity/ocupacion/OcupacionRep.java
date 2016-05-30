/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.ui.administracion.reportes.entity.ocupacion;

import com.ahms.ui.administracion.reportes.entity.Header;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rsoto
 */
@XmlRootElement
public class OcupacionRep {

    Header header;
    Rent rent;

    public Header getHeader() {
        return header;
    }

    @XmlElement
    public void setHeader(Header eader) {
        this.header = eader;
    }

    public Rent getRent() {
        return rent;
    }

    @XmlElement
    public void setRent(Rent rent) {
        this.rent = rent;
    }

}
