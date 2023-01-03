package it.corso.esercizio0301.repository;

import it.corso.esercizio0301.model.Corso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorsoRepository extends JpaRepository<Corso, Integer> {
}
