/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
@Table(name = "rooms", catalog = "db_ahms", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"RMS_NUMBER"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r"),
    @NamedQuery(name = "Rooms.findByRmsId", query = "SELECT r FROM Rooms r WHERE r.rmsId = :rmsId"),
    @NamedQuery(name = "Rooms.findByRmsNumber", query = "SELECT r FROM Rooms r WHERE r.rmsNumber = :rmsNumber"),
    @NamedQuery(name = "Rooms.findByRmsDteMod", query = "SELECT r FROM Rooms r WHERE r.rmsDteMod = :rmsDteMod"),
    @NamedQuery(name = "Rooms.findByRmsMaxOcu", query = "SELECT r FROM Rooms r WHERE r.rmsMaxOcu = :rmsMaxOcu"),
    @NamedQuery(name = "Rooms.findByRmsDesc", query = "SELECT r FROM Rooms r WHERE r.rmsDesc = :rmsDesc"),
    @NamedQuery(name = "Rooms.findByFlrId", query = "SELECT r FROM Rooms r WHERE r.flrId = :flrId"),
    @NamedQuery(name = "Rooms.findByRmsStatus", query = "SELECT r FROM Rooms r WHERE r.rmsStatus = :rmsStatus"),
    @NamedQuery(name = "Rooms.findByRmsBeds", query = "SELECT r FROM Rooms r WHERE r.rmsBeds = :rmsBeds")/*,
    @NamedQuery(name = "Rooms.findAvailable", query = "SELECT r FROM Rooms R WHERE R.rmsBeds = 1 and ( r.rmsStatus = 'AV' or (r.rmsStatus = 'RS' AND r.rmsId in ( SELECT b.rmsId FROM Reservation b WHERE b.resFecIni < :fechaFin AND b.resFecFin > :fechaIni )))")*/
})
public class Rooms implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chaRmB")
    private List<ChangeHistory> changeHistoryList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chaRmA")
    private List<ChangeHistory> changeHistoryList1;
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
    @Column(name = "RMS_DTE_MOD", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date rmsDteMod;
    @Basic(optional = false)
    @Column(name = "RMS_MAX_OCU", nullable = false)
    private int rmsMaxOcu;
    @Basic(optional = false)
    @Column(name = "RMS_DESC", nullable = false, length = 200)
    private String rmsDesc;
    @JoinColumn(name = "FLR_ID", referencedColumnName = "FLR_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Floors flrId;
    @JoinColumn(name = "RMS_STATUS", referencedColumnName = "MVA_KEY", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MultiValue rmsStatus;
    @JoinColumn(name = "RTE_ID", referencedColumnName = "RTE_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Rates rteId;
    @JoinColumn(name = "RMS_BEDS", referencedColumnName = "RTY_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private RoomTypes rmsBeds;
    @JoinColumn(name = "RMS_USR_MOD", referencedColumnName = "USR_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users rmsUsrMod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rmsId", fetch = FetchType.LAZY)
    private Collection<AccountTransactions> accountTransactionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rmsId", fetch = FetchType.LAZY)
    private Collection<Reservation> reservationCollection;

    public Rooms() {
    }

    public Rooms(Integer rmsId) {
        this.rmsId = rmsId;
    }

    public Rooms(Integer rmsId, String rmsNumber, Date rmsDteMod, int rmsMaxOcu, String rmsDesc) {
        this.rmsId = rmsId;
        this.rmsNumber = rmsNumber;
        this.rmsDteMod = rmsDteMod;
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

    public MultiValue getRmsStatus() {
        return rmsStatus;
    }

    public void setRmsStatus(MultiValue rmsStatus) {
        this.rmsStatus = rmsStatus;
    }

    public Rates getRteId() {
        return rteId;
    }

    public void setRteId(Rates rteId) {
        this.rteId = rteId;
    }

    public RoomTypes getRmsBeds() {
        return rmsBeds;
    }

    public void setRmsBeds(RoomTypes rmsBeds) {
        this.rmsBeds = rmsBeds;
    }

    public Users getRmsUsrMod() {
        return rmsUsrMod;
    }

    public void setRmsUsrMod(Users rmsUsrMod) {
        this.rmsUsrMod = rmsUsrMod;
    }

    @XmlTransient
    public Collection<AccountTransactions> getAccountTransactionsCollection() {
        return accountTransactionsCollection;
    }

    public void setAccountTransactionsCollection(Collection<AccountTransactions> accountTransactionsCollection) {
        this.accountTransactionsCollection = accountTransactionsCollection;
    }

    @XmlTransient
    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
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
        return getRmsNumber();
    }

    @XmlTransient
    public List<ChangeHistory> getChangeHistoryList() {
        return changeHistoryList;
    }

    public void setChangeHistoryList(List<ChangeHistory> changeHistoryList) {
        this.changeHistoryList = changeHistoryList;
    }

    @XmlTransient
    public List<ChangeHistory> getChangeHistoryList1() {
        return changeHistoryList1;
    }

    public void setChangeHistoryList1(List<ChangeHistory> changeHistoryList1) {
        this.changeHistoryList1 = changeHistoryList1;
    }
    
  
}
