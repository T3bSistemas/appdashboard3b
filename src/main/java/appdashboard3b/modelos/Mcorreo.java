package appdashboard3b.modelos;

import javax.mail.internet.MimeMessage;

import org.springframework.core.io.InputStreamSource;
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

    public boolean sendWithAttach(String from, String to, String subject,
                               String text, String attachName,
                               InputStreamSource inputStream){
    	try {
    		 MimeMessage message = mailSender.createMimeMessage();
    	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
    	        helper.setFrom(from);
    	        helper.setTo(to);
    	        helper.setSubject(subject);
    	        helper.setText(text, true);
    	        //helper.addAttachment(attachName, inputStream);
    	        mailSender.send(message);
    	        return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return false;
       
    }
}
