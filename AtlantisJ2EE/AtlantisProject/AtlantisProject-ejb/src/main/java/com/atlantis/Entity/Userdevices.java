/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.Entity;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "userdevices")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userdevices.findAll", query = "SELECT u FROM Userdevices u"),
    @NamedQuery(name = "Userdevices.findByIduserdevices", query = "SELECT u FROM Userdevices u WHERE u.iduserdevices = :iduserdevices"),
    @NamedQuery(name = "Userdevices.findByCreatedAt", query = "SELECT u FROM Userdevices u WHERE u.createdAt = :createdAt")})
public class Userdevices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iduserdevices")
    private Integer iduserdevices;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "userID", referencedColumnName = "iduser")
    @ManyToOne
    private User userID;
    @JoinColumn(name = "deviceID", referencedColumnName = "iddevices")
    @ManyToOne
    private Devices deviceID;

    public Userdevices() {
    }

    public Userdevices(Integer iduserdevices) {
        this.iduserdevices = iduserdevices;
    }

    public Integer getIduserdevices() {
        return iduserdevices;
    }

    public void setIduserdevices(Integer iduserdevices) {
        this.iduserdevices = iduserdevices;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Devices getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(Devices deviceID) {
        this.deviceID = deviceID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iduserdevices != null ? iduserdevices.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userdevices)) {
            return false;
        }
        Userdevices other = (Userdevices) object;
        if ((this.iduserdevices == null && other.iduserdevices != null) || (this.iduserdevices != null && !this.iduserdevices.equals(other.iduserdevices))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.atlantis.Entity.Userdevices[ iduserdevices=" + iduserdevices + " ]";
    }
    
}
