package com.ahms.ui.administracion.reportes.entity.servicios;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class TipoPago {
	private String typPago;
	private Double total;
	
	private List<TipoServicio> tserList;
	public TipoPago(String typPago,  Double total){
		super();
		this.typPago = typPago;
		this.total = total;
	}
	public String getTypPago() {
		return typPago;
	}
	@XmlAttribute
	public void setTypPago(String typPago) {
		this.typPago = typPago;
	}
		
	public Double getTotal() {
		return total;
	}
	@XmlAttribute
	public void setTotal(Double total) {
		this.total = total;
	}
	public List<TipoServicio> getTserList() {
		return tserList;
	}
	@XmlElement
	public void setTserList(List<TipoServicio> tserList) {
		this.tserList = tserList;
	}
	
	
}
