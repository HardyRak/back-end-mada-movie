package com.spring.hard.models;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bouquet")
@Getter
@Setter
public class Bouquet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_bouquet;
    private String nom;
    @ManyToMany
    @JoinTable(
        name = "bouquet_categorie",
        joinColumns = @JoinColumn(name="id_bouquet"),
        inverseJoinColumns = @JoinColumn(name="id_categorie")
    )
    private Set<Categorie> categories = new HashSet<>();
    private double prix;
}
