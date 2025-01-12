package com.spring.hard.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.spring.hard.models.Abonnement;

@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
    Page<Abonnement> findAll(Pageable pageable);
}
