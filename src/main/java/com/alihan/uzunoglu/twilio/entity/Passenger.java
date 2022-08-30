package com.alihan.uzunoglu.twilio.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "passenger")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int age;

    private String phoneNumber;

    private String email;

    private float latitude;

    private float longitude;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id", nullable = true)
    private Driver driver;
}
