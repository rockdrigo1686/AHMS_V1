package com.ahms.ui.administracion.reportes.entity.servicios;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class TipoServicio {
	private String typServ;
	private List<Servicio> Service;
	public TipoServicio(String typServ, List<Servicio> servList) {
		super();
		this.typServ = typServ;
		this.Service = servList;
	}
	public String getTypServ() {
		return typServ;
	}
	@XmlAttribute
	public void setTypServ(String typServ) {
		this.typServ = typServ;
	}
	public List<Servicio> getService() {
		return Service;
	}
	@XmlElement
	public void setService(List<Servicio> servList) {
		this.Service = servList;
	}
	
	
}
