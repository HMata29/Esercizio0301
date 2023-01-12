package it.corso.esercizio0301.business.interfaces;


import it.corso.esercizio0301.model.Utente;

import java.util.List;

public interface UtenteServiceInterface {
    public void deleteById(Integer id);

    public List<Utente> getAll();

    public Utente getById(Integer id);

    public Utente insertUtente (Utente utente);

    public Utente updateUtenteById(Integer id);
}
