package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ArtistController {
    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping({"/artistdetails/{id}", "/artistdetails"})
    public String artistdetails(Model model, @PathVariable (required = false) Integer id) {
        if(id == null) return "artistdetails";
        // comment

        //nog is proberen

        //proberen


        Optional<Artist> artistFromDb = artistRepository.findById(id);

        if (artistFromDb.isPresent()) {
            model.addAttribute("artist", artistFromDb.get());
            long maxlist = artistRepository.count();
            model.addAttribute("maxlist",maxlist);
        }
        return "artistdetails";
    }

    @GetMapping({"/artistlist"})
    public String artistList(Model model) {
        final Iterable< Artist> allartists = artistRepository.findAll();
        model.addAttribute("artists",allartists);
        return "artistlist";
    }

}
