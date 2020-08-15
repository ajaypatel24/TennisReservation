package com.example.TennisReservation.Service;

import java.util.List;
import java.util.Map;

import com.example.TennisReservation.Model.Reservation;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;


@Service
public interface ReservationService {
    
    List<Reservation> listAll();

    /*
    @Query(value = "select * from reservation order by reservation_id LIMIT 1")
    Map<String, Long> getLast();
    */
    
    Reservation getByReservationId(Long id);

    Reservation addReservation(Reservation reservation);
    
    String getNewestFile();
        
    long getReservationCountByDate(String date);

    List<Reservation> getReservationByDate(String date);

    List<Reservation> getReservationByDateRange(String date1, String date2);
}