package com.spring.hard.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "favoris")
public class Favoris {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFavoris;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "id_film")
    private Film film;
}
