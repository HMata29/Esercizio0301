package it.corso.esercizio0301.controller;

import it.corso.esercizio0301.business.service.RuoloService;
import it.corso.esercizio0301.business.service.UtenteService;
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

    @Autowired
    RuoloService ruoloService;
    @Autowired
    UtenteService utenteService;

    @GetMapping("/ruolo/getAll")
    public ResponseEntity <List<Ruolo>> getAll (){
        return new ResponseEntity<>(ruoloService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/ruolo/utente/insert/{id_utente}/")
    public ResponseEntity <Ruolo> insert(@PathVariable("id_utente") Integer id , @RequestBody Ruolo ruoloRequest){
        Utente u = utenteService.getById(id);
        Set<Utente> utenteSet = new HashSet<>();
        utenteSet.add(u);
        ruoloRequest.setUtenti(utenteSet);
        Ruolo _ruolo = ruoloRepository.save(ruoloRequest);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/ruolo/delete/{id}")
    public ResponseEntity <HttpStatus> delete(@PathVariable("id") Integer id){
        ruoloService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/ruolo/modifica/{id}")
    public ResponseEntity <Ruolo> update (@PathVariable("id") Integer id , @RequestBody Ruolo ruoloRequest){
        Ruolo ruoloTrovato = ruoloService.findById(id);
        ruoloTrovato.setPosizione(ruoloRequest.getPosizione());
        return new ResponseEntity<>(ruoloRepository.save(ruoloTrovato), HttpStatus.OK);
    }
}
