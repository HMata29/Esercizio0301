package it.corso.esercizio0301.controller;

import it.corso.esercizio0301.model.Utente;
import it.corso.esercizio0301.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utente")
public class UtenteController {
    @Autowired
    UtenteRepository utenteRepository;

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


}
