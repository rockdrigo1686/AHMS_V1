/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ahms.model.entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jorge
 */
@Entity
@Table(name = "users", catalog = "db_ahms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUsrId", query = "SELECT u FROM Users u WHERE u.usrId = :usrId"),
    @NamedQuery(name = "Users.findByUsrCode", query = "SELECT u FROM Users u WHERE u.usrCode = :usrCode"),
    @NamedQuery(name = "Users.findByUsrName", query = "SELECT u FROM Users u WHERE u.usrName = :usrName"),
    @NamedQuery(name = "Users.findByUsrLst1", query = "SELECT u FROM Users u WHERE u.usrLst1 = :usrLst1"),
    @NamedQuery(name = "Users.findByUsrLst2", query = "SELECT u FROM Users u WHERE u.usrLst2 = :usrLst2"),
    @NamedQuery(name = "Users.findByUsrStatus", query = "SELECT u FROM Users u WHERE u.usrStatus = :usrStatus"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.usrStatus = 'Activo' and u.usrPwd = :usrPwd"),
    @NamedQuery(name = "Users.login", query = "SELECT u FROM Users u WHERE u.usrPwd = :usrPwd and u.usrCode = :usrCode and u.usrStatus = 'Activo'"),
    @NamedQuery(name = "Users.findByUsrPwd", query = "SELECT u FROM Users u WHERE u.usrPwd = :usrPwd")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usr_id", nullable = false)
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
    @Column(name = "USR_STATUS", nullable = false, length = 10)
    private String usrStatus;
    @Basic(optional = false)
    @Column(name = "USR_PWD", nullable = false, length = 10)
    private String usrPwd;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rmsUsrMod", fetch = FetchType.LAZY)
    private Collection<Rooms> roomsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rteUsrMod", fetch = FetchType.LAZY)
    private Collection<Rates> ratesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proUsrMod", fetch = FetchType.EAGER)
    private Collection<Profiles> profilesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "payUsrMod", fetch = FetchType.LAZY)
    private Collection<PaymentTypes> paymentTypesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "srvUsrMod", fetch = FetchType.LAZY)
    private Collection<Services> servicesCollection;
    @JoinColumn(name = "PRO_ID", referencedColumnName = "PRO_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Profiles proId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flrUsrMod", fetch = FetchType.LAZY)
    private Collection<Floors> floorsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atrUsrMod", fetch = FetchType.LAZY)
    private Collection<AccountTransactions> accountTransactionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mmoUsrMod", fetch = FetchType.LAZY)
    private Collection<MoneyMovement> moneyMovementCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usrId", fetch = FetchType.LAZY)
    private Collection<CashOut> cashOutCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gstUsrMod", fetch = FetchType.LAZY)
    private Collection<Guests> guestsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resUsrMod", fetch = FetchType.LAZY)
    private Collection<Reservation> reservationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "svtUsrMod", fetch = FetchType.LAZY)
    private Collection<ServiceTypes> serviceTypesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cusUsrMod", fetch = FetchType.LAZY)
    private Collection<Customers> customersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ftrUsrMod", fetch = FetchType.LAZY)
    private Collection<FolioTransaction> folioTransactionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actUsrMod", fetch = FetchType.LAZY)
    private Collection<Account> accountCollection;

    public Users() {
    }

    public Users(Integer usrId) {
        this.usrId = usrId;
    }

    public Users(Integer usrId, String usrCode, String usrName, String usrLst1, String usrStatus, String usrPwd) {
        this.usrId = usrId;
        this.usrCode = usrCode;
        this.usrName = usrName;
        this.usrLst1 = usrLst1;
        this.usrStatus = usrStatus;
        this.usrPwd = usrPwd;
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

    public String getUsrStatus() {
        return usrStatus;
    }

    public void setUsrStatus(String usrStatus) {
        this.usrStatus = usrStatus;
    }

    public String getUsrPwd() {
        return usrPwd;
    }

    public void setUsrPwd(String usrPwd) {
        this.usrPwd = usrPwd;
    }

    @XmlTransient
    public Collection<Rooms> getRoomsCollection() {
        return roomsCollection;
    }

    public void setRoomsCollection(Collection<Rooms> roomsCollection) {
        this.roomsCollection = roomsCollection;
    }

    @XmlTransient
    public Collection<Rates> getRatesCollection() {
        return ratesCollection;
    }

    public void setRatesCollection(Collection<Rates> ratesCollection) {
        this.ratesCollection = ratesCollection;
    }

    @XmlTransient
    public Collection<Profiles> getProfilesCollection() {
        return profilesCollection;
    }

    public void setProfilesCollection(Collection<Profiles> profilesCollection) {
        this.profilesCollection = profilesCollection;
    }

    @XmlTransient
    public Collection<PaymentTypes> getPaymentTypesCollection() {
        return paymentTypesCollection;
    }

    public void setPaymentTypesCollection(Collection<PaymentTypes> paymentTypesCollection) {
        this.paymentTypesCollection = paymentTypesCollection;
    }

    @XmlTransient
    public Collection<Services> getServicesCollection() {
        return servicesCollection;
    }

    public void setServicesCollection(Collection<Services> servicesCollection) {
        this.servicesCollection = servicesCollection;
    }

    public Profiles getProId() {
        return proId;
    }

    public void setProId(Profiles proId) {
        this.proId = proId;
    }

    @XmlTransient
    public Collection<Floors> getFloorsCollection() {
        return floorsCollection;
    }

    public void setFloorsCollection(Collection<Floors> floorsCollection) {
        this.floorsCollection = floorsCollection;
    }

    @XmlTransient
    public Collection<AccountTransactions> getAccountTransactionsCollection() {
        return accountTransactionsCollection;
    }

    public void setAccountTransactionsCollection(Collection<AccountTransactions> accountTransactionsCollection) {
        this.accountTransactionsCollection = accountTransactionsCollection;
    }

    @XmlTransient
    public Collection<MoneyMovement> getMoneyMovementCollection() {
        return moneyMovementCollection;
    }

    public void setMoneyMovementCollection(Collection<MoneyMovement> moneyMovementCollection) {
        this.moneyMovementCollection = moneyMovementCollection;
    }

    @XmlTransient
    public Collection<CashOut> getCashOutCollection() {
        return cashOutCollection;
    }

    public void setCashOutCollection(Collection<CashOut> cashOutCollection) {
        this.cashOutCollection = cashOutCollection;
    }

    @XmlTransient
    public Collection<Guests> getGuestsCollection() {
        return guestsCollection;
    }

    public void setGuestsCollection(Collection<Guests> guestsCollection) {
        this.guestsCollection = guestsCollection;
    }

    @XmlTransient
    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
    }

    @XmlTransient
    public Collection<ServiceTypes> getServiceTypesCollection() {
        return serviceTypesCollection;
    }

    public void setServiceTypesCollection(Collection<ServiceTypes> serviceTypesCollection) {
        this.serviceTypesCollection = serviceTypesCollection;
    }

    @XmlTransient
    public Collection<Customers> getCustomersCollection() {
        return customersCollection;
    }

    public void setCustomersCollection(Collection<Customers> customersCollection) {
        this.customersCollection = customersCollection;
    }

    @XmlTransient
    public Collection<FolioTransaction> getFolioTransactionCollection() {
        return folioTransactionCollection;
    }

    public void setFolioTransactionCollection(Collection<FolioTransaction> folioTransactionCollection) {
        this.folioTransactionCollection = folioTransactionCollection;
    }

    @XmlTransient
    public Collection<Account> getAccountCollection() {
        return accountCollection;
    }

    public void setAccountCollection(Collection<Account> accountCollection) {
        this.accountCollection = accountCollection;
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
        return "com.ahms.model.entity.Users[ usrId=" + usrId + " ]";
    }

    public String getFullName() {
        return this.usrName + " " + this.usrLst1 + " " + this.usrLst2;
    }

}
