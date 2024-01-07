package in.Student.service.mailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
@Component
public class MailSenders {
	@Autowired
    JavaMailSender mail ;
	public void send(String email , String password) {
		
		SimpleMailMessage message = new SimpleMailMessage() ;
		message.setTo(email);
		message.setSubject("password recovery");
		message.setText("This is your password of my-application /n  PASSWORD : " +password );
		
		mail.send(message);
		
		
	}
}
