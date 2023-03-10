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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping ("/upload/{id}")
    public   ResponseEntity<Map<String,String>> uploadFile(@PathVariable("id") Integer id, @RequestParam("file")MultipartFile data){
        try{
            corsoService.uploadFile(id, data);
            return  new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            Map<String,String> map = new HashMap<>();
            String message = "Non posso caricare il file: " + data.getOriginalFilename();
            map.put("Error",message);
            return new ResponseEntity<>(map, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/deleteFile/corso/{id}")
    public ResponseEntity <HttpStatus> setFileNull(@PathVariable("id") Integer id){
            if(!(corsoService.getById(id).getData()== null)){
                corsoService.deleteFile(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

}
