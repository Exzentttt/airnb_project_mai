package org.example.airbnb.repos;

import org.example.airbnb.model.EsRoomBookedDates;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.time.LocalDate;
import java.util.List;

public interface EsRoomRepository extends ElasticsearchRepository<EsRoomBookedDates, Long> {

    List<Long> findByRoomIdAndDateBetween(List<Long> roomIds, LocalDate dateStart, LocalDate dateEnd);
}
