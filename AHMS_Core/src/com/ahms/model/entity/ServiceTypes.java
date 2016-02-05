/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jorge
 */
@Entity
@Table(name = "service_types", catalog = "db_ahms", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"SVT_CODE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceTypes.findAll", query = "SELECT s FROM ServiceTypes s"),
    @NamedQuery(name = "ServiceTypes.findBySvtId", query = "SELECT s FROM ServiceTypes s WHERE s.svtId = :svtId"),
    @NamedQuery(name = "ServiceTypes.findBySvtCode", query = "SELECT s FROM ServiceTypes s WHERE s.svtCode = :svtCode"),
    @NamedQuery(name = "ServiceTypes.findBySvtDesc", query = "SELECT s FROM ServiceTypes s WHERE s.svtDesc = :svtDesc"),
    @NamedQuery(name = "ServiceTypes.findBySvtStatus", query = "SELECT s FROM ServiceTypes s WHERE s.svtStatus = :svtStatus"),
    @NamedQuery(name = "ServiceTypes.findBySvtUsrMod", query = "SELECT s FROM ServiceTypes s WHERE s.svtUsrMod = :svtUsrMod"),
    @NamedQuery(name = "ServiceTypes.findBySvtDteMod", query = "SELECT s FROM ServiceTypes s WHERE s.svtDteMod = :svtDteMod")})
public class ServiceTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SVT_ID", nullable = false)
    private Integer svtId;
    @Basic(optional = false)
    @Column(name = "SVT_CODE", nullable = false, length = 5)
    private String svtCode;
    @Basic(optional = false)
    @Column(name = "SVT_DESC", nullable = false, length = 150)
    private String svtDesc;
    @Basic(optional = false)
    @Column(name = "SVT_STATUS", nullable = false, length = 10)
    private String svtStatus;
    @Column(name = "SVT_USR_MOD", length = 6)
    private String svtUsrMod;
    @Column(name = "SVT_DTE_MOD")
    @Temporal(TemporalType.DATE)
    private Date svtDteMod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "svtId", fetch = FetchType.EAGER)
    private Collection<Services> servicesCollection;

    public ServiceTypes() {
    }

    public ServiceTypes(Integer svtId) {
        this.svtId = svtId;
    }

    public ServiceTypes(Integer svtId, String svtCode, String svtDesc, String svtStatus) {
        this.svtId = svtId;
        this.svtCode = svtCode;
        this.svtDesc = svtDesc;
        this.svtStatus = svtStatus;
    }

    public Integer getSvtId() {
        return svtId;
    }

    public void setSvtId(Integer svtId) {
        this.svtId = svtId;
    }

    public String getSvtCode() {
        return svtCode;
    }

    public void setSvtCode(String svtCode) {
        this.svtCode = svtCode;
    }

    public String getSvtDesc() {
        return svtDesc;
    }

    public void setSvtDesc(String svtDesc) {
        this.svtDesc = svtDesc;
    }

    public String getSvtStatus() {
        return svtStatus;
    }

    public void setSvtStatus(String svtStatus) {
        this.svtStatus = svtStatus;
    }

    public String getSvtUsrMod() {
        return svtUsrMod;
    }

    public void setSvtUsrMod(String svtUsrMod) {
        this.svtUsrMod = svtUsrMod;
    }

    public Date getSvtDteMod() {
        return svtDteMod;
    }

    public void setSvtDteMod(Date svtDteMod) {
        this.svtDteMod = svtDteMod;
    }

    @XmlTransient
    public Collection<Services> getServicesCollection() {
        return servicesCollection;
    }

    public void setServicesCollection(Collection<Services> servicesCollection) {
        this.servicesCollection = servicesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (svtId != null ? svtId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceTypes)) {
            return false;
        }
        ServiceTypes other = (ServiceTypes) object;
        if ((this.svtId == null && other.svtId != null) || (this.svtId != null && !this.svtId.equals(other.svtId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entity.ServiceTypes[ svtId=" + svtId + " ]";
    }
    
}
