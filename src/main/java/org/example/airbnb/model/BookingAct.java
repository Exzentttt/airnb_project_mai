package org.example.airbnb.model;

import lombok.Data;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Cacheable
public class BookingAct implements Serializable {
    @Id
    private Long id;
    private Long customerId;
    private Long roomId;
    private BookingStatus status;
    private LocalDate fromDate;
    private LocalDate tillDate;
}
