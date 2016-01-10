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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "tasks", catalog = "DB_AHMS", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"TSK_CODE"}),
    @UniqueConstraint(columnNames = {"TSK_ID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tasks.findAll", query = "SELECT t FROM Tasks t"),
    @NamedQuery(name = "Tasks.find", query = "SELECT t FROM Tasks t WHERE t.tskId = :id"),
    @NamedQuery(name = "Tasks.findByModId", query = "SELECT t FROM Tasks t WHERE t.modId = :modId"),
    @NamedQuery(name = "Tasks.findByTskCode", query = "SELECT t FROM Tasks t WHERE t.tskCode = :tskCode"),
    @NamedQuery(name = "Tasks.findByTskName", query = "SELECT t FROM Tasks t WHERE t.tskName = :tskName"),
    @NamedQuery(name = "Tasks.findByTskUsrMod", query = "SELECT t FROM Tasks t WHERE t.tskUsrMod = :tskUsrMod"),
    @NamedQuery(name = "Tasks.findByTskDteMod", query = "SELECT t FROM Tasks t WHERE t.tskDteMod = :tskDteMod")})
public class Tasks implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TSK_ID", nullable = false)
    private Integer tskId;
    @Basic(optional = false)
    @Column(name = "MOD_ID", nullable = false)
    private int modId;
    @Basic(optional = false)
    @Column(name = "TSK_CODE", nullable = false, length = 6)
    private String tskCode;
    @Basic(optional = false)
    @Column(name = "TSK_NAME", nullable = false, length = 50)
    private String tskName;
    @Basic(optional = false)
    @Column(name = "TSK_USR_MOD", nullable = false, length = 6)
    private String tskUsrMod;
    @Basic(optional = false)
    @Column(name = "TSK_DTE_MOD", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date tskDteMod;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tasks", fetch = FetchType.EAGER)
    private ProfileTasks profileTasks;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tasks", fetch = FetchType.EAGER)
    private Collection<TaskPrivilege> taskPrivilegeCollection;

    public Tasks() {
    }

    public Tasks(Integer tskId) {
        this.tskId = tskId;
    }

    public Tasks(Integer tskId, int modId, String tskCode, String tskName, String tskUsrMod, Date tskDteMod) {
        this.tskId = tskId;
        this.modId = modId;
        this.tskCode = tskCode;
        this.tskName = tskName;
        this.tskUsrMod = tskUsrMod;
        this.tskDteMod = tskDteMod;
    }

    public Integer getTskId() {
        return tskId;
    }

    public void setTskId(Integer tskId) {
        this.tskId = tskId;
    }

    public int getModId() {
        return modId;
    }

    public void setModId(int modId) {
        this.modId = modId;
    }

    public String getTskCode() {
        return tskCode;
    }

    public void setTskCode(String tskCode) {
        this.tskCode = tskCode;
    }

    public String getTskName() {
        return tskName;
    }

    public void setTskName(String tskName) {
        this.tskName = tskName;
    }

    public String getTskUsrMod() {
        return tskUsrMod;
    }

    public void setTskUsrMod(String tskUsrMod) {
        this.tskUsrMod = tskUsrMod;
    }

    public Date getTskDteMod() {
        return tskDteMod;
    }

    public void setTskDteMod(Date tskDteMod) {
        this.tskDteMod = tskDteMod;
    }

    public ProfileTasks getProfileTasks() {
        return profileTasks;
    }

    public void setProfileTasks(ProfileTasks profileTasks) {
        this.profileTasks = profileTasks;
    }

    @XmlTransient
    public Collection<TaskPrivilege> getTaskPrivilegeCollection() {
        return taskPrivilegeCollection;
    }

    public void setTaskPrivilegeCollection(Collection<TaskPrivilege> taskPrivilegeCollection) {
        this.taskPrivilegeCollection = taskPrivilegeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tskId != null ? tskId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tasks)) {
            return false;
        }
        Tasks other = (Tasks) object;
        if ((this.tskId == null && other.tskId != null) || (this.tskId != null && !this.tskId.equals(other.tskId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entitys.Tasks[ tskId=" + tskId + " ]";
    }
    
}
