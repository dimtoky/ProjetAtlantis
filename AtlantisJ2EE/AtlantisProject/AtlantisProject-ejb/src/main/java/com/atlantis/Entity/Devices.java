/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author root
 */
@Entity
@Table(name = "devices")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Devices.findAll", query = "SELECT d FROM Devices d"),
    @NamedQuery(name = "Devices.findByIddevices", query = "SELECT d FROM Devices d WHERE d.iddevices = :iddevices"),
    @NamedQuery(name = "Devices.findByDeviceType", query = "SELECT d FROM Devices d WHERE d.deviceType = :deviceType"),
    @NamedQuery(name = "Devices.findByMacAddress", query = "SELECT d FROM Devices d WHERE d.macAddress = :macAddress"),
    @NamedQuery(name = "Devices.findByCreatedAt", query = "SELECT d FROM Devices d WHERE d.createdAt = :createdAt"),
    @NamedQuery(name = "Devices.findByDeviceName", query = "SELECT d FROM Devices d WHERE d.deviceName = :deviceName")})
public class Devices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddevices")
    private Integer iddevices;
    @Size(max = 45)
    @Column(name = "device_type")
    private String deviceType;
    @Size(max = 45)
    @Column(name = "mac_address")
    private String macAddress;
    @Column(name = "created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Size(max = 45)
    @Column(name = "device_Name")
    private String deviceName;
    @OneToMany(mappedBy = "deviceID")
    private List<Userdevices> userdevicesList;
    @OneToMany(mappedBy = "deviceID")
    private List<Metrics> metricsList;

    public Devices() {
    }

    public Devices(Integer iddevices) {
        this.iddevices = iddevices;
    }

    public Integer getIddevices() {
        return iddevices;
    }

    public void setIddevices(Integer iddevices) {
        this.iddevices = iddevices;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @XmlTransient
    @JsonbTransient
    public List<Userdevices> getUserdevicesList() {
        return userdevicesList;
    }

    public void setUserdevicesList(List<Userdevices> userdevicesList) {
        this.userdevicesList = userdevicesList;
    }

    @XmlTransient
    @JsonbTransient
    public List<Metrics> getMetricsList() {
        return metricsList;
    }

    public void setMetricsList(List<Metrics> metricsList) {
        this.metricsList = metricsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddevices != null ? iddevices.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Devices)) {
            return false;
        }
        Devices other = (Devices) object;
        if ((this.iddevices == null && other.iddevices != null) || (this.iddevices != null && !this.iddevices.equals(other.iddevices))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.atlantis.Entity.Devices[ iddevices=" + iddevices + " ]";
    }
    
}
