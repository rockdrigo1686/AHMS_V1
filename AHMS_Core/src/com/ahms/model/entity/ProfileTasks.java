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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "profile_tasks", catalog = "DB_AHMS", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"PRO_ID"}),
    @UniqueConstraint(columnNames = {"MOD_ID"}),
    @UniqueConstraint(columnNames = {"MOD_ID", "PRO_ID", "TSK_ID"}),
    @UniqueConstraint(columnNames = {"TSK_ID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfileTasks.findAll", query = "SELECT p FROM ProfileTasks p"),
    @NamedQuery(name = "ProfileTasks.findByProId", query = "SELECT p FROM ProfileTasks p WHERE p.profileTasksPK.proId = :proId"),
    @NamedQuery(name = "ProfileTasks.findByTskId", query = "SELECT p FROM ProfileTasks p WHERE p.profileTasksPK.tskId = :tskId"),
    @NamedQuery(name = "ProfileTasks.findByModId", query = "SELECT p FROM ProfileTasks p WHERE p.profileTasksPK.modId = :modId"),
    @NamedQuery(name = "ProfileTasks.findByPtUsuMod", query = "SELECT p FROM ProfileTasks p WHERE p.ptUsuMod = :ptUsuMod"),
    @NamedQuery(name = "ProfileTasks.findByPtDteReg", query = "SELECT p FROM ProfileTasks p WHERE p.ptDteReg = :ptDteReg")})
public class ProfileTasks implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProfileTasksPK profileTasksPK;
    @Basic(optional = false)
    @Column(name = "PT_USU_MOD", nullable = false, length = 6)
    private String ptUsuMod;
    @Basic(optional = false)
    @Column(name = "PT_DTE_REG", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ptDteReg;
    @JoinColumn(name = "PRO_ID", referencedColumnName = "PRO_ID", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Profiles profiles;
    @JoinColumn(name = "MOD_ID", referencedColumnName = "MOD_ID", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Module module;
    @JoinColumn(name = "TSK_ID", referencedColumnName = "TSK_ID", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Tasks tasks;

    public ProfileTasks() {
    }

    public ProfileTasks(ProfileTasksPK profileTasksPK) {
        this.profileTasksPK = profileTasksPK;
    }

    public ProfileTasks(ProfileTasksPK profileTasksPK, String ptUsuMod, Date ptDteReg) {
        this.profileTasksPK = profileTasksPK;
        this.ptUsuMod = ptUsuMod;
        this.ptDteReg = ptDteReg;
    }

    public ProfileTasks(int proId, int tskId, int modId) {
        this.profileTasksPK = new ProfileTasksPK(proId, tskId, modId);
    }

    public ProfileTasksPK getProfileTasksPK() {
        return profileTasksPK;
    }

    public void setProfileTasksPK(ProfileTasksPK profileTasksPK) {
        this.profileTasksPK = profileTasksPK;
    }

    public String getPtUsuMod() {
        return ptUsuMod;
    }

    public void setPtUsuMod(String ptUsuMod) {
        this.ptUsuMod = ptUsuMod;
    }

    public Date getPtDteReg() {
        return ptDteReg;
    }

    public void setPtDteReg(Date ptDteReg) {
        this.ptDteReg = ptDteReg;
    }

    public Profiles getProfiles() {
        return profiles;
    }

    public void setProfiles(Profiles profiles) {
        this.profiles = profiles;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Tasks getTasks() {
        return tasks;
    }

    public void setTasks(Tasks tasks) {
        this.tasks = tasks;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (profileTasksPK != null ? profileTasksPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfileTasks)) {
            return false;
        }
        ProfileTasks other = (ProfileTasks) object;
        if ((this.profileTasksPK == null && other.profileTasksPK != null) || (this.profileTasksPK != null && !this.profileTasksPK.equals(other.profileTasksPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entitys.ProfileTasks[ profileTasksPK=" + profileTasksPK + " ]";
    }
    
}
