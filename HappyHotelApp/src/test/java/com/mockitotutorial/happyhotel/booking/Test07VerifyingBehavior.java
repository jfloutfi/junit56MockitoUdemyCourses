package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class Test07VerifyingBehavior {
    private BookingService bookingService;
    private PaymentService paymentService;
    private RoomService roomService;
    private BookingDAO bookingDAO;
    private MailSender mailSender;

    @BeforeEach
    void setUp() {
        this.paymentService = mock(PaymentService.class);
        this.roomService = mock(RoomService.class);
        this.bookingDAO = mock(BookingDAO.class);
        this.mailSender = mock(MailSender.class);

        this.bookingService = new BookingService(this.paymentService, this.roomService, this.bookingDAO, this.mailSender);
    }

    @Test
    void shouldInvokePaymentMethod_whenPrepaid() {
        // Given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05), 2, true);
        // When
        this.bookingService.makeBooking(bookingRequest);
        // Then
        verify(this.paymentService, times(1)).pay(bookingRequest, 400.0);
        verifyNoMoreInteractions(this.paymentService);
    }

    @Test
    void shouldNotInvokePaymentMethod_whenNotPrepaid() {
        // Given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05), 2, false);
        // When
        this.bookingService.makeBooking(bookingRequest);
        // Then
        verify(this.paymentService, never()).pay(any(), anyDouble());
    }
}
