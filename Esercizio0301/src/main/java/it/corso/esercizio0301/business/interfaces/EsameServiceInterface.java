package it.corso.esercizio0301.business.interfaces;

import it.corso.esercizio0301.model.Esame;

import java.util.List;

public interface EsameServiceInterface {
    public void deleteById(Integer id);

    public List<Esame> getAll();

    public Esame getById(Integer id);

    public Esame insert (Esame esame);
}
