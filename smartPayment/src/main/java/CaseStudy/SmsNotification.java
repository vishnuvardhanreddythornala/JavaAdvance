package CaseStudy;
import org.springframework.stereotype.Component;

@Component

public class SmsNotification implements NotificationServices {

	@Override
	public void sendMessage() {
		System.out.println("Notification through SMS ");
		
	}

}
