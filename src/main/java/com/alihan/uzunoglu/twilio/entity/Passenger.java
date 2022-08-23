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

    private Float latitude;

    private Float longitude;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private Driver driver;
}
