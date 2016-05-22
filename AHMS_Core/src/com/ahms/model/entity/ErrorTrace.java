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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jorge
 */
@Entity
@Table(name = "error_trace", catalog = "db_ahms", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"err_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ErrorTrace.findAll", query = "SELECT e FROM ErrorTrace e"),
    @NamedQuery(name = "ErrorTrace.findByErrId", query = "SELECT e FROM ErrorTrace e WHERE e.errId = :errId"),
    @NamedQuery(name = "ErrorTrace.findByErrDate", query = "SELECT e FROM ErrorTrace e WHERE e.errDate = :errDate")})
public class ErrorTrace implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "err_id", nullable = false)
    private Integer errId;
    @Basic(optional = false)
    @Lob
    @Column(name = "err_trace", nullable = false, length = 65535)
    private String errTrace;
    @Basic(optional = false)
    @Column(name = "err_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date errDate;

    public ErrorTrace() {
    }

    public ErrorTrace(Integer errId) {
        this.errId = errId;
    }

    public ErrorTrace(Integer errId, String errTrace, Date errDate) {
        this.errId = errId;
        this.errTrace = errTrace;
        this.errDate = errDate;
    }

    public Integer getErrId() {
        return errId;
    }

    public void setErrId(Integer errId) {
        this.errId = errId;
    }

    public String getErrTrace() {
        return errTrace;
    }

    public void setErrTrace(String errTrace) {
        this.errTrace = errTrace;
    }

    public Date getErrDate() {
        return errDate;
    }

    public void setErrDate(Date errDate) {
        this.errDate = errDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (errId != null ? errId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ErrorTrace)) {
            return false;
        }
        ErrorTrace other = (ErrorTrace) object;
        if ((this.errId == null && other.errId != null) || (this.errId != null && !this.errId.equals(other.errId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entity.ErrorTrace[ errId=" + errId + " ]";
    }
    
}
