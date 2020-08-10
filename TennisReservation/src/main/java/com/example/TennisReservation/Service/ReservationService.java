package com.example.TennisReservation.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.example.TennisReservation.Model.Reservation;

import org.springframework.stereotype.Service;


@Service
public interface ReservationService {
    List<Reservation> listAll();

    Reservation getByReservationId(Long id);

    Long addReservation(int Court, String ConfirmationPDF, LocalDate date);
	
		
}