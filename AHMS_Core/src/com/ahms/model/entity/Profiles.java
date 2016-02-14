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
@Table(name = "profiles", catalog = "db_ahms", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"PRO_CODE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profiles.findAll", query = "SELECT p FROM Profiles p"),
    @NamedQuery(name = "Profiles.findByProId", query = "SELECT p FROM Profiles p WHERE p.proId = :proId"),
    @NamedQuery(name = "Profiles.findByProCode", query = "SELECT p FROM Profiles p WHERE p.proCode = :proCode"),
    @NamedQuery(name = "Profiles.findByProName", query = "SELECT p FROM Profiles p WHERE p.proName = :proName"),
    @NamedQuery(name = "Profiles.findByProUsrMod", query = "SELECT p FROM Profiles p WHERE p.proUsrMod = :proUsrMod"),
    @NamedQuery(name = "Profiles.findByProDteMod", query = "SELECT p FROM Profiles p WHERE p.proDteMod = :proDteMod")})
public class Profiles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRO_ID", nullable = false)
    private Integer proId;
    @Basic(optional = false)
    @Column(name = "PRO_CODE", nullable = false, length = 5)
    private String proCode;
    @Basic(optional = false)
    @Column(name = "PRO_NAME", nullable = false, length = 50)
    private String proName;
    @Basic(optional = false)
    @Column(name = "PRO_USR_MOD", nullable = false)
    private int proUsrMod;
    @Basic(optional = false)
    @Column(name = "PRO_DTE_MOD", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date proDteMod;
    @JoinColumn(name = "PRO_STATUS", referencedColumnName = "MVA_KEY", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private MultiValue proStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proId", fetch = FetchType.EAGER)
    private Collection<Users> usersCollection;

    public Profiles() {
    }

    public Profiles(Integer proId) {
        this.proId = proId;
    }

    public Profiles(Integer proId, String proCode, String proName, int proUsrMod, Date proDteMod) {
        this.proId = proId;
        this.proCode = proCode;
        this.proName = proName;
        this.proUsrMod = proUsrMod;
        this.proDteMod = proDteMod;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getProUsrMod() {
        return proUsrMod;
    }

    public void setProUsrMod(int proUsrMod) {
        this.proUsrMod = proUsrMod;
    }

    public Date getProDteMod() {
        return proDteMod;
    }

    public void setProDteMod(Date proDteMod) {
        this.proDteMod = proDteMod;
    }

    public MultiValue getProStatus() {
        return proStatus;
    }

    public void setProStatus(MultiValue proStatus) {
        this.proStatus = proStatus;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proId != null ? proId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profiles)) {
            return false;
        }
        Profiles other = (Profiles) object;
        if ((this.proId == null && other.proId != null) || (this.proId != null && !this.proId.equals(other.proId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.boundary.Profiles[ proId=" + proId + " ]";
    }
    
}
