package com.spring.hard.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.hard.DTO.LoginData;
import com.spring.hard.models.Client;
import com.spring.hard.repository.ClientRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;
    @Autowired
    private AuthenticationManager authenticationManager;

    public Client saveOrUpdate(Client entity) {
        return repository.save(entity);
    }

    public Optional<Client> getById(Long id) {
        return repository.findById(id);
    }

    public Page<Client> getAll(int page, int size, String fieldTri) {
        Sort sort = null;
        if (fieldTri != null && !fieldTri.isEmpty()) {
            String[] split = fieldTri.split(",");
            sort = Sort.by(Sort.Direction.fromString(split[1]), split[0]);
            return repository.findAll(PageRequest.of(page, size, sort));
        }
        return repository.findAll(PageRequest.of(page, size));
    }

    public List<Client> getAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Client authenticate(LoginData input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getMotDePasse()
                )
        );
        return repository.findByMail(input.getEmail())
                .orElseThrow();
    }

    public Client getByEmail(String email){
        return repository.findByMail(email).get();
    }

}
