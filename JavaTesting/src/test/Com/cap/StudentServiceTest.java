package test.Com.cap;

import main.Com.cap.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    StudentService service;

    @BeforeEach
    void setUp() {
        service = new StudentService();
    }
    
    // 1. assertTrue
    @Test
    void testEligibleAge() {
        assertTrue(service.isEligible(20));
    }
    
    // 2. assertFalse
    @Test
    void testNotEligibleAge() {
    	assertFalse(service.isEligible(16));
    }
    
    //3.assertEquals
    @Test
    void testEqualsEligibleAge() {
    	assertEquals(true,service.isEligible(18));
    }
    
    //4.assertNotEquals
    @Test
    void testNotEqualsEligibleAge() {
    	assertNotEquals(true, service.isEligible(16));
    }
    
    //5.assertNotNull
    @Test
    void testNotNullAge() {
    	assertNotNull(service);
    }
    
    //6.assertSame
    @Test
	void isAgeSame() {
		Boolean flag1 = true;
		Boolean flag2 = service.isEligible(33);
		assertSame(flag1, flag2);
	}
	
    //7.assertNotSame
	@Test
	void isAgeNotSame() {
		Boolean flag1 = false;
		boolean flag2 = service.isEligible(33);
		assertNotSame(flag1, flag2);
	}
	
	//8.assertAll
	@Test
	void isMultipleAge() {
		 assertAll(
				 ()-> assertTrue(service.isEligible(48)),
				 ()-> assertFalse(service.isEligible(9)),
				 ()-> assertNotNull(service)
		);
	}
	
	//9.assertthrows
	@Test
	void isAgeValid() {
		assertThrows(ArithmeticException.class, ()->service.isEligible(-9));
	}
	
	//10.fail
	@Test
	void testFailUsage() {
	    try {
	        service.isEligible(-1);
	    } catch (ArithmeticException e) {
	        return; 
	    }
	    fail("Exception expected for negative age");
	}
    
    
}