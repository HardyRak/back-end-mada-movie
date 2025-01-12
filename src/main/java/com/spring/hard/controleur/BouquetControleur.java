package com.spring.hard.controleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.hard.models.Bouquet;
import com.spring.hard.service.BouquetService;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/bouquet")
public class BouquetControleur {
    @Autowired
    private BouquetService service;
    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public ResponseEntity<List<Bouquet>> getAllBouquetApi() {
        List<Bouquet> entities = service.getAll();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }
}
