package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class Test09MockingVoidMethods {
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
    void shouldThrowException_whenMailNotReady() {
        // Given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05), 2, false);
        // when(this.roomService.findAvailableRoomId(bookingRequest)).thenThrow(BusinessException.class);
        doThrow(BusinessException.class).when(this.mailSender).sendBookingConfirmation(any());
        // When
        Executable executable = () -> this.bookingService.makeBooking(bookingRequest);
        // Then
        assertThrows(BusinessException.class, executable);
    }

    @Test
    void shouldNotThrowException_whenNoRoomAvailable() {
        // Given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05), 2, false);
        // when(this.roomService.findAvailableRoomId(bookingRequest)).thenThrow(BusinessException.class);
        doNothing().when(this.mailSender).sendBookingConfirmation(any());
        // When
        this.bookingService.makeBooking(bookingRequest);
        // Then
        // no exception is thrown
    }
}
