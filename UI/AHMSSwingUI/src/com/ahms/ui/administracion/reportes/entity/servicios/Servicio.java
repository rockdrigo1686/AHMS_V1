package com.ahms.ui.administracion.reportes.entity.servicios;

import javax.xml.bind.annotation.XmlAttribute;

public class Servicio {
	private String serv;
	private Integer qty;
	private String amnt;
	public Servicio(String serv, Integer qty, String amnt) {
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
	public String getAmnt() {
		return amnt;
	}
	@XmlAttribute
	public void setAmnt(String amnt) {
		this.amnt = amnt;
	}
	
	
}
