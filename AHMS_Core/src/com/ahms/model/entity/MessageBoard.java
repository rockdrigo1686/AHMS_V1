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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rsoto
 */
@Entity
@Table(name = "message_board", catalog = "db_ahms", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"msg_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MessageBoard.findAll", query = "SELECT m FROM MessageBoard m WHERE m.msgDate >= sysdate()"),
    @NamedQuery(name = "MessageBoard.findByMsgId", query = "SELECT m FROM MessageBoard m WHERE m.msgId = :msgId"),
    @NamedQuery(name = "MessageBoard.findByMsgMessage", query = "SELECT m FROM MessageBoard m WHERE m.msgMessage = :msgMessage"),
    @NamedQuery(name = "MessageBoard.findByMsgDate", query = "SELECT m FROM MessageBoard m WHERE m.msgDate = :msgDate")})
public class MessageBoard implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "msg_id", nullable = false)
    private Integer msgId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "msg_message", nullable = false, length = 255)
    private String msgMessage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "msg_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date msgDate;
    @JoinColumn(name = "msg_user", referencedColumnName = "USR_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users msgUser;

    public MessageBoard() {
    }

    public MessageBoard(Integer msgId) {
        this.msgId = msgId;
    }

    public MessageBoard(Integer msgId, String msgMessage, Date msgDate) {
        this.msgId = msgId;
        this.msgMessage = msgMessage;
        this.msgDate = msgDate;
    }

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public String getMsgMessage() {
        return msgMessage;
    }

    public void setMsgMessage(String msgMessage) {
        this.msgMessage = msgMessage;
    }

    public Date getMsgDate() {
        return msgDate;
    }

    public void setMsgDate(Date msgDate) {
        this.msgDate = msgDate;
    }

    public Users getMsgUser() {
        return msgUser;
    }

    public void setMsgUser(Users msgUser) {
        this.msgUser = msgUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (msgId != null ? msgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessageBoard)) {
            return false;
        }
        MessageBoard other = (MessageBoard) object;
        if ((this.msgId == null && other.msgId != null) || (this.msgId != null && !this.msgId.equals(other.msgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entity.MessageBoard[ msgId=" + msgId + " ]";
    }
    
}
