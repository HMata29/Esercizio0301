package it.corso.esercizio0301.business.impl;

import it.corso.esercizio0301.business.interfaces.CorsoServiceInterface;
import it.corso.esercizio0301.model.Corso;
import it.corso.esercizio0301.model.Utente;
import it.corso.esercizio0301.repository.CorsoRepository;
import it.corso.esercizio0301.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CorsoService implements CorsoServiceInterface {
    @Autowired
    CorsoRepository corsoRepository;
    @Autowired
    UtenteRepository utenteRepository;

    public Corso getById(Integer id){
        return corsoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CorsoId " + id + "not found"));
    }

    public Corso insert(Corso c){
        return corsoRepository.save(c);
    }

    public Corso update(Integer id, Corso request){
       Corso c1 =  corsoRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("CorsoId " + id + "not found"));
       c1.setNome(request.getNome());

        return corsoRepository.save(c1);
    }

    public void delete(Integer id){
        corsoRepository.deleteById(id);
    }

    public List<Corso> getAll(){
    List <Corso> lista = corsoRepository.findAll();
    return lista;
    }

    public void creaCorsoConUtente(Integer id, Corso c){
        Utente utente = utenteRepository.getReferenceById(id);
        Set<Utente> utenteSet = new HashSet<>();
        utenteSet.add(utente);
        c.setUtenti(utenteSet);
        Corso _corso = corsoRepository.save(c);
    }

}
