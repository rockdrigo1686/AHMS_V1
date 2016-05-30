package com.ahms.ui.administracion.reportes.entity;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAttribute;

public class Header {

	private String dteIni;
	private String dteFin;
	private String user;
	
	public Header(String dteIni, String dteFin){
		this.dteIni = dteIni;
		this.dteFin = dteFin;
	}
	
	public String getDteIni() {
		return dteIni;
	}
	@XmlAttribute
	public void setDteIni(String dteIni) {
		this.dteIni = dteIni;
	}
	public String getDteFin() {
		return dteFin;
	}
	@XmlAttribute
	public void setDteFin(String dteFin) {
		this.dteFin = dteFin;
	}
	public String getUser() {
		return user;
	}
	@XmlAttribute
	public void setUser(String user) {
		this.user = user;
	}
	
	
}
