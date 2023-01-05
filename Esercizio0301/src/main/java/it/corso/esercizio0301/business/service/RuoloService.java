package it.corso.esercizio0301.business.service;

import it.corso.esercizio0301.model.Ruolo;
import it.corso.esercizio0301.repository.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuoloService {
    @Autowired
    RuoloRepository ruoloRepository;

    public List<Ruolo> getAll(){
        return ruoloRepository.findAll();
    }

    public void deleteById(Integer id){
         ruoloRepository.deleteById(id);
    }

    public Ruolo findById(Integer id){
        return ruoloRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ruolo " + id + "not found"));
    }
}
