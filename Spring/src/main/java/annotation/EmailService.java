package annotation;

import org.springframework.stereotype.Component;
@Component
public class EmailService implements NotificationService {
	public void sendEmail() {
		System.out.println("Through Email..");
	}

	@Override
	public void notification() {
		// TODO Auto-generated method stub
		System.out.println("Email Service");
		
	}
}
