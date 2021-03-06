/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.entity;

import com.ahms.util.MMKeys;
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
@Table(name = "floors", catalog = "db_ahms", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"FLR_CODE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Floors.findAll", query = "SELECT f FROM Floors f "),
    @NamedQuery(name = "Floors.findAllAvailable", query = "SELECT f FROM Floors f WHERE f.flrStatus = :flrStatus "),
    @NamedQuery(name = "Floors.findByFlrId", query = "SELECT f FROM Floors f WHERE f.flrId = :flrId"),
    @NamedQuery(name = "Floors.findByFlrCode", query = "SELECT f FROM Floors f WHERE f.flrCode = :flrCode"),
    @NamedQuery(name = "Floors.findByFlrDteMod", query = "SELECT f FROM Floors f WHERE f.flrDteMod = :flrDteMod")})
public class Floors implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FLR_ID", nullable = false)
    private Integer flrId;
    @Basic(optional = false)
    @Column(name = "FLR_CODE", nullable = false, length = 5)
    private String flrCode;
    @Basic(optional = false)
    @Column(name = "FLR_DTE_MOD", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date flrDteMod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flrId", fetch = FetchType.EAGER)
    private Collection<Rooms> roomsCollection;
    @JoinColumn(name = "FLR_STATUS", referencedColumnName = "MVA_KEY", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MultiValue flrStatus;
    @JoinColumn(name = "FLR_USR_MOD", referencedColumnName = "USR_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users flrUsrMod;

    public Floors() {
    }

    public Floors(Integer flrId) {
        this.flrId = flrId;
    }

    public Floors(Integer flrId, String flrCode, Date flrDteMod) {
        this.flrId = flrId;
        this.flrCode = flrCode;
        this.flrDteMod = flrDteMod;
    }

    public Integer getFlrId() {
        return flrId;
    }

    public void setFlrId(Integer flrId) {
        this.flrId = flrId;
    }

    public String getFlrCode() {
        return flrCode;
    }

    public void setFlrCode(String flrCode) {
        this.flrCode = flrCode;
    }

    public Date getFlrDteMod() {
        return flrDteMod;
    }

    public void setFlrDteMod(Date flrDteMod) {
        this.flrDteMod = flrDteMod;
    }

    @XmlTransient
    public Collection<Rooms> getRoomsCollection() {
        return roomsCollection;
    }

    public void setRoomsCollection(Collection<Rooms> roomsCollection) {
        this.roomsCollection = roomsCollection;
    }

    public MultiValue getFlrStatus() {
        return flrStatus;
    }

    public void setFlrStatus(MultiValue flrStatus) {
        this.flrStatus = flrStatus;
    }

    public Users getFlrUsrMod() {
        return flrUsrMod;
    }

    public void setFlrUsrMod(Users flrUsrMod) {
        this.flrUsrMod = flrUsrMod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flrId != null ? flrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Floors)) {
            return false;
        }
        Floors other = (Floors) object;
        if ((this.flrId == null && other.flrId != null) || (this.flrId != null && !this.flrId.equals(other.flrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getFlrCode();
    }
    
}
