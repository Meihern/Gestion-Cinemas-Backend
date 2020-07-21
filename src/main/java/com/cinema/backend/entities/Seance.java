package com.cinema.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "seances")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIME)
    private Date heureDebut;
}
