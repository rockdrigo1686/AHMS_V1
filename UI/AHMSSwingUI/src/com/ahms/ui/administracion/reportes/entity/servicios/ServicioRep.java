package com.ahms.ui.administracion.reportes.entity.servicios;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ahms.ui.administracion.reportes.entity.Header;

@XmlRootElement
public class ServicioRep {
	private Header header;
	private List<TipoPago> payList;
	public Header getHeader() {
		return header;
	}
	@XmlElement
	public void setHeader(Header header) {
		this.header = header;
	}
	public List<TipoPago> getPayList() {
		return payList;
	}
	@XmlElement
	public void setPayList(List<TipoPago> payList) {
		this.payList = payList;
	}
	
	
}
