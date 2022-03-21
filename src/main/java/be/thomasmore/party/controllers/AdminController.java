package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.PartyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping({"/partyedit/{id}"})
    public String partyedit(Model model, @PathVariable(required = false) Integer id) {
        if (id == null) return "/admin/partyedit";


        Optional<Party> partyFromDb = partyRepository.findById(id);

        if (partyFromDb.isPresent()) {
            model.addAttribute("party", partyFromDb.get());
            logger.info(partyFromDb.get().getName());
//            long maxvenues = partyRepository.count();
//            model.addAttribute("maxvenues",maxvenues);

        }
        return "/admin/partyedit";
    }

    @PostMapping("/partyedit/{id}")
    public String partyEditPost(Model model, @PathVariable(required = false) Integer id, @ModelAttribute("party") Party party) {
        logger.info("partyEditPost" + id);
        Optional<Party> optionalParty = partyRepository.findById(id);
        if (optionalParty.isPresent()) {

            Party editParty = optionalParty.get();

            editParty.setName(party.getName());
            editParty.setPriceInEur(party.getPriceInEur());
            editParty.setPricePresaleInEur(party.getPricePresaleInEur());
            editParty.setExtraInfo(party.getExtraInfo());
            partyRepository.save(editParty);
            model.addAttribute("party", party);
        }
        return "redirect:/partydetails/" + id;
    }
}

