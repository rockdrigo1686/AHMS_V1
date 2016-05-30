package com.ahms.ui.administracion.reportes.entity.movimientos;


import javax.xml.bind.annotation.XmlAttribute;

public class MovimientoCuarto {

	private String fecMov;
	private String cusName;
	private String rmsNumAnt;
	private String rmsNumNew;
	private String movDesc;
	private String usuAut;
		
	public MovimientoCuarto(String fecha, String cusName, String rmsNumOrg,
			String rmsNumNew, String movDesc, String usuAut) {
		super();
		this.fecMov = fecha;
		this.cusName = cusName;
		this.rmsNumAnt = rmsNumOrg;
		this.rmsNumNew = rmsNumNew;
		this.movDesc = movDesc;
		this.usuAut = usuAut;
	}

	public String getFecha() {
		return fecMov;
	}
	
	@XmlAttribute
	public void setFecha(String fecha) {
		this.fecMov = fecha;
	}
	public String getCusName() {
		return cusName;
	}
	@XmlAttribute
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getRmsNumOrg() {
		return rmsNumAnt;
	}
	@XmlAttribute
	public void setRmsNumOrg(String rmsNumOrg) {
		this.rmsNumAnt = rmsNumOrg;
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
