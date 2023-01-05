package it.corso.esercizio0301.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "esame")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Esame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Column(name = "valutazione")
    private Integer valutazione;

    @Getter
    @Setter
    @Column(name = "giorno")
    private int giorno;

    @Getter
    @Setter
    @Column(name = "mese")
    private int mese;

    @Getter
    @Setter
    @Column(name = "anno")
    private int anno;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinTable(name = "corso_id",
            joinColumns = @JoinColumn(name = "esame_id"))
    private Corso corso;

    public Corso getCorso() {
        return corso;
    }

    public void setCorso(Corso corso) {
        this.corso = corso;
    }
}
