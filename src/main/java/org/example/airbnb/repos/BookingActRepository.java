package org.example.airbnb.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingActRepository extends MongoRepository<BookingActRepository, Long> {
}
