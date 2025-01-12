package com.spring.hard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.hard.controleur.FilmControleur;
import com.spring.hard.models.Categorie;
import com.spring.hard.models.Client;
import com.spring.hard.models.Film;
import com.spring.hard.repository.FilmRepository;
import com.spring.hard.security.JwtService;

import io.jsonwebtoken.io.IOException;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.data.domain.Sort;

@Service
public class FilmService {
    @Autowired
    private FilmRepository repository;
    @Autowired
    private JwtService jwtService;
    @Autowired 
    private ClientService clientService;

    public Film saveOrUpdate(Film entity) {
        return repository.save(entity);
    }

    public Optional<Film> getById(Long id) {
        return repository.findById(id);
    }

    public Page<Film> getAll(int page, int size, String fieldTri) {
        return repository.findAll(PageRequest.of(page, size));
    }

    public List<Film> getAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void formDelete(Film film) {
        this.saveOrUpdate(film);
    }

    public List<Film> getByCategorie(Categorie categorie){
        return repository.findByCategorie(categorie);
    }

    public Page<Film> rechecheParNom(String nom,int page,int taille){
        Pageable pageable = PageRequest.of(page, taille, Sort.by("nom"));
        return repository.rechercheParNom(nom,pageable);
    }



    public Film estAbonne(String token,long id) throws Exception{
        String mail=jwtService.extractUsername(token);
        Client client=clientService.getByEmail(mail);
        Film film=this.getById(id).get();
        if(client.getAbonnement()==null){
            throw new Exception("Abonné vous pour pouvoir lire la video");
        }
        Set<Categorie> categoriesClient=client.getAbonnement().getBouquet().getCategories();
        Categorie categorieFilm=film.getCategorie();
        if (categoriesClient.contains(categorieFilm)) {
            return film;
        }else{
            throw new Exception("Vous etes pas abonne dans le bouquet de ce film");
        }
    }

    public String saveFile(MultipartFile file,Film film) throws IOException, java.io.IOException {
        Path uploadPath = Paths.get(FilmControleur.path+"/"+film.getCategorie().getNom());
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalFileName = film.getIdFilm()+"-"+film.getNom()+"("+film.getAnnee()+").mp4";

        Path filePath = uploadPath.resolve(originalFileName);

        Files.copy(file.getInputStream(), filePath);

        return filePath.toString();
    }

}
