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
@Table(name = "cash_out", catalog = "db_ahms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CashOut.findAll", query = "SELECT c FROM CashOut c"),
    @NamedQuery(name = "CashOut.findByCouId", query = "SELECT c FROM CashOut c WHERE c.couId = :couId"),
    @NamedQuery(name = "CashOut.findByCouAmoIni", query = "SELECT c FROM CashOut c WHERE c.couAmoIni = :couAmoIni"),
    @NamedQuery(name = "CashOut.findByCouMonEnd", query = "SELECT c FROM CashOut c WHERE c.couMonEnd = :couMonEnd"),
    @NamedQuery(name = "CashOut.findByCouDteIni", query = "SELECT c FROM CashOut c WHERE c.couDteIni = :couDteIni"),
    @NamedQuery(name = "CashOut.findByCouDteEnd", query = "SELECT c FROM CashOut c WHERE c.couDteEnd = :couDteEnd"),
    @NamedQuery(name = "CashOut.findByCouStatus", query = "SELECT c FROM CashOut c WHERE c.couStatus = 'Abierto'")})
public class CashOut implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "couId", fetch = FetchType.EAGER)
    private Collection<MoneyMovement> moneyMovementCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "COU_ID", nullable = false)
    private Integer couId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "COU_AMO_INI", nullable = false, precision = 10, scale = 2)
    private BigDecimal couAmoIni;
    @Column(name = "COU_MON_END", precision = 10, scale = 2)
    private BigDecimal couMonEnd;
    @Basic(optional = false)
    @Column(name = "COU_DTE_INI", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date couDteIni;
    @Column(name = "COU_DTE_END")
    @Temporal(TemporalType.TIMESTAMP)
    private Date couDteEnd;
    @OneToMany(mappedBy = "couId", fetch = FetchType.EAGER)
    private Collection<AccountTransactions> accountTransactionsCollection;
    @JoinColumn(name = "COU_STATUS", referencedColumnName = "MVA_KEY", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MultiValue couStatus;
    @JoinColumn(name = "USR_ID", referencedColumnName = "USR_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users usrId;
    @OneToMany(mappedBy = "couId", fetch = FetchType.EAGER)
    private Collection<FolioTransaction> folioTransactionCollection;

    public CashOut() {
    }

    public CashOut(Integer couId) {
        this.couId = couId;
    }

    public CashOut(Integer couId, BigDecimal couAmoIni, Date couDteIni) {
        this.couId = couId;
        this.couAmoIni = couAmoIni;
        this.couDteIni = couDteIni;
    }

    public Integer getCouId() {
        return couId;
    }

    public void setCouId(Integer couId) {
        this.couId = couId;
    }

    public BigDecimal getCouAmoIni() {
        return couAmoIni;
    }

    public void setCouAmoIni(BigDecimal couAmoIni) {
        this.couAmoIni = couAmoIni;
    }

    public BigDecimal getCouMonEnd() {
        return couMonEnd;
    }

    public void setCouMonEnd(BigDecimal couMonEnd) {
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

    @XmlTransient
    public Collection<AccountTransactions> getAccountTransactionsCollection() {
        return accountTransactionsCollection;
    }

    public void setAccountTransactionsCollection(Collection<AccountTransactions> accountTransactionsCollection) {
        this.accountTransactionsCollection = accountTransactionsCollection;
    }

    public MultiValue getCouStatus() {
        return couStatus;
    }

    public void setCouStatus(MultiValue couStatus) {
        this.couStatus = couStatus;
    }

    public Users getUsrId() {
        return usrId;
    }

    public void setUsrId(Users usrId) {
        this.usrId = usrId;
    }

    @XmlTransient
    public Collection<FolioTransaction> getFolioTransactionCollection() {
        return folioTransactionCollection;
    }

    public void setFolioTransactionCollection(Collection<FolioTransaction> folioTransactionCollection) {
        this.folioTransactionCollection = folioTransactionCollection;
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
        return "com.ahms.boundary.CashOut[ couId=" + couId + " ]";
    }

    @XmlTransient
    public Collection<MoneyMovement> getMoneyMovementCollection() {
        return moneyMovementCollection;
    }

    public void setMoneyMovementCollection(Collection<MoneyMovement> moneyMovementCollection) {
        this.moneyMovementCollection = moneyMovementCollection;
    }

}
