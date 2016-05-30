package com.ahms.ui.administracion.reportes.entity.cancelaciones;

import javax.xml.bind.annotation.XmlAttribute;

public class Cancelacion {
	private String fec;
	private String serv;
	public Cancelacion(String fecha, String serv) {
		super();
		this.fec = fecha;
		this.serv = serv;
	}
	public String getFecha() {
		return fec;
	}
	@XmlAttribute
	public void setFecha(String fecha) {
		this.fec = fecha;
	}
	public String getServ() {
		return serv;
	}
	@XmlAttribute
	public void setServ(String serv) {
		this.serv = serv;
	}
	
	
}
