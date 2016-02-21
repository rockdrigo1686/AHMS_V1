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
 * @author rsoto
 */
@Entity
@Table(name = "account_transactions", catalog = "db_ahms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountTransactions.findAll", query = "SELECT a FROM AccountTransactions a"),
    @NamedQuery(name = "AccountTransactions.findAllByRmsId", query = "SELECT a FROM AccountTransactions a WHERE a.rmsId = :rmsId AND a.srvId is not null "),
    @NamedQuery(name = "AccountTransactions.findByAtrId", query = "SELECT a FROM AccountTransactions a WHERE a.atrId = :atrId"),
    @NamedQuery(name = "AccountTransactions.findByAtrQuantity", query = "SELECT a FROM AccountTransactions a WHERE a.atrQuantity = :atrQuantity"),
    @NamedQuery(name = "AccountTransactions.findByAtrNotes", query = "SELECT a FROM AccountTransactions a WHERE a.atrNotes = :atrNotes"),
    @NamedQuery(name = "AccountTransactions.findByAtrDteMod", query = "SELECT a FROM AccountTransactions a WHERE a.atrDteMod = :atrDteMod"),
    @NamedQuery(name = "AccountTransactions.findRentsByActId", query = "SELECT a FROM AccountTransactions a WHERE a.actId = :actId and a.srvId is null"),
    @NamedQuery(name = "AccountTransactions.deleteByActId", query = "Delete FROM Guests g WHERE g.atrId in "
                                                                  + " ( SELECT a FROM AccountTransactions a WHERE a.actId = :actId and a.srvId is null ) ")})
public class AccountTransactions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ATR_ID", nullable = false)
    private Integer atrId;
    @Basic(optional = false)
    @Column(name = "ATR_QUANTITY", nullable = false)
    private int atrQuantity;
    @Column(name = "ATR_NOTES", length = 250)
    private String atrNotes;
    @Basic(optional = false)
    @Column(name = "ATR_DTE_MOD", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date atrDteMod;
    @JoinColumn(name = "ACT_ID", referencedColumnName = "ACT_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Account actId;
    @JoinColumn(name = "COU_ID", referencedColumnName = "COU_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private CashOut couId;
    @JoinColumn(name = "ATR_STATUS", referencedColumnName = "MVA_KEY", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MultiValue atrStatus;
    @JoinColumn(name = "RMS_ID", referencedColumnName = "RMS_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Rooms rmsId;
    @JoinColumn(name = "SRV_ID", referencedColumnName = "SRV_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Services srvId;
    @JoinColumn(name = "ATR_USR_MOD", referencedColumnName = "USR_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users atrUsrMod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atrId", fetch = FetchType.EAGER)
    private Collection<Guests> guestsCollection;

    public AccountTransactions() {
    }

    public AccountTransactions(Integer atrId) {
        this.atrId = atrId;
    }

    public AccountTransactions(Integer atrId, int atrQuantity, Date atrDteMod) {
        this.atrId = atrId;
        this.atrQuantity = atrQuantity;
        this.atrDteMod = atrDteMod;
    }

    public Integer getAtrId() {
        return atrId;
    }

    public void setAtrId(Integer atrId) {
        this.atrId = atrId;
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

    public MultiValue getAtrStatus() {
        return atrStatus;
    }

    public void setAtrStatus(MultiValue atrStatus) {
        this.atrStatus = atrStatus;
    }

    public Rooms getRmsId() {
        return rmsId;
    }

    public void setRmsId(Rooms rmsId) {
        this.rmsId = rmsId;
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

    @XmlTransient
    public Collection<Guests> getGuestsCollection() {
        return guestsCollection;
    }

    public void setGuestsCollection(Collection<Guests> guestsCollection) {
        this.guestsCollection = guestsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (atrId != null ? atrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountTransactions)) {
            return false;
        }
        AccountTransactions other = (AccountTransactions) object;
        if ((this.atrId == null && other.atrId != null) || (this.atrId != null && !this.atrId.equals(other.atrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.boundary.AccountTransactions[ atrId=" + atrId + " ]";
    }
    
}
