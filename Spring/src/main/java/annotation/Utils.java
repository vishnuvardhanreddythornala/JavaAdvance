
package annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class  Utils {

    @Autowired
    @Qualifier("emailService")
    NotificationService sms;
    
    
}
