package com.alihan.uzunoglu.twilio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "driver")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int age;

    private String phoneNumber;

    private String experience;

    private String email;

    @Lob
    private byte[] driverPhoto;

    private String carMake;

    private String carModel;

    @Lob
    private byte[] carPhoto;

    private String carTag;

    private float latitude;

    private float longitude;


    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = false, mappedBy = "driver")
    private Set<Passenger> passengers = new HashSet<>();

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name = "route_id")
    private Route route;

    public Driver(String name, int age, String phoneNumber, String experience, String email, String carMake, String carModel, String carTag, Float latitude, Float longitude) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.experience = experience;
        this.email = email;
        this.carMake = carMake;
        this.carModel = carModel;
        this.carTag = carTag;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
