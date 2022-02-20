package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Client;
import be.thomasmore.party.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

@Controller
public class ClientController {
    @Autowired

    private ClientRepository clientRepository;

    @GetMapping({"/client"})
    public String client(Model model) {
        Optional<Client> clientFromDb = clientRepository.findById(1);
        if (clientFromDb.isPresent()) {
            model.addAttribute("client", clientFromDb.get());
        }
        return "client";
    }

    public String generateClientCode(Client client) {
        String eersteLetter = client.getName().substring(0, 2);
        char laatsteLetter = client.getName().charAt(client.getName().length() - 1);
        String dag[] = client.getBirthdate().split("-");
        String birthday = dag[2];
        String geboortejaar = dag[0];
        Random r = new Random();
        int getal = r.nextInt(1, Integer.parseInt(geboortejaar));
        String code = eersteLetter + laatsteLetter + birthday + getal;
        return code;
    }

    @GetMapping({"/showSecretCode"})
    public String showSecretCode(Model model) {
        Optional<Client> clientFromDb = clientRepository.findById(1); // optional array van client
        String message = "";
        if (clientFromDb.isPresent()) {

            if (clientFromDb.get().getGender().equals("F")) {
                message = "Mevrouw, " + clientFromDb.get().getName() + " uw code is " + generateClientCode(clientFromDb.get());
            } else {
                message = "Meneer, " + clientFromDb.get().getName() + " uw code is " + generateClientCode(clientFromDb.get());
            }
        }
        model.addAttribute("message", message);
        return "showSecretCode";

    }

    @GetMapping({"/greetingNewClient"})
    public String greetingNewClient(Model model) {
        Optional<Client> clientFromDb = clientRepository.findById((1));
        String message = "";
        if (clientFromDb.isPresent()) {

            if (LocalTime.now().isBefore(LocalTime.parse("12:00:00"))) {
                message = "Goedemorge";

            } else if (LocalTime.now().isAfter(LocalTime.parse("17:00:00"))) {
                message = "Goedenavond, ";
            } else {
                message = "Goedemiddag, ";
            }

            if (clientFromDb.get().getGender().equals("F")) {
                message += "mevrouw " + clientFromDb.get().getName();
            } else {
                message += "meneer " + clientFromDb.get().getName();
            }

        }
        model.addAttribute("greetings",message);
        return "greetingNewClient";

    }
}



