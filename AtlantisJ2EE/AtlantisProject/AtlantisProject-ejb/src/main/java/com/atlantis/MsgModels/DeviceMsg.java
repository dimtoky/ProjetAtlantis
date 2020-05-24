/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.MsgModels;

/**
 *
 * @author root
 */
public class DeviceMsg {

    private int id;

    private String name;

    private String deviceType;

    private String macAddress;

    private String metricDate;

    private String metricValue;

    /**
     * Get the value of metricValue
     *
     * @return the value of metricValue
     */
    public String getMetricValue() {
        return metricValue;
    }

    /**
     * Set the value of metricValue
     *
     * @param metricValue new value of metricValue
     */
    public void setMetricValue(String metricValue) {
        this.metricValue = metricValue;
    }

    /**
     * Get the value of createdAt
     *
     * @return the value of createdAt
     */
    public String getmetricDate() {
        return metricDate;
    }

    /**
     * Set the value of createdAt
     *
     * @param metricDate new value of createdAt
     */
    public void setmetricDate(String metricDate) {
        this.metricDate = metricDate;
    }

    /**
     * Get the value of deviceAddress
     *
     * @return the value of deviceAddress
     */
    public String getmacAddress() {
        return macAddress;
    }

    /**
     * Set the value of deviceAddress
     *
     * @param macAddress new value of deviceAddress
     */
    public void setmacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    /**
     * Get the value of deviceType
     *
     * @return the value of deviceType
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * Set the value of deviceType
     *
     * @param deviceType new value of deviceType
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the value of deviceName
     *
     * @return the value of deviceName
     */
    public String getname() {
        return name;
    }

    /**
     * Set the value of deviceName
     *
     * @param name new value of deviceName
     */
    public void setname(String name) {
        this.name = name;
    }

}
