package it.corso.esercizio0301.controller;

import it.corso.esercizio0301.business.impl.RuoloService;
import it.corso.esercizio0301.business.impl.UtenteService;
import it.corso.esercizio0301.model.Role;
import it.corso.esercizio0301.model.Utente;
import it.corso.esercizio0301.repository.RuoloRepository;
import it.corso.esercizio0301.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity <List<Role>> getAll (){
        return new ResponseEntity<>(ruoloService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/ruolo/utente/insert/{id_utente}/")
    public ResponseEntity <Role> insert(@PathVariable("id_utente") Integer id , @RequestBody Role roleRequest){
        Utente u = utenteService.getById(id);
        Set<Utente> utenteSet = new HashSet<>();
        utenteSet.add(u);
        roleRequest.setUtenti(utenteSet);
        Role _role = ruoloRepository.save(roleRequest);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/ruolo/delete/{id}")
    public ResponseEntity <HttpStatus> delete(@PathVariable("id") Integer id){
        ruoloService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/ruolo/modifica/{id}")
    public ResponseEntity <Role> update (@PathVariable("id") Integer id , @RequestBody Role roleRequest){
        Role roleTrovato = ruoloService.findById(id);
        roleTrovato.setPosizione(roleRequest.getPosizione());
        return new ResponseEntity<>(ruoloRepository.save(roleTrovato), HttpStatus.OK);
    }
}
