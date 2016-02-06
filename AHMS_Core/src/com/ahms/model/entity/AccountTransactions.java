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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rsoto
 */
@Entity
@Table(name = "account_transactions", catalog = "db_ahms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountTransactions.findAll", query = "SELECT a FROM AccountTransactions a"),
    @NamedQuery(name = "AccountTransactions.findByAtrId", query = "SELECT a FROM AccountTransactions a WHERE a.accountTransactionsPK.atrId = :atrId"),
    @NamedQuery(name = "AccountTransactions.findByRmsId", query = "SELECT a FROM AccountTransactions a WHERE a.accountTransactionsPK.rmsId = :rmsId"),
    @NamedQuery(name = "AccountTransactions.findByAtrQuantity", query = "SELECT a FROM AccountTransactions a WHERE a.atrQuantity = :atrQuantity"),
    @NamedQuery(name = "AccountTransactions.findByAtrNotes", query = "SELECT a FROM AccountTransactions a WHERE a.atrNotes = :atrNotes"),
    @NamedQuery(name = "AccountTransactions.findByAtrStatus", query = "SELECT a FROM AccountTransactions a WHERE a.atrStatus = :atrStatus"),
    @NamedQuery(name = "AccountTransactions.findByAtrDteMod", query = "SELECT a FROM AccountTransactions a WHERE a.atrDteMod = :atrDteMod")})
public class AccountTransactions implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AccountTransactionsPK accountTransactionsPK;
    @Basic(optional = false)
    @Column(name = "atr_quantity", nullable = false)
    private int atrQuantity;
    @Column(name = "atr_notes", length = 250)
    private String atrNotes;
    @Basic(optional = false)
    @Column(name = "atr_status", nullable = false)
    private int atrStatus;
    @Basic(optional = false)
    @Column(name = "atr_dte_mod", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date atrDteMod;
    @JoinColumn(name = "act_id", referencedColumnName = "act_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Account actId;
    @JoinColumn(name = "cou_id", referencedColumnName = "cou_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CashOut couId;
    @JoinColumn(name = "rms_id", referencedColumnName = "RMS_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Rooms rooms;
    @JoinColumn(name = "srv_id", referencedColumnName = "SRV_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Services srvId;
    @JoinColumn(name = "atr_usr_mod", referencedColumnName = "usr_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users atrUsrMod;

    public AccountTransactions() {
    }

    public AccountTransactions(AccountTransactionsPK accountTransactionsPK) {
        this.accountTransactionsPK = accountTransactionsPK;
    }

    public AccountTransactions(AccountTransactionsPK accountTransactionsPK, int atrQuantity, int atrStatus, Date atrDteMod) {
        this.accountTransactionsPK = accountTransactionsPK;
        this.atrQuantity = atrQuantity;
        this.atrStatus = atrStatus;
        this.atrDteMod = atrDteMod;
    }

    public AccountTransactions(int atrId, int rmsId) {
        this.accountTransactionsPK = new AccountTransactionsPK(atrId, rmsId);
    }

    public AccountTransactionsPK getAccountTransactionsPK() {
        return accountTransactionsPK;
    }

    public void setAccountTransactionsPK(AccountTransactionsPK accountTransactionsPK) {
        this.accountTransactionsPK = accountTransactionsPK;
    }

    public int getAtrQuantity() {
        return atrQuantity;
    }

    public void setAtrQuantity(int atrQuantity) {
        this.atrQuantity = atrQuantity;
    }

    public String getAtrNotes() {
        return atrNotes;
    }

    public void setAtrNotes(String atrNotes) {
        this.atrNotes = atrNotes;
    }

    public int getAtrStatus() {
        return atrStatus;
    }

    public void setAtrStatus(int atrStatus) {
        this.atrStatus = atrStatus;
    }

    public Date getAtrDteMod() {
        return atrDteMod;
    }

    public void setAtrDteMod(Date atrDteMod) {
        this.atrDteMod = atrDteMod;
    }

    public Account getActId() {
        return actId;
    }

    public void setActId(Account actId) {
        this.actId = actId;
    }

    public CashOut getCouId() {
        return couId;
    }

    public void setCouId(CashOut couId) {
        this.couId = couId;
    }

    public Rooms getRooms() {
        return rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }

    public Services getSrvId() {
        return srvId;
    }

    public void setSrvId(Services srvId) {
        this.srvId = srvId;
    }

    public Users getAtrUsrMod() {
        return atrUsrMod;
    }

    public void setAtrUsrMod(Users atrUsrMod) {
        this.atrUsrMod = atrUsrMod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountTransactionsPK != null ? accountTransactionsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountTransactions)) {
            return false;
        }
        AccountTransactions other = (AccountTransactions) object;
        if ((this.accountTransactionsPK == null && other.accountTransactionsPK != null) || (this.accountTransactionsPK != null && !this.accountTransactionsPK.equals(other.accountTransactionsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entity.AccountTransactions[ accountTransactionsPK=" + accountTransactionsPK + " ]";
    }
    
}
