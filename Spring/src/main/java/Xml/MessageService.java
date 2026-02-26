package Xml;

public class MessageService {

    private EmailService emailservice;
    

    public EmailService getEmailservice() {
        return emailservice;
    }

    public void setEmailservice(EmailService emailservice) {
        this.emailservice = emailservice;
    }

    public void sendMessage() {
        System.out.println("Message being sent!!");
        emailservice.sendEmail();   // calling dependency
    }
}