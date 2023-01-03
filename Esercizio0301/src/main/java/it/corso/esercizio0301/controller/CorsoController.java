package it.corso.esercizio0301.controller;

import it.corso.esercizio0301.model.Corso;
import it.corso.esercizio0301.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/corso")
public class CorsoController {
    @Autowired
    CorsoRepository corsoRepository;

    @GetMapping("/getAll")
    public ResponseEntity <List <Corso>> getCorso(){
        List<Corso> listaCorso = new ArrayList<>();
        corsoRepository.findAll().forEach(listaCorso::add);

        if(listaCorso.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaCorso, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity <Corso> inserisci(@RequestBody  Corso c){
         Corso cNuovo = corsoRepository.save(c);
        return new ResponseEntity<>(cNuovo, HttpStatus.CREATED);
    }

    @PutMapping ("/update/{id}")
    public ResponseEntity <Corso> update(@PathVariable("id") Integer id, @RequestBody Corso corsoRequest){
        Corso c1 = corsoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TagId " + id + "not found"));
        c1.setNome(corsoRequest.getNome());
        return new ResponseEntity<>(corsoRepository.save(c1), HttpStatus.OK);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity <HttpStatus> delete (@PathVariable("id") Integer i){
        corsoRepository.deleteById(i);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
