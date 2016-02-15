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
 * @author rsoto
 */
@Entity
@Table(name = "room_types", catalog = "db_ahms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoomTypes.findAll", query = "SELECT r FROM RoomTypes r"),
    @NamedQuery(name = "RoomTypes.findActiveTypes", query = "SELECT r FROM RoomTypes r WHERE r.rtyStatus = :rtyStatus "),
    @NamedQuery(name = "RoomTypes.findByRtyId", query = "SELECT r FROM RoomTypes r WHERE r.rtyId = :rtyId"),
    @NamedQuery(name = "RoomTypes.findByRtyDescription", query = "SELECT r FROM RoomTypes r WHERE r.rtyDescription = :rtyDescription"),
    @NamedQuery(name = "RoomTypes.findByRtyBeds", query = "SELECT r FROM RoomTypes r WHERE r.rtyBeds = :rtyBeds"),
    @NamedQuery(name = "RoomTypes.findByRtyDteMod", query = "SELECT r FROM RoomTypes r WHERE r.rtyDteMod = :rtyDteMod")})
public class RoomTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RTY_ID", nullable = false)
    private Integer rtyId;
    @Basic(optional = false)
    @Column(name = "RTY_DESCRIPTION", nullable = false, length = 50)
    private String rtyDescription;
    @Basic(optional = false)
    @Column(name = "RTY_BEDS", nullable = false)
    private int rtyBeds;
    @Basic(optional = false)
    @Column(name = "RTY_DTE_MOD", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date rtyDteMod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rmsBeds", fetch = FetchType.EAGER)
    private Collection<Rooms> roomsCollection;
    @JoinColumn(name = "RTY_STATUS", referencedColumnName = "MVA_KEY", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MultiValue rtyStatus;
    @JoinColumn(name = "RTY_USR_MOD", referencedColumnName = "USR_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users rtyUsrMod;

    public RoomTypes() {
    }

    public RoomTypes(Integer rtyId) {
        this.rtyId = rtyId;
    }

    public RoomTypes(Integer rtyId, String rtyDescription, int rtyBeds, Date rtyDteMod) {
        this.rtyId = rtyId;
        this.rtyDescription = rtyDescription;
        this.rtyBeds = rtyBeds;
        this.rtyDteMod = rtyDteMod;
    }

    public Integer getRtyId() {
        return rtyId;
    }

    public void setRtyId(Integer rtyId) {
        this.rtyId = rtyId;
    }

    public String getRtyDescription() {
        return rtyDescription;
    }

    public void setRtyDescription(String rtyDescription) {
        this.rtyDescription = rtyDescription;
    }

    public int getRtyBeds() {
        return rtyBeds;
    }

    public void setRtyBeds(int rtyBeds) {
        this.rtyBeds = rtyBeds;
    }

    public Date getRtyDteMod() {
        return rtyDteMod;
    }

    public void setRtyDteMod(Date rtyDteMod) {
        this.rtyDteMod = rtyDteMod;
    }

    @XmlTransient
    public Collection<Rooms> getRoomsCollection() {
        return roomsCollection;
    }

    public void setRoomsCollection(Collection<Rooms> roomsCollection) {
        this.roomsCollection = roomsCollection;
    }

    public MultiValue getRtyStatus() {
        return rtyStatus;
    }

    public void setRtyStatus(MultiValue rtyStatus) {
        this.rtyStatus = rtyStatus;
    }

    public Users getRtyUsrMod() {
        return rtyUsrMod;
    }

    public void setRtyUsrMod(Users rtyUsrMod) {
        this.rtyUsrMod = rtyUsrMod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rtyId != null ? rtyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomTypes)) {
            return false;
        }
        RoomTypes other = (RoomTypes) object;
        if ((this.rtyId == null && other.rtyId != null) || (this.rtyId != null && !this.rtyId.equals(other.rtyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.boundary.RoomTypes[ rtyId=" + rtyId + " ]";
    }
    
}
