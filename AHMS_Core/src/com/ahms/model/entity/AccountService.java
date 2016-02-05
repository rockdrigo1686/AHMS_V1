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
@Table(name = "account_service", catalog = "db_ahms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountService.findAll", query = "SELECT a FROM AccountService a"),
    @NamedQuery(name = "AccountService.findByAseId", query = "SELECT a FROM AccountService a WHERE a.aseId = :aseId"),
    @NamedQuery(name = "AccountService.findByAseTotal", query = "SELECT a FROM AccountService a WHERE a.aseTotal = :aseTotal"),
    @NamedQuery(name = "AccountService.findByAseAmount1", query = "SELECT a FROM AccountService a WHERE a.aseAmount1 = :aseAmount1"),
    @NamedQuery(name = "AccountService.findByAseFolio1", query = "SELECT a FROM AccountService a WHERE a.aseFolio1 = :aseFolio1"),
    @NamedQuery(name = "AccountService.findByAseAmount2", query = "SELECT a FROM AccountService a WHERE a.aseAmount2 = :aseAmount2"),
    @NamedQuery(name = "AccountService.findByAseFolio2", query = "SELECT a FROM AccountService a WHERE a.aseFolio2 = :aseFolio2"),
    @NamedQuery(name = "AccountService.findByAseAmount3", query = "SELECT a FROM AccountService a WHERE a.aseAmount3 = :aseAmount3"),
    @NamedQuery(name = "AccountService.findByAseFolio3", query = "SELECT a FROM AccountService a WHERE a.aseFolio3 = :aseFolio3"),
    @NamedQuery(name = "AccountService.findByAseUsrMod", query = "SELECT a FROM AccountService a WHERE a.aseUsrMod = :aseUsrMod"),
    @NamedQuery(name = "AccountService.findByAseDteMod", query = "SELECT a FROM AccountService a WHERE a.aseDteMod = :aseDteMod"),
    @NamedQuery(name = "AccountService.findByAseIva", query = "SELECT a FROM AccountService a WHERE a.aseIva = :aseIva"),
    @NamedQuery(name = "AccountService.findByAseIvaAmt", query = "SELECT a FROM AccountService a WHERE a.aseIvaAmt = :aseIvaAmt"),
    @NamedQuery(name = "AccountService.findByAseSubtotal", query = "SELECT a FROM AccountService a WHERE a.aseSubtotal = :aseSubtotal")})
public class AccountService implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ase_id", nullable = false)
    private Integer aseId;
    @Basic(optional = false)
    @Column(name = "ase_total", nullable = false)
    private long aseTotal;
    @Column(name = "ase_amount1")
    private Long aseAmount1;
    @Column(name = "ase_folio1", length = 15)
    private String aseFolio1;
    @Column(name = "ase_amount2")
    private Long aseAmount2;
    @Column(name = "ase_folio2", length = 15)
    private String aseFolio2;
    @Column(name = "ase_amount3")
    private Long aseAmount3;
    @Column(name = "ase_folio3", length = 15)
    private String aseFolio3;
    @Column(name = "ase_usr_mod", length = 6)
    private String aseUsrMod;
    @Column(name = "ase_dte_mod")
    @Temporal(TemporalType.DATE)
    private Date aseDteMod;
    @Column(name = "ase_iva")
    private Long aseIva;
    @Column(name = "ase_iva_amt")
    private Long aseIvaAmt;
    @Column(name = "ase_subtotal")
    private Long aseSubtotal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountService", fetch = FetchType.EAGER)
    private Collection<RoomService> roomServiceCollection;
    @JoinColumn(name = "act_id", referencedColumnName = "act_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Account actId;
    @JoinColumn(name = "pay_id1", referencedColumnName = "PAY_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private PaymentTypes payId1;
    @JoinColumn(name = "pay_id2", referencedColumnName = "PAY_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private PaymentTypes payId2;
    @JoinColumn(name = "pay_id3", referencedColumnName = "PAY_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private PaymentTypes payId3;

    public AccountService() {
    }

    public AccountService(Integer aseId) {
        this.aseId = aseId;
    }

    public AccountService(Integer aseId, long aseTotal) {
        this.aseId = aseId;
        this.aseTotal = aseTotal;
    }

    public Integer getAseId() {
        return aseId;
    }

    public void setAseId(Integer aseId) {
        this.aseId = aseId;
    }

    public long getAseTotal() {
        return aseTotal;
    }

    public void setAseTotal(long aseTotal) {
        this.aseTotal = aseTotal;
    }

    public Long getAseAmount1() {
        return aseAmount1;
    }

    public void setAseAmount1(Long aseAmount1) {
        this.aseAmount1 = aseAmount1;
    }

    public String getAseFolio1() {
        return aseFolio1;
    }

    public void setAseFolio1(String aseFolio1) {
        this.aseFolio1 = aseFolio1;
    }

    public Long getAseAmount2() {
        return aseAmount2;
    }

    public void setAseAmount2(Long aseAmount2) {
        this.aseAmount2 = aseAmount2;
    }

    public String getAseFolio2() {
        return aseFolio2;
    }

    public void setAseFolio2(String aseFolio2) {
        this.aseFolio2 = aseFolio2;
    }

    public Long getAseAmount3() {
        return aseAmount3;
    }

    public void setAseAmount3(Long aseAmount3) {
        this.aseAmount3 = aseAmount3;
    }

    public String getAseFolio3() {
        return aseFolio3;
    }

    public void setAseFolio3(String aseFolio3) {
        this.aseFolio3 = aseFolio3;
    }

    public String getAseUsrMod() {
        return aseUsrMod;
    }

    public void setAseUsrMod(String aseUsrMod) {
        this.aseUsrMod = aseUsrMod;
    }

    public Date getAseDteMod() {
        return aseDteMod;
    }

    public void setAseDteMod(Date aseDteMod) {
        this.aseDteMod = aseDteMod;
    }

    public Long getAseIva() {
        return aseIva;
    }

    public void setAseIva(Long aseIva) {
        this.aseIva = aseIva;
    }

    public Long getAseIvaAmt() {
        return aseIvaAmt;
    }

    public void setAseIvaAmt(Long aseIvaAmt) {
        this.aseIvaAmt = aseIvaAmt;
    }

    public Long getAseSubtotal() {
        return aseSubtotal;
    }

    public void setAseSubtotal(Long aseSubtotal) {
        this.aseSubtotal = aseSubtotal;
    }

    @XmlTransient
    public Collection<RoomService> getRoomServiceCollection() {
        return roomServiceCollection;
    }

    public void setRoomServiceCollection(Collection<RoomService> roomServiceCollection) {
        this.roomServiceCollection = roomServiceCollection;
    }

    public Account getActId() {
        return actId;
    }

    public void setActId(Account actId) {
        this.actId = actId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aseId != null ? aseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountService)) {
            return false;
        }
        AccountService other = (AccountService) object;
        if ((this.aseId == null && other.aseId != null) || (this.aseId != null && !this.aseId.equals(other.aseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entity.AccountService[ aseId=" + aseId + " ]";
    }
    
}
