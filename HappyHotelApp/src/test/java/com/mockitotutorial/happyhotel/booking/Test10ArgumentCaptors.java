package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;

public class Test10ArgumentCaptors {
    private BookingService bookingService;
    private PaymentService paymentService;
    private RoomService roomService;
    private BookingDAO bookingDAO;
    private MailSender mailSender;
    private ArgumentCaptor<Double> doubleArgumentCaptor;

    @BeforeEach
    void setUp() {
        this.paymentService = mock(PaymentService.class);
        this.roomService = mock(RoomService.class);
        this.bookingDAO = mock(BookingDAO.class);
        this.mailSender = mock(MailSender.class);

        this.bookingService = new BookingService(this.paymentService, this.roomService, this.bookingDAO, this.mailSender);

        this.doubleArgumentCaptor = ArgumentCaptor.forClass(Double.class);
    }

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
