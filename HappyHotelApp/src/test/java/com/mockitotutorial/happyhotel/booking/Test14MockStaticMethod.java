package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Test14MockStaticMethod {
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
    void shouldCalculateCorrectPrice() {
        try(MockedStatic<CurrencyConverter> mockedStatic = mockStatic(CurrencyConverter.class)) {
            // Given
            BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05), 2, false);
            double expected = 400.0;
            mockedStatic.when(() -> CurrencyConverter.toEuro(anyDouble())).thenReturn(400.0);
            // When
            double actual = bookingService.calculatePrice(bookingRequest);
            // Then
            assertEquals(expected, actual);
        }
    }
}
