package com.example.TennisReservation.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.TennisReservation.Model.Distribution;
import com.example.TennisReservation.Model.Park;
import com.example.TennisReservation.Model.Reservation;
import com.example.TennisReservation.Repository.DistributionRepository;
import com.example.TennisReservation.Repository.ParkRepository;
import com.example.TennisReservation.Repository.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationrepository;

    private DistributionRepository distributionrepository;

    private ParkRepository parkrepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationrepository) {
        this.reservationrepository = reservationrepository;
    }

    @Override
    public List<Reservation> listAll() {
        List<Reservation> res = new ArrayList<>();
        reservationrepository.findAll().forEach(res::add);
        return res;
    }

    @Override
    public Reservation getByReservationId(Long id) {
        return reservationrepository.findByReservationId(id);
    }

    @Override
    public List<Reservation> getReservationByDate(String date) {
        return reservationrepository.findByDateEqualTo(date);
    }

    @Override
    public long getReservationCountByDate(String date) {
        return reservationrepository.countBydate(date);
    }

    @Override
    public List<Reservation> getReservationByDateRange(String date1, String date2) {
        return reservationrepository.findByDateRange(date1, date2);
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        reservationrepository.save(reservation);
        return reservation;
        
    }

    @Override
    public List<Park> getParkData(String park) {
        return parkrepository.getParkData(park);
    }

    @Override
    public List<Distribution> getCourtDistributionByDate(String date) {
        return distributionrepository.getCourtDistributionByDate(date);
    }


    @Override
    public String getNewestFile() {
        File directory = new File("/Users/ajaypatel/Desktop/TennisReservation/TennisReservation/frontend/public/confirmation");
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;

        if (files != null) {
            for (File file : files) {
                if (file.lastModified() > lastModifiedTime) {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }
        return chosenFile.getName();
    }
    
}