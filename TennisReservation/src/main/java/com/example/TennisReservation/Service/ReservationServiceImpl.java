package com.example.TennisReservation.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.TennisReservation.Model.Reservation;
import com.example.TennisReservation.Repository.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public Reservation getByReservationId(Long id) {
        return reservationrepository.findByReservationId(id);
    }

    @Override
    public List<Reservation> getReservationByDate(String date) {
        return reservationrepository.findByDateEqualTo(date);
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