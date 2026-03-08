package annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component

public class MessageService {
	
	@Autowired
	private EmailService emailService;
	private SmsNotificationService smsService;
	public void sendMessage() {
		System.out.println("Sending message...");
		emailService.sendEmail();
		smsService.sendSMS();
		
	}
	
	public MessageService(EmailService em, SmsNotificationService sm) {
		emailService = em;
		smsService = sm;
		
	}
	public EmailService getEmailService() {
		return emailService;
	}
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	public SmsNotificationService getSmsService() {
		return smsService;
	}
	public void setSmsService(SmsNotificationService smsService) {
		this.smsService = smsService;
	}
}
