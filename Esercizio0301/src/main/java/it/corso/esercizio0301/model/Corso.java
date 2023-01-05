package it.corso.esercizio0301.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Corso")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "corso_id")
    private Integer id;

    @Setter
    @Getter
    @Column(name = "nome")
    private String nome;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "corsi_utenti",
    joinColumns = @JoinColumn(name = "corso_id"),
    inverseJoinColumns = @JoinColumn(name = "utente_id"))
    private Set<Utente> utenti = new LinkedHashSet<>();

    @OneToMany(mappedBy = "corso",cascade = {CascadeType.ALL})
    private List<Esame> esami;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return nome;
    }

    public void setName(String name) {
        this.nome = name;
    }

    public List<Esame> getEsami() {
        return esami;
    }

    public void setEsami(List<Esame> esami) {
        this.esami = esami;
    }
}
