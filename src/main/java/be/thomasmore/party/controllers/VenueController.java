package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class VenueController {
    @Autowired
    private VenueRepository venueRepository;
    private Logger logger = LoggerFactory.getLogger(VenueController.class);

    @GetMapping({"/venuedetails/{id}", "/venuedetails"})
    public String venuedetails(Model model, @PathVariable(required = false) Integer id) {
        if (id == null) return "venuedetails";

        Optional<Venue> venueFromDb = venueRepository.findById(id);

        if (venueFromDb.isPresent()) {
            model.addAttribute("venue", venueFromDb.get());
            long maxvenues = venueRepository.count();
            model.addAttribute("maxvenues", maxvenues);
        }
        return "venuedetails";
    }

    @GetMapping({"/venuelist", "/venuelist/{filter}"})
    public String venuelist(Model model, @PathVariable(required = false) String filter, @RequestParam(required = false) Integer minCapacity,@RequestParam(required = false) Integer maxCapacity) {
        logger.info("venueListWithFilter");
        List<Venue> venues;
        Boolean showFilter = false;

        if (filter == null) {
            venues = venueRepository.findAllBy();

        } else  {
            logger.info(String.format("venueListWithFilter --=min %d", minCapacity));
//wat we hier doen is kijken wat op her formulier werd ingevuld
            if (minCapacity != null) {
                if (maxCapacity != null) {
                    venues = venueRepository.findByCapacityBetween(minCapacity, maxCapacity);
                } else {
                    venues = venueRepository.findByCapacityIsGreaterThanEqual(minCapacity);
                }

            } else {
                if (maxCapacity != null){
                    venues = venueRepository.findByCapacityBetween(0,maxCapacity);
                }else {
                    venues = venueRepository.findAllBy();
                }


            }

            showFilter = true;
        }

        model.addAttribute("showFilter", showFilter);
        model.addAttribute("venues", venues);
        model.addAttribute("minCapacity", minCapacity);
        model.addAttribute("maxCapacity",maxCapacity);
        model.addAttribute("nrofVenues",venues.size());

        return "venuelist";
    }

}
