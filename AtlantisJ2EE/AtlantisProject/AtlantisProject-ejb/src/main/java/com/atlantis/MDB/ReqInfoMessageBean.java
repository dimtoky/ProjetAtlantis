/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.MDB;

import com.atlantis.DAO.DevicesFacadeLocal;
import com.atlantis.Entity.Devices;
import com.atlantis.MsgModels.DeviceMsg;
import com.atlantis.queues.RepInfoqueueLocal;
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
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/reqInfo"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "REQINFO"),
    @ActivationConfigProperty(propertyName = "resourceAdapter", propertyValue = "activemq-rar-51590091651894963472.15.9")
})
public class ReqInfoMessageBean implements MessageListener {

    @EJB
    private RepInfoqueueLocal repInfoqueue;

    @EJB
    private DevicesFacadeLocal devicesFacade;



    public ReqInfoMessageBean() {
    }

    @Override
    public void onMessage(Message message) {

        try {
            TextMessage tmessage = (TextMessage) message;
            System.out.println("Nouveau Device: " + tmessage);
            ObjectMapper objectMapper = new ObjectMapper();
            DeviceMsg devicemsg = objectMapper.readValue(tmessage.getText(), DeviceMsg.class);
            devicemsg.setname(devicemsg.getDeviceType() + devicemsg.getmacAddress());

            System.out.println(devicemsg.getmacAddress());
            System.out.println(devicemsg.getDeviceType());
            System.out.println(devicemsg.getname());
            System.out.println(devicemsg.getMetricValue());
            System.out.println(devicemsg.getmetricDate());

            List<Devices> dbdevice = devicesFacade.findAllbyaddress(devicemsg.getmacAddress());
            if (dbdevice.isEmpty()) {

                Devices devices = new Devices();
                devices.setDeviceName(devicemsg.getname());
                devices.setDeviceType(devicemsg.getDeviceType());
                devices.setMacAddress(devicemsg.getmacAddress());
                devices.setCreatedAt(new Date());
                devicesFacade.create(devices);
                repInfoqueue.sendMessage(objectMapper.writeValueAsString(devicemsg));
            } else {

            }

        } catch (JMSException | IOException ex) {
            Logger.getLogger(ReqInfoMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
