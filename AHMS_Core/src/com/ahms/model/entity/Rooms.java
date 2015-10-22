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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "rooms", catalog = "DB_AHMS", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"RMS_NUMBER"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r"),
    @NamedQuery(name = "Rooms.findByRmsId", query = "SELECT r FROM Rooms r WHERE r.rmsId = :rmsId"),
    @NamedQuery(name = "Rooms.findByRmsNumber", query = "SELECT r FROM Rooms r WHERE r.rmsNumber = :rmsNumber"),
    @NamedQuery(name = "Rooms.findByRmsBsnStatus", query = "SELECT r FROM Rooms r WHERE r.rmsBsnStatus = :rmsBsnStatus"),
    @NamedQuery(name = "Rooms.findByRmsStatus", query = "SELECT r FROM Rooms r WHERE r.rmsStatus = :rmsStatus"),
    @NamedQuery(name = "Rooms.findByRmsUsrMod", query = "SELECT r FROM Rooms r WHERE r.rmsUsrMod = :rmsUsrMod"),
    @NamedQuery(name = "Rooms.findByRmsDteMod", query = "SELECT r FROM Rooms r WHERE r.rmsDteMod = :rmsDteMod")})
public class Rooms implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RMS_ID", nullable = false)
    private Integer rmsId;
    @Basic(optional = false)
    @Column(name = "RMS_NUMBER", nullable = false, length = 4)
    private String rmsNumber;
    @Basic(optional = false)
    @Column(name = "RMS_BSN_STATUS", nullable = false)
    private int rmsBsnStatus;
    @Basic(optional = false)
    @Column(name = "RMS_STATUS", nullable = false)
    private int rmsStatus;
    @Column(name = "RMS_USR_MOD", length = 6)
    private String rmsUsrMod;
    @Column(name = "RMS_DTE_MOD")
    @Temporal(TemporalType.DATE)
    private Date rmsDteMod;
    @JoinColumn(name = "FLR_ID", referencedColumnName = "FLR_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Floors flrId;

    public Rooms() {
    }

    public Rooms(Integer rmsId) {
        this.rmsId = rmsId;
    }

    public Rooms(Integer rmsId, String rmsNumber, int rmsBsnStatus, int rmsStatus) {
        this.rmsId = rmsId;
        this.rmsNumber = rmsNumber;
        this.rmsBsnStatus = rmsBsnStatus;
        this.rmsStatus = rmsStatus;
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

    public int getRmsBsnStatus() {
        return rmsBsnStatus;
    }

    public void setRmsBsnStatus(int rmsBsnStatus) {
        this.rmsBsnStatus = rmsBsnStatus;
    }

    public int getRmsStatus() {
        return rmsStatus;
    }

    public void setRmsStatus(int rmsStatus) {
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

    public Floors getFlrId() {
        return flrId;
    }

    public void setFlrId(Floors flrId) {
        this.flrId = flrId;
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
        return "com.ahms.model.entitys.Rooms[ rmsId=" + rmsId + " ]";
    }
    
}
