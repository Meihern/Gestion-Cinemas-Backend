package com.cinema.backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "films")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private int duree;
    private String realisateur;
    private String description;
    private String photo;
    private Date dateSortie;
    @ManyToOne
    @JoinColumn(name = "categorie_id", referencedColumnName = "id")
    private Categorie categorie;
    @OneToMany(mappedBy = "film")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<FilmProjection> filmProjections;
}
