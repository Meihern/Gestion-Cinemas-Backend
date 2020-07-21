package com.cinema.backend.controllers;


import com.cinema.backend.dao.ClientRepository;
import com.cinema.backend.dao.FilmRepository;
import com.cinema.backend.dao.TicketRepository;
import com.cinema.backend.entities.Client;
import com.cinema.backend.entities.Film;
import com.cinema.backend.entities.Ticket;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CinemaRestController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping(value = "/films/imagefilm/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable(name = "id") Long id){
            Film film = filmRepository.findById(id).get();
            String imageName = film.getPhoto();
            File file = new File(System.getProperty("user.home")+"/cinema/images/"+imageName);
            Path path = Paths.get(file.toURI());
            try {
                return Files.readAllBytes(path);
            } catch (IOException e) {
                e.printStackTrace();
            }

        return null;
    }

    @Transactional
    @PostMapping(value = "/paiement")
    public List<Ticket> postPaiementTickets(@RequestBody TicketForm ticketForm){
        List<Ticket> ticketList = new ArrayList<>();
        // Saving Client
        Client client = new Client();
        client.setPrenom(ticketForm.getPrenomClient());
        client.setNom(ticketForm.getNomClient());
        client.setNumeroTelephone(ticketForm.getNumTelephoneClient());
        clientRepository.save(client);
        ticketForm.getIdTicketList().forEach(idTicket->{
            //Saving tickets
            Ticket ticket = ticketRepository.findById(idTicket).get();
            ticket.setClient(client);
            ticket.setReservee(true);
            ticket.setCodePayement(ticketForm.getCodePaiement());
            ticketRepository.save(ticket);
            ticketList.add(ticket);
        });
        return ticketList;
    }

}

@Data
class TicketForm{
    private String nomClient;
    private String prenomClient;
    private String numTelephoneClient;
    private int codePaiement;
    private List<Long> idTicketList = new ArrayList<>();
}