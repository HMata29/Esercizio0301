package it.corso.esercizio0301.controller;

import it.corso.esercizio0301.business.service.UtenteService;
import it.corso.esercizio0301.model.Corso;
import it.corso.esercizio0301.model.Posizione;
import it.corso.esercizio0301.model.Ruolo;
import it.corso.esercizio0301.model.Utente;
import it.corso.esercizio0301.payload.request.SignupRequest;
import it.corso.esercizio0301.payload.response.MessageResponse;
import it.corso.esercizio0301.repository.CorsoRepository;
import it.corso.esercizio0301.repository.RuoloRepository;
import it.corso.esercizio0301.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class UtenteController {
    @Autowired
    UtenteRepository utenteRepository;
    @Autowired
    CorsoRepository corsoRepository;

    @Autowired
    RuoloRepository ruoloRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UtenteService utenteService;

    @PostMapping("/utente/insert")
    public ResponseEntity<Utente> inserisci(@RequestBody Utente u){
        Utente uInserito = utenteService.insertUtente(u);
        return new ResponseEntity<>(uInserito, HttpStatus.CREATED);
    }

    @PutMapping("/utente/update/{id}")
    public ResponseEntity <Utente> modifica(@PathVariable("id") Integer id, @RequestBody Utente utenteRequest){
        Utente utenteTrovato = utenteService.updateUtenteById(id);
        utenteTrovato.setUsername(utenteRequest.getUsername());
        utenteTrovato.setEmail(utenteRequest.getEmail());
        utenteTrovato.setPassword(utenteRequest.getPassword());
        utenteTrovato.setCorsi(utenteRequest.getCorsi());
        return new ResponseEntity<>(utenteRepository.save(utenteTrovato), HttpStatus.OK);
    }

    @DeleteMapping("/utente/delete/{id}")
    public ResponseEntity <HttpStatus> elimina (@PathVariable("id")Integer id){
        utenteService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/utente/corso/{id}/utente")
    public ResponseEntity <Corso> creaUtenteCorso(@PathVariable("id") Integer id,@RequestBody  Utente u){
        Corso corso = corsoRepository.getReferenceById(id);
        Set<Corso> corsoSet = new HashSet<>();
        corsoSet.add(corso);
        u.setCorsi(corsoSet);
        Utente _utente = utenteRepository.save(u);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/utente/corso/{id_corso}/{id_utente}")
    public ResponseEntity <HttpStatus> associaCorsoUtente(@PathVariable("id_corso") Integer idCorso, @PathVariable("id_utente")Integer idUtente){
        Corso c = corsoRepository.findById(idCorso).orElseThrow(() -> new ResourceNotFoundException("CorsoId " + idCorso + "not found"));
        Utente u = utenteRepository.findById(idUtente).orElseThrow(() -> new ResourceNotFoundException("UtenteId " + idUtente + "not found"));

        c.setUtenti((Set<Utente>) u);
        u.setCorsi((Set<Corso>) c);
        Corso _corso = corsoRepository.save(c);
        Utente _utente = utenteRepository.save(u);

        return new ResponseEntity<>(HttpStatus.CREATED);


    }
    @GetMapping("/utente/getAll")
    public ResponseEntity<List<Utente>> getAll(){
        List <Utente> lista = utenteService.getAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/utente/get/{id}")
    public ResponseEntity<Utente>getUtente(@RequestParam("id") Integer id){
        return new ResponseEntity<>(utenteService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/utente/insert/admin")
    public ResponseEntity<?> insertAdmin(@RequestBody SignupRequest signupRequest){
        Utente newUtente = new Utente(signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));

        Set <Ruolo> ruoli= new HashSet<>();

        Ruolo ruoloAdmin = ruoloRepository.findByPosizione(Posizione.ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        ruoli.add(ruoloAdmin);

        newUtente.setRuoli(ruoli);
        utenteRepository.save(newUtente);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    @PostMapping("/utente/insert/moderatore")
    public ResponseEntity<?> insertModerator(@RequestBody SignupRequest signupRequest){
        Utente newUtente = new Utente(signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));


        Set <Ruolo> ruoli= new HashSet<>();

        Ruolo ruoloAdmin = ruoloRepository.findByPosizione(Posizione.MODERATORE).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        ruoli.add(ruoloAdmin);

        newUtente.setRuoli(ruoli);
        utenteRepository.save(newUtente);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/utente/insert/utente")
    public ResponseEntity<?> insertUtente(@RequestBody SignupRequest signupRequest){
        Utente newUtente = new Utente(signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));
        Set <Ruolo> ruoli= new HashSet<>();

        Ruolo ruoloAdmin = ruoloRepository.findByPosizione(Posizione.UTENTE).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        ruoli.add(ruoloAdmin);

        newUtente.setRuoli(ruoli);
        utenteRepository.save(newUtente);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


}
