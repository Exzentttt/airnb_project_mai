package org.example.airbnb.repos;

import org.example.airbnb.model.BookingAct;
import org.springframework.data.hazelcast.repository.HazelcastRepository;

import java.time.LocalDate;
import java.util.List;

public interface HzBookingRepository extends HazelcastRepository<BookingAct, Long> {
    List<BookingAct> findByFromAndTillAndRoomIds(LocalDate from, LocalDate till, List<Long> roomIds);
}
