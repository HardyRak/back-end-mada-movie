package com.spring.hard.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.spring.hard.models.Bouquet;

@Repository
public interface BouquetRepository extends JpaRepository<Bouquet, Long> {
    Page<Bouquet> findAll(Pageable pageable);
}