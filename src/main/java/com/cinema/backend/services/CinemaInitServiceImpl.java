package com.cinema.backend.services;

import com.cinema.backend.dao.*;
import com.cinema.backend.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService{

    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private FilmProjectionRepository filmProjectionRepository;
    @Autowired
    private TicketRepository ticketRepository;



    @Override
    public void initVilles() {
        Stream.of("Casablanca", "Tanger", "Rabat", "Oujda", "Marrakech").forEach(nomVille->{
            Ville ville = new Ville();
            ville.setName(nomVille);
            villeRepository.save(ville);
        });
    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(ville -> {
            Stream.of("MegaRama", "Imax", "Lynx", "Rif").forEach(nomCinema->{
                Cinema cinema = new Cinema();
                cinema.setName(nomCinema);
                cinema.setVille(ville);
                cinema.setNombreSalles(3+(int)(Math.random()*7));
                cinemaRepository.save(cinema);
            });
        });
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(cinema -> {
            for (int i = 0; i < cinema.getNombreSalles(); i++) {
                Salle salle = new Salle();
                salle.setName("Salle "+(i+1));
                salle.setCinema(cinema);
                salle.setNombrePlaces(15+(int)(Math.random()*5));
                salleRepository.save(salle);
            }
        });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
            for (int i = 0; i < salle.getNombrePlaces(); i++) {
                Place place = new Place();
                place.setNumero(i+1);
                place.setSalle(salle);
                placeRepository.save(place);
            }
        });
    }

    @Override
    public void initSeances() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Stream.of("12:00", "15:00", "17:00", "19:00", "21:00").forEach(heureSeance -> {
            Seance seance = new Seance();
            try {
                seance.setHeureDebut(dateFormat.parse(heureSeance));
                seanceRepository.save(seance);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        });
    }

    @Override
    public void initCategories() {
        Stream.of("Thriller", "Action", "Fantasy", "Sci-Fi", "History", "Horror", "Comedy").forEach(nomCategorie->{
            Categorie categorie = new Categorie();
            categorie.setName(nomCategorie);
            categorieRepository.save(categorie);
        });
    }

    @Override
    public void initFilms() {
        int[] durees = new int[] {80, 60, 120, 130, 90, 150};
        List<Categorie> categories = categorieRepository.findAll();
        AtomicInteger i= new AtomicInteger();
            Stream.of("The God Father", "John Wick", "Warcraft", "Interstellar", "The Pianist", "Home Alone").forEach(titreFilm -> {
                Film film = new Film();
                film.setCategorie(categories.get(i.get()));
                film.setTitre(titreFilm);
                film.setDescription("Description of the movie "+titreFilm);
                film.setDuree(durees[new Random().nextInt(durees.length)]);
                film.setPhoto(titreFilm.toLowerCase().replace(" ", "-")+".jpg");
                filmRepository.save(film);
                i.getAndIncrement();
            });
    }



    @Override
    public void initClients() {
        Stream.of("Youssef Achir", "Ouiam Khattach", "Yasser Chihab", "Oussama AitAlla", "Hajar Lablaoui").forEach(nomClient -> {
            Client client = new Client();
            client.setNom(nomClient.substring(0, nomClient.indexOf(" ")));
            client.setPrenom(nomClient.substring(nomClient.indexOf(" ")));
            client.setNumeroTelephone("06"+(10000000+(int)(Math.random()*89999999)));
            clientRepository.save(client);
        });
    }

    @Override
    public void initFilmProjections() {
        double[] prices = new double[] {30, 45, 50, 65};
        List<Film> filmList = filmRepository.findAll();
        villeRepository.findAll().forEach(ville -> {
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle -> {
                    int index = new Random().nextInt(filmList.size());
                        seanceRepository.findAll().forEach(seance -> {
                            Film film = filmList.get(index);
                            FilmProjection filmProjection = new FilmProjection();
                            filmProjection.setFilm(film);
                            filmProjection.setSeance(seance);
                            filmProjection.setSalle(salle);
                            filmProjection.setDateProjection(new Date());
                            filmProjection.setPrix(prices[new Random().nextInt(prices.length)]);
                            filmProjectionRepository.save(filmProjection);
                        });
                });
            });
        });
    }

    @Override
    public void initTickets() {
        filmProjectionRepository.findAll().forEach(filmProjection -> {
            filmProjection.getSalle().getPlaces().forEach(place -> {
                Ticket ticket = new Ticket();
                ticket.setPlace(place);
                ticket.setPrix(filmProjection.getPrix());
                ticket.setReservee(false);
                ticket.setFilmProjection(filmProjection);
                ticketRepository.save(ticket);
            });
        });
    }
}
