package com.ahms.ui.administracion.reportes.entity.servicios;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class TipoServicio {
	private String typServ;
	private List<Servicio> servList;
	public TipoServicio(String typServ, List<Servicio> servList) {
		super();
		this.typServ = typServ;
		this.servList = servList;
	}
	public String getTypServ() {
		return typServ;
	}
	@XmlAttribute
	public void setTypServ(String typServ) {
		this.typServ = typServ;
	}
	public List<Servicio> getServList() {
		return servList;
	}
	@XmlElement
	public void setServList(List<Servicio> servList) {
		this.servList = servList;
	}
	
	
}
