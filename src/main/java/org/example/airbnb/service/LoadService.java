package org.example.airbnb.service;

import org.springframework.stereotype.Component;

@Component
public class LoadService {
    public void loadAllRooms() {
        //load from airbnb
        //save to mongodb
    }

    public void loadAllUsers() {
        //load all users
        //save to mongodb
    }

    public void generateBookings() {
        //for 1-1000000
        //find user
        //find room
        //create booking
        //save to mongo and es
    }
}
