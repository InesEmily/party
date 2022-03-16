package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Animal;
import be.thomasmore.party.model.Artist;
import be.thomasmore.party.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class AnimalController {
    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping({"/animaldetails","/animaldetails/{id}"})
    public String animaldetails(Model model, @PathVariable(required = false)Integer id){
        if(id == null) return "artistdetails";

        Optional<Animal> animalFromDb = animalRepository.findById(id);

        if (animalFromDb.isPresent()) {
            model.addAttribute("animal", animalFromDb.get());
            long maxlist = animalRepository.count();
            model.addAttribute("maxlist",maxlist);
        }
        return "animaldetails";
    }
}
