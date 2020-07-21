package com.cinema.backend.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "villes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "nom")
    private String name;
    private double longitude;
    private double latitude;
    private double altitude;
    @OneToMany(mappedBy = "ville")
    private Collection<Cinema> cinemas;
}
