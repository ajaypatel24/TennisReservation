package com.example.TennisReservation.Repository;

import java.util.List;
import java.util.Map;

import com.example.TennisReservation.Model.Distribution;
import com.example.TennisReservation.Model.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributionRepository extends JpaRepository<Distribution, Long> {

    @Query(value = "SELECT court, count(*) as count FROM reservation WHERE date = ?1 GROUP BY court ORDER BY court", nativeQuery = true)
    List<Distribution> getCourtDistributionByDate(String date);

}