package com.alihan.uzunoglu.twilio.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "experience")
    private String experience;

    @Column(name = "email")
    private String email;

    @Lob
    @Column(name = "driverPhoto")
    private byte[] driverPhoto;

    @Column(name = "carMake")
    private String carMake;

    @Column(name = "carModel")
    private String carModel;

    @Lob
    @Column(name = "carPhoto")
    private byte[] carPhoto;

    @Column(name = "carTag")
    private String carTag;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude")
    private Float longitude;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Passenger> passengers = new ArrayList<>();

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Driver() {

    }

    public Driver(String name, int age, String phoneNumber, String experience, String email, String carMake, String carModel, String carTag) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.experience = experience;
        this.email = email;
        this.carMake = carMake;
        this.carModel = carModel;
        this.carTag = carTag;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public byte[] getDriverPhoto() {
        return driverPhoto;
    }

    public void setDriverPhoto(byte[] driverPhoto) {
        this.driverPhoto = driverPhoto;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public byte[] getCarPhoto() {
        return carPhoto;
    }

    public void setCarPhoto(byte[] carPhoto) {
        this.carPhoto = carPhoto;
    }

    public String getCarTag() {
        return carTag;
    }

    public void setCarTag(String carTag) {
        this.carTag = carTag;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", experience='" + experience + '\'' +
                ", email='" + email + '\'' +
                ", driverPhoto=" + Arrays.toString(driverPhoto) +
                ", carMake='" + carMake + '\'' +
                ", carModel='" + carModel + '\'' +
                ", carPhoto=" + Arrays.toString(carPhoto) +
                ", carTag='" + carTag + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
