package org.example.airbnb.service;

import org.example.airbnb.model.Room;
import org.example.airbnb.repos.RoomRepository;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomService {
    private static final Double RANGE_FOR_POLYGON = 1.5;
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void save(Room room) {
        roomRepository.save(room);
    }

    public void saveAll(Iterable<Room> room) {
        roomRepository.saveAll(room);
    }

    public List<Long> findRooms(Double longitude, double latitude) {
        return roomRepository.findByLocationWithin(new Polygon(
                new Point(longitude - RANGE_FOR_POLYGON, latitude - RANGE_FOR_POLYGON),
                new Point(longitude - RANGE_FOR_POLYGON, latitude + RANGE_FOR_POLYGON),
                new Point(longitude + RANGE_FOR_POLYGON, latitude + RANGE_FOR_POLYGON),
                new Point(longitude + RANGE_FOR_POLYGON, latitude + RANGE_FOR_POLYGON),
                new Point(longitude + RANGE_FOR_POLYGON, latitude - RANGE_FOR_POLYGON),
                new Point(longitude - RANGE_FOR_POLYGON, latitude - RANGE_FOR_POLYGON)
        ))
                .stream()
                .map(Room::getId)
                .collect(Collectors.toList());
    }


    public Iterable<Room> findRoomsByIds(List<Long> ids) {
        return roomRepository.findAllById(ids);
    }
}
