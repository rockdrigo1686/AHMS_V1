package com.ahms.ui.administracion.reportes.entity.servicios;

import javax.xml.bind.annotation.XmlAttribute;

public class Servicio {
	private String serv;
	private Integer qty;
	private Double amnt;
	public Servicio(String serv, Integer qty, Double amnt) {
		super();
		this.serv = serv;
		this.qty = qty;
		this.amnt = amnt;
	}
	public String getServ() {
		return serv;
	}
	@XmlAttribute
	public void setServ(String serv) {
		this.serv = serv;
	}
	public Integer getQty() {
		return qty;
	}
	@XmlAttribute
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Double getAmnt() {
		return amnt;
	}
	@XmlAttribute
	public void setAmnt(Double amnt) {
		this.amnt = amnt;
	}
	
	
}
