package com.mockitotutorial.happyhotel.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Test17MockConstruction {
    private RoomService roomService;

    @Test
    void shouldShowAvailableRoom_whenBigCapacity() {
        try (
                MockedConstruction<Room> mocked = mockConstruction(
                        Room.class, (mock, context) -> {
                            // context.arguments() gives constructor args
                            // example: new MyClass("abc", 123)
                            // context.arguments() would be ["abc", 123].
                            when(mock.getCapacity()).thenReturn(6);
                            when(mock.getId()).thenReturn("5.0");
                        }
                )
        ) {
            this.roomService = new RoomService();
            BookingRequest bookingRequest = new BookingRequest("1", LocalDate.of(2020, 01, 01), LocalDate.of(2020, 01, 05), 6, false);

            String actualRoomId = this.roomService.findAvailableRoomId(bookingRequest);

            assertEquals("5.0", actualRoomId);
        }
    }
}
