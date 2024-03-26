package appdashboard3b;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import appdashboard3b.utelerias.Codificacion;

import java.util.Properties;

@Configuration
public class MailSenderConfig {
	@Autowired
	Environment env;
	
	@Bean("javaMailSender")
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(env.getProperty("spring.mail.host"));
        sender.setPort(Integer.parseInt(env.getProperty("spring.mail.port")));
        sender.setUsername(env.getProperty("spring.mail.username"));
        sender.setPassword(new Codificacion().toBase64String(env.getProperty("spring.mail.password")));

        Properties props = sender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return sender;
    }
}
