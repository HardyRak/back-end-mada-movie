package com.spring.hard.controleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.hard.DTO.AbonnementDTO;
import com.spring.hard.models.Abonnement;
import com.spring.hard.service.AbonnementService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/abonnement")
public class AbonnementControleur {
    @Autowired
    private AbonnementService service;
    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public ResponseEntity<List<Abonnement>> getAllAbonnementApi() {
        List<Abonnement> entities = service.getAll();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @PostMapping("/validation/transaction")
    public ResponseEntity<?> transaction(@RequestBody AbonnementDTO abonnementDTO,HttpServletRequest httpServletRequest){
        final String header=httpServletRequest.getHeader("Authorization");
        final String token=header.substring(7);
        try {
            service.nouveau(token, abonnementDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(501).body(e);
        }
    }

}
