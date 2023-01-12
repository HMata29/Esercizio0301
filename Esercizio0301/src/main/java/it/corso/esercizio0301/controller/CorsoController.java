package it.corso.esercizio0301.controller;

import it.corso.esercizio0301.business.impl.CorsoService;
import it.corso.esercizio0301.model.Corso;
import it.corso.esercizio0301.model.Utente;
import it.corso.esercizio0301.repository.CorsoRepository;
import it.corso.esercizio0301.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class CorsoController {
    @Autowired
    CorsoRepository corsoRepository;

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    CorsoService corsoService;


    @GetMapping("/corso/get/{id}")
    public ResponseEntity <Corso> getCorso(@PathVariable("id") Integer id){
        return new ResponseEntity<>(corsoService.getById(id), HttpStatus.FOUND);
    }

    @PostMapping("/corso/insert")
    public ResponseEntity <Corso> inserisci(@RequestBody  Corso c){
        return new ResponseEntity<>(corsoService.insert(c), HttpStatus.CREATED);
    }

    @PutMapping ("/corso/update/{id}")
    public ResponseEntity <Corso> update(@PathVariable("id") Integer id, @RequestBody Corso corsoRequest){
        return new ResponseEntity<>(corsoService.update(id, corsoRequest), HttpStatus.OK);
    }

    @DeleteMapping ("/corso/delete/{id}")
    public ResponseEntity <HttpStatus> delete (@PathVariable("id") Integer i){
        corsoService.delete(i);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/corso/utente/{id}/corso")
    public ResponseEntity <Corso> creaCorsoUtente(@PathVariable("id") Integer id,@RequestBody  Corso c){
        corsoService.creaCorsoConUtente(id, c);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/corso/getAll")
    public ResponseEntity<List <Corso>> findAll(){
        List <Corso> lista = corsoService.getAll();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista , HttpStatus.OK);
    }

}
