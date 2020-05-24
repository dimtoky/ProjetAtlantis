/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.MDB;

import com.atlantis.DAO.DevicesFacadeLocal;
import com.atlantis.DAO.MetricsFacadeLocal;
import com.atlantis.Entity.Devices;
import com.atlantis.Entity.Metrics;
import com.atlantis.MsgModels.DeviceMsg;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author root
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/dbMetrics"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "DBMETRICS"),
    @ActivationConfigProperty(propertyName = "resourceAdapter", propertyValue = "activemq-rar-51590091651894963472.15.9")
})
public class DBMertricsMessageBean implements MessageListener {

    @EJB
    private DevicesFacadeLocal devicesFacade;

    @EJB
    private MetricsFacadeLocal metricsFacade;


    public DBMertricsMessageBean() {
    }

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage tmessage = (TextMessage) message;
            System.out.println("Nouveau Device: " + tmessage);
            ObjectMapper objectMapper = new ObjectMapper();
            DeviceMsg devicemsg = objectMapper.readValue(tmessage.getText(), DeviceMsg.class);

            List<Devices> deviceExist = devicesFacade.findAllbyaddress(devicemsg.getmacAddress());
            if (!deviceExist.isEmpty()) {
                Devices dbdevice = devicesFacade.findbyaddress(devicemsg.getmacAddress());
                Metrics metric = new Metrics();
                metric.setDeviceID(dbdevice);
                metric.setMetricValue(devicemsg.getMetricValue());            
                metric.setCreatedAt(new Date());
                metricsFacade.create(metric);
            }
            //System.out.println("Metrics recu: "+tmessage);
        } catch (JMSException | IOException ex) {
            Logger.getLogger(DBMertricsMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
