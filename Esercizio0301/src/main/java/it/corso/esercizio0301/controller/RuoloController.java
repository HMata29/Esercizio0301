package it.corso.esercizio0301.controller;

import it.corso.esercizio0301.model.Ruolo;
import it.corso.esercizio0301.model.Utente;
import it.corso.esercizio0301.repository.RuoloRepository;
import it.corso.esercizio0301.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class RuoloController {

    @Autowired
    RuoloRepository ruoloRepository;

    @Autowired
    UtenteRepository utenteRepository;

    @GetMapping("/ruolo/getAll")
    public ResponseEntity <List<Ruolo>> getAll (){
        List <Ruolo> lista = ruoloRepository.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping("/ruolo/utente/insert/{id_utente}/")
    public ResponseEntity <Ruolo> insert(@PathVariable("id_utente") Integer id , @RequestBody Ruolo ruoloRequest){
        Utente u = utenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Utente " + id + "not found"));
        Set<Utente> utenteSet = new HashSet<>();
        utenteSet.add(u);
        ruoloRequest.setUtenti(utenteSet);
        Ruolo _ruolo = ruoloRepository.save(ruoloRequest);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/ruolo/delete/{id}")
    public ResponseEntity <HttpStatus> delete(@PathVariable("id") Integer id){
        ruoloRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/ruolo/modifica/{id}")
    public ResponseEntity <Ruolo> update (@PathVariable("id") Integer id , @RequestBody Ruolo ruoloRequest){
        Ruolo ruoloTrovato = ruoloRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ruolo " + id + "not found"));
        ruoloTrovato.setPosizione(ruoloRequest.getPosizione());
        return new ResponseEntity<>(ruoloRepository.save(ruoloTrovato), HttpStatus.OK);
    }
}
