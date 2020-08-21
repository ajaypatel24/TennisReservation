package com.example.TennisReservation.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ParkInformation")
public class Park {
    
    @Id
    @Column(name = "park", nullable = false)
    private String park;
    
    private String shortPark;

    private String address; 

    private String phone;

    //getters to show in query
    public String getPark() {
        return park;
    }

    public String getShortPark() {
        return shortPark;
    }

    public String getAddress() {
        return address;
    }
    public String getPhoneNumber() {
        return phone;
    }

    //setters
    public void setPark(String park) {
        this.park = park;
    }

    public void setShortPark(String shortpark) {
        this.shortPark = shortpark;
    }

    public void setDate(String address) {
        this.address = address;
    }

    public void setTime(String phone) {
        this.phone = phone;
    }



}