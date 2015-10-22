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
 * @author rsoto
 */
@Embeddable
public class ProfileTasksPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "PRO_ID", nullable = false)
    private int proId;
    @Basic(optional = false)
    @Column(name = "TSK_ID", nullable = false)
    private int tskId;
    @Basic(optional = false)
    @Column(name = "MOD_ID", nullable = false)
    private int modId;

    public ProfileTasksPK() {
    }

    public ProfileTasksPK(int proId, int tskId, int modId) {
        this.proId = proId;
        this.tskId = tskId;
        this.modId = modId;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getTskId() {
        return tskId;
    }

    public void setTskId(int tskId) {
        this.tskId = tskId;
    }

    public int getModId() {
        return modId;
    }

    public void setModId(int modId) {
        this.modId = modId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) proId;
        hash += (int) tskId;
        hash += (int) modId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfileTasksPK)) {
            return false;
        }
        ProfileTasksPK other = (ProfileTasksPK) object;
        if (this.proId != other.proId) {
            return false;
        }
        if (this.tskId != other.tskId) {
            return false;
        }
        if (this.modId != other.modId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entitys.ProfileTasksPK[ proId=" + proId + ", tskId=" + tskId + ", modId=" + modId + " ]";
    }
    
}
