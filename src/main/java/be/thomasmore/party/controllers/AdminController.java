package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.PartyRepository;
import be.thomasmore.party.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private PartyRepository partyRepository;
    @Autowired
    private VenueRepository venueRepository;

    @GetMapping({"/partyedit/{id}"})
    public String partyedit(Model model, @PathVariable(required = false) Integer id) {
        logger.info("partyEdit" + id);
        final Iterable<Venue> venues = venueRepository.findAll();
        model.addAttribute("venues", venues);
        return "/admin/partyedit";
    }

    @PostMapping("/partyedit/{id}")
    public String partyEditPost(Model model, @PathVariable Integer id, @ModelAttribute("party") Party party) {
        logger.info("partyEditPost" + id);
        partyRepository.save(party);
        model.addAttribute("party", party);
        return "redirect:/partydetails/" + id;
    }

    @ModelAttribute("party")
    public Party findparty(@PathVariable(required = false) Integer id) {
        logger.info("findParty" + id);
        if (id == null) {
            return new Party();
        }
        Optional<Party> optionalParty = partyRepository.findById(id);
        if (optionalParty.isPresent())
            return optionalParty.get();
        return null;
    }

    @GetMapping({"/partynew"})
    public String partynew(Model model) {
        final Iterable<Venue> venues = venueRepository.findAll();
        model.addAttribute("venues", venues);
        return "admin/partynew";
    }
    @PostMapping("/partynew")
    public String partyNewPost(Model model,@ModelAttribute("party")Party party){
        partyRepository.save(party);
        int id = partyRepository.save(party).getId();
        return "redirect:/partydetails"+id;
    }
}

