package com.ahms.ui.administracion.reportes.entity;

import com.ahms.ui.administracion.reportes.entity.cancelaciones.Cancelacion;
import com.ahms.ui.administracion.reportes.entity.movimientos.MovimientoCuarto;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

;

public class ReqUser {

    private String usuReq;
    private String usuAut;
    private List<MovimientoCuarto> RoomMov;
    private List<Cancelacion> Cancelation;

    public ReqUser(String user) {
        this.usuReq = user;
    }

    public String getReqUser() {
        return usuReq;
    }

    @XmlAttribute
    public void setReqUser(String reqUser) {
        this.usuReq = reqUser;
    }

    public List<MovimientoCuarto> getMovList() {
        return RoomMov;
    }

    @XmlElement
    public void setMovList(List<MovimientoCuarto> movList) {
        this.RoomMov = movList;
    }

    public List<Cancelacion> getCanList() {
        return Cancelation;
    }

    @XmlElement
    public void setCanList(List<Cancelacion> canList) {
        this.Cancelation = canList;
    }

    public String getUsuAut() {
        return usuAut;
    }
    
    @XmlAttribute
    public void setUsuAut(String usuAut) {
        this.usuAut = usuAut;
    }

}
