package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class Test08Spies {
    private BookingService bookingService;
    private PaymentService paymentService;
    private RoomService roomService;
    private BookingDAO bookingDAO;
    private MailSender mailSender;

    @BeforeEach
    void setUp() {
        this.paymentService = mock(PaymentService.class);
        this.roomService = mock(RoomService.class);
        this.bookingDAO = spy(BookingDAO.class);
        this.mailSender = mock(MailSender.class);

        this.bookingService = new BookingService(this.paymentService, this.roomService, this.bookingDAO, this.mailSender);
    }

    @Test
    void shouldMakeBooking_whenInputOk() {
        // Given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05), 2, true);
        // When
        String bookingId = this.bookingService.makeBooking(bookingRequest);
        // Then
        verify(this.bookingDAO).save(bookingRequest);
        System.out.println("BookingId: " + bookingId);
    }

    @Test
    void shouldCancelBooking_whenInputOk() {
        // Given
        BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05), 2, true);
        bookingRequest.setRoomId("1.3");
        String bookingId = "1";
        // When
        // this.bookingService.cancelBooking(bookingId);
        // the above line will cause an exception because the cancelBooking method
        // will actually try to get the real booking request using an ID that does not exist
        // this will result in a null, the code in cancelBooking will then try to do an operation
        // on said null which will throw a NullPointerException
        doReturn(bookingRequest).when(bookingDAO).get(bookingId);
        // bookingDAO has a get method that fetches the booking request using a given booking ID
        // it is this method that ends up returning the null
        // here, we are telling it, that, in case I am passing bookingId = "1",
        // even if the booking ID does not exist in the list of booking, return the bookingRequest
        // instance I created
        // Then
    }
}
