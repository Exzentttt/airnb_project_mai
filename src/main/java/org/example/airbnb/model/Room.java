package org.example.airbnb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Data
public class Room {
    @Id
    private Long id;
    private String address;
    private String description;
    private Long price;
    private GeoJsonPoint location;
}
