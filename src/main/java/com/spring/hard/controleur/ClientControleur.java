package com.spring.hard.controleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spring.hard.DTO.LoginData;
import com.spring.hard.DTO.LoginResponse;
import com.spring.hard.models.Client;
import com.spring.hard.security.JwtService;
import com.spring.hard.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/client")
public class ClientControleur {
    @Autowired
    private ClientService service;
    @Autowired 
    private JwtService jwtService;
    @Autowired 
    private PasswordEncoder encoder;

    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClientApi() {
        List<Client> entities = service.getAll();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/encode/{mdp}")
    public String encode(@PathVariable String mdp){
        return encoder.encode(mdp);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginClient(@RequestBody LoginData data){
        try {
            Client client=service.authenticate(data);
            String jwtToken = jwtService.generateToken(client);
            LoginResponse loginResponse=new LoginResponse();
            loginResponse.setToken(jwtToken);
            loginResponse.setExpiresIn(jwtService.getExpirationTime());
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            return ResponseEntity.status(502).body(e);
        }
    }

    @PostMapping("/auth/login/admin")
    public ResponseEntity<?> loginAdmin(@RequestBody LoginData data){
        try {
            Client client=service.authenticate(data);
            if (client.getIsAdmin()!=1) {
                return ResponseEntity.status(502).body("profil unvalable");
            }
            String jwtToken = jwtService.generateToken(client);
            LoginResponse loginResponse=new LoginResponse();
            loginResponse.setToken(jwtToken);
            loginResponse.setExpiresIn(jwtService.getExpirationTime());
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            return ResponseEntity.status(502).body(e);
        }
    }

    @GetMapping("/find/{mail}")
    public ResponseEntity<?> getClientByEmail(@PathVariable String mail){
        return ResponseEntity.ok(service.getByEmail(mail));
    }

}
