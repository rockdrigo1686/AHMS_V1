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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rsoto
 */
@Entity
@Table(name = "customers", catalog = "db_ahms", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customers.findAll", query = "SELECT c FROM Customers c"),
    @NamedQuery(name = "Customers.findByCusId", query = "SELECT c FROM Customers c WHERE c.cusId = :cusId"),
    @NamedQuery(name = "Customers.findByCusName", query = "SELECT c FROM Customers c WHERE c.cusName = :cusName"),
    @NamedQuery(name = "Customers.findByCusLst1", query = "SELECT c FROM Customers c WHERE c.cusLst1 = :cusLst1"),
    @NamedQuery(name = "Customers.findByCusLst2", query = "SELECT c FROM Customers c WHERE c.cusLst2 = :cusLst2"),
    @NamedQuery(name = "Customers.findByCusAddress", query = "SELECT c FROM Customers c WHERE c.cusAddress = :cusAddress"),
    @NamedQuery(name = "Customers.findByCusPostalCode", query = "SELECT c FROM Customers c WHERE c.cusPostalCode = :cusPostalCode"),
    @NamedQuery(name = "Customers.findByCusState", query = "SELECT c FROM Customers c WHERE c.cusState = :cusState"),
    @NamedQuery(name = "Customers.findByCusCity", query = "SELECT c FROM Customers c WHERE c.cusCity = :cusCity"),
    @NamedQuery(name = "Customers.findByCusTel", query = "SELECT c FROM Customers c WHERE c.cusTel = :cusTel"),
    @NamedQuery(name = "Customers.findByCusRfc", query = "SELECT c FROM Customers c WHERE c.cusRfc = :cusRfc"),
    @NamedQuery(name = "Customers.findByCusCel", query = "SELECT c FROM Customers c WHERE c.cusCel = :cusCel"),
    @NamedQuery(name = "Customers.findByCusEmail", query = "SELECT c FROM Customers c WHERE c.cusEmail = :cusEmail")})
public class Customers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cus_id", nullable = false)
    private Integer cusId;
    @Basic(optional = false)
    @Column(name = "cus_name", nullable = false, length = 50)
    private String cusName;
    @Basic(optional = false)
    @Column(name = "cus_lst_1", nullable = false, length = 50)
    private String cusLst1;
    @Basic(optional = false)
    @Column(name = "cus_lst_2", nullable = false, length = 50)
    private String cusLst2;
    @Column(name = "cus_address", length = 200)
    private String cusAddress;
    @Column(name = "cus_postal_code")
    private Integer cusPostalCode;
    @Column(name = "cus_state", length = 50)
    private String cusState;
    @Column(name = "cus_city", length = 50)
    private String cusCity;
    @Column(name = "cus_tel", length = 15)
    private String cusTel;
    @Basic(optional = false)
    @Column(name = "cus_rfc", nullable = false, length = 15)
    private String cusRfc;
    @Basic(optional = false)
    @Column(name = "cus_cel", nullable = false, length = 15)
    private String cusCel;
    @Basic(optional = false)
    @Column(name = "cus_email", nullable = false, length = 100)
    private String cusEmail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cusId")
    private Collection<Reservation> reservationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cusId")
    private Collection<Account> accountCollection;

    public Customers() {
    }

    public Customers(Integer cusId) {
        this.cusId = cusId;
    }

    public Customers(Integer cusId, String cusName, String cusLst1, String cusLst2, String cusRfc, String cusCel, String cusEmail) {
        this.cusId = cusId;
        this.cusName = cusName;
        this.cusLst1 = cusLst1;
        this.cusLst2 = cusLst2;
        this.cusRfc = cusRfc;
        this.cusCel = cusCel;
        this.cusEmail = cusEmail;
    }

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusLst1() {
        return cusLst1;
    }

    public void setCusLst1(String cusLst1) {
        this.cusLst1 = cusLst1;
    }

    public String getCusLst2() {
        return cusLst2;
    }

    public void setCusLst2(String cusLst2) {
        this.cusLst2 = cusLst2;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public Integer getCusPostalCode() {
        return cusPostalCode;
    }

    public void setCusPostalCode(Integer cusPostalCode) {
        this.cusPostalCode = cusPostalCode;
    }

    public String getCusState() {
        return cusState;
    }

    public void setCusState(String cusState) {
        this.cusState = cusState;
    }

    public String getCusCity() {
        return cusCity;
    }

    public void setCusCity(String cusCity) {
        this.cusCity = cusCity;
    }

    public String getCusTel() {
        return cusTel;
    }

    public void setCusTel(String cusTel) {
        this.cusTel = cusTel;
    }

    public String getCusRfc() {
        return cusRfc;
    }

    public void setCusRfc(String cusRfc) {
        this.cusRfc = cusRfc;
    }

    public String getCusCel() {
        return cusCel;
    }

    public void setCusCel(String cusCel) {
        this.cusCel = cusCel;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public void setCusEmail(String cusEmail) {
        this.cusEmail = cusEmail;
    }

    @XmlTransient
    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
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
        hash += (cusId != null ? cusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customers)) {
            return false;
        }
        Customers other = (Customers) object;
        if ((this.cusId == null && other.cusId != null) || (this.cusId != null && !this.cusId.equals(other.cusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ahms.model.entity.Customers[ cusId=" + cusId + " ]";
    }
    
}
