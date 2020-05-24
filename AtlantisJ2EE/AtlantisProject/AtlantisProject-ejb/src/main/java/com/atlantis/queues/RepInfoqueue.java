/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atlantis.queues;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author root
 */
@Stateless
public class RepInfoqueue implements RepInfoqueueLocal {

    Connection connection;
    
    Session session;
    
    Destination destination;
    
    
    public RepInfoqueue() throws JMSException, NamingException {

  // Obtain a JNDI connection

  InitialContext jndi = new InitialContext();

  
  // Look up a JMS connection factory

  ConnectionFactory conFactory = (ConnectionFactory) jndi

    .lookup("jms/repInfoConnectionFactory");

  // Getting JMS connection from the server and starting it

  connection = conFactory.createConnection();

   connection.start();

   // JMS messages are sent and received using a Session. We will

   // create here a non-transactional session object. If you want

   // to use transactions you should set the first parameter to 'true'
   destination = (Destination) jndi.lookup("jms/repInfo");

 }
    
    @Override
    public void sendMessage(String messagep){
        try {
            
             session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

            // MessageProducer is used for sending messages (as opposed
            
            // to MessageConsumer which is used for receiving them)
            
            MessageProducer producer = session.createProducer(destination);
            
            // We will send a small text message saying 'Hello World!'
            
            TextMessage message = session.createTextMessage(messagep);
            
            // Here we are sending the message!
            
            
         
            producer.send(message);
            
            System.out.println("Sent message '" + message.getText() + "'");
        } catch (JMSException ex) {
            Logger.getLogger(RepInfoqueue.class.getName()).log(Level.SEVERE, null, ex);
        }

  }
    
    @PreDestroy
    public void destroy() {
        try {
            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(RepInfoqueue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
    
    


