package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class Test02DefaultValues {
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
        System.out.println("List returned: " + this.roomService.getAvailableRooms());
        System.out.println("Object returned: " + this.roomService.findAvailableRoomId(null));
        System.out.println("Primitive returned: " + this.roomService.getRoomCount());
    }

    @Test
    void shouldCountAvailablePlaces() {
        // Given
        int expected = 0;
        // When
        int actual = bookingService.getAvailablePlaceCount();

        // Then
        assertEquals(expected, actual);
    }
}
