package com.spring.hard.models;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFilm;
    private String nom;
    @Column(columnDefinition = "text")
    private String synopsis;
    private int annee;
    private Date dateAjout;
    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private Categorie categorie;
    private int dure;
    @Column(nullable = true)
    private String chemin;
    @Column(columnDefinition = "text",nullable = true)
    private String image;
    private int isDelete; //1=>delete 0=>existe

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSynopsis(String synopsis) throws Exception {
        if(synopsis.equals("") || synopsis.isEmpty()){
            throw new Exception("sysnop vide");
        }
        this.synopsis = synopsis;
    }

    public void setAnnee(int annee) throws Exception {
        LocalDateTime dateTime=LocalDateTime.now();
        if (annee==0 || annee>dateTime.getYear()) {
            throw new Exception("AnneeImpossible");
        }
        this.annee = annee;
    }

    public void setDure(int dure) throws Exception {
        if (dure<=0) {
            throw new Exception("DureeTropCourt");
        }
        this.dure = dure;
    }

    public void setImage(String image) throws Exception {
        this.image = image;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public void setCategorie(Categorie categorie) throws Exception {
        if (categorie==null) {
            throw new Exception("NullCategorie");
        }
        this.categorie = categorie;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

}
