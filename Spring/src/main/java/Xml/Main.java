package Xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
    	
    	//specifying the path
        ApplicationContext context =new ClassPathXmlApplicationContext("bean.xml");

        MessageService messageService =context.getBean(MessageService.class);
        MessageService messageService1 =context.getBean(MessageService.class);
        
        System.out.println("=== Singleton ===");
       
        //Same instance reused,Same memory reference,Same hashcode
        //Container start → Bean created once → Reused everywhere → Destroyed at shutdown
        //Example: Everyone uses same printer
         
        messageService.sendMessage();
        //messageService1.sendMessage();
        System.out.println();
        System.out.println(messageService);
        System.out.println(messageService1); //one object
        
        EmailService emailService =context.getBean(EmailService.class);
        EmailService emailService1 =context.getBean(EmailService.class);
        
        System.out.println("=== prototype ==="); // we need to specify score as prototype
        //spring creates new object every time when we call getBean()
        //Example: while having coffee we have to take new cup everytime
        emailService.sendEmail();
        //emailService1.sendEmail();
       
        System.out.println(emailService); 
        System.out.println(emailService1); //different objects
        

        ((ClassPathXmlApplicationContext) context).close();
    }
}