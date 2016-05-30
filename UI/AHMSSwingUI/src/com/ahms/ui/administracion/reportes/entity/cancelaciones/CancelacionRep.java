package com.ahms.ui.administracion.reportes.entity.cancelaciones;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ahms.ui.administracion.reportes.entity.Header;
import com.ahms.ui.administracion.reportes.entity.ReqUser;


@XmlRootElement
public class CancelacionRep {
	private Header header;
	private List<ReqUser> userList;
	
	public Header getHeader() {
		return header;
	}
	@XmlElement
	public void setHeader(Header header) {
		this.header = header;
	}
	public List<ReqUser> getUser() {
		return userList;
	}
	@XmlElement
	public void setUser(List<ReqUser> userList) {
		this.userList = userList;
	}
	
	
}
