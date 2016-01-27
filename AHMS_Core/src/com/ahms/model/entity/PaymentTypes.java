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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @Column(name = "PAY_CODE", nullable = false, length = 5)
    private String payCode;
    @Basic(optional = false)
    @Column(name = "PAY_DESC", nullable = false, length = 20)
    private String payDesc;
    @Basic(optional = false)
    @Column(name = "PAY_STATUS", nullable = false, length = 10)
    private String payStatus;
    @Column(name = "PAY_USR_MOD", length = 6)
    private String payUsrMod;
    @Column(name = "PAY_DTE_MOD")
    @Temporal(TemporalType.DATE)
    private Date payDteMod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "payId1")
    private Collection<AccountService> accountServiceCollection;
    @OneToMany(mappedBy = "payId2")
    private Collection<AccountService> accountServiceCollection1;
    @OneToMany(mappedBy = "payId3")
    private Collection<AccountService> accountServiceCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "payId1")
    private Collection<Account> accountCollection;
    @OneToMany(mappedBy = "payId2")
    private Collection<Account> accountCollection1;
    @OneToMany(mappedBy = "payId3")
    private Collection<Account> accountCollection2;

    public PaymentTypes() {
    }

    public PaymentTypes(Integer payId) {
        this.payId = payId;
    }

    public PaymentTypes(Integer payId, String payCode, String payDesc, String payStatus) {
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

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
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

    @XmlTransient
    public Collection<AccountService> getAccountServiceCollection() {
        return accountServiceCollection;
    }

    public void setAccountServiceCollection(Collection<AccountService> accountServiceCollection) {
        this.accountServiceCollection = accountServiceCollection;
    }

    @XmlTransient
    public Collection<AccountService> getAccountServiceCollection1() {
        return accountServiceCollection1;
    }

    public void setAccountServiceCollection1(Collection<AccountService> accountServiceCollection1) {
        this.accountServiceCollection1 = accountServiceCollection1;
    }

    @XmlTransient
    public Collection<AccountService> getAccountServiceCollection2() {
        return accountServiceCollection2;
    }

    public void setAccountServiceCollection2(Collection<AccountService> accountServiceCollection2) {
        this.accountServiceCollection2 = accountServiceCollection2;
    }

    @XmlTransient
    public Collection<Account> getAccountCollection() {
        return accountCollection;
    }

    public void setAccountCollection(Collection<Account> accountCollection) {
        this.accountCollection = accountCollection;
    }

    @XmlTransient
    public Collection<Account> getAccountCollection1() {
        return accountCollection1;
    }

    public void setAccountCollection1(Collection<Account> accountCollection1) {
        this.accountCollection1 = accountCollection1;
    }

    @XmlTransient
    public Collection<Account> getAccountCollection2() {
        return accountCollection2;
    }

    public void setAccountCollection2(Collection<Account> accountCollection2) {
        this.accountCollection2 = accountCollection2;
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
        return "com.ahms.model.entity.PaymentTypes[ payId=" + payId + " ]";
    }
    
}
