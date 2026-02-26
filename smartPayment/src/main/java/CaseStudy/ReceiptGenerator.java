package CaseStudy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ReceiptGenerator {
	public void generateReceipt() {
		System.out.println("Generating the Receipt");
		System.out.println(this.hashCode());
	}
}
