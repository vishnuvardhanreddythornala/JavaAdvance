package annotation;

import org.springframework.stereotype.Component;
@Component
public class SmsNotificationService implements NotificationService {
	public void sendSMS() {
		System.out.println("sending SMS");
	}

	@Override
	public void notification() {
		// TODO Auto-generated method stub
		System.out.println("SMS Service");
		
	}

}
