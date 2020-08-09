package com.example.TennisReservation.Model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
	public Integer reservationId;

    private int court;

    private Date date;

    public String confirmationPDF;



}