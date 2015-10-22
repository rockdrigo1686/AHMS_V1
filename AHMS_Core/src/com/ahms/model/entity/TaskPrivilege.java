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
@Table(name = "task_privilege", catalog = "DB_AHMS", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"PRV_ID", "TSK_ID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TaskPrivilege.findAll", query = "SELECT t FROM TaskPrivilege t"),
    @NamedQuery(name = "TaskPrivilege.findByTskId", query = "SELECT t FROM TaskPrivilege t WHERE t.taskPrivilegePK.tskId = :tskId"),
    @NamedQuery(name = "TaskPrivilege.findByPrvId", query = "SELECT t FROM TaskPrivilege t WHERE t.taskPrivilegePK.prvId = :prvId"),
    @NamedQuery(name = "TaskPrivilege.findByTpUsrMod", query = "SELECT t FROM TaskPrivilege t WHERE t.tpUsrMod = :tpUsrMod"),
    @NamedQuery(name = "TaskPrivilege.findByTpDteMod", query = "SELECT t FROM TaskPrivilege t WHERE t.tpDteMod = :tpDteMod")})
public class TaskPrivilege implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TaskPrivilegePK taskPrivilegePK;
    @Basic(optional = false)
    @Column(name = "TP_USR_MOD", nullable = false, length = 6)
    private String tpUsrMod;
    @Basic(optional = false)
    @Column(name = "TP_DTE_MOD", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date tpDteMod;
    @JoinColumn(name = "TSK_ID", referencedColumnName = "TSK_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tasks tasks;
    @JoinColumn(name = "PRV_ID", referencedColumnName = "PRV_ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Privileges privileges;

    public TaskPrivilege() {
    }

    public TaskPrivilege(TaskPrivilegePK taskPrivilegePK) {
        this.taskPrivilegePK = taskPrivilegePK;
    }

    public TaskPrivilege(TaskPrivilegePK taskPrivilegePK, String tpUsrMod, Date tpDteMod) {
        this.taskPrivilegePK = taskPrivilegePK;
        this.tpUsrMod = tpUsrMod;
        this.tpDteMod = tpDteMod;
    }

    public TaskPrivilege(int tskId, int prvId) {
        this.taskPrivilegePK = new TaskPrivilegePK(tskId, prvId);
    }

    public TaskPrivilegePK getTaskPrivilegePK() {
        return taskPrivilegePK;
    }

    public void setTaskPrivilegePK(TaskPrivilegePK taskPrivilegePK) {
        this.taskPrivilegePK = taskPrivilegePK;
    }

    public String getTpUsrMod() {
        return tpUsrMod;
    }

    public void setTpUsrMod(String tpUsrMod) {
        this.tpUsrMod = tpUsrMod;
    }

    public Date getTpDteMod() {
        return tpDteMod;
    }

    public void setTpDteMod(Date tpDteMod) {
        this.tpDteMod = tpDteMod;
    }

    public Tasks getTasks() {
        return tasks;
    }

    public void setTasks(Tasks tasks) {
        this.tasks = tasks;
    }

    public Privileges getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Privileges privileges) {
        this.privileges = privileges;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskPrivilegePK != null ? taskPrivilegePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaskPrivilege)) {
            return false;
        }
        TaskPrivilege other = (TaskPrivilege) object;
        if ((this.taskPrivilegePK == null && other.taskPrivilegePK != null) || (this.taskPrivilegePK != null && !this.taskPrivilegePK.equals(other.taskPrivilegePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entitys.TaskPrivilege[ taskPrivilegePK=" + taskPrivilegePK + " ]";
    }
    
}
