/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rsoto
 */
@Entity
@Table(name = "services", catalog = "db_ahms", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"SRV_CODE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Services.findAll", query = "SELECT s FROM Services s"),
    @NamedQuery(name = "Services.findAllByServiceType", query = "SELECT s FROM Services s WHERE s.svtId = :svtId "),
    @NamedQuery(name = "Services.findBySrvId", query = "SELECT s FROM Services s WHERE s.srvId = :srvId"),
    @NamedQuery(name = "Services.findBySrvCode", query = "SELECT s FROM Services s WHERE s.srvCode = :srvCode"),
    @NamedQuery(name = "Services.findBySrvName", query = "SELECT s FROM Services s WHERE s.srvName = :srvName"),
    @NamedQuery(name = "Services.findBySrvDesc", query = "SELECT s FROM Services s WHERE s.srvDesc = :srvDesc"),
    @NamedQuery(name = "Services.findBySrvPrice", query = "SELECT s FROM Services s WHERE s.srvPrice = :srvPrice"),
    @NamedQuery(name = "Services.findBySrvDteMod", query = "SELECT s FROM Services s WHERE s.srvDteMod = :srvDteMod")})
public class Services implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SRV_ID", nullable = false)
    private Integer srvId;
    @Basic(optional = false)
    @Column(name = "SRV_CODE", nullable = false, length = 5)
    private String srvCode;
    @Basic(optional = false)
    @Column(name = "SRV_NAME", nullable = false, length = 40)
    private String srvName;
    @Basic(optional = false)
    @Column(name = "SRV_DESC", nullable = false, length = 150)
    private String srvDesc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "SRV_PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal srvPrice;
    @Basic(optional = false)
    @Column(name = "SRV_DTE_MOD", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date srvDteMod;
    @JoinColumn(name = "SRV_STATUS", referencedColumnName = "MVA_KEY", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MultiValue srvStatus;
    @JoinColumn(name = "SVT_ID", referencedColumnName = "SVT_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ServiceTypes svtId;
    @JoinColumn(name = "SRV_USR_MOD", referencedColumnName = "USR_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users srvUsrMod;
    @OneToMany(mappedBy = "srvId", fetch = FetchType.EAGER)
    private Collection<AccountTransactions> accountTransactionsCollection;

    public Services() {
    }

    public Services(Integer srvId) {
        this.srvId = srvId;
    }

    public Services(Integer srvId, String srvCode, String srvName, String srvDesc, BigDecimal srvPrice, Date srvDteMod) {
        this.srvId = srvId;
        this.srvCode = srvCode;
        this.srvName = srvName;
        this.srvDesc = srvDesc;
        this.srvPrice = srvPrice;
        this.srvDteMod = srvDteMod;
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

    public BigDecimal getSrvPrice() {
        return srvPrice;
    }

    public void setSrvPrice(BigDecimal srvPrice) {
        this.srvPrice = srvPrice;
    }

    public Date getSrvDteMod() {
        return srvDteMod;
    }

    public void setSrvDteMod(Date srvDteMod) {
        this.srvDteMod = srvDteMod;
    }

    public MultiValue getSrvStatus() {
        return srvStatus;
    }

    public void setSrvStatus(MultiValue srvStatus) {
        this.srvStatus = srvStatus;
    }

    public ServiceTypes getSvtId() {
        return svtId;
    }

    public void setSvtId(ServiceTypes svtId) {
        this.svtId = svtId;
    }

    public Users getSrvUsrMod() {
        return srvUsrMod;
    }

    public void setSrvUsrMod(Users srvUsrMod) {
        this.srvUsrMod = srvUsrMod;
    }

    @XmlTransient
    public Collection<AccountTransactions> getAccountTransactionsCollection() {
        return accountTransactionsCollection;
    }

    public void setAccountTransactionsCollection(Collection<AccountTransactions> accountTransactionsCollection) {
        this.accountTransactionsCollection = accountTransactionsCollection;
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
        return srvDesc;
    }
    
}
