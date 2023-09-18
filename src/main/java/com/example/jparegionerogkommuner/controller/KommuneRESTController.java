package com.example.jparegionerogkommuner.controller;

import com.example.jparegionerogkommuner.model.Kommune;
import com.example.jparegionerogkommuner.model.Region;
import com.example.jparegionerogkommuner.repositories.KommuneRepository;
import com.example.jparegionerogkommuner.service.ApiServiceGetKommuner;
import com.example.jparegionerogkommuner.service.ApiServiceGetKommunerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class KommuneRESTController {


    @Autowired
    ApiServiceGetKommuner apiServiceGetKommuner;

    @Autowired
    KommuneRepository kommuneRepository;



    //This get the data from the api
    @GetMapping("/getKommuner")
    public List<Kommune> getKommuner() {
        return apiServiceGetKommuner.getKommuner();
    }

    //All these method get the data from our database.
    @GetMapping("/kommune")
    public List<Kommune> getKommune(){
        return kommuneRepository.findAll();
    }

    @PostMapping("/kommune")
    public ResponseEntity<Kommune> postKommune(@RequestBody Kommune kommune) {
        Kommune savedKommune = kommuneRepository.save(kommune);
        if(savedKommune == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(savedKommune, HttpStatus.CREATED);
        }
    }

    @PutMapping("/kommune/{id}")
    public ResponseEntity<Kommune> putKommune(@PathVariable String id, @RequestBody Kommune kommune) {
        Optional<Kommune> orgKommune = kommuneRepository.findById(id);
        if(orgKommune.isPresent()) {
            kommune.setKode(id);
            kommuneRepository.save(kommune);
            return ResponseEntity.ok(kommune);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/kommune/{id}")
    public ResponseEntity<String> deleteKommune(@PathVariable String id){
        Optional<Kommune> orgKommune = kommuneRepository.findById(id);
        if(orgKommune.isPresent()) {
            kommuneRepository.deleteById(id);
            return ResponseEntity.ok("Kommunen er blevet slettet");

        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kommunen er ikke fundet");
        }


    }



}
