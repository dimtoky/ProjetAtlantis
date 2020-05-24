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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "metrics")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Metrics.findAll", query = "SELECT m FROM Metrics m"),
    @NamedQuery(name = "Metrics.findByIdmetrics", query = "SELECT m FROM Metrics m WHERE m.idmetrics = :idmetrics"),
    @NamedQuery(name = "Metrics.findByMetricValue", query = "SELECT m FROM Metrics m WHERE m.metricValue = :metricValue"),
    @NamedQuery(name = "Metrics.findByCreatedAt", query = "SELECT m FROM Metrics m WHERE m.createdAt = :createdAt")})
public class Metrics implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmetrics")
    private Integer idmetrics;
    @Size(max = 45)
    @Column(name = "metric_value")
    private String metricValue;
    @Column(name = "created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "deviceID", referencedColumnName = "iddevices")
    @ManyToOne
    private Devices deviceID;

    public Metrics() {
    }

    public Metrics(Integer idmetrics) {
        this.idmetrics = idmetrics;
    }

    public Integer getIdmetrics() {
        return idmetrics;
    }

    public void setIdmetrics(Integer idmetrics) {
        this.idmetrics = idmetrics;
    }

    public String getMetricValue() {
        return metricValue;
    }

    public void setMetricValue(String metricValue) {
        this.metricValue = metricValue;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
        hash += (idmetrics != null ? idmetrics.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Metrics)) {
            return false;
        }
        Metrics other = (Metrics) object;
        if ((this.idmetrics == null && other.idmetrics != null) || (this.idmetrics != null && !this.idmetrics.equals(other.idmetrics))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.atlantis.Entity.Metrics[ idmetrics=" + idmetrics + " ]";
    }
    
}
