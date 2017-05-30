/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*; 
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Antonio
 */
public class Mail {
    
    @Autowired
    public MimeMessage email(String toAddress, String fromAddress, String subject, String msgBody) {
      String to = toAddress; 
      String from = fromAddress;
      String host = "localhost";//or IP address  
  
     //Get the session object  
      Properties properties = System.getProperties();  
      properties.setProperty("mail.smtp.host", host);  
      Session session = Session.getDefaultInstance(properties);  
  
     //compose the message  
      try{  
         MimeMessage message = new MimeMessage(session);  
         message.setFrom(new InternetAddress(from));  
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
         message.setSubject(subject);  
         message.setText(msgBody);  
  
         // Send message
         return message;  
  
      }catch (MessagingException mex) {mex.printStackTrace();}
      return null;
    }
}
