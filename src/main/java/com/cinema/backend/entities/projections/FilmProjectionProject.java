package com.cinema.backend.entities.projections;

import com.cinema.backend.entities.*;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.Date;

@Projection(name = "filmProj", types = {FilmProjection.class})
public interface FilmProjectionProject {
    Long getId();
    double getPrix();
    Date getDateProjection();
    Salle getSalle();
    Film getFilm();
    Seance getSeance();
}
