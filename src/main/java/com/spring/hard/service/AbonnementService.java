package com.spring.hard.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.hard.DTO.AbonnementDTO;
import com.spring.hard.models.Abonnement;
import com.spring.hard.models.Client;
import com.spring.hard.repository.AbonnementRepository;
import com.spring.hard.security.JwtService;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import org.springframework.data.domain.Sort;

@Service
public class AbonnementService {
    @Autowired
    private AbonnementRepository repository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ClientService clientService;

    public Abonnement saveOrUpdate(Abonnement entity) {
        return repository.save(entity);
    }

    public Optional<Abonnement> getById(Long id) {
        return repository.findById(id);
    }

    public Page<Abonnement> getAll(int page, int size, String fieldTri) {
        Sort sort = null;
        if (fieldTri != null && !fieldTri.isEmpty()) {
            String[] split = fieldTri.split(",");
            sort = Sort.by(Sort.Direction.fromString(split[1]), split[0]);
            return repository.findAll(PageRequest.of(page, size, sort));
        }
        return repository.findAll(PageRequest.of(page, size));
    }

    public List<Abonnement> getAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Abonnement nouveau(String token,AbonnementDTO abonnementDTO){
        String emailClient=jwtService.extractUsername(token);
        Client client=clientService.getByEmail(emailClient);
        Abonnement abonnement=new Abonnement();
        abonnement.setClient(client);
        abonnement.setBouquet(abonnementDTO.getBouquet());
        abonnement.setRef(abonnementDTO.getRef());
        abonnement.setDateDebut(null);
        abonnement.setDatefin(null);
        Abonnement save=this.saveOrUpdate(abonnement);
        return save;
    }

}
