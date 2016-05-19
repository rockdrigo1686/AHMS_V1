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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rsoto
 */
@Entity
@Table(name = "reservation", catalog = "db_ahms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
    @NamedQuery(name = "Reservation.findByResId", query = "SELECT r FROM Reservation r WHERE r.resId = :resId"),
    @NamedQuery(name = "Reservation.findByResFecIni", query = "SELECT r FROM Reservation r WHERE r.resFecIni = :resFecIni"),
    @NamedQuery(name = "Reservation.findByResFecFin", query = "SELECT r FROM Reservation r WHERE r.resFecFin = :resFecFin"),
    @NamedQuery(name = "Reservation.findByRmsId", query = "SELECT r FROM Reservation r WHERE r.rmsId = :rmsId AND r.resStatus =:status AND r.resFecIni >= :date"),
    @NamedQuery(name = "Reservation.checkReservation", query = "SELECT r FROM Reservation r WHERE r.resFecIni < :date AND r.resStatus = :status"),
    @NamedQuery(name = "Reservation.findByCusId", query = "SELECT r FROM Reservation r WHERE r.cusId = :cusId AND r.resStatus = :status"),
    @NamedQuery(name = "Reservation.findByResDteMod", query = "SELECT r FROM Reservation r WHERE r.resDteMod = :resDteMod")})
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RES_ID", nullable = false)
    private Integer resId;
    @Basic(optional = false)
    @Column(name = "RES_FEC_INI", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date resFecIni;
    @Basic(optional = false)
    @Column(name = "RES_FEC_FIN", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date resFecFin;
    @Basic(optional = false)
    @Column(name = "RES_DTE_MOD", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date resDteMod;
    @JoinColumn(name = "CUS_ID", referencedColumnName = "CUS_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Customers cusId;
    @JoinColumn(name = "RES_STATUS", referencedColumnName = "MVA_KEY", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MultiValue resStatus;
    @JoinColumn(name = "RMS_ID", referencedColumnName = "RMS_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Rooms rmsId;
    @JoinColumn(name = "RES_USR_MOD", referencedColumnName = "USR_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users resUsrMod;

    public Reservation() {
    }

    public Reservation(Integer resId) {
        this.resId = resId;
    }

    public Reservation(Integer resId, Date resFecIni, Date resFecFin, Date resDteMod) {
        this.resId = resId;
        this.resFecIni = resFecIni;
        this.resFecFin = resFecFin;
        this.resDteMod = resDteMod;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public Date getResFecIni() {
        return resFecIni;
    }

    public void setResFecIni(Date resFecIni) {
        this.resFecIni = resFecIni;
    }

    public Date getResFecFin() {
        return resFecFin;
    }

    public void setResFecFin(Date resFecFin) {
        this.resFecFin = resFecFin;
    }

    public Date getResDteMod() {
        return resDteMod;
    }

    public void setResDteMod(Date resDteMod) {
        this.resDteMod = resDteMod;
    }

    public Customers getCusId() {
        return cusId;
    }

    public void setCusId(Customers cusId) {
        this.cusId = cusId;
    }

    public MultiValue getResStatus() {
        return resStatus;
    }

    public void setResStatus(MultiValue resStatus) {
        this.resStatus = resStatus;
    }

    public Rooms getRmsId() {
        return rmsId;
    }

    public void setRmsId(Rooms rmsId) {
        this.rmsId = rmsId;
    }

    public Users getResUsrMod() {
        return resUsrMod;
    }

    public void setResUsrMod(Users resUsrMod) {
        this.resUsrMod = resUsrMod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resId != null ? resId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.resId == null && other.resId != null) || (this.resId != null && !this.resId.equals(other.resId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.boundary.Reservation[ resId=" + resId + " ]";
    }
    
}
