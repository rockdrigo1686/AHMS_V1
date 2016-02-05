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
 * @author jorge
 */
@Entity
@Table(name = "cash_out", catalog = "db_ahms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CashOut.findAll", query = "SELECT c FROM CashOut c"),
    @NamedQuery(name = "CashOut.findByCouId", query = "SELECT c FROM CashOut c WHERE c.couId = :couId"),
    @NamedQuery(name = "CashOut.findByCouAmoIni", query = "SELECT c FROM CashOut c WHERE c.couAmoIni = :couAmoIni"),
    @NamedQuery(name = "CashOut.findByCouMonEnd", query = "SELECT c FROM CashOut c WHERE c.couMonEnd = :couMonEnd"),
    @NamedQuery(name = "CashOut.findByCouDteIni", query = "SELECT c FROM CashOut c WHERE c.couDteIni = :couDteIni"),
    @NamedQuery(name = "CashOut.findByCouDteEnd", query = "SELECT c FROM CashOut c WHERE c.couDteEnd = :couDteEnd"),
    @NamedQuery(name = "CashOut.findByCouStatus", query = "SELECT c FROM CashOut c WHERE c.couStatus = :couStatus")})
public class CashOut implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cou_id", nullable = false)
    private Integer couId;
    @Basic(optional = false)
    @Column(name = "cou_amo_ini", nullable = false)
    private long couAmoIni;
    @Basic(optional = false)
    @Column(name = "cou_mon_end", nullable = false)
    private long couMonEnd;
    @Basic(optional = false)
    @Column(name = "cou_dte_ini", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date couDteIni;
    @Basic(optional = false)
    @Column(name = "cou_dte_end", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date couDteEnd;
    @Basic(optional = false)
    @Column(name = "cou_status", nullable = false, length = 10)
    private String couStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "couId", fetch = FetchType.EAGER)
    private Collection<AccountTransactions> accountTransactionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "couId", fetch = FetchType.EAGER)
    private Collection<MoneyMovement> moneyMovementCollection;
    @JoinColumn(name = "usr_id", referencedColumnName = "usr_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users usrId;

    public CashOut() {
    }

    public CashOut(Integer couId) {
        this.couId = couId;
    }

    public CashOut(Integer couId, long couAmoIni, long couMonEnd, Date couDteIni, Date couDteEnd, String couStatus) {
        this.couId = couId;
        this.couAmoIni = couAmoIni;
        this.couMonEnd = couMonEnd;
        this.couDteIni = couDteIni;
        this.couDteEnd = couDteEnd;
        this.couStatus = couStatus;
    }

    public Integer getCouId() {
        return couId;
    }

    public void setCouId(Integer couId) {
        this.couId = couId;
    }

    public long getCouAmoIni() {
        return couAmoIni;
    }

    public void setCouAmoIni(long couAmoIni) {
        this.couAmoIni = couAmoIni;
    }

    public long getCouMonEnd() {
        return couMonEnd;
    }

    public void setCouMonEnd(long couMonEnd) {
        this.couMonEnd = couMonEnd;
    }

    public Date getCouDteIni() {
        return couDteIni;
    }

    public void setCouDteIni(Date couDteIni) {
        this.couDteIni = couDteIni;
    }

    public Date getCouDteEnd() {
        return couDteEnd;
    }

    public void setCouDteEnd(Date couDteEnd) {
        this.couDteEnd = couDteEnd;
    }

    public String getCouStatus() {
        return couStatus;
    }

    public void setCouStatus(String couStatus) {
        this.couStatus = couStatus;
    }

    @XmlTransient
    public Collection<AccountTransactions> getAccountTransactionsCollection() {
        return accountTransactionsCollection;
    }

    public void setAccountTransactionsCollection(Collection<AccountTransactions> accountTransactionsCollection) {
        this.accountTransactionsCollection = accountTransactionsCollection;
    }

    @XmlTransient
    public Collection<MoneyMovement> getMoneyMovementCollection() {
        return moneyMovementCollection;
    }

    public void setMoneyMovementCollection(Collection<MoneyMovement> moneyMovementCollection) {
        this.moneyMovementCollection = moneyMovementCollection;
    }

    public Users getUsrId() {
        return usrId;
    }

    public void setUsrId(Users usrId) {
        this.usrId = usrId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (couId != null ? couId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CashOut)) {
            return false;
        }
        CashOut other = (CashOut) object;
        if ((this.couId == null && other.couId != null) || (this.couId != null && !this.couId.equals(other.couId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entity.CashOut[ couId=" + couId + " ]";
    }
    
}
