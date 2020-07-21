package com.cinema.backend;

import com.cinema.backend.entities.Film;
import com.cinema.backend.entities.Salle;
import com.cinema.backend.entities.Ticket;
import com.cinema.backend.services.CinemaInitServiceImpl;
import com.cinema.backend.services.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

    @Autowired
    private ICinemaInitService cinemaInitService = new CinemaInitServiceImpl();
    @Autowired
    private RepositoryRestConfiguration restConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        restConfiguration.exposeIdsFor(Film.class, Salle.class, Ticket.class);
        cinemaInitService.initVilles();
        cinemaInitService.initCinemas();
        cinemaInitService.initSalles();
        cinemaInitService.initPlaces();
        cinemaInitService.initClients();
        cinemaInitService.initCategories();
        cinemaInitService.initFilms();
        cinemaInitService.initSeances();
        cinemaInitService.initFilmProjections();
        cinemaInitService.initTickets();
    }
}
