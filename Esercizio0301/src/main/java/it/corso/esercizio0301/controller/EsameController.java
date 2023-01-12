package it.corso.esercizio0301.controller;

import it.corso.esercizio0301.business.impl.EsameService;
import it.corso.esercizio0301.model.Corso;
import it.corso.esercizio0301.model.Esame;
import it.corso.esercizio0301.repository.CorsoRepository;
import it.corso.esercizio0301.repository.EsameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class EsameController {
    @Autowired
    EsameService esameService;

    @Autowired
    CorsoRepository corsoRepository;

    @Autowired
    EsameRepository esameRepository;

    @GetMapping("/esame/getAll")
    public ResponseEntity<List <Esame>> getAll(){
        return new ResponseEntity<>(esameService.getAll(), HttpStatus.FOUND);
    }

    @GetMapping("/esame/getById/{id}")
    public ResponseEntity<Esame> getAll(@RequestParam("id") Integer id){
        return new ResponseEntity<>(esameService.getById(id), HttpStatus.FOUND);
    }

    @PostMapping("/esame/insert")
        public ResponseEntity<Esame> insert (@RequestBody Esame e) {
        return new ResponseEntity<>(esameService.insert(e), HttpStatus.CREATED);
    }

    @DeleteMapping("/esame/deleteById/{id}")
        public ResponseEntity<HttpStatus> deleteById(@RequestParam("id") Integer id){
        esameService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/esame/update/{id}")
    public ResponseEntity <Esame> updateBy(@RequestParam("id") Integer id, @RequestBody Esame esameRequest){
        Esame esameTrovato = esameService.getById(id);
        esameTrovato.setValutazione(esameRequest.getValutazione());
        esameTrovato.setGiorno(esameRequest.getGiorno());
        esameTrovato.setMese(esameRequest.getMese());
        esameTrovato.setAnno(esameRequest.getAnno());
        return new ResponseEntity<>(esameRepository.save(esameTrovato) , HttpStatus.OK);
    }

    @GetMapping("/esame/getByValutazione/{valutazopme}")
    public ResponseEntity<List <Esame>> findByValutazione(@RequestParam("valutazione") Integer valutazione){
        return new ResponseEntity<>(esameService.findByValutazione(valutazione), HttpStatus.FOUND);
    }

    @PostMapping("/esame/corso/{id}/aggiungi")
    public ResponseEntity<Esame> creaEsameDaCorso(@PathVariable("id") Integer id, @RequestBody Esame e){
    Corso corso = corsoRepository.getReferenceById(id);
    Set<Corso> corsoSet = new HashSet<>();
    corsoSet.add(corso);
    e.setCorso((Corso) corsoSet);
    Esame _esame = esameRepository.save(e);
    return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
