package com.example.TennisReservation.Controller;

import java.text.Format;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.TennisReservation.Repository.ReservationRepository;
import com.example.TennisReservation.Service.ReservationService;
import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;
import com.example.TennisReservation.Model.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import okhttp3.Response;

@RestController
public class ReservationController {
    
    @Autowired
    private ReservationService reservationService;

    

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
            res.put(count ,reservation.getConfirmationPDF());
            count++;
        }

        return res;
    }

    @RequestMapping("/Reservation/{id}")
    public Reservation getReservation(@PathVariable Long id) {
        return reservationService.getByReservationId(id);
    }

    
    
    @PostMapping("/NewReservation/")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String,String> addReservation( @RequestBody Reservation reservation) {
        Map<String,String> res = new HashMap<>();
        try {
        LocalDate FormatDate = reservation.getDate();
        reservationService.addReservation(reservation.getCourt(), reservation.getConfirmationPDF(), FormatDate);
        res.put("Status", "Added");
        return res;
        } catch (Exception e) {
            res.put("Status", "Added");
            return res;
        }

    }


}