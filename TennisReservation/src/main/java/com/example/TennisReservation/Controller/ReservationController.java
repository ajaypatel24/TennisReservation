package com.example.TennisReservation.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.TennisReservation.Repository.ReservationRepository;
import com.example.TennisReservation.Service.ReservationService;
import com.example.TennisReservation.Model.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @RequestMapping("/")
    public String redirToList() {
        return "redirect:/reservation/list";
    }

    @RequestMapping({ "/reservation/list", "/Reservation" })
    public Map<Long, List<String>> listReservation(Model model) {
        Map<Long, List<String>> res = new HashMap<>();
        model.addAttribute("reservations", reservationService.listAll());
        for (Reservation reservation : reservationService.listAll()) {

            res.put(reservation.getReservationId(), new ArrayList<String>() {

                private static final long serialVersionUID = 1L;

                {
                add(reservation.getConfirmationPDF());
                add(reservation.getDate());
                add(reservation.getTime());
                add(Integer.toString(reservation.getCourt()));
                add(reservation.getPark()); 
                }});
        }

        return res;
    }

    @RequestMapping("/Reservation/{id}")
    public Reservation getReservation(@PathVariable Long id) {
        System.out.println("invoked");
        return reservationService.getByReservationId(id);
    }

    @RequestMapping("/ReservationDate/{date}")
    public List<Reservation> getRes(@PathVariable String date) {
        return reservationService.getReservationByDate(date);
    }

    @RequestMapping("ReservationRange/{date1}/{date2}")
    public List<Reservation> getResRange(@PathVariable String date1, @PathVariable String date2) {
        return reservationService.getReservationByDateRange(date1, date2);

    }


    @PostMapping("/NewReservation/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addReservation(Reservation reservation, @RequestBody Map<String, String> body,
            HttpServletResponse response, HttpServletRequest request) throws ParseException {
        reservation.setConfirmationPDF(body.get("confirmationpdf"));
        reservation.setCourt(Integer.parseInt(body.get("court")));
        reservation.setDate(body.get("date"));
        reservation.setTime(body.get("time"));
        reservation.setPark(body.get("park"));

        Reservation res = reservationService.addReservation(reservation);
        System.out.println(res.getReservationId() + " " + res.getCourt() + " " + res.getConfirmationPDF() + " " + res.getDate());
        response.setHeader("Location", (request.getRequestURL().append("/").append(res.getReservationId())).toString());

    }

    @RequestMapping("/File/{id}")
    public String recentFile(@PathVariable Long id) {
        Reservation edit = reservationService.getByReservationId(id);
        edit.setConfirmationPDF(reservationService.getNewestFile());
        Reservation res = reservationService.addReservation(edit);
        return reservationService.getNewestFile();
    }


    @RequestMapping("/Changedate/{date}")
    public Map<String,String> convertDate(@PathVariable Date date) throws ParseException {
        System.out.println(date);
        DateFormat format = new SimpleDateFormat("EEE MMM dd hh:kk:hh zzz yyyy", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = format.parse(date.toString());
       String res = targetFormat.format(date);
       System.out.println(res);
       
        return new HashMap<String,String>(){
            private static final long serialVersionUID = 1L;
            {
                put("newDate", res);
            }
        };
    }


}