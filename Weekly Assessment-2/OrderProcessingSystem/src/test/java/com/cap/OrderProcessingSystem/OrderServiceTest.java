package com.cap.OrderProcessingSystem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    PaymentGateway paymentGateway;

    @InjectMocks
    OrderService orderService;

    @Test
    void testSuccessfulPayment() {
        when(paymentGateway.processPayment(2000)).thenReturn(true);
        String result = orderService.placeOrder(2000);
        assertEquals("Order Confirmed", result);
        verify(paymentGateway, times(1)).processPayment(2000);
    }

    @Test
    void testPaymentFailure() {
        when(paymentGateway.processPayment(1500)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> orderService.placeOrder(1500));
        verify(paymentGateway, times(1)).processPayment(1500);
    }

    @Test
    void testInvalidOrderAmount() {
        assertThrows(IllegalArgumentException.class, () -> orderService.placeOrder(0));
        verify(paymentGateway, never()).processPayment(anyDouble());
    }
}