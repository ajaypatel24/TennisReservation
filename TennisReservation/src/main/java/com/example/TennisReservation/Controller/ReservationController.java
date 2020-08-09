package com.example.TennisReservation.Controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.TennisReservation.Repository.ReservationRepository;
import com.example.TennisReservation.Service.ReservationService;
import com.example.TennisReservation.Model.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {
    
    
    private ReservationService reservationService;

    @Autowired
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping("/")
    public String redirToList() {
        return "redirect:/reservation/list";
    }

    @RequestMapping({"/reservation/list", "/Reservation"})
    public Map<Integer,String> listReservation(Model model) {
        Map<Integer, String> res = new HashMap<>();
        model.addAttribute("reservations", reservationService.listAll());
        int count = 1;
        for (Reservation reservation : reservationService.listAll()) {
            res.put(count ,reservation.confirmationPDF);
            count++;
        }

        return res;
    }



}