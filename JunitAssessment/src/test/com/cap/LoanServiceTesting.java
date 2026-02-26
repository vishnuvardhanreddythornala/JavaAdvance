package test.com.cap;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.com.cap.LoanService;

class LoanServiceTesting {
	LoanService service;
	
	@BeforeEach
	void setUp() {
	    service = new LoanService();
	}
    @Test
    void testValidEligibility() {
        assertTrue(service.isEligible(30, 45000));
    }
    @Test
    void testInvalidAgeYounger() {
        assertFalse(service.isEligible(18, 50000));
    }
    @Test
    void testInvalidAgeElder() {
    	assertFalse(service.isEligible(65, 60000));
    }
    @Test
    void testInvalidSalary() {
    	assertFalse(service.isEligible(30,20000));
    	
    }
    @Test
    void testBoundaryAge() {
        assertAll(
            () -> assertTrue(service.isEligible(21, 25000)),
            () -> assertTrue(service.isEligible(60, 25000))
        );
        
    }
    @Test
    void testValidEMI() {
        double EMI = service.calculateEMI(120000, 1);
        assertEquals(10000, EMI);
    }
    @Test
    void testInvalidLoanAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculateEMI(0, 5);
        });
    }
    @Test
    void testInvalidTenure() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.calculateEMI(120000, 0);
        });
    }
    @Test
    void testPremiumCategory() {
        assertEquals("Premium", service.getLoanCategory(800));
    }
    @Test
    void testStandardCategory() {
    	assertEquals("Standard", service.getLoanCategory(700));
    }
    @Test
    void testHighRiskCategory() {
    	assertEquals("High Risk", service.getLoanCategory(500));
    	
    }
    @Test
    void testCategoryNotNull() {
        assertNotNull(service.getLoanCategory(650));
    }


}
