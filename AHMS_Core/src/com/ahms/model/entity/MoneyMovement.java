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
@Table(name = "money_movement", catalog = "db_ahms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MoneyMovement.findAll", query = "SELECT m FROM MoneyMovement m"),
    @NamedQuery(name = "MoneyMovement.findByMmoId", query = "SELECT m FROM MoneyMovement m WHERE m.mmoId = :mmoId"),
    @NamedQuery(name = "MoneyMovement.findByMmoDescription", query = "SELECT m FROM MoneyMovement m WHERE m.mmoDescription = :mmoDescription"),
    @NamedQuery(name = "MoneyMovement.findByMmoCasOut", query = "SELECT m FROM MoneyMovement m WHERE m.mmoCasOut = :mmoCasOut"),
    @NamedQuery(name = "MoneyMovement.findByMmoCasIn", query = "SELECT m FROM MoneyMovement m WHERE m.mmoCasIn = :mmoCasIn"),
    @NamedQuery(name = "MoneyMovement.findByCouId", query = "SELECT m FROM MoneyMovement m WHERE m.couId = :couId"),
    @NamedQuery(name = "MoneyMovement.findByMmoDteMod", query = "SELECT m FROM MoneyMovement m WHERE m.mmoDteMod = :mmoDteMod")})
public class MoneyMovement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "mmo_id", nullable = false)
    private Integer mmoId;
    @Column(name = "mmo_description", length = 150)
    private String mmoDescription;
    @Column(name = "mmo_cas_out")
    private Long mmoCasOut;
    @Column(name = "mmo_cas_in")
    private Long mmoCasIn;
    @Basic(optional = false)
    @Column(name = "mmo_dte_mod", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date mmoDteMod;
    @JoinColumn(name = "cou_id", referencedColumnName = "cou_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CashOut couId;
    @JoinColumn(name = "mmo_usr_mod", referencedColumnName = "usr_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users mmoUsrMod;

    public MoneyMovement() {
    }

    public MoneyMovement(Integer mmoId) {
        this.mmoId = mmoId;
    }

    public MoneyMovement(Integer mmoId, Date mmoDteMod) {
        this.mmoId = mmoId;
        this.mmoDteMod = mmoDteMod;
    }

    public Integer getMmoId() {
        return mmoId;
    }

    public void setMmoId(Integer mmoId) {
        this.mmoId = mmoId;
    }

    public String getMmoDescription() {
        return mmoDescription;
    }

    public void setMmoDescription(String mmoDescription) {
        this.mmoDescription = mmoDescription;
    }

    public Long getMmoCasOut() {
        return mmoCasOut;
    }

    public void setMmoCasOut(Long mmoCasOut) {
        this.mmoCasOut = mmoCasOut;
    }

    public Long getMmoCasIn() {
        return mmoCasIn;
    }

    public void setMmoCasIn(Long mmoCasIn) {
        this.mmoCasIn = mmoCasIn;
    }

    public Date getMmoDteMod() {
        return mmoDteMod;
    }

    public void setMmoDteMod(Date mmoDteMod) {
        this.mmoDteMod = mmoDteMod;
    }

    public CashOut getCouId() {
        return couId;
    }

    public void setCouId(CashOut couId) {
        this.couId = couId;
    }

    public Users getMmoUsrMod() {
        return mmoUsrMod;
    }

    public void setMmoUsrMod(Users mmoUsrMod) {
        this.mmoUsrMod = mmoUsrMod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mmoId != null ? mmoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MoneyMovement)) {
            return false;
        }
        MoneyMovement other = (MoneyMovement) object;
        if ((this.mmoId == null && other.mmoId != null) || (this.mmoId != null && !this.mmoId.equals(other.mmoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entity.MoneyMovement[ mmoId=" + mmoId + " ]";
    }
    
}
