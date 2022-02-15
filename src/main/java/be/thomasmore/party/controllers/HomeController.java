package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // zegt dat deze klassen een controller
public class HomeController {

    @GetMapping({"/","/home"})
    public String home(){
        return "home";
    }
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
