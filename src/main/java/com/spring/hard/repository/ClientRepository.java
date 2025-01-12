package com.spring.hard.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.spring.hard.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Page<Client> findAll(Pageable pageable);
    Optional<Client> findByMail(String eMail);
}
