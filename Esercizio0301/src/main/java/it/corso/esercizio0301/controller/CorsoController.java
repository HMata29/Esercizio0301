package it.corso.esercizio0301.controller;

import it.corso.esercizio0301.model.Corso;
import it.corso.esercizio0301.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
}
