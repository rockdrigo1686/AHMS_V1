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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "users", catalog = "DB_AHMS", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"USR_CODE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUsrId", query = "SELECT u FROM Users u WHERE u.usrId = :usrId"),
    @NamedQuery(name = "Users.findByUsrCode", query = "SELECT u FROM Users u WHERE u.usrCode = :usrCode"),
    @NamedQuery(name = "Users.findByUsrName", query = "SELECT u FROM Users u WHERE u.usrName = :usrName"),
    @NamedQuery(name = "Users.findByUsrLst1", query = "SELECT u FROM Users u WHERE u.usrLst1 = :usrLst1"),
    @NamedQuery(name = "Users.findByUsrLst2", query = "SELECT u FROM Users u WHERE u.usrLst2 = :usrLst2"),
    @NamedQuery(name = "Users.findByUsrStatus", query = "SELECT u FROM Users u WHERE u.usrStatus = :usrStatus"),
    @NamedQuery(name = "Users.login", query = "SELECT u FROM Users u WHERE u.usrCode = :usrCode and u.usrPwd = :usrPwd"),
    @NamedQuery(name = "Users.findByUsrDteMod", query = "SELECT u FROM Users u WHERE u.usrDteMod = :usrDteMod"),
    @NamedQuery(name = "Users.findByUsrUsrMod", query = "SELECT u FROM Users u WHERE u.usrUsrMod = :usrUsrMod")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USR_ID", nullable = false)
    private Integer usrId;
    @Basic(optional = false)
    @Column(name = "USR_CODE", nullable = false, length = 6)
    private String usrCode;
    @Basic(optional = false)
    @Column(name = "USR_NAME", nullable = false, length = 50)
    private String usrName;
    @Basic(optional = false)
    @Column(name = "USR_LST_1", nullable = false, length = 50)
    private String usrLst1;
    @Column(name = "USR_LST_2", length = 50)
    private String usrLst2;
    @Basic(optional = false)
    @Column(name = "USR_STATUS", nullable = false)
    private int usrStatus;
    @Basic(optional = false)
    @Column(name = "USR_PWD", nullable = false, length = 10)
    private String usrPwd;
    @Basic(optional = false)
    @Column(name = "USR_DTE_MOD", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date usrDteMod;
    @Column(name = "USR_USR_MOD", length = 6)
    private String usrUsrMod;
    @JoinColumn(name = "PRO_ID", referencedColumnName = "PRO_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Profiles proId;

    public Users() {
    }

    public Users(Integer usrId) {
        this.usrId = usrId;
    }

    public Users(Integer usrId, String usrCode, String usrName, String usrLst1, int usrStatus, String usrPwd, Date usrDteMod) {
        this.usrId = usrId;
        this.usrCode = usrCode;
        this.usrName = usrName;
        this.usrLst1 = usrLst1;
        this.usrStatus = usrStatus;
        this.usrPwd = usrPwd;
        this.usrDteMod = usrDteMod;
    }

    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }

    public String getUsrCode() {
        return usrCode;
    }

    public void setUsrCode(String usrCode) {
        this.usrCode = usrCode;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrLst1() {
        return usrLst1;
    }

    public void setUsrLst1(String usrLst1) {
        this.usrLst1 = usrLst1;
    }

    public String getUsrLst2() {
        return usrLst2;
    }

    public void setUsrLst2(String usrLst2) {
        this.usrLst2 = usrLst2;
    }

    public int getUsrStatus() {
        return usrStatus;
    }

    public void setUsrStatus(int usrStatus) {
        this.usrStatus = usrStatus;
    }

    public String getUsrPwd() {
        return usrPwd;
    }

    public void setUsrPwd(String usrPwd) {
        this.usrPwd = usrPwd;
    }

    public Date getUsrDteMod() {
        return usrDteMod;
    }

    public void setUsrDteMod(Date usrDteMod) {
        this.usrDteMod = usrDteMod;
    }

    public String getUsrUsrMod() {
        return usrUsrMod;
    }

    public void setUsrUsrMod(String usrUsrMod) {
        this.usrUsrMod = usrUsrMod;
    }

    public Profiles getProId() {
        return proId;
    }

    public void setProId(Profiles proId) {
        this.proId = proId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usrId != null ? usrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.usrId == null && other.usrId != null) || (this.usrId != null && !this.usrId.equals(other.usrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entitys.Users[ usrId=" + usrId + " ]";
    }
    
}
