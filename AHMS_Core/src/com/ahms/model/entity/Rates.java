/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "rates", catalog = "db_ahms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rates.findAll", query = "SELECT r FROM Rates r"),
    @NamedQuery(name = "Rates.findByRteId", query = "SELECT r FROM Rates r WHERE r.rteId = :rteId"),
    @NamedQuery(name = "Rates.findByRteDesc", query = "SELECT r FROM Rates r WHERE r.rteDesc = :rteDesc"),
    @NamedQuery(name = "Rates.findByRtePrice", query = "SELECT r FROM Rates r WHERE r.rtePrice = :rtePrice"),
    @NamedQuery(name = "Rates.findByRteDteMod", query = "SELECT r FROM Rates r WHERE r.rteDteMod = :rteDteMod")})
public class Rates implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RTE_ID", nullable = false)
    private Integer rteId;
    @Basic(optional = false)
    @Column(name = "RTE_DESC", nullable = false, length = 20)
    private String rteDesc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "RTE_PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal rtePrice;
    @Basic(optional = false)
    @Column(name = "RTE_DTE_MOD", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date rteDteMod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rteId", fetch = FetchType.EAGER)
    private Collection<Rooms> roomsCollection;
    @JoinColumn(name = "RTE_STATUS", referencedColumnName = "MVA_KEY", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MultiValue rteStatus;
    @JoinColumn(name = "RTE_USR_MOD", referencedColumnName = "USR_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users rteUsrMod;

    public Rates() {
    }

    public Rates(Integer rteId) {
        this.rteId = rteId;
    }

    public Rates(Integer rteId, String rteDesc, BigDecimal rtePrice, Date rteDteMod) {
        this.rteId = rteId;
        this.rteDesc = rteDesc;
        this.rtePrice = rtePrice;
        this.rteDteMod = rteDteMod;
    }

    public Integer getRteId() {
        return rteId;
    }

    public void setRteId(Integer rteId) {
        this.rteId = rteId;
    }

    public String getRteDesc() {
        return rteDesc;
    }

    public void setRteDesc(String rteDesc) {
        this.rteDesc = rteDesc;
    }

    public BigDecimal getRtePrice() {
        return rtePrice;
    }

    public void setRtePrice(BigDecimal rtePrice) {
        this.rtePrice = rtePrice;
    }

    public Date getRteDteMod() {
        return rteDteMod;
    }

    public void setRteDteMod(Date rteDteMod) {
        this.rteDteMod = rteDteMod;
    }

    @XmlTransient
    public Collection<Rooms> getRoomsCollection() {
        return roomsCollection;
    }

    public void setRoomsCollection(Collection<Rooms> roomsCollection) {
        this.roomsCollection = roomsCollection;
    }

    public MultiValue getRteStatus() {
        return rteStatus;
    }

    public void setRteStatus(MultiValue rteStatus) {
        this.rteStatus = rteStatus;
    }

    public Users getRteUsrMod() {
        return rteUsrMod;
    }

    public void setRteUsrMod(Users rteUsrMod) {
        this.rteUsrMod = rteUsrMod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rteId != null ? rteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rates)) {
            return false;
        }
        Rates other = (Rates) object;
        if ((this.rteId == null && other.rteId != null) || (this.rteId != null && !this.rteId.equals(other.rteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.boundary.Rates[ rteId=" + rteId + " ]";
    }
    
}
