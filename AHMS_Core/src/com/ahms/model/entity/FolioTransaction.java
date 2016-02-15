/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rsoto
 */
@Entity
@Table(name = "folio_transaction", catalog = "db_ahms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FolioTransaction.findAll", query = "SELECT f FROM FolioTransaction f"),
    @NamedQuery(name = "FolioTransaction.findByFtrId", query = "SELECT f FROM FolioTransaction f WHERE f.ftrId = :ftrId"),
    @NamedQuery(name = "FolioTransaction.findByFtrFolio", query = "SELECT f FROM FolioTransaction f WHERE f.ftrFolio = :ftrFolio"),
    @NamedQuery(name = "FolioTransaction.findByFtrAmount", query = "SELECT f FROM FolioTransaction f WHERE f.ftrAmount = :ftrAmount"),
    @NamedQuery(name = "FolioTransaction.findByFtrCardNumber", query = "SELECT f FROM FolioTransaction f WHERE f.ftrCardNumber = :ftrCardNumber"),
    @NamedQuery(name = "FolioTransaction.findByFtrDteMod", query = "SELECT f FROM FolioTransaction f WHERE f.ftrDteMod = :ftrDteMod"),
    @NamedQuery(name = "FolioTransaction.findByCouId", query= "SELECT f FROM FolioTransaction f WHERE f.couId = :couId")})
public class FolioTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FTR_ID", nullable = false)
    private Integer ftrId;
    @Column(name = "FTR_FOLIO", length = 50)
    private String ftrFolio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "FTR_AMOUNT", nullable = false, precision = 10, scale = 2)
    private BigDecimal ftrAmount;
    @Column(name = "FTR_CARD_NUMBER", length = 4)
    private String ftrCardNumber;
    @Basic(optional = false)
    @Column(name = "FTR_DTE_MOD", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ftrDteMod;
    @JoinColumn(name = "ACT_ID", referencedColumnName = "ACT_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Account actId;
    @JoinColumn(name = "COU_ID", referencedColumnName = "COU_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private CashOut couId;
    @JoinColumn(name = "PAY_ID", referencedColumnName = "PAY_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private PaymentTypes payId;
    @JoinColumn(name = "FTR_USR_MOD", referencedColumnName = "USR_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users ftrUsrMod;

    public FolioTransaction() {
    }

    public FolioTransaction(Integer ftrId) {
        this.ftrId = ftrId;
    }

    public FolioTransaction(Integer ftrId, BigDecimal ftrAmount, Date ftrDteMod) {
        this.ftrId = ftrId;
        this.ftrAmount = ftrAmount;
        this.ftrDteMod = ftrDteMod;
    }

    public Integer getFtrId() {
        return ftrId;
    }

    public void setFtrId(Integer ftrId) {
        this.ftrId = ftrId;
    }

    public String getFtrFolio() {
        return ftrFolio;
    }

    public void setFtrFolio(String ftrFolio) {
        this.ftrFolio = ftrFolio;
    }

    public BigDecimal getFtrAmount() {
        return ftrAmount;
    }

    public void setFtrAmount(BigDecimal ftrAmount) {
        this.ftrAmount = ftrAmount;
    }

    public String getFtrCardNumber() {
        return ftrCardNumber;
    }

    public void setFtrCardNumber(String ftrCardNumber) {
        this.ftrCardNumber = ftrCardNumber;
    }

    public Date getFtrDteMod() {
        return ftrDteMod;
    }

    public void setFtrDteMod(Date ftrDteMod) {
        this.ftrDteMod = ftrDteMod;
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

    public PaymentTypes getPayId() {
        return payId;
    }

    public void setPayId(PaymentTypes payId) {
        this.payId = payId;
    }

    public Users getFtrUsrMod() {
        return ftrUsrMod;
    }

    public void setFtrUsrMod(Users ftrUsrMod) {
        this.ftrUsrMod = ftrUsrMod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ftrId != null ? ftrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FolioTransaction)) {
            return false;
        }
        FolioTransaction other = (FolioTransaction) object;
        if ((this.ftrId == null && other.ftrId != null) || (this.ftrId != null && !this.ftrId.equals(other.ftrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.boundary.FolioTransaction[ ftrId=" + ftrId + " ]";
    }

}
