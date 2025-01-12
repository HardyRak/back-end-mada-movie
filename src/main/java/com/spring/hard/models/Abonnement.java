package com.spring.hard.models;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "abonnement")
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAbonnement;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_client")
    private  Client client;
    @ManyToOne
    @JoinColumn(name = "id_bouquet")
    private Bouquet bouquet;
    private String ref;
    private ZonedDateTime dateDebut;
    private ZonedDateTime datefin;
}
