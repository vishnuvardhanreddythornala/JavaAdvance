package primary_qualifier;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context =new AnnotationConfigApplicationContext(PrimaryQualifierConfig.class);

        NotificationManager manager = context.getBean(NotificationManager.class);
        manager.sendNotifications();
    }
}