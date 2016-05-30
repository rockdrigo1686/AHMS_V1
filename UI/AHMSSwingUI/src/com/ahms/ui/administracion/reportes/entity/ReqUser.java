package com.ahms.ui.administracion.reportes.entity;

import com.ahms.ui.administracion.reportes.entity.cancelaciones.Cancelacion;
import com.ahms.ui.administracion.reportes.entity.movimientos.MovimientoCuarto;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

;

public class ReqUser {
	
	private String reqUser;
	private List<MovimientoCuarto> movList;
	private List<Cancelacion> canList;
	public ReqUser(String user){
		this.reqUser = user;
	}
	
	public String getReqUser() {
		return reqUser;
	}
	@XmlAttribute
	public void setReqUser(String reqUser) {
		this.reqUser = reqUser;
	}
	public List<MovimientoCuarto> getMovList() {
		return movList;
	}
	@XmlElement
	public void setMovList(List<MovimientoCuarto> movList) {
		this.movList = movList;
	}

	public List<Cancelacion> getCanList() {
		return canList;
	}
	@XmlElement
	public void setCanList(List<Cancelacion> canList) {
		this.canList = canList;
	}
	
	
}
