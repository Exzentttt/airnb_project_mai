package org.example.airbnb.repos;

import org.example.airbnb.model.Room;
import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoomRepository extends MongoRepository<Room, Long> {
    List<Room> findByLocationWithin(Polygon polygon);
}
