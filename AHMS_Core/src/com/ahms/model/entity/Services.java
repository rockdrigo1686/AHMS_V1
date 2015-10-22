/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rsoto
 */
@Entity
@Table(name = "services", catalog = "DB_AHMS", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"SRV_CODE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Services.findAll", query = "SELECT s FROM Services s"),
    @NamedQuery(name = "Services.findBySrvId", query = "SELECT s FROM Services s WHERE s.srvId = :srvId"),
    @NamedQuery(name = "Services.findBySrvCode", query = "SELECT s FROM Services s WHERE s.srvCode = :srvCode"),
    @NamedQuery(name = "Services.findBySrvName", query = "SELECT s FROM Services s WHERE s.srvName = :srvName"),
    @NamedQuery(name = "Services.findBySrvDesc", query = "SELECT s FROM Services s WHERE s.srvDesc = :srvDesc"),
    @NamedQuery(name = "Services.findBySrvStatus", query = "SELECT s FROM Services s WHERE s.srvStatus = :srvStatus"),
    @NamedQuery(name = "Services.findBySrvPrice", query = "SELECT s FROM Services s WHERE s.srvPrice = :srvPrice"),
    @NamedQuery(name = "Services.findBySrvUsrMod", query = "SELECT s FROM Services s WHERE s.srvUsrMod = :srvUsrMod"),
    @NamedQuery(name = "Services.findBySrvDteMod", query = "SELECT s FROM Services s WHERE s.srvDteMod = :srvDteMod")})
public class Services implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SRV_ID", nullable = false)
    private Integer srvId;
    @Basic(optional = false)
    @Column(name = "SRV_CODE", nullable = false, length = 4)
    private String srvCode;
    @Basic(optional = false)
    @Column(name = "SRV_NAME", nullable = false, length = 15)
    private String srvName;
    @Basic(optional = false)
    @Column(name = "SRV_DESC", nullable = false, length = 20)
    private String srvDesc;
    @Basic(optional = false)
    @Column(name = "SRV_STATUS", nullable = false)
    private int srvStatus;
    @Basic(optional = false)
    @Column(name = "SRV_PRICE", nullable = false)
    private long srvPrice;
    @Column(name = "SRV_USR_MOD", length = 6)
    private String srvUsrMod;
    @Column(name = "SRV_DTE_MOD")
    @Temporal(TemporalType.DATE)
    private Date srvDteMod;
    @JoinColumn(name = "SVT_ID", referencedColumnName = "SVT_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ServiceTypes svtId;

    public Services() {
    }

    public Services(Integer srvId) {
        this.srvId = srvId;
    }

    public Services(Integer srvId, String srvCode, String srvName, String srvDesc, int srvStatus, long srvPrice) {
        this.srvId = srvId;
        this.srvCode = srvCode;
        this.srvName = srvName;
        this.srvDesc = srvDesc;
        this.srvStatus = srvStatus;
        this.srvPrice = srvPrice;
    }

    public Integer getSrvId() {
        return srvId;
    }

    public void setSrvId(Integer srvId) {
        this.srvId = srvId;
    }

    public String getSrvCode() {
        return srvCode;
    }

    public void setSrvCode(String srvCode) {
        this.srvCode = srvCode;
    }

    public String getSrvName() {
        return srvName;
    }

    public void setSrvName(String srvName) {
        this.srvName = srvName;
    }

    public String getSrvDesc() {
        return srvDesc;
    }

    public void setSrvDesc(String srvDesc) {
        this.srvDesc = srvDesc;
    }

    public int getSrvStatus() {
        return srvStatus;
    }

    public void setSrvStatus(int srvStatus) {
        this.srvStatus = srvStatus;
    }

    public long getSrvPrice() {
        return srvPrice;
    }

    public void setSrvPrice(long srvPrice) {
        this.srvPrice = srvPrice;
    }

    public String getSrvUsrMod() {
        return srvUsrMod;
    }

    public void setSrvUsrMod(String srvUsrMod) {
        this.srvUsrMod = srvUsrMod;
    }

    public Date getSrvDteMod() {
        return srvDteMod;
    }

    public void setSrvDteMod(Date srvDteMod) {
        this.srvDteMod = srvDteMod;
    }

    public ServiceTypes getSvtId() {
        return svtId;
    }

    public void setSvtId(ServiceTypes svtId) {
        this.svtId = svtId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (srvId != null ? srvId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Services)) {
            return false;
        }
        Services other = (Services) object;
        if ((this.srvId == null && other.srvId != null) || (this.srvId != null && !this.srvId.equals(other.srvId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entitys.Services[ srvId=" + srvId + " ]";
    }
    
}
