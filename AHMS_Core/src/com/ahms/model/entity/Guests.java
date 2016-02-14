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
 * @author rsoto
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
    @NamedQuery(name = "Guests.findByGstDteMod", query = "SELECT g FROM Guests g WHERE g.gstDteMod = :gstDteMod")})
public class Guests implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "GST_ID", nullable = false)
    private Integer gstId;
    @Basic(optional = false)
    @Column(name = "GST_NAME", nullable = false, length = 100)
    private String gstName;
    @Basic(optional = false)
    @Column(name = "GST_LST_1", nullable = false, length = 100)
    private String gstLst1;
    @Basic(optional = false)
    @Column(name = "GST_LST_2", nullable = false, length = 100)
    private String gstLst2;
    @Basic(optional = false)
    @Column(name = "GST_DTE_MOD", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date gstDteMod;
    @JoinColumn(name = "ATR_ID", referencedColumnName = "ATR_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AccountTransactions atrId;
    @JoinColumn(name = "GST_USR_MOD", referencedColumnName = "USR_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users gstUsrMod;

    public Guests() {
    }

    public Guests(Integer gstId) {
        this.gstId = gstId;
    }

    public Guests(Integer gstId, String gstName, String gstLst1, String gstLst2, Date gstDteMod) {
        this.gstId = gstId;
        this.gstName = gstName;
        this.gstLst1 = gstLst1;
        this.gstLst2 = gstLst2;
        this.gstDteMod = gstDteMod;
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

    public Date getGstDteMod() {
        return gstDteMod;
    }

    public void setGstDteMod(Date gstDteMod) {
        this.gstDteMod = gstDteMod;
    }

    public AccountTransactions getAtrId() {
        return atrId;
    }

    public void setAtrId(AccountTransactions atrId) {
        this.atrId = atrId;
    }

    public Users getGstUsrMod() {
        return gstUsrMod;
    }

    public void setGstUsrMod(Users gstUsrMod) {
        this.gstUsrMod = gstUsrMod;
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
        return "com.ahms.boundary.Guests[ gstId=" + gstId + " ]";
    }
    
}
