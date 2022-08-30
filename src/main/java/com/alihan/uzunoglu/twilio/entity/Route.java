package com.alihan.uzunoglu.twilio.entity;


import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "route")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @NonNull
    private Float driverLatitude;
    @NonNull
    private Float driverLongitude;
    @NonNull
    private Float passengerLatitude;
    @NonNull
    private Float passengerLongitude;

    @OneToOne(mappedBy = "route")
    @ToString.Exclude
    private Driver driver;

}
