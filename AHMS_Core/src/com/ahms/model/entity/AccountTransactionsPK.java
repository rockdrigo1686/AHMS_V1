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
public class AccountTransactionsPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "atr_id", nullable = false)
    private int atrId;
    @Basic(optional = false)
    @Column(name = "rms_id", nullable = false)
    private int rmsId;

    public AccountTransactionsPK() {
    }

    public AccountTransactionsPK(int atrId, int rmsId) {
        this.atrId = atrId;
        this.rmsId = rmsId;
    }

    public int getAtrId() {
        return atrId;
    }

    public void setAtrId(int atrId) {
        this.atrId = atrId;
    }

    public int getRmsId() {
        return rmsId;
    }

    public void setRmsId(int rmsId) {
        this.rmsId = rmsId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) atrId;
        hash += (int) rmsId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountTransactionsPK)) {
            return false;
        }
        AccountTransactionsPK other = (AccountTransactionsPK) object;
        if (this.atrId != other.atrId) {
            return false;
        }
        if (this.rmsId != other.rmsId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entity.AccountTransactionsPK[ atrId=" + atrId + ", rmsId=" + rmsId + " ]";
    }
    
}
