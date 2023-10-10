package appdashboard3b.modelos;

import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class Mcorreo {
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
}
