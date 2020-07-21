package com.cinema.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double prix;
    private Integer codePayement;
    private boolean reservee;
    @ManyToOne
    @JoinColumn(name = "place_id", referencedColumnName = "id")
    private Place place;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "film_projection_id", referencedColumnName = "id")
    private FilmProjection filmProjection;
}
