package it.corso.esercizio0301.repository;

import it.corso.esercizio0301.model.Posizione;
import it.corso.esercizio0301.model.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RuoloRepository extends JpaRepository<Ruolo, Integer> {
    Optional<Ruolo> findByPosizione(Posizione name);
}
