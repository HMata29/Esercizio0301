package it.corso.esercizio0301.business.interfaces;

import it.corso.esercizio0301.model.Corso;

import java.util.List;

public interface CorsoServiceInterface {
    public Corso getById (Integer id);

    Corso insert(Corso c);

    Corso update(Integer id, Corso request);

    void delete (Integer id);

    void creaCorsoConUtente(Integer id, Corso c);

    List<Corso> getAll();

}
