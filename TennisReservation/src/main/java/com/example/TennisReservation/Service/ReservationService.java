package com.example.TennisReservation.Service;

import java.util.List;

import com.example.TennisReservation.Model.Reservation;

import org.springframework.stereotype.Service;


@Service
public interface ReservationService {
    List<Reservation> listAll();

    Long getLast();
    
    Reservation getByReservationId(Long id);

    Reservation addReservation(Reservation reservation);
    
    String getNewestFile();
		
}