package com.example.TennisReservation.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Override public Long addReservation(int Court, String ConfirmationPDF, LocalDate date) {
        Reservation reservation = new Reservation();

        reservation.setCourt(Court);
        reservation.setConfirmationPDF(ConfirmationPDF);
        reservation.setDate(date);

        return reservationrepository.save(reservation).getReservationId();
    }
}