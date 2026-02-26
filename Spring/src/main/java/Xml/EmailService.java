package Xml;

public class EmailService {
	
	public EmailService() {
		System.out.println("1. Constructor Called");
	}
	public void init() {
		System.out.println("2. Init method called");
		
	}
	public void destroy() {
		System.out.println("3. Destroy method called");
	}

    public  void sendEmail() {
        System.out.println("Email sent");
    }
}