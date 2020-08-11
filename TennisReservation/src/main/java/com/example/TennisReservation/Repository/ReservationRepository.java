package com.example.TennisReservation.Repository;

import java.util.List;

import com.example.TennisReservation.Model.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation findByReservationId(Long id);
}