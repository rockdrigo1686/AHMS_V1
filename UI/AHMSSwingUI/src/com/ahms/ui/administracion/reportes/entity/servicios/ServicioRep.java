package com.ahms.ui.administracion.reportes.entity.servicios;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ahms.ui.administracion.reportes.entity.Header;

@XmlRootElement
public class ServicioRep {

    private Header header;
    private List<TipoPago> PymentType;
    private List<TipoServicio> ServiceType;

    public Header getHeader() {
        return header;
    }

    @XmlElement
    public void setHeader(Header header) {
        this.header = header;
    }

    public List<TipoPago> getPymentType() {
        return PymentType;
    }

    @XmlElement
    public void setPymentType(List<TipoPago> payList) {
        this.PymentType = payList;
    }

    public List<TipoServicio> getServiceType() {
        return ServiceType;
    }

    @XmlElement
    public void setServiceType(List<TipoServicio> tserList) {
        this.ServiceType = tserList;
    }

}
