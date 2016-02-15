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
@Table(name = "multi_value", catalog = "db_ahms", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"MVA_KEY"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MultiValue.findAll", query = "SELECT m FROM MultiValue m"),
    @NamedQuery(name = "MultiValue.findByMvaId", query = "SELECT m FROM MultiValue m WHERE m.mvaId = :mvaId"),
    @NamedQuery(name = "MultiValue.findByMvaKey", query = "SELECT m FROM MultiValue m WHERE m.mvaKey = :mvaKey"),
    @NamedQuery(name = "MultiValue.findByMvaType", query = "SELECT m FROM MultiValue m WHERE m.mvaType = :mvaType"),
    @NamedQuery(name = "MultiValue.findByMvaDescription", query = "SELECT m FROM MultiValue m WHERE m.mvaDescription = :mvaDescription"),
    @NamedQuery(name = "MultiValue.findByMvaValue", query = "SELECT m FROM MultiValue m WHERE m.mvaValue = :mvaValue"),
    @NamedQuery(name = "MultiValue.findByMvaDteMod", query = "SELECT m FROM MultiValue m WHERE m.mvaDteMod = :mvaDteMod")})
public class MultiValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "MVA_ID", nullable = false)
    private Integer mvaId;
    @Basic(optional = false)
    @Column(name = "MVA_KEY", nullable = false, length = 5)
    private String mvaKey;
    @Basic(optional = false)
    @Column(name = "MVA_TYPE", nullable = false, length = 5)
    private String mvaType;
    @Basic(optional = false)
    @Column(name = "MVA_DESCRIPTION", nullable = false, length = 100)
    private String mvaDescription;
    @Basic(optional = false)
    @Column(name = "MVA_VALUE", nullable = false, length = 50)
    private String mvaValue;
    @Basic(optional = false)
    @Column(name = "MVA_DTE_MOD", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date mvaDteMod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rmsStatus", fetch = FetchType.EAGER)
    private Collection<Rooms> roomsCollection;
    @JoinColumn(name = "MVA_USR_MOD", referencedColumnName = "USR_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Users mvaUsrMod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rteStatus", fetch = FetchType.EAGER)
    private Collection<Rates> ratesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proStatus", fetch = FetchType.EAGER)
    private Collection<Profiles> profilesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "payStatus", fetch = FetchType.EAGER)
    private Collection<PaymentTypes> paymentTypesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "srvStatus", fetch = FetchType.EAGER)
    private Collection<Services> servicesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usrStatus", fetch = FetchType.EAGER)
    private Collection<Users> usersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flrStatus", fetch = FetchType.EAGER)
    private Collection<Floors> floorsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rtyStatus", fetch = FetchType.EAGER)
    private Collection<RoomTypes> roomTypesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atrStatus", fetch = FetchType.EAGER)
    private Collection<AccountTransactions> accountTransactionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "couStatus", fetch = FetchType.EAGER)
    private Collection<CashOut> cashOutCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resStatus", fetch = FetchType.EAGER)
    private Collection<Reservation> reservationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "svtStatus", fetch = FetchType.EAGER)
    private Collection<ServiceTypes> serviceTypesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cusStatus", fetch = FetchType.EAGER)
    private Collection<Customers> customersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actStatus", fetch = FetchType.EAGER)
    private Collection<Account> accountCollection;

    public MultiValue() {
    }

    public MultiValue(String mvaKey) {
        this.mvaKey = mvaKey;
    }

    public MultiValue(Integer mvaId, String mvaKey, String mvaType, String mvaDescription, String mvaValue, Date mvaDteMod) {
        this.mvaId = mvaId;
        this.mvaKey = mvaKey;
        this.mvaType = mvaType;
        this.mvaDescription = mvaDescription;
        this.mvaValue = mvaValue;
        this.mvaDteMod = mvaDteMod;
    }

    public Integer getMvaId() {
        return mvaId;
    }

    public void setMvaId(Integer mvaId) {
        this.mvaId = mvaId;
    }

    public String getMvaKey() {
        return mvaKey;
    }

    public void setMvaKey(String mvaKey) {
        this.mvaKey = mvaKey;
    }

    public String getMvaType() {
        return mvaType;
    }

    public void setMvaType(String mvaType) {
        this.mvaType = mvaType;
    }

    public String getMvaDescription() {
        return mvaDescription;
    }

    public void setMvaDescription(String mvaDescription) {
        this.mvaDescription = mvaDescription;
    }

    public String getMvaValue() {
        return mvaValue;
    }

    public void setMvaValue(String mvaValue) {
        this.mvaValue = mvaValue;
    }

    public Date getMvaDteMod() {
        return mvaDteMod;
    }

    public void setMvaDteMod(Date mvaDteMod) {
        this.mvaDteMod = mvaDteMod;
    }

    @XmlTransient
    public Collection<Rooms> getRoomsCollection() {
        return roomsCollection;
    }

    public void setRoomsCollection(Collection<Rooms> roomsCollection) {
        this.roomsCollection = roomsCollection;
    }

    public Users getMvaUsrMod() {
        return mvaUsrMod;
    }

    public void setMvaUsrMod(Users mvaUsrMod) {
        this.mvaUsrMod = mvaUsrMod;
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

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @XmlTransient
    public Collection<Floors> getFloorsCollection() {
        return floorsCollection;
    }

    public void setFloorsCollection(Collection<Floors> floorsCollection) {
        this.floorsCollection = floorsCollection;
    }

    @XmlTransient
    public Collection<RoomTypes> getRoomTypesCollection() {
        return roomTypesCollection;
    }

    public void setRoomTypesCollection(Collection<RoomTypes> roomTypesCollection) {
        this.roomTypesCollection = roomTypesCollection;
    }

    @XmlTransient
    public Collection<AccountTransactions> getAccountTransactionsCollection() {
        return accountTransactionsCollection;
    }

    public void setAccountTransactionsCollection(Collection<AccountTransactions> accountTransactionsCollection) {
        this.accountTransactionsCollection = accountTransactionsCollection;
    }

    @XmlTransient
    public Collection<CashOut> getCashOutCollection() {
        return cashOutCollection;
    }

    public void setCashOutCollection(Collection<CashOut> cashOutCollection) {
        this.cashOutCollection = cashOutCollection;
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
    public Collection<Account> getAccountCollection() {
        return accountCollection;
    }

    public void setAccountCollection(Collection<Account> accountCollection) {
        this.accountCollection = accountCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mvaId != null ? mvaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MultiValue)) {
            return false;
        }
        MultiValue other = (MultiValue) object;
        if ((this.mvaId == null && other.mvaId != null) || (this.mvaId != null && !this.mvaId.equals(other.mvaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getMvaDescription();
    }

}
