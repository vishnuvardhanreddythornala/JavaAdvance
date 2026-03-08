package test.Com.cap;

import main.Com.cap.Calculator;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

public class CalculatorTest {

    Calculator calc;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
    }

    @Test 
    void testAdd() {
        assertEquals(5, calc.add(2, 3)); //assertEquals
    }
    void testAdd1() {
    	assertEquals(calc.add(2, 3),6); //assertNotEquals
    }

    @Test
    void testSub() {
        assertEquals(-1, calc.Sub(2, 3));
    }
    

    @Test
    void testIsEven() {
        assertTrue(calc.isEven(4)); //assertTrue
    }
    @Test
    void testIsEven1() {
        assertFalse(calc.isEven(3)); //assertFalse
    }
    
//    @Test
//    void testDivideByZero() {
//        IllegalArgumentException ex = assertThrows(
//            IllegalArgumentException.class,() -> calc.divide(10, 0)
//        );
//    }


    @Test
    void testDivide() {
//        assertEquals(2, calc.divide(4, 2));
    	ArithmeticException exception = assertThrows(ArithmeticException.class, () -> calc.divide(8, 0)); //assertThrows
    }
    
    @Test
    void testOdd() {
    	assertNull(calc.isOdd(6)); //assertNull
    }
    
    @Test 
    void testOdd1() {
    	assertNotNull(calc.isOdd(3)); //assertNotNull
    }
    
    @Test
    void testSub2() {
    	assertSame(calc.Sub(2, 3),-1);//assertSame()

    }
    @Test
    void testSub3() {
    	assertNotSame(calc.Sub(2, 3),2);//assertNotSame()

    }
    
    //parameterizedTest
    @ParameterizedTest
    @CsvSource({
    	"2, 3, 5",
    	"0,0,0",
    	"-5, 10, 5",
    	"100, 200, 300"
    })
    public void testAddParameterized(int a, int b, int expected) {
    	assertEquals(expected, calc.add(a,b));
    }
    
    // parameterized test for isEven method using ValueSource
    @ParameterizedTest
    @ValueSource(ints = {2,4,6,8,10,0,-2,-4})
    public void testIsEvenReturnsTrue(int number) {
    	assertTrue(calc.isEven(number));
    }
    @ParameterizedTest
    @ValueSource(ints = {1,3,5,7,9,11,-3,-5})
    public void testIsEvenReturnsFalse(int number) {
    	assertFalse(calc.isEven(number));
    }
    
    //Parameterized test with methodSource
    @ParameterizedTest
    @MethodSource("provideDivisionTestCases")
    public void testDivisionWithMethodSource(int a, int b, int expected) {
    	assertEquals(expected, calc.divide(a, b));
    }
    	private static Stream<Arguments> provideDivisionTestCases(){
    		return Stream.of(
    				Arguments.of(25,5,5),
    				Arguments.of(15,5,3),
    				Arguments.of(0,7,0),
    				Arguments.of(100,25,4)
    				);
    }
    @ParameterizedTest
    @CsvFileSource(files = "test_data/add.csv", numLinesToSkip = 1)
    public void simpleAddTest(int a, int b, int expected) {
    	   System.out.println("Simple @CsvFileSource test: " + a + " " + b);
    	   assertEquals(expected, calc.add(a, b));
    }
}