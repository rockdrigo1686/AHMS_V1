package com.ahms.ui.administracion.reportes.entity;

import com.ahms.ui.utils.UIConstants;
import javax.xml.bind.annotation.XmlAttribute;

public class Header {

    private String dteIni;
    private String dteFin;
    private String user;
    private String logo = UIConstants.LOGO;

    public Header(String dteIni, String dteFin) {
        this.dteIni = dteIni;
        this.dteFin = dteFin;
    }

    public String getDteIni() {
        return dteIni;
    }

    @XmlAttribute
    public void setDteIni(String dteIni) {
        this.dteIni = dteIni;
    }

    public String getDteFin() {
        return dteFin;
    }

    @XmlAttribute
    public void setDteFin(String dteFin) {
        this.dteFin = dteFin;
    }

    public String getUser() {
        return user;
    }

    @XmlAttribute
    public void setUser(String user) {
        this.user = user;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

}
