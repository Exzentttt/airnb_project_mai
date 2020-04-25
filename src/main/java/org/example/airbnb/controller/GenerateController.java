package org.example.airbnb.controller;

import org.example.airbnb.service.LoadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateController {
    private final LoadService service;

    public GenerateController(LoadService service) {
        this.service = service;
    }

    @PostMapping("/generateTestData")
    public void generate(){
        service.loadAllUsers();
        service.loadAllRooms();
        service.generateBookings();
    }
}
