package com.spring.hard.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.spring.hard.models.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Page<Categorie> findAll(Pageable pageable);
}
