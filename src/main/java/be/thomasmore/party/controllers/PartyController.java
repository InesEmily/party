package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.PartyRepository;
import be.thomasmore.party.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class PartyController {
    @Autowired
    private PartyRepository partyRepository;


    @GetMapping({"/partylist"})
    public String party (Model model){
        Iterable<Party> parties = partyRepository.findAll();
        model.addAttribute("parties",parties);

        return "partylist";
    }
    @GetMapping({"/partydetails/{id}"})
    public String partydetails(Model model, @PathVariable(required = false) Integer id){
        if (id == null) return "partydetails";

        Optional<Party> partyFromDb = partyRepository.findById(id);

        if (partyFromDb.isPresent()) {
            model.addAttribute("party", partyFromDb.get());
            long maxvenues = partyRepository.count();
            model.addAttribute("maxvenues",maxvenues);

        }
        return "partydetails";
    }

}
