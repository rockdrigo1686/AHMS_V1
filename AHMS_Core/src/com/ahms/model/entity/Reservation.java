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
@Table(name = "reservation", catalog = "db_ahms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
    @NamedQuery(name = "Reservation.findByResId", query = "SELECT r FROM Reservation r WHERE r.resId = :resId"),
    @NamedQuery(name = "Reservation.findByResFecIni", query = "SELECT r FROM Reservation r WHERE r.resFecIni = :resFecIni"),
    @NamedQuery(name = "Reservation.findByResFecFin", query = "SELECT r FROM Reservation r WHERE r.resFecFin = :resFecFin"),
    @NamedQuery(name = "Reservation.findByResStatus", query = "SELECT r FROM Reservation r WHERE r.resStatus = :resStatus")})
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "res_id", nullable = false)
    private Integer resId;
    @Basic(optional = false)
    @Column(name = "res_fec_ini", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date resFecIni;
    @Basic(optional = false)
    @Column(name = "res_fec_fin", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date resFecFin;
    @Basic(optional = false)
    @Column(name = "res_status", nullable = false, length = 15)
    private String resStatus;
    @JoinColumn(name = "cus_id", referencedColumnName = "cus_id", nullable = false)
    @ManyToOne(optional = false)
    private Customers cusId;
    @JoinColumn(name = "rms_id", referencedColumnName = "RMS_ID", nullable = false)
    @ManyToOne(optional = false)
    private Rooms rmsId;

    public Reservation() {
    }

    public Reservation(Integer resId) {
        this.resId = resId;
    }

    public Reservation(Integer resId, Date resFecIni, Date resFecFin, String resStatus) {
        this.resId = resId;
        this.resFecIni = resFecIni;
        this.resFecFin = resFecFin;
        this.resStatus = resStatus;
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

    public String getResStatus() {
        return resStatus;
    }

    public void setResStatus(String resStatus) {
        this.resStatus = resStatus;
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
        return "com.ahms.model.entity.Reservation[ resId=" + resId + " ]";
    }
    
}
