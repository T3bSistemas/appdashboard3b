package appdashboard3b.modelos;

import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import appdashboard3b.utelerias.Codificacion;

import java.util.Base64;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

@Service
public class Mcorreo {
	@Autowired
	Environment env;
	
	private JavaMailSender mailSender;

    public Mcorreo(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String from, String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public boolean sendWithAttach(String from, String to, String toReply, String subject,String text, String attachName, byte[] pdf, byte[] xml){
    	try {
    		 MimeMessage message = mailSender.createMimeMessage();
    	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
    	        helper.setFrom(from);
    	        helper.setTo(to);
    	        if(toReply != null && !toReply.equals("")) {
    	        	 helper.setReplyTo(toReply);
    	        }    	       
    	        helper.setSubject(subject);
    	        helper.setText(text, true);
    	        ByteArrayDataSource pdfAttch = new ByteArrayDataSource(pdf, "application/pdf");
    	        helper.addAttachment(attachName+".pdf", pdfAttch);
    	        ByteArrayDataSource xmlAttch = new ByteArrayDataSource(xml, "application/xml");
    	        helper.addAttachment(attachName+".xml", xmlAttch);    	        
    	        mailSender.send(message);
    	        return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return false;
       
    }
    
	public boolean EnviarMail(String from, Address[] to, String mensaje, String titulo,String uuid, String xmlbase, String pdfBase64) {
		try {
	        Properties props = new Properties();
	        props.setProperty("mail.smtp.host", env.getProperty("spring.mail.host"));
	        props.setProperty("mail.smtp.starttls.enable", "true");
	        props.setProperty("mail.smtp.port", env.getProperty("spring.mail.port"));
	        props.setProperty("mail.smtp.user", from);
	        props.setProperty("mail.smtp.auth", "true");    
	        
	        Session session = Session.getInstance(props, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(from,new Codificacion().toBase64String(env.getProperty("spring.mail.password")));
	            }
	        });
	
	
	        BodyPart texto = new MimeBodyPart();
	        texto.setText(mensaje);
	        
	        MimeMultipart multiParte = new MimeMultipart();
	        
	        if(!uuid.isEmpty()) {
	        	if(!xmlbase.isEmpty()) {      
		            MimeBodyPart filePart = new MimeBodyPart(); 
		            DataSource ds;
		            ds = new ByteArrayDataSource(xmlbase.getBytes(), "xml/*");
		            filePart.setDataHandler(new DataHandler(ds));
		            filePart.setFileName(uuid+".xml");
		            multiParte.addBodyPart(filePart);
	        	}
	        	
	        	if(!pdfBase64.isEmpty()) {
	        		MimeBodyPart filePart = new MimeBodyPart(); 
		            byte[] decoder = Base64.getDecoder().decode(pdfBase64);
		            DataSource ds;
		            ds = new ByteArrayDataSource(decoder, "pdf/*");
		            filePart.setDataHandler(new DataHandler(ds));
		            filePart.setFileName(uuid+".pdf");
		            multiParte.addBodyPart(filePart);
	        	}
	            
	        }
	
	        
	        multiParte.addBodyPart(texto);   
	        
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(from));
	                    
	        message.addRecipients(Message.RecipientType.TO, to);
	        message.setSubject(titulo);
	        message.setContent(multiParte);
	
	
	        Transport t = session.getTransport("smtp");                  
	        
	        while(!t.isConnected()){
	            t.connect(from, "Daz57292");
	        }
	        if(t.isConnected()){
	            try{
	                t.sendMessage(message, message.getAllRecipients());
	                t.close();
	                return true;
	            }catch(Exception e){
	                while(!t.isConnected()){
	                    t.connect(from, "Daz57292");
	                } 
	                if(t.isConnected()){
	                    t.sendMessage(message, message.getAllRecipients());
	                    t.close();
	                    return true;
	                }
	            }
	        }                        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}	   
}
