package org.example.airbnb.controller;

import org.example.airbnb.dto.RoomFilter;
import org.example.airbnb.model.Room;
import org.example.airbnb.service.RoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/room/location")
    public List<Long> findRooms(
            @RequestBody RoomFilter roomFilter
    ) {
        return roomService.findRooms(roomFilter.getLongitude(), roomFilter.getLatitude());
    }
}
