package it.corso.esercizio0301.business.service;

import it.corso.esercizio0301.model.Utente;
import it.corso.esercizio0301.repository.CorsoRepository;
import it.corso.esercizio0301.repository.RuoloRepository;
import it.corso.esercizio0301.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {
    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    RuoloRepository ruoloRepository;

    @Autowired
    CorsoRepository corsoRepository;


    public List<Utente> getAll (){
        List <Utente> lista = utenteRepository.findAll();
        return lista;
    }

    public Utente getById(Integer id){
        return utenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("UtenteId " + id + "not found"));
    }

    public Utente insertUtente(Utente utente){
        utenteRepository.save(utente);
        return utente;
    }

    public Utente updateUtenteById(Integer id){
        Utente utenteTrovato = utenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("UtenteId " + id + "not found"));
        return utenteTrovato;
    }

    public void deleteById(Integer id){
        utenteRepository.deleteById(id);
    }
}
