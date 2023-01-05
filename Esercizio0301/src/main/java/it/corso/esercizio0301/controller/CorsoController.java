package it.corso.esercizio0301.controller;

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


    @GetMapping("/corso/get/{id}")
    public ResponseEntity <Corso> getCorso(@PathVariable("id") Integer id){
        Corso cNuovo = corsoRepository.getById(id);
        return new ResponseEntity<>(cNuovo, HttpStatus.FOUND);
    }

    @PostMapping("/corso/insert")
    public ResponseEntity <Corso> inserisci(@RequestBody  Corso c){
         Corso cNuovo = corsoRepository.save(c);
        return new ResponseEntity<>(cNuovo, HttpStatus.CREATED);
    }

    @PutMapping ("/corso/update/{id}")
    public ResponseEntity <Corso> update(@PathVariable("id") Integer id, @RequestBody Corso corsoRequest){
        Corso c1 = corsoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TagId " + id + "not found"));
        c1.setNome(corsoRequest.getNome());
        return new ResponseEntity<>(corsoRepository.save(c1), HttpStatus.OK);
    }

    @DeleteMapping ("/corso/delete/{id}")
    public ResponseEntity <HttpStatus> delete (@PathVariable("id") Integer i){
        corsoRepository.deleteById(i);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/corso/utente/{id}/corso")
    public ResponseEntity <Corso> creaCorsoUtente(@PathVariable("id") Integer id,@RequestBody  Corso c){
        Utente utente = utenteRepository.getReferenceById(id);
        Set<Utente> utenteSet = new HashSet<>();
        utenteSet.add(utente);
        c.setUtenti(utenteSet);
        Corso _corso = corsoRepository.save(c);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/corso/getAll")
    public ResponseEntity<List <Corso>> findAll(){
        List <Corso> lista = corsoRepository.findAll();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista , HttpStatus.OK);
    }

}
