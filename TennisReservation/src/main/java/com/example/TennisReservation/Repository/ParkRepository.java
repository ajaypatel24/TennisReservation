package com.example.TennisReservation.Repository;

import java.util.List;

import com.example.TennisReservation.Model.Park;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkRepository extends JpaRepository<Park, Long> {

    @Query(value = "SELECT * FROM park_information WHERE short_park = ?1", nativeQuery = true)
    List<Park> getParkData(String park);

}