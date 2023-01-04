package it.corso.esercizio0301.repository;

import it.corso.esercizio0301.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository <Utente, Integer> {
}
