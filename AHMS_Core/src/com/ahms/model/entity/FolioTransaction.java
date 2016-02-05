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
 * @author jorge
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
    @NamedQuery(name = "FolioTransaction.findByFtrDteMod", query = "SELECT f FROM FolioTransaction f WHERE f.ftrDteMod = :ftrDteMod")})
public class FolioTransaction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ftr_id", nullable = false)
    private Integer ftrId;
    @Basic(optional = false)
    @Column(name = "ftr_folio", nullable = false, length = 50)
    private String ftrFolio;
    @Basic(optional = false)
    @Column(name = "ftr_amount", nullable = false)
    private long ftrAmount;
    @Basic(optional = false)
    @Column(name = "ftr_card_number", nullable = false, length = 4)
    private String ftrCardNumber;
    @Basic(optional = false)
    @Column(name = "ftr_dte_mod", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ftrDteMod;
    @JoinColumn(name = "act_id", referencedColumnName = "act_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Account actId;
    @JoinColumn(name = "pay_id", referencedColumnName = "PAY_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private PaymentTypes payId;
    @JoinColumn(name = "ftr_usr_mod", referencedColumnName = "usr_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users ftrUsrMod;

    public FolioTransaction() {
    }

    public FolioTransaction(Integer ftrId) {
        this.ftrId = ftrId;
    }

    public FolioTransaction(Integer ftrId, String ftrFolio, long ftrAmount, String ftrCardNumber, Date ftrDteMod) {
        this.ftrId = ftrId;
        this.ftrFolio = ftrFolio;
        this.ftrAmount = ftrAmount;
        this.ftrCardNumber = ftrCardNumber;
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

    public long getFtrAmount() {
        return ftrAmount;
    }

    public void setFtrAmount(long ftrAmount) {
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
        return "com.ahms.model.entity.FolioTransaction[ ftrId=" + ftrId + " ]";
    }
    
}
