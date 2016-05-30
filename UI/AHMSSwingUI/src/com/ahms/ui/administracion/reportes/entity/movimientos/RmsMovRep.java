package com.ahms.ui.administracion.reportes.entity.movimientos;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ahms.ui.administracion.reportes.entity.Header;
import com.ahms.ui.administracion.reportes.entity.ReqUser;


@XmlRootElement
public class RmsMovRep {
	private List<ReqUser> usrList;
	private Header header;
	public List<ReqUser> getUsrList() {
		return usrList;
	}
	@XmlElement
	public void setUsrList(List<ReqUser> usrList) {
		this.usrList = usrList;
	}
	public Header getHeader() {
		return header;
	}
	@XmlElement
	public void setHeader(Header header) {
		this.header = header;
	}
	
	
}
