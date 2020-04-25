package org.example.airbnb.controller;

import org.example.airbnb.dto.BookingFilter;
import org.example.airbnb.model.Room;
import org.example.airbnb.service.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

@RestController
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/room/booking/search")
    public Iterable<Room> checkRoomsAvailability(@RequestBody BookingFilter bookingFilter) {

        return bookingService.checkRoomsAvailability(
                bookingFilter.getRoomLocation(),
                bookingFilter.getFrom(),
                bookingFilter.getTill()
        );
    }

    @PostMapping("/room/booking/prepare")
    public Long bookRoom(Long roomId, Long customerId, LocalDate from, LocalDate till) {
        return bookingService.bookRoom(roomId, customerId, from, till);
    }

    @PostMapping("/room/booking/pay")
    public boolean payForBooking(Long bookingId) {
        return bookingService.payForBooking(bookingId);
    }
}
