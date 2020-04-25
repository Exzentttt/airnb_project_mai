package org.example.airbnb.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import static org.springframework.data.elasticsearch.annotations.FieldType.Date;

@Data
@Builder
@Document(indexName = "room-index", type = "room-type", shards = 1, replicas = 0, refreshInterval = "-1")
@AllArgsConstructor
@NoArgsConstructor
public class EsRoomBookedDates {
    private @Id
    Long id;
    private Long roomId;
    private @Field(type = Date) String date;
    private Long bookingActId;
}
