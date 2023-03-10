package it.corso.esercizio0301.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "utente")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "utente_id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @NotBlank
    @Column(name = "email")
    private String email;

    @NotBlank
    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "corsi_utenti",
            joinColumns = @JoinColumn(name = "utente_id"),
            inverseJoinColumns = @JoinColumn(name = "corso_id"))
    private Set<Corso> corsi = new LinkedHashSet<>();


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "utente_ruolo",
            joinColumns = @JoinColumn(name = "utente_id"),
    inverseJoinColumns = @JoinColumn(name = "ruolo_id"))
    private Set <Role> ruoli = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Utente(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Set<Corso> getCorso(){
        return corsi;
    }

    public void setCorsi(Set<Corso> corsi){
        this.corsi = corsi;
    }
}
