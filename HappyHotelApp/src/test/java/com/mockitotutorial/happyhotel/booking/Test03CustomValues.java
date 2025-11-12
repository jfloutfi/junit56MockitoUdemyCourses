package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Test03CustomValues {
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
    void shouldCountAvailablePlaces_whenOneRoomIsAvailable() {
        // Given
        when(this.roomService.getAvailableRooms()).thenReturn(Collections.singletonList(new Room("1", 2)));
        int expected = 2;
        // When
        int actual = this.bookingService.getAvailablePlaceCount();
        // Then
        assertEquals(expected, actual);
    }

    @Test
    void shouldCountAvailablePlaces_whenMultipleRoomsAreAvailable() {
        // Given
        List<Room> rooms = Arrays.asList(new Room("1", 2), new Room("2", 5));
        when(this.roomService.getAvailableRooms()).thenReturn(rooms);
        int expected = 7;
        // When
        int actual = this.bookingService.getAvailablePlaceCount();
        // Then
        assertEquals(expected, actual);
    }
}
