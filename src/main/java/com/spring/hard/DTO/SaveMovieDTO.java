package com.spring.hard.DTO;
import com.spring.hard.models.Categorie;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveMovieDTO {
    String nom;
    int annee;
    String sysnopsis;
    String image;
    Categorie categorie;
}
