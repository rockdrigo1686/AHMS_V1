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
    @NamedQuery(name = "Account.findByActAmount1", query = "SELECT a FROM Account a WHERE a.actAmount1 = :actAmount1"),
    @NamedQuery(name = "Account.findByActFolio1", query = "SELECT a FROM Account a WHERE a.actFolio1 = :actFolio1"),
    @NamedQuery(name = "Account.findByActAmount2", query = "SELECT a FROM Account a WHERE a.actAmount2 = :actAmount2"),
    @NamedQuery(name = "Account.findByActFolio2", query = "SELECT a FROM Account a WHERE a.actFolio2 = :actFolio2"),
    @NamedQuery(name = "Account.findByActAmount3", query = "SELECT a FROM Account a WHERE a.actAmount3 = :actAmount3"),
    @NamedQuery(name = "Account.findByActFolio3", query = "SELECT a FROM Account a WHERE a.actFolio3 = :actFolio3")})
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
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
    private long actTotal;
    @Basic(optional = false)
    @Column(name = "act_amount1", nullable = false)
    private long actAmount1;
    @Basic(optional = false)
    @Column(name = "act_folio1", nullable = false, length = 15)
    private String actFolio1;
    @Column(name = "act_amount2")
    private Long actAmount2;
    @Column(name = "act_folio2", length = 15)
    private String actFolio2;
    @Column(name = "act_amount3")
    private Long actAmount3;
    @Column(name = "act_folio3", length = 15)
    private String actFolio3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actId")
    private Collection<AccountService> accountServiceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actId")
    private Collection<Guests> guestsCollection;
    @JoinColumn(name = "cus_id", referencedColumnName = "cus_id", nullable = false)
    @ManyToOne(optional = false)
    private Customers cusId;
    @JoinColumn(name = "pay_id1", referencedColumnName = "PAY_ID", nullable = false)
    @ManyToOne(optional = false)
    private PaymentTypes payId1;
    @JoinColumn(name = "pay_id2", referencedColumnName = "PAY_ID")
    @ManyToOne
    private PaymentTypes payId2;
    @JoinColumn(name = "pay_id3", referencedColumnName = "PAY_ID")
    @ManyToOne
    private PaymentTypes payId3;
    @JoinColumn(name = "rms_id", referencedColumnName = "RMS_ID", nullable = false)
    @ManyToOne(optional = false)
    private Rooms rmsId;

    public Account() {
    }

    public Account(Integer actId) {
        this.actId = actId;
    }

    public Account(Integer actId, Date actFecIni, Date actFecFin, String actStatus, long actTotal, long actAmount1, String actFolio1) {
        this.actId = actId;
        this.actFecIni = actFecIni;
        this.actFecFin = actFecFin;
        this.actStatus = actStatus;
        this.actTotal = actTotal;
        this.actAmount1 = actAmount1;
        this.actFolio1 = actFolio1;
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

    public long getActTotal() {
        return actTotal;
    }

    public void setActTotal(long actTotal) {
        this.actTotal = actTotal;
    }

    public long getActAmount1() {
        return actAmount1;
    }

    public void setActAmount1(long actAmount1) {
        this.actAmount1 = actAmount1;
    }

    public String getActFolio1() {
        return actFolio1;
    }

    public void setActFolio1(String actFolio1) {
        this.actFolio1 = actFolio1;
    }

    public Long getActAmount2() {
        return actAmount2;
    }

    public void setActAmount2(Long actAmount2) {
        this.actAmount2 = actAmount2;
    }

    public String getActFolio2() {
        return actFolio2;
    }

    public void setActFolio2(String actFolio2) {
        this.actFolio2 = actFolio2;
    }

    public Long getActAmount3() {
        return actAmount3;
    }

    public void setActAmount3(Long actAmount3) {
        this.actAmount3 = actAmount3;
    }

    public String getActFolio3() {
        return actFolio3;
    }

    public void setActFolio3(String actFolio3) {
        this.actFolio3 = actFolio3;
    }

    @XmlTransient
    public Collection<AccountService> getAccountServiceCollection() {
        return accountServiceCollection;
    }

    public void setAccountServiceCollection(Collection<AccountService> accountServiceCollection) {
        this.accountServiceCollection = accountServiceCollection;
    }

    @XmlTransient
    public Collection<Guests> getGuestsCollection() {
        return guestsCollection;
    }

    public void setGuestsCollection(Collection<Guests> guestsCollection) {
        this.guestsCollection = guestsCollection;
    }

    public Customers getCusId() {
        return cusId;
    }

    public void setCusId(Customers cusId) {
        this.cusId = cusId;
    }

    public PaymentTypes getPayId1() {
        return payId1;
    }

    public void setPayId1(PaymentTypes payId1) {
        this.payId1 = payId1;
    }

    public PaymentTypes getPayId2() {
        return payId2;
    }

    public void setPayId2(PaymentTypes payId2) {
        this.payId2 = payId2;
    }

    public PaymentTypes getPayId3() {
        return payId3;
    }

    public void setPayId3(PaymentTypes payId3) {
        this.payId3 = payId3;
    }

    public Rooms getRmsId() {
        return rmsId;
    }

    public void setRmsId(Rooms rmsId) {
        this.rmsId = rmsId;
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
