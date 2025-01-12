package com.spring.hard.models;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "client")
@Getter
@Setter
public class Client implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idClient;
    private String nom;
    private String prenom;
    private Date naissance;
    private String mail;
    private String motDePasse;
    @Column(nullable = true)
    private int isAdmin;
    @OneToMany(mappedBy = "client",fetch = FetchType.EAGER)
    private List<Favoris> favoris;
    @ManyToOne
    private Abonnement abonnement;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
    @Override
    public String getPassword() {
        return motDePasse;
    }
    @Override
    public String getUsername() {
        return mail;  //cles de l'objet du token
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
