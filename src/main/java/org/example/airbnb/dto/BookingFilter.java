package org.example.airbnb.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class BookingFilter {
    private LocalDate from;
    private LocalDate till;
    private RoomFilter roomLocation;
    private List<Long> roomIds;
}
