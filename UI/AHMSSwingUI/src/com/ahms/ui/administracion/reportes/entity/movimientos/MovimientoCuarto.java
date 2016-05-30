package com.ahms.ui.administracion.reportes.entity.movimientos;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAttribute;

public class MovimientoCuarto {

	private String fecha;
	private String cusName;
	private String rmsNumOrg;
	private String rmsNumNew;
	private String movDesc;
	private String usuAut;
		
	public MovimientoCuarto(String fecha, String cusName, String rmsNumOrg,
			String rmsNumNew, String movDesc, String usuAut) {
		super();
		this.fecha = fecha;
		this.cusName = cusName;
		this.rmsNumOrg = rmsNumOrg;
		this.rmsNumNew = rmsNumNew;
		this.movDesc = movDesc;
		this.usuAut = usuAut;
	}

	public String getFecha() {
		return fecha;
	}
	
	@XmlAttribute
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getCusName() {
		return cusName;
	}
	@XmlAttribute
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getRmsNumOrg() {
		return rmsNumOrg;
	}
	@XmlAttribute
	public void setRmsNumOrg(String rmsNumOrg) {
		this.rmsNumOrg = rmsNumOrg;
	}
	public String getRmsNumNew() {
		return rmsNumNew;
	}
	@XmlAttribute
	public void setRmsNumNew(String rmsNumNew) {
		this.rmsNumNew = rmsNumNew;
	}
	public String getMovDesc() {
		return movDesc;
	}
	@XmlAttribute
	public void setMovDesc(String movDesc) {
		this.movDesc = movDesc;
	}
	public String getUsuAut() {
		return usuAut;
	}
	@XmlAttribute
	public void setUsuAut(String usuAut) {
		this.usuAut = usuAut;
	}
	
}
