/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jorge
 */
@Entity
@Table(name = "preference_detail", catalog = "db_ahms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreferenceDetail.findAll", query = "SELECT p FROM PreferenceDetail p"),
    @NamedQuery(name = "PreferenceDetail.findByPrefId", query = "SELECT p FROM PreferenceDetail p WHERE p.prefId = :prefId"),
    @NamedQuery(name = "PreferenceDetail.findByPrefAmount", query = "SELECT p FROM PreferenceDetail p WHERE p.prefAmount = :prefAmount"),
    @NamedQuery(name = "PreferenceDetail.findByCus", query = "SELECT p FROM PreferenceDetail p WHERE p.cusId = :cusId and p.rmsId = :rmsId")
    })
public class PreferenceDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PREF_ID", nullable = false)
    private Integer prefId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "PREF_AMOUNT", nullable = false, precision = 11, scale = 2)
    private BigDecimal prefAmount;
    @JoinColumn(name = "CUS_ID", referencedColumnName = "CUS_ID", nullable = false)
    @ManyToOne(optional = false)
    private Customers cusId;
    @JoinColumn(name = "RMS_ID", referencedColumnName = "RMS_ID", nullable = false)
    @ManyToOne(optional = false)
    private Rooms rmsId;

    public PreferenceDetail() {
    }

    public PreferenceDetail(Integer prefId) {
        this.prefId = prefId;
    }

    public PreferenceDetail(Integer prefId, BigDecimal prefAmount) {
        this.prefId = prefId;
        this.prefAmount = prefAmount;
    }

    public Integer getPrefId() {
        return prefId;
    }

    public void setPrefId(Integer prefId) {
        this.prefId = prefId;
    }

    public BigDecimal getPrefAmount() {
        return prefAmount;
    }

    public void setPrefAmount(BigDecimal prefAmount) {
        this.prefAmount = prefAmount;
    }

    public Customers getCusId() {
        return cusId;
    }

    public void setCusId(Customers cusId) {
        this.cusId = cusId;
    }

    public Rooms getRmsId() {
        return rmsId;
    }

    public void setRmsId(Rooms rmsId) {
        this.rmsId = rmsId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prefId != null ? prefId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreferenceDetail)) {
            return false;
        }
        PreferenceDetail other = (PreferenceDetail) object;
        if ((this.prefId == null && other.prefId != null) || (this.prefId != null && !this.prefId.equals(other.prefId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entity.PreferenceDetail[ prefId=" + prefId + " ]";
    }
    
}
