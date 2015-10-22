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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rsoto
 */
@Entity
@Table(name = "payment_types", catalog = "DB_AHMS", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"PAY_CODE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentTypes.findAll", query = "SELECT p FROM PaymentTypes p"),
    @NamedQuery(name = "PaymentTypes.findByPayId", query = "SELECT p FROM PaymentTypes p WHERE p.payId = :payId"),
    @NamedQuery(name = "PaymentTypes.findByPayCode", query = "SELECT p FROM PaymentTypes p WHERE p.payCode = :payCode"),
    @NamedQuery(name = "PaymentTypes.findByPayDesc", query = "SELECT p FROM PaymentTypes p WHERE p.payDesc = :payDesc"),
    @NamedQuery(name = "PaymentTypes.findByPayStatus", query = "SELECT p FROM PaymentTypes p WHERE p.payStatus = :payStatus"),
    @NamedQuery(name = "PaymentTypes.findByPayUsrMod", query = "SELECT p FROM PaymentTypes p WHERE p.payUsrMod = :payUsrMod"),
    @NamedQuery(name = "PaymentTypes.findByPayDteMod", query = "SELECT p FROM PaymentTypes p WHERE p.payDteMod = :payDteMod")})
public class PaymentTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PAY_ID", nullable = false)
    private Integer payId;
    @Basic(optional = false)
    @Column(name = "PAY_CODE", nullable = false, length = 4)
    private String payCode;
    @Basic(optional = false)
    @Column(name = "PAY_DESC", nullable = false, length = 20)
    private String payDesc;
    @Basic(optional = false)
    @Column(name = "PAY_STATUS", nullable = false)
    private int payStatus;
    @Column(name = "PAY_USR_MOD", length = 6)
    private String payUsrMod;
    @Column(name = "PAY_DTE_MOD")
    @Temporal(TemporalType.DATE)
    private Date payDteMod;

    public PaymentTypes() {
    }

    public PaymentTypes(Integer payId) {
        this.payId = payId;
    }

    public PaymentTypes(Integer payId, String payCode, String payDesc, int payStatus) {
        this.payId = payId;
        this.payCode = payCode;
        this.payDesc = payDesc;
        this.payStatus = payStatus;
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

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayUsrMod() {
        return payUsrMod;
    }

    public void setPayUsrMod(String payUsrMod) {
        this.payUsrMod = payUsrMod;
    }

    public Date getPayDteMod() {
        return payDteMod;
    }

    public void setPayDteMod(Date payDteMod) {
        this.payDteMod = payDteMod;
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
        return "com.ahms.model.entitys.PaymentTypes[ payId=" + payId + " ]";
    }
    
}
