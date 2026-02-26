package primary_qualifier;
import org.springframework.stereotype.Component;

@Component

public class PushNotificationService implements NotificationService {

	@Override
	public void sendNotification(String message) {
		System.out.println("Push notification: " + message);
		
	}

}
