package org.example.airbnb.service;

import org.example.airbnb.dto.RoomFilter;
import org.example.airbnb.model.BookingAct;
import org.example.airbnb.model.BookingStatus;
import org.example.airbnb.model.Room;
import org.example.airbnb.repos.EsRoomRepository;
import org.example.airbnb.repos.HzBookingRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookingService {
    private final RoomService roomService;
    private final EsRoomRepository esRoomRepository;
    private final HzBookingRepository hzBookingRepository;

    public BookingService(RoomService roomService, EsRoomRepository esRoomRepository, HzBookingRepository hzBookingRepository) {
        this.roomService = roomService;
        this.esRoomRepository = esRoomRepository;
        this.hzBookingRepository = hzBookingRepository;
    }

    public Iterable<Room> checkRoomsAvailability(RoomFilter roomFilter, LocalDate from, LocalDate till) {
        List<Long> rooms = roomService.findRooms(roomFilter.getLongitude(), roomFilter.getLatitude());

        List<Long> byDatesNoCross = esRoomRepository.findByRoomIdAndDateBetween(rooms, from, till);
        List<BookingAct> byHzCst = hzBookingRepository.findByFromAndTillAndRoomIds(from, till, byDatesNoCross);
        List<Long> lockedRoomsByHzCst = byHzCst.stream().map(BookingAct::getRoomId).collect(Collectors.toList());
        return roomService.findRoomsByIds(
                byDatesNoCross.stream()
                        .filter(lockedRoomsByHzCst::contains)
                        .collect(
                                Collectors.toList()
                        )
        );
    }

    public Long bookRoom(Long roomId, Long customerId, LocalDate from, LocalDate till) {
        BookingAct bookingAct = new BookingAct();
        bookingAct.setCustomerId(customerId);
        bookingAct.setRoomId(roomId);
        bookingAct.setFromDate(from);
        bookingAct.setTillDate(till);
        bookingAct.setStatus(BookingStatus.NON_PAYED);
        BookingAct save = hzBookingRepository.save(bookingAct);
        return save.getId();
    }

    public boolean payForBooking(Long bookingId) {
        Optional<BookingAct> byId = hzBookingRepository.findById(bookingId);
        if (byId.isPresent()) {
            byId.get().setStatus(BookingStatus.PAYED);
            return true;
        }
        return false;

    }
}
