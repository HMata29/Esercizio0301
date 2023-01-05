package it.corso.esercizio0301.business.service;

import it.corso.esercizio0301.model.Esame;
import it.corso.esercizio0301.repository.EsameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsameService {
    @Autowired
    EsameRepository esameRepository;

    public List<Esame> getAll(){
        return esameRepository.findAll();
    }

    public Esame getById(Integer id){
        return esameRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("EsameId " + id + "not found"));
    }

    public void deleteById(Integer id){
        esameRepository.deleteById(id);
    }

    public List <Esame> findByValutazione(Integer valutazione){
        return esameRepository.findEsamesByValutazione(valutazione);
    }

    public Esame insert(Esame esame){
        return esameRepository.save(esame);

    }



}
