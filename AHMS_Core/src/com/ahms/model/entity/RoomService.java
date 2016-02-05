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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "room_service", catalog = "db_ahms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoomService.findAll", query = "SELECT r FROM RoomService r"),
    @NamedQuery(name = "RoomService.findByRseId", query = "SELECT r FROM RoomService r WHERE r.roomServicePK.rseId = :rseId"),
    @NamedQuery(name = "RoomService.findByAseId", query = "SELECT r FROM RoomService r WHERE r.roomServicePK.aseId = :aseId"),
    @NamedQuery(name = "RoomService.findByRseQuantity", query = "SELECT r FROM RoomService r WHERE r.rseQuantity = :rseQuantity"),
    @NamedQuery(name = "RoomService.findByRseUsrMod", query = "SELECT r FROM RoomService r WHERE r.rseUsrMod = :rseUsrMod"),
    @NamedQuery(name = "RoomService.findByRseDteMod", query = "SELECT r FROM RoomService r WHERE r.rseDteMod = :rseDteMod")})
public class RoomService implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RoomServicePK roomServicePK;
    @Basic(optional = false)
    @Column(name = "rse_quantity", nullable = false)
    private int rseQuantity;
    @Column(name = "rse_usr_mod", length = 6)
    private String rseUsrMod;
    @Column(name = "rse_dte_mod")
    @Temporal(TemporalType.DATE)
    private Date rseDteMod;
    @JoinColumn(name = "ase_id", referencedColumnName = "ase_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AccountService accountService;
    @JoinColumn(name = "srv_id", referencedColumnName = "SRV_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Services srvId;

    public RoomService() {
    }

    public RoomService(RoomServicePK roomServicePK) {
        this.roomServicePK = roomServicePK;
    }

    public RoomService(RoomServicePK roomServicePK, int rseQuantity) {
        this.roomServicePK = roomServicePK;
        this.rseQuantity = rseQuantity;
    }

    public RoomService(int rseId, int aseId) {
        this.roomServicePK = new RoomServicePK(rseId, aseId);
    }

    public RoomServicePK getRoomServicePK() {
        return roomServicePK;
    }

    public void setRoomServicePK(RoomServicePK roomServicePK) {
        this.roomServicePK = roomServicePK;
    }

    public int getRseQuantity() {
        return rseQuantity;
    }

    public void setRseQuantity(int rseQuantity) {
        this.rseQuantity = rseQuantity;
    }

    public String getRseUsrMod() {
        return rseUsrMod;
    }

    public void setRseUsrMod(String rseUsrMod) {
        this.rseUsrMod = rseUsrMod;
    }

    public Date getRseDteMod() {
        return rseDteMod;
    }

    public void setRseDteMod(Date rseDteMod) {
        this.rseDteMod = rseDteMod;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public Services getSrvId() {
        return srvId;
    }

    public void setSrvId(Services srvId) {
        this.srvId = srvId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomServicePK != null ? roomServicePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomService)) {
            return false;
        }
        RoomService other = (RoomService) object;
        if ((this.roomServicePK == null && other.roomServicePK != null) || (this.roomServicePK != null && !this.roomServicePK.equals(other.roomServicePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entity.RoomService[ roomServicePK=" + roomServicePK + " ]";
    }
    
}
