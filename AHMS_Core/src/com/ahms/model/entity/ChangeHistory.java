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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rsoto
 */
@Entity
@Table(name = "change_history", catalog = "db_ahms", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"cha_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChangeHistory.findAll", query = "SELECT c FROM ChangeHistory c"),
    @NamedQuery(name = "ChangeHistory.findByChaId", query = "SELECT c FROM ChangeHistory c WHERE c.chaId = :chaId"),
    @NamedQuery(name = "ChangeHistory.findByChaDate", query = "SELECT c FROM ChangeHistory c WHERE c.chaDate = :chaDate"),
    @NamedQuery(name = "ChangeHistory.findByChaDescc", query = "SELECT c FROM ChangeHistory c WHERE c.chaDescc = :chaDescc")})
public class ChangeHistory implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cha_usr_aut_code", nullable = false, length = 255)
    private String chaUsrAutCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cha_usr_code", nullable = false, length = 255)
    private String chaUsrCode;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cha_id", nullable = false)
    private Integer chaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cha_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date chaDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cha_descc", nullable = false, length = 255)
    private String chaDescc;
    @JoinColumn(name = "cha_rm_b", referencedColumnName = "RMS_ID", nullable = false)
    @ManyToOne(optional = false)
    private Rooms chaRmB;
    @JoinColumn(name = "act_id", referencedColumnName = "ACT_ID", nullable = false)
    @ManyToOne(optional = false)
    private Account actId;
    @JoinColumn(name = "cha_rm_a", referencedColumnName = "RMS_ID", nullable = false)
    @ManyToOne(optional = false)
    private Rooms chaRmA;
    @JoinColumn(name = "cha_usr", referencedColumnName = "USR_ID", nullable = false)
    @ManyToOne(optional = false)
    private Users chaUsr;
    @JoinColumn(name = "cha_usr_aut", referencedColumnName = "USR_ID", nullable = false)
    @ManyToOne(optional = false)
    private Users chaUsrAut;
    @Transient 
     private Date chaDateAux;

    public ChangeHistory() {
    }

    public ChangeHistory(Integer chaId) {
        this.chaId = chaId;
    }

    public ChangeHistory(Integer chaId, Date chaDate, String chaDescc) {
        this.chaId = chaId;
        this.chaDate = chaDate;
        this.chaDescc = chaDescc;
    }

    public Integer getChaId() {
        return chaId;
    }

    public void setChaId(Integer chaId) {
        this.chaId = chaId;
    }

    public Date getChaDate() {
        return chaDate;
    }

    public void setChaDate(Date chaDate) {
        this.chaDate = chaDate;
    }

    public String getChaDescc() {
        return chaDescc;
    }

    public void setChaDescc(String chaDescc) {
        this.chaDescc = chaDescc;
    }

    public Rooms getChaRmB() {
        return chaRmB;
    }

    public void setChaRmB(Rooms chaRmB) {
        this.chaRmB = chaRmB;
    }

    public Account getActId() {
        return actId;
    }

    public void setActId(Account actId) {
        this.actId = actId;
    }

    public Rooms getChaRmA() {
        return chaRmA;
    }

    public void setChaRmA(Rooms chaRmA) {
        this.chaRmA = chaRmA;
    }

    public Users getChaUsr() {
        return chaUsr;
    }

    public void setChaUsr(Users chaUsr) {
        this.chaUsr = chaUsr;
    }

    public Users getChaUsrAut() {
        return chaUsrAut;
    }

    public void setChaUsrAut(Users chaUsrAut) {
        this.chaUsrAut = chaUsrAut;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chaId != null ? chaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChangeHistory)) {
            return false;
        }
        ChangeHistory other = (ChangeHistory) object;
        if ((this.chaId == null && other.chaId != null) || (this.chaId != null && !this.chaId.equals(other.chaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entity.ChangeHistory[ chaId=" + chaId + " ]";
    }

    public String getChaUsrAutCode() {
        return chaUsrAutCode;
    }

    public void setChaUsrAutCode(String chaUsrAutCode) {
        this.chaUsrAutCode = chaUsrAutCode;
    }

    public String getChaUsrCode() {
        return chaUsrCode;
    }

    public void setChaUsrCode(String chaUsrCode) {
        this.chaUsrCode = chaUsrCode;
    }

    /**
     * @return the chaDateAux
     */
    public Date getChaDateAux() {
        return chaDateAux;
    }

    /**
     * @param chaDateAux the chaDateAux to set
     */
    public void setChaDateAux(Date chaDateAux) {
        this.chaDateAux = chaDateAux;
    }
}
