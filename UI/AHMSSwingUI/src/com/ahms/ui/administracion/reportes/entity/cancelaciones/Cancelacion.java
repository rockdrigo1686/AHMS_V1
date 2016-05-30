package com.ahms.ui.administracion.reportes.entity.cancelaciones;

import javax.xml.bind.annotation.XmlAttribute;

public class Cancelacion {
	private String fecha;
	private String serv;
	public Cancelacion(String fecha, String serv) {
		super();
		this.fecha = fecha;
		this.serv = serv;
	}
	public String getFecha() {
		return fecha;
	}
	@XmlAttribute
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getServ() {
		return serv;
	}
	@XmlAttribute
	public void setServ(String serv) {
		this.serv = serv;
	}
	
	
}
