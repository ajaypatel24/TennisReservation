package com.example.TennisReservation.Repository;

import java.util.List;

import com.example.TennisReservation.Model.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation findByReservationId(Long id);

    @Query(value = "SELECT * FROM reservation WHERE date = ?1 ORDER BY date", nativeQuery = true)
    List<Reservation> findByDateEqualTo(String date);

    @Query(value = "SELECT * FROM reservation WHERE date > ?1 AND date < ?2 ORDER BY date", nativeQuery = true)
    List<Reservation> findByDateRange(String date1, String date2);

}