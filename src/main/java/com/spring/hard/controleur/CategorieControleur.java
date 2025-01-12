package com.spring.hard.controleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.hard.models.Categorie;
import com.spring.hard.service.CategorieService;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/categorie")
public class CategorieControleur {
    @Autowired
    private CategorieService service;
    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public ResponseEntity<List<Categorie>> getAllCategorieApi() {
        List<Categorie> entities = service.getAll();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }
}
