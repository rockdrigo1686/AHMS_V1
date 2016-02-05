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
 * @author jorge
 */
@Entity
@Table(name = "rooms", catalog = "db_ahms", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"RMS_NUMBER"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r"),
    @NamedQuery(name = "Rooms.findByRmsId", query = "SELECT r FROM Rooms r WHERE r.rmsId = :rmsId"),
    @NamedQuery(name = "Rooms.findByRmsNumber", query = "SELECT r FROM Rooms r WHERE r.rmsNumber = :rmsNumber"),
    @NamedQuery(name = "Rooms.findByRmsBeds", query = "SELECT r FROM Rooms r WHERE r.rmsBeds = :rmsBeds"),
    @NamedQuery(name = "Rooms.findByRmsStatus", query = "SELECT r FROM Rooms r WHERE r.rmsStatus = :rmsStatus"),
    @NamedQuery(name = "Rooms.findByRmsUsrMod", query = "SELECT r FROM Rooms r WHERE r.rmsUsrMod = :rmsUsrMod"),
    @NamedQuery(name = "Rooms.findByRmsDteMod", query = "SELECT r FROM Rooms r WHERE r.rmsDteMod = :rmsDteMod"),
    @NamedQuery(name = "Rooms.findByRmsMaxOcu", query = "SELECT r FROM Rooms r WHERE r.rmsMaxOcu = :rmsMaxOcu"),
    @NamedQuery(name = "Rooms.findByRmsDesc", query = "SELECT r FROM Rooms r WHERE r.rmsDesc = :rmsDesc"),
    @NamedQuery(name = "Rooms.findByFlrId", query = "SELECT r FROM Rooms r WHERE r.flrId = :flrId")})
public class Rooms implements Serializable {
    //private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RMS_ID", nullable = false)
    private Integer rmsId;
    @Basic(optional = false)
    @Column(name = "RMS_NUMBER", nullable = false, length = 4)
    private String rmsNumber;
    @Basic(optional = false)
    @Column(name = "RMS_BEDS", nullable = false)
    private int rmsBeds;
    @Basic(optional = false)
    @Column(name = "RMS_STATUS", nullable = false, length = 20)
    private String rmsStatus;
    @Column(name = "RMS_USR_MOD", length = 6)
    private String rmsUsrMod;
    @Column(name = "RMS_DTE_MOD")
    @Temporal(TemporalType.DATE)
    private Date rmsDteMod;
    @Basic(optional = false)
    @Column(name = "RMS_MAX_OCU", nullable = false)
    private int rmsMaxOcu;
    @Basic(optional = false)
    @Column(name = "rms_desc", nullable = false, length = 200)
    private String rmsDesc;
    @JoinColumn(name = "FLR_ID", referencedColumnName = "FLR_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Floors flrId;
    @JoinColumn(name = "RTE_ID", referencedColumnName = "RTE_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Rates rteId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rmsId", fetch = FetchType.EAGER)
    private Collection<Reservation> reservationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rmsId", fetch = FetchType.EAGER)
    private Collection<Account> accountCollection;

    public Rooms() {
    }

    public Rooms(Integer rmsId) {
        this.rmsId = rmsId;
    }

    public Rooms(Integer rmsId, String rmsNumber, int rmsBeds, String rmsStatus, int rmsMaxOcu, String rmsDesc) {
        this.rmsId = rmsId;
        this.rmsNumber = rmsNumber;
        this.rmsBeds = rmsBeds;
        this.rmsStatus = rmsStatus;
        this.rmsMaxOcu = rmsMaxOcu;
        this.rmsDesc = rmsDesc;
    }

    public Integer getRmsId() {
        return rmsId;
    }

    public void setRmsId(Integer rmsId) {
        this.rmsId = rmsId;
    }

    public String getRmsNumber() {
        return rmsNumber;
    }

    public void setRmsNumber(String rmsNumber) {
        this.rmsNumber = rmsNumber;
    }

    public int getRmsBeds() {
        return rmsBeds;
    }

    public void setRmsBeds(int rmsBeds) {
        this.rmsBeds = rmsBeds;
    }

    public String getRmsStatus() {
        return rmsStatus;
    }

    public void setRmsStatus(String rmsStatus) {
        this.rmsStatus = rmsStatus;
    }

    public String getRmsUsrMod() {
        return rmsUsrMod;
    }

    public void setRmsUsrMod(String rmsUsrMod) {
        this.rmsUsrMod = rmsUsrMod;
    }

    public Date getRmsDteMod() {
        return rmsDteMod;
    }

    public void setRmsDteMod(Date rmsDteMod) {
        this.rmsDteMod = rmsDteMod;
    }

    public int getRmsMaxOcu() {
        return rmsMaxOcu;
    }

    public void setRmsMaxOcu(int rmsMaxOcu) {
        this.rmsMaxOcu = rmsMaxOcu;
    }

    public String getRmsDesc() {
        return rmsDesc;
    }

    public void setRmsDesc(String rmsDesc) {
        this.rmsDesc = rmsDesc;
    }

    public Floors getFlrId() {
        return flrId;
    }

    public void setFlrId(Floors flrId) {
        this.flrId = flrId;
    }

    public Rates getRteId() {
        return rteId;
    }

    public void setRteId(Rates rteId) {
        this.rteId = rteId;
    }

    @XmlTransient
    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
    }

    @XmlTransient
    public Collection<Account> getAccountCollection() {
        return accountCollection;
    }

    public void setAccountCollection(Collection<Account> accountCollection) {
        this.accountCollection = accountCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rmsId != null ? rmsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rooms)) {
            return false;
        }
        Rooms other = (Rooms) object;
        if ((this.rmsId == null && other.rmsId != null) || (this.rmsId != null && !this.rmsId.equals(other.rmsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entity.Rooms[ rmsId=" + rmsId + " ]";
    }
    
}
