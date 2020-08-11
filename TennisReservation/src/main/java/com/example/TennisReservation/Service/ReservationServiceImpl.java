package com.example.TennisReservation.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.TennisReservation.Model.Reservation;
import com.example.TennisReservation.Repository.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationrepository;

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

    /*
    @Override
    public Map<String,Long> getLast() { //optimize
        List<Reservation> res = new ArrayList<>();
        reservationrepository.findAll().forEach(res::add);
        Map<String,Long> response = new HashMap<>();
        long max = -1;
        for (Reservation r : res) {
            if (r.getReservationId() > max) {
                max = r.getReservationId();
            }
        }
        response.put("Id", max);
        return response;
    }
    */

    @Override
    public Reservation getByReservationId(Long id) {
        return reservationrepository.findByReservationId(id);
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        reservationrepository.save(reservation);
        return reservation;
        
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