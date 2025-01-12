package com.spring.hard.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.hard.models.Categorie;
import com.spring.hard.models.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    Page<Film> findAll(Pageable pageable);
    List<Film> findByCategorie(Categorie categorie);
    
    @Query(value = "SELECT * FROM Film c WHERE LOWER(c.nom) LIKE LOWER(CONCAT('%', :nom, '%'))", nativeQuery = true)
    Page<Film> rechercheParNom(@Param("nom") String nom, Pageable pageable);

}