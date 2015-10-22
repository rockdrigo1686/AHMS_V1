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
public class TaskPrivilegePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "TSK_ID", nullable = false)
    private int tskId;
    @Basic(optional = false)
    @Column(name = "PRV_ID", nullable = false)
    private int prvId;

    public TaskPrivilegePK() {
    }

    public TaskPrivilegePK(int tskId, int prvId) {
        this.tskId = tskId;
        this.prvId = prvId;
    }

    public int getTskId() {
        return tskId;
    }

    public void setTskId(int tskId) {
        this.tskId = tskId;
    }

    public int getPrvId() {
        return prvId;
    }

    public void setPrvId(int prvId) {
        this.prvId = prvId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tskId;
        hash += (int) prvId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaskPrivilegePK)) {
            return false;
        }
        TaskPrivilegePK other = (TaskPrivilegePK) object;
        if (this.tskId != other.tskId) {
            return false;
        }
        if (this.prvId != other.prvId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entitys.TaskPrivilegePK[ tskId=" + tskId + ", prvId=" + prvId + " ]";
    }
    
}
