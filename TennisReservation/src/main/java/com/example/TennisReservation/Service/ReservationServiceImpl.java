package com.example.TennisReservation.Service;

import java.util.ArrayList;
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
}