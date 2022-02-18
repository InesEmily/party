package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller // zegt dat deze klassen een controller
public class HomeController {

    @GetMapping({"/","/home"})
    public String home(Model model){
        int myCalculatedValue = 34*62;
        model.addAttribute("myCalculatedValue",myCalculatedValue);

        return "home";
    }
    @GetMapping("/about")
    public String about(Model model){
        String naam = "Anne Van Goethem";
        String adres = "Koning Albertlaan 23";
        String stad = "Leuven";
        model.addAttribute("naam",naam);
        model.addAttribute("adres",adres);
        model.addAttribute("stad",stad);
        return "about";
    }
    @GetMapping("/pay")
    public String date(Model model){
        LocalDate date = LocalDate.now();
        LocalDate date30 = date.plusDays(30);
        model.addAttribute("date",date);
        model.addAttribute("date30",date30);

        return "pay";
    }
}
