package primary_qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class NotificationManager {

    private final NotificationService primaryService;
    private final NotificationService emailService;
    private final NotificationService pushService;

    @Autowired
    public NotificationManager(

            NotificationService primaryService,

            @Qualifier("emailNotificationService")
            NotificationService emailService,

            @Qualifier("pushNotificationService")
            NotificationService pushService
    ) {
        this.primaryService = primaryService;
        this.emailService = emailService;
        this.pushService = pushService;
    }

    public void sendNotifications() {

        System.out.println("--- Using @Primary Bean ---");
        primaryService.sendNotification("Hello via Primary!");

        System.out.println("--- Using Email Bean ---");
        emailService.sendNotification("Hello via Email!");

        System.out.println("--- Using Push Bean ---");
        pushService.sendNotification("Hello via Push!");
    }
}