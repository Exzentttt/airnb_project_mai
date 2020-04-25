package org.example.airbnb;

import com.mongodb.client.MongoClients;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.airbnb.model.Room;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import static org.springframework.data.mongodb.core.query.Criteria.where;


@SpringBootApplication
public class Application {
    private static final Log log = LogFactory.getLog(Application.class);

    public static void main(String[] args) {
        mongocheck();
        SpringApplication.run(Application.class, args);
    }

    private static void mongocheck() {
        MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "database");
        Room t = new Room();
        t.setId(2L);
        t.setDescription("desc");
        mongoOps.insert(t);

        log.info(mongoOps.findOne(new Query(where("description").is("desc")), Room.class));

        mongoOps.dropCollection("room");
    }
}
