package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Test11Annotations {
    @InjectMocks
    private BookingService bookingService;

    @Mock
    private PaymentService paymentService;

    @Mock
    private RoomService roomService;

    @Mock
    private BookingDAO bookingDAO;

    @Mock
    private MailSender mailSender;

    @Captor
    private ArgumentCaptor<Double> doubleArgumentCaptor;
    
    @Test
    void shouldPayCorrectPrice_whenInputCorrect() {
        // Given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05), 2, true);
        // When
        this.bookingService.makeBooking(bookingRequest);
        // Then
        verify(this.paymentService, times(1)).pay(eq(bookingRequest), this.doubleArgumentCaptor.capture());
        double capturedArgument = this.doubleArgumentCaptor.getValue();
        System.out.println(capturedArgument);
    }

    @Test
    void shouldPayCorrectPrice_whenInputCorrect_MultipleCalls() {
        // Given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05), 2, true);
        BookingRequest bookingRequest2 = new BookingRequest("2", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 02), 2, true);
        // When
        this.bookingService.makeBooking(bookingRequest);
        this.bookingService.makeBooking(bookingRequest2);
        // Then
        verify(this.paymentService, times(2)).pay(any(), this.doubleArgumentCaptor.capture());
        List<Double> capturedArgument = this.doubleArgumentCaptor.getAllValues();
        System.out.println(capturedArgument);
    }
}
