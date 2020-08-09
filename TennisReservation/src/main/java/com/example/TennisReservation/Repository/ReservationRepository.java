package com.example.TennisReservation.Repository;

import com.example.TennisReservation.Model.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findByReservationId(Long id);
}