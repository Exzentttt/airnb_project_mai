package org.example.airbnb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Customer {
    @Id
    private Long id;
    private String fio;

}
