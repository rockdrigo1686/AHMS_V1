/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.entity;


import java.io.Serializable;
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
@Table(name = "module", catalog = "DB_AHMS", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"MOD_CODE"}),
    @UniqueConstraint(columnNames = {"MOD_ID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Module.findAll", query = "SELECT m FROM Module m"),
    @NamedQuery(name = "Module.find", query = "SELECT m FROM Module m WHERE m.modId = :id"),
    @NamedQuery(name = "Module.findByModCode", query = "SELECT m FROM Module m WHERE m.modCode = :modCode"),
    @NamedQuery(name = "Module.findByModName", query = "SELECT m FROM Module m WHERE m.modName = :modName"),
    @NamedQuery(name = "Module.findByModStatus", query = "SELECT m FROM Module m WHERE m.modStatus = :modStatus"),
    @NamedQuery(name = "Module.findByModUsrMod", query = "SELECT m FROM Module m WHERE m.modUsrMod = :modUsrMod"),
    @NamedQuery(name = "Module.findByModDteMod", query = "SELECT m FROM Module m WHERE m.modDteMod = :modDteMod")})
public class Module implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MOD_ID", nullable = false)
    private Integer modId;
    @Basic(optional = false)
    @Column(name = "MOD_CODE", nullable = false, length = 6)
    private String modCode;
    @Basic(optional = false)
    @Column(name = "MOD_NAME", nullable = false, length = 50)
    private String modName;
    @Basic(optional = false)
    @Column(name = "MOD_STATUS", nullable = false)
    private int modStatus;
    @Basic(optional = false)
    @Column(name = "MOD_USR_MOD", nullable = false, length = 6)
    private String modUsrMod;
    @Basic(optional = false)
    @Column(name = "MOD_DTE_MOD", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modDteMod;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "module", fetch = FetchType.EAGER)
    private ProfileTasks profileTasks;

    public Module() {
    }

    public Module(Integer modId) {
        this.modId = modId;
    }

    public Module(Integer modId, String modCode, String modName, int modStatus, String modUsrMod, Date modDteMod) {
        this.modId = modId;
        this.modCode = modCode;
        this.modName = modName;
        this.modStatus = modStatus;
        this.modUsrMod = modUsrMod;
        this.modDteMod = modDteMod;
    }

    public Integer getModId() {
        return modId;
    }

    public void setModId(Integer modId) {
        this.modId = modId;
    }

    public String getModCode() {
        return modCode;
    }

    public void setModCode(String modCode) {
        this.modCode = modCode;
    }

    public String getModName() {
        return modName;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    public int getModStatus() {
        return modStatus;
    }

    public void setModStatus(int modStatus) {
        this.modStatus = modStatus;
    }

    public String getModUsrMod() {
        return modUsrMod;
    }

    public void setModUsrMod(String modUsrMod) {
        this.modUsrMod = modUsrMod;
    }

    public Date getModDteMod() {
        return modDteMod;
    }

    public void setModDteMod(Date modDteMod) {
        this.modDteMod = modDteMod;
    }

    public ProfileTasks getProfileTasks() {
        return profileTasks;
    }

    public void setProfileTasks(ProfileTasks profileTasks) {
        this.profileTasks = profileTasks;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (modId != null ? modId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Module)) {
            return false;
        }
        Module other = (Module) object;
        if ((this.modId == null && other.modId != null) || (this.modId != null && !this.modId.equals(other.modId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entitys.Module[ modId=" + modId + " ]";
    }
    
}
