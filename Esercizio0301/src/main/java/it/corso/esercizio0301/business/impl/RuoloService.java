package it.corso.esercizio0301.business.impl;

import it.corso.esercizio0301.business.interfaces.RuoloServiceInterface;
import it.corso.esercizio0301.model.Role;
import it.corso.esercizio0301.repository.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuoloService implements RuoloServiceInterface {
    @Autowired
    RuoloRepository ruoloRepository;

    public List<Role> getAll(){
        return ruoloRepository.findAll();
    }

    public void deleteById(Integer id){
         ruoloRepository.deleteById(id);
    }

    public Role findById(Integer id){
        return ruoloRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ruolo " + id + "not found"));
    }
}
