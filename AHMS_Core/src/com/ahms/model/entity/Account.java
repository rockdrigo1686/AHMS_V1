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
import javax.persistence.CascadeType;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rsoto
 */
@Entity
@Table(name = "account", catalog = "db_ahms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByActId", query = "SELECT a FROM Account a WHERE a.actId = :actId"),
    @NamedQuery(name = "Account.findByActFecIni", query = "SELECT a FROM Account a WHERE a.actFecIni = :actFecIni"),
    @NamedQuery(name = "Account.findByActFecFin", query = "SELECT a FROM Account a WHERE a.actFecFin = :actFecFin"),
    @NamedQuery(name = "Account.findByActStatus", query = "SELECT a FROM Account a WHERE a.actStatus = :actStatus"),
    @NamedQuery(name = "Account.findByActTotal", query = "SELECT a FROM Account a WHERE a.actTotal = :actTotal"),
    @NamedQuery(name = "Account.findByActDteMod", query = "SELECT a FROM Account a WHERE a.actDteMod = :actDteMod"),
    @NamedQuery(name = "Account.findByActSubtotal", query = "SELECT a FROM Account a WHERE a.actSubtotal = :actSubtotal"),
    @NamedQuery(name = "Account.findByActIva", query = "SELECT a FROM Account a WHERE a.actIva = :actIva"),
    @NamedQuery(name = "Account.findByActIvaAmt", query = "SELECT a FROM Account a WHERE a.actIvaAmt = :actIvaAmt"),
    @NamedQuery(name = "Account.findByCusId", query = "SELECT a FROM Account a WHERE a.cusId = :cusId")})
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "act_id", nullable = false)
    private Integer actId;
    @Basic(optional = false)
    @Column(name = "act_fec_ini", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date actFecIni;
    @Basic(optional = false)
    @Column(name = "act_fec_fin", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date actFecFin;
    @Basic(optional = false)
    @Column(name = "act_status", nullable = false, length = 20)
    private String actStatus;
    @Basic(optional = false)
    @Column(name = "act_total", nullable = false)
    private BigDecimal actTotal;
    @Basic(optional = false)
    @Column(name = "act_dte_mod", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date actDteMod;
    @Column(name = "act_subtotal")
    private BigDecimal actSubtotal;
    @Column(name = "act_iva")
    private BigDecimal actIva;
    @Column(name = "act_iva_amt")
    private BigDecimal actIvaAmt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actId", fetch = FetchType.EAGER)
    private Collection<AccountTransactions> accountTransactionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actId", fetch = FetchType.EAGER)
    private Collection<FolioTransaction> folioTransactionCollection;
    @JoinColumn(name = "cus_id", referencedColumnName = "cus_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Customers cusId;
    @JoinColumn(name = "act_usr_mod", referencedColumnName = "usr_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users actUsrMod;

    public Account() {
    }

    public Account(Integer actId) {
        this.actId = actId;
    }

    public Account(Integer actId, Date actFecIni, Date actFecFin, String actStatus, BigDecimal actTotal, Date actDteMod) {
        this.actId = actId;
        this.actFecIni = actFecIni;
        this.actFecFin = actFecFin;
        this.actStatus = actStatus;
        this.actTotal = actTotal;
        this.actDteMod = actDteMod;
    }

    public Integer getActId() {
        return actId;
    }

    public void setActId(Integer actId) {
        this.actId = actId;
    }

    public Date getActFecIni() {
        return actFecIni;
    }

    public void setActFecIni(Date actFecIni) {
        this.actFecIni = actFecIni;
    }

    public Date getActFecFin() {
        return actFecFin;
    }

    public void setActFecFin(Date actFecFin) {
        this.actFecFin = actFecFin;
    }

    public String getActStatus() {
        return actStatus;
    }

    public void setActStatus(String actStatus) {
        this.actStatus = actStatus;
    }

    public BigDecimal getActTotal() {
        return actTotal;
    }

    public void setActTotal(BigDecimal actTotal) {
        this.actTotal = actTotal;
    }

    public Date getActDteMod() {
        return actDteMod;
    }

    public void setActDteMod(Date actDteMod) {
        this.actDteMod = actDteMod;
    }

    public BigDecimal getActSubtotal() {
        return actSubtotal;
    }

    public void setActSubtotal(BigDecimal actSubtotal) {
        this.actSubtotal = actSubtotal;
    }

    public BigDecimal getActIva() {
        return actIva;
    }

    public void setActIva(BigDecimal actIva) {
        this.actIva = actIva;
    }

    public BigDecimal getActIvaAmt() {
        return actIvaAmt;
    }

    public void setActIvaAmt(BigDecimal actIvaAmt) {
        this.actIvaAmt = actIvaAmt;
    }

    @XmlTransient
    public Collection<AccountTransactions> getAccountTransactionsCollection() {
        return accountTransactionsCollection;
    }

    public void setAccountTransactionsCollection(Collection<AccountTransactions> accountTransactionsCollection) {
        this.accountTransactionsCollection = accountTransactionsCollection;
    }

    @XmlTransient
    public Collection<FolioTransaction> getFolioTransactionCollection() {
        return folioTransactionCollection;
    }

    public void setFolioTransactionCollection(Collection<FolioTransaction> folioTransactionCollection) {
        this.folioTransactionCollection = folioTransactionCollection;
    }

    public Customers getCusId() {
        return cusId;
    }

    public void setCusId(Customers cusId) {
        this.cusId = cusId;
    }

    public Users getActUsrMod() {
        return actUsrMod;
    }

    public void setActUsrMod(Users actUsrMod) {
        this.actUsrMod = actUsrMod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actId != null ? actId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.actId == null && other.actId != null) || (this.actId != null && !this.actId.equals(other.actId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entity.Account[ actId=" + actId + " ]";
    }
    
}
