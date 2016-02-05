/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author jorge
 */
@Embeddable
public class RoomServicePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "rse_id", nullable = false)
    private int rseId;
    @Basic(optional = false)
    @Column(name = "ase_id", nullable = false)
    private int aseId;

    public RoomServicePK() {
    }

    public RoomServicePK(int rseId, int aseId) {
        this.rseId = rseId;
        this.aseId = aseId;
    }

    public int getRseId() {
        return rseId;
    }

    public void setRseId(int rseId) {
        this.rseId = rseId;
    }

    public int getAseId() {
        return aseId;
    }

    public void setAseId(int aseId) {
        this.aseId = aseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) rseId;
        hash += (int) aseId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomServicePK)) {
            return false;
        }
        RoomServicePK other = (RoomServicePK) object;
        if (this.rseId != other.rseId) {
            return false;
        }
        if (this.aseId != other.aseId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entity.RoomServicePK[ rseId=" + rseId + ", aseId=" + aseId + " ]";
    }
    
}
