package com.cinema.backend.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "cinemas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nom")
    private String name;
    private double longitude;
    private double latitude;
    private double altitude;
    private int nombreSalles;
    @ManyToOne
    @JoinColumn(name = "ville_id", referencedColumnName = "id")
    private Ville ville;
    @OneToMany(mappedBy = "cinema")
    private Collection<Salle> salles;
}
