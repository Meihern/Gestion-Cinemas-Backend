package com.cinema.backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Collection;

@Entity
@Table(name = "films_projections")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FilmProjection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateProjection;
    private double prix;
    @ManyToOne
    @JoinColumn(name = "salle_id", referencedColumnName = "id")
    private Salle salle;
    @ManyToOne
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Film film;
    @ManyToOne
    @JoinColumn(name = "seance_id", referencedColumnName = "id")
    private Seance seance;
    @OneToMany(mappedBy = "filmProjection")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Collection<Ticket> tickets;

}
