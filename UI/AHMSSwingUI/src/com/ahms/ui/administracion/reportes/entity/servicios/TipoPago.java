package com.ahms.ui.administracion.reportes.entity.servicios;


import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAttribute;

public class TipoPago {
	private String typPago;
	private BigDecimal total;
	
	
;	public TipoPago(String typPago,  BigDecimal total){
		super();
		this.typPago = typPago;
		this.total = total;
	}
	public String getTypPago() {
		return typPago;
	}
	@XmlAttribute
	public void setTypPago(String typPago) {
		this.typPago = typPago;
	}
		
	public BigDecimal getTotal() {
		return total;
	}
	@XmlAttribute
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
	
}
