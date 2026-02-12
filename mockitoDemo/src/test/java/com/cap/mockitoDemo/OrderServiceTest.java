package com.cap.mockitoDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class OrderServiceTest {

	@Mock
    PaymentService paymentServiceMock;

    @InjectMocks
    OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        System.out.println("\n=== Setting up fresh mocks for next test ===");
    }

    /*
     BASIC TEST WITH STUBBING
    */
    @Test
    @DisplayName("Test successful order placement")
    void testPlaceOrder_Success() {

        //When & then
        when(paymentServiceMock.processPayment(500.0))
                .thenReturn(true);

        String result = orderService.placeOrder(500.0);
        assertEquals("ORDER PLACED", result);

        //Verify
        verify(paymentServiceMock).processPayment(500.0);
    }

    @Test
    @DisplayName("Test failed order placement")
    void testPlaceOrder_Failure() {
        // Stub: Mock returns false for payment
        when(paymentServiceMock.processPayment(300.0))
                .thenReturn(false);

        String result = orderService.placeOrder(300.0);

        assertEquals("PAYMENT FAILED", result);
        verify(paymentServiceMock).processPayment(300.0);
    }
    
    @Test
    @DisplayName("Test with any amount - mock returns true for ANY amount")
    void testPlaceOrder_AnyAmount() {
        when(paymentServiceMock.processPayment(anyDouble()))
                .thenReturn(true);

        String result = orderService.placeOrder(999.99);

        assertEquals("ORDER PLACED", result);
        verify(paymentServiceMock).processPayment(anyDouble());
    }

    /*
    📌 TESTING EXCEPTIONS
    =====================
    */
    @Test
    @DisplayName("Test when payment service throws exception")
    void testPlaceOrder_Exception() {
        /*
        📌 when().thenThrow() - SIMULATE FAILURES
        ==========================================
        */
        when(paymentServiceMock.processPayment(anyDouble()))
                .thenThrow(new RuntimeException("Bank API down!"));

        /*
        📌 assertThrows() - EXPECT EXCEPTION
        ====================================
        */
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> orderService.placeOrder(100.0)
        );

        assertEquals("Bank API down!", exception.getMessage());
        verify(paymentServiceMock).processPayment(anyDouble());
    }
}