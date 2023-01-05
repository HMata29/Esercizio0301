package it.corso.esercizio0301.repository;

import it.corso.esercizio0301.model.Esame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EsameRepository extends JpaRepository <Esame,Integer> {
    List<Esame> findEsamesByValutazione(Integer valutazione);
}
