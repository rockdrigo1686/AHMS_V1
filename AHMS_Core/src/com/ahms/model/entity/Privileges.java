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
@Table(name = "privileges", catalog = "DB_AHMS", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"PRV_CODE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Privileges.findAll", query = "SELECT p FROM Privileges p"),
    @NamedQuery(name = "Privileges.findByPrvId", query = "SELECT p FROM Privileges p WHERE p.prvId = :prvId"),
    @NamedQuery(name = "Privileges.findByPrvCode", query = "SELECT p FROM Privileges p WHERE p.prvCode = :prvCode"),
    @NamedQuery(name = "Privileges.findByPrvDesc", query = "SELECT p FROM Privileges p WHERE p.prvDesc = :prvDesc"),
    @NamedQuery(name = "Privileges.findByPrvStatus", query = "SELECT p FROM Privileges p WHERE p.prvStatus = :prvStatus"),
    @NamedQuery(name = "Privileges.findByPrvUsrMod", query = "SELECT p FROM Privileges p WHERE p.prvUsrMod = :prvUsrMod"),
    @NamedQuery(name = "Privileges.findByPrvDteMod", query = "SELECT p FROM Privileges p WHERE p.prvDteMod = :prvDteMod")})
public class Privileges implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRV_ID", nullable = false)
    private Integer prvId;
    @Basic(optional = false)
    @Column(name = "PRV_CODE", nullable = false, length = 6)
    private String prvCode;
    @Basic(optional = false)
    @Column(name = "PRV_DESC", nullable = false, length = 20)
    private String prvDesc;
    @Basic(optional = false)
    @Column(name = "PRV_STATUS", nullable = false)
    private int prvStatus;
    @Column(name = "PRV_USR_MOD", length = 6)
    private String prvUsrMod;
    @Column(name = "PRV_DTE_MOD")
    @Temporal(TemporalType.DATE)
    private Date prvDteMod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "privileges", fetch = FetchType.EAGER)
    private Collection<TaskPrivilege> taskPrivilegeCollection;

    public Privileges() {
    }

    public Privileges(Integer prvId) {
        this.prvId = prvId;
    }

    public Privileges(Integer prvId, String prvCode, String prvDesc, int prvStatus) {
        this.prvId = prvId;
        this.prvCode = prvCode;
        this.prvDesc = prvDesc;
        this.prvStatus = prvStatus;
    }

    public Integer getPrvId() {
        return prvId;
    }

    public void setPrvId(Integer prvId) {
        this.prvId = prvId;
    }

    public String getPrvCode() {
        return prvCode;
    }

    public void setPrvCode(String prvCode) {
        this.prvCode = prvCode;
    }

    public String getPrvDesc() {
        return prvDesc;
    }

    public void setPrvDesc(String prvDesc) {
        this.prvDesc = prvDesc;
    }

    public int getPrvStatus() {
        return prvStatus;
    }

    public void setPrvStatus(int prvStatus) {
        this.prvStatus = prvStatus;
    }

    public String getPrvUsrMod() {
        return prvUsrMod;
    }

    public void setPrvUsrMod(String prvUsrMod) {
        this.prvUsrMod = prvUsrMod;
    }

    public Date getPrvDteMod() {
        return prvDteMod;
    }

    public void setPrvDteMod(Date prvDteMod) {
        this.prvDteMod = prvDteMod;
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
        hash += (prvId != null ? prvId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Privileges)) {
            return false;
        }
        Privileges other = (Privileges) object;
        if ((this.prvId == null && other.prvId != null) || (this.prvId != null && !this.prvId.equals(other.prvId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entitys.Privileges[ prvId=" + prvId + " ]";
    }
    
}
