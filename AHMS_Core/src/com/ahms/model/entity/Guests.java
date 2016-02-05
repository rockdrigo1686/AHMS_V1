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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jorge
 */
@Entity
@Table(name = "guests", catalog = "db_ahms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Guests.findAll", query = "SELECT g FROM Guests g"),
    @NamedQuery(name = "Guests.findByGstId", query = "SELECT g FROM Guests g WHERE g.gstId = :gstId"),
    @NamedQuery(name = "Guests.findByGstName", query = "SELECT g FROM Guests g WHERE g.gstName = :gstName"),
    @NamedQuery(name = "Guests.findByGstLst1", query = "SELECT g FROM Guests g WHERE g.gstLst1 = :gstLst1"),
    @NamedQuery(name = "Guests.findByGstLst2", query = "SELECT g FROM Guests g WHERE g.gstLst2 = :gstLst2"),
    @NamedQuery(name = "Guests.findByGstUsrMod", query = "SELECT g FROM Guests g WHERE g.gstUsrMod = :gstUsrMod"),
    @NamedQuery(name = "Guests.findByGstDteMod", query = "SELECT g FROM Guests g WHERE g.gstDteMod = :gstDteMod")})
public class Guests implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gst_id", nullable = false)
    private Integer gstId;
    @Basic(optional = false)
    @Column(name = "gst_name", nullable = false, length = 50)
    private String gstName;
    @Basic(optional = false)
    @Column(name = "gst_lst_1", nullable = false, length = 50)
    private String gstLst1;
    @Basic(optional = false)
    @Column(name = "gst_lst_2", nullable = false, length = 50)
    private String gstLst2;
    @Column(name = "gst_usr_mod", length = 6)
    private String gstUsrMod;
    @Column(name = "gst_dte_mod")
    @Temporal(TemporalType.DATE)
    private Date gstDteMod;
    @JoinColumn(name = "act_id", referencedColumnName = "act_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Account actId;

    public Guests() {
    }

    public Guests(Integer gstId) {
        this.gstId = gstId;
    }

    public Guests(Integer gstId, String gstName, String gstLst1, String gstLst2) {
        this.gstId = gstId;
        this.gstName = gstName;
        this.gstLst1 = gstLst1;
        this.gstLst2 = gstLst2;
    }

    public Integer getGstId() {
        return gstId;
    }

    public void setGstId(Integer gstId) {
        this.gstId = gstId;
    }

    public String getGstName() {
        return gstName;
    }

    public void setGstName(String gstName) {
        this.gstName = gstName;
    }

    public String getGstLst1() {
        return gstLst1;
    }

    public void setGstLst1(String gstLst1) {
        this.gstLst1 = gstLst1;
    }

    public String getGstLst2() {
        return gstLst2;
    }

    public void setGstLst2(String gstLst2) {
        this.gstLst2 = gstLst2;
    }

    public String getGstUsrMod() {
        return gstUsrMod;
    }

    public void setGstUsrMod(String gstUsrMod) {
        this.gstUsrMod = gstUsrMod;
    }

    public Date getGstDteMod() {
        return gstDteMod;
    }

    public void setGstDteMod(Date gstDteMod) {
        this.gstDteMod = gstDteMod;
    }

    public Account getActId() {
        return actId;
    }

    public void setActId(Account actId) {
        this.actId = actId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gstId != null ? gstId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Guests)) {
            return false;
        }
        Guests other = (Guests) object;
        if ((this.gstId == null && other.gstId != null) || (this.gstId != null && !this.gstId.equals(other.gstId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entity.Guests[ gstId=" + gstId + " ]";
    }
    
}
