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
@Table(name="Reservation")
public class Reservation {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    @Column(columnDefinition="serial")
    private Long reservationId;

    private String park;
    
    private int court; 

    private String date;

    private String confirmationPDF;

    private String time;



    //getters to show in query
    public Long getReservationId() {
        return reservationId;
    }

    public String getPark() {
        return park;
    }
    public int getCourt() {
        return court;
    }

    public String getDate() {
        return date;
    }

    public String getConfirmationPDF() {
        return confirmationPDF;
    }

    public String getTime() {
        return time;
    }

    //setters
    public void setPark(String park) {
        this.park = park;
    }
    public void setConfirmationPDF(String confirmationPDF) {
        this.confirmationPDF = confirmationPDF;
    }

    public void setCourt(int court) {
        this.court = court;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }



}