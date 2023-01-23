package com.exercise.travelAgency;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hotel")
@NoArgsConstructor
@Data
public class Hotel {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;

    private String hotelName, hotelService;

    public Hotel(String hotelName, String hotelService) {
        this.hotelName = hotelName;
        this.hotelService = hotelService;
    }
}