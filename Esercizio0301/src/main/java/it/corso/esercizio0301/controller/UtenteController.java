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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/utente")
public class UtenteController {
    @Autowired
    UtenteRepository utenteRepository;
    @Autowired
    CorsoRepository corsoRepository;

    @PostMapping("/insert")
    public ResponseEntity<Utente> inserisci(@RequestBody Utente u){
        Utente uInserito = utenteRepository.save(u);
        return new ResponseEntity<>(uInserito, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity <Utente> modifica(@PathVariable("id") Integer id, @RequestBody Utente utenteRequest){
        Utente utenteTrovato = utenteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TagId " + id + "not found"));
        utenteTrovato.setUsername(utenteRequest.getUsername());
        utenteTrovato.setEmail(utenteRequest.getEmail());
        utenteTrovato.setPassword(utenteRequest.getPassword());
        utenteTrovato.setCorsi(utenteRequest.getCorsi());
        return new ResponseEntity<>(utenteRepository.save(utenteTrovato), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity <HttpStatus> elimina (@PathVariable("id")Integer id){
        utenteRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/corso/{id}/utente")
    public ResponseEntity <Corso> creaUtenteCorso(@PathVariable("id") Integer id,@RequestBody  Utente u){
        Corso corso = corsoRepository.getReferenceById(id);
        Set<Corso> corsoSet = new HashSet<>();
        corsoSet.add(corso);
        u.setCorsi(corsoSet);
        Utente _utente = utenteRepository.save(u);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/corso/{id_corso}/{id_utente}")
    public ResponseEntity <HttpStatus> associaCorsoUtente(@PathVariable("id_corso") Integer idCorso, @PathVariable("id_utente")Integer idUtente){
        Corso c = corsoRepository.findById(idCorso).orElseThrow(() -> new ResourceNotFoundException("CorsoId " + idCorso + "not found"));
        Utente u = utenteRepository.findById(idUtente).orElseThrow(() -> new ResourceNotFoundException("UtenteId " + idUtente + "not found"));

        c.setUtenti((Set<Utente>) u);
        u.setCorsi((Set<Corso>) c);
        Corso _corso = corsoRepository.save(c);
        Utente _utente = utenteRepository.save(u);

        return new ResponseEntity<>(HttpStatus.CREATED);


    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Utente>> getAll(){
        List <Utente> lista =  utenteRepository.findAll();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }


}
