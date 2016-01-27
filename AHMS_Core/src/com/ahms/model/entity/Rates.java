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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jorge
 */
@Entity
@Table(name = "rates", catalog = "DB_AHMS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rates.findAll", query = "SELECT r FROM Rates r"),
    @NamedQuery(name = "Rates.findByRteId", query = "SELECT r FROM Rates r WHERE r.rteId = :rteId"),
    @NamedQuery(name = "Rates.findByRteDesc", query = "SELECT r FROM Rates r WHERE r.rteDesc = :rteDesc"),
    @NamedQuery(name = "Rates.findByRtePrice", query = "SELECT r FROM Rates r WHERE r.rtePrice = :rtePrice"),
    @NamedQuery(name = "Rates.findByRteStatus", query = "SELECT r FROM Rates r WHERE r.rteStatus = :rteStatus"),
    @NamedQuery(name = "Rates.findByRteUsrMod", query = "SELECT r FROM Rates r WHERE r.rteUsrMod = :rteUsrMod"),
    @NamedQuery(name = "Rates.findByRteDteMod", query = "SELECT r FROM Rates r WHERE r.rteDteMod = :rteDteMod")})
public class Rates implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rteId")
    private Collection<Rooms> roomsCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RTE_ID", nullable = false)
    private Integer rteId;
    @Basic(optional = false)
    @Column(name = "RTE_DESC", nullable = false, length = 20)
    private String rteDesc;
    @Basic(optional = false)
    @Column(name = "RTE_PRICE", nullable = false)
    private long rtePrice;
    @Basic(optional = false)
    @Column(name = "RTE_STATUS", nullable = false)
    private int rteStatus;
    @Column(name = "RTE_USR_MOD", length = 6)
    private String rteUsrMod;
    @Column(name = "RTE_DTE_MOD")
    @Temporal(TemporalType.DATE)
    private Date rteDteMod;

    public Rates() {
    }

    public Rates(Integer rteId) {
        this.rteId = rteId;
    }

    public Rates(Integer rteId, String rteDesc, long rtePrice, int rteStatus) {
        this.rteId = rteId;
        this.rteDesc = rteDesc;
        this.rtePrice = rtePrice;
        this.rteStatus = rteStatus;
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

    public long getRtePrice() {
        return rtePrice;
    }

    public void setRtePrice(long rtePrice) {
        this.rtePrice = rtePrice;
    }

    public int getRteStatus() {
        return rteStatus;
    }

    public void setRteStatus(int rteStatus) {
        this.rteStatus = rteStatus;
    }

    public String getRteUsrMod() {
        return rteUsrMod;
    }

    public void setRteUsrMod(String rteUsrMod) {
        this.rteUsrMod = rteUsrMod;
    }

    public Date getRteDteMod() {
        return rteDteMod;
    }

    public void setRteDteMod(Date rteDteMod) {
        this.rteDteMod = rteDteMod;
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
        return "com.ahms.model.entity.Rates[ rteId=" + rteId + " ]";
    }

    @XmlTransient
    public Collection<Rooms> getRoomsCollection() {
        return roomsCollection;
    }

    public void setRoomsCollection(Collection<Rooms> roomsCollection) {
        this.roomsCollection = roomsCollection;
    }
    
}
