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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rsoto
 */
@Entity
@Table(name = "payment_types", catalog = "db_ahms", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"PAY_CODE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentTypes.findAll", query = "SELECT p FROM PaymentTypes p"),
    @NamedQuery(name = "PaymentTypes.findByPayId", query = "SELECT p FROM PaymentTypes p WHERE p.payId = :payId"),
    @NamedQuery(name = "PaymentTypes.findByPayCode", query = "SELECT p FROM PaymentTypes p WHERE p.payCode = :payCode"),
    @NamedQuery(name = "PaymentTypes.findByPayDesc", query = "SELECT p FROM PaymentTypes p WHERE p.payDesc = :payDesc"),
    @NamedQuery(name = "PaymentTypes.findByPayDteMod", query = "SELECT p FROM PaymentTypes p WHERE p.payDteMod = :payDteMod")})
public class PaymentTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PAY_ID", nullable = false)
    private Integer payId;
    @Basic(optional = false)
    @Column(name = "PAY_CODE", nullable = false, length = 5)
    private String payCode;
    @Basic(optional = false)
    @Column(name = "PAY_DESC", nullable = false, length = 150)
    private String payDesc;
    @Basic(optional = false)
    @Column(name = "PAY_DTE_MOD", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date payDteMod;
    @JoinColumn(name = "PAY_STATUS", referencedColumnName = "MVA_KEY", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MultiValue payStatus;
    @JoinColumn(name = "PAY_USR_MOD", referencedColumnName = "USR_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users payUsrMod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "payId", fetch = FetchType.EAGER)
    private Collection<FolioTransaction> folioTransactionCollection;

    public PaymentTypes() {
    }

    public PaymentTypes(Integer payId) {
        this.payId = payId;
    }

    public PaymentTypes(Integer payId, String payCode, String payDesc, Date payDteMod) {
        this.payId = payId;
        this.payCode = payCode;
        this.payDesc = payDesc;
        this.payDteMod = payDteMod;
    }

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayDesc() {
        return payDesc;
    }

    public void setPayDesc(String payDesc) {
        this.payDesc = payDesc;
    }

    public Date getPayDteMod() {
        return payDteMod;
    }

    public void setPayDteMod(Date payDteMod) {
        this.payDteMod = payDteMod;
    }

    public MultiValue getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(MultiValue payStatus) {
        this.payStatus = payStatus;
    }

    public Users getPayUsrMod() {
        return payUsrMod;
    }

    public void setPayUsrMod(Users payUsrMod) {
        this.payUsrMod = payUsrMod;
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
        hash += (payId != null ? payId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentTypes)) {
            return false;
        }
        PaymentTypes other = (PaymentTypes) object;
        if ((this.payId == null && other.payId != null) || (this.payId != null && !this.payId.equals(other.payId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.boundary.PaymentTypes[ payId=" + payId + " ]";
    }
    
}
