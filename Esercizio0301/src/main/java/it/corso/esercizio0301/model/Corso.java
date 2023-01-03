package it.corso.esercizio0301.model;

import jakarta.persistence.*;
import lombok.*;

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
    private Integer id;

    @Setter
    @Getter
    @Column(name = "nome")
    private String nome;

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
}
