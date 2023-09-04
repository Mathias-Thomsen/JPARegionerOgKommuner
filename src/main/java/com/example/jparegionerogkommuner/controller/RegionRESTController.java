package com.example.jparegionerogkommuner.controller;


import com.example.jparegionerogkommuner.model.Kommune;
import com.example.jparegionerogkommuner.model.Region;
import com.example.jparegionerogkommuner.repositories.KommuneRepository;
import com.example.jparegionerogkommuner.repositories.RegionRepository;
import com.example.jparegionerogkommuner.service.ApiServiceGetKommuner;
import com.example.jparegionerogkommuner.service.ApiServiceGetRegioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RegionRESTController {

    @Autowired
    ApiServiceGetRegioner apiServiceGetRegioner;

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    ApiServiceGetKommuner apiServiceGetKommuner;


    @GetMapping("/getRegioner")
    public List<Region> getRegionerFromApi() {
        List<Region> lstRegioner = apiServiceGetRegioner.getRegioner();
        return lstRegioner;
    }


    //All these method get the data from our database.
    @GetMapping("/regioner")
    public List<Region> getRegioner() {
        return regionRepository.findAll();
    }

    @PostMapping("/region")
    @ResponseStatus(HttpStatus.CREATED)
    public Region postRegion(@RequestBody Region region) {
        System.out.println(region);
        return regionRepository.save(region);
    }

    @PutMapping("/region/{id}")
    public ResponseEntity<Region> putRegion(@PathVariable String id, @RequestBody Region region) {
        Optional<Region> orgRegion = regionRepository.findById(id);
        if(orgRegion.isPresent()) {
            region.setKode(id);
            regionRepository.save(region);
            return ResponseEntity.ok(region);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/region/{id}")
    public ResponseEntity<String> deleteRegion(@PathVariable String id){
        Optional<Region> orgRegion = regionRepository.findById(id);
        if(orgRegion.isPresent()) {
            regionRepository.deleteById(id);
            return ResponseEntity.ok("Regionen er blevet slettet");

        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Regionen er ikke fundet");
        }


    }


    //Opgave 2
    @GetMapping("/kommuneNavne/{id}")
    public List<String> getKommuneNavne(@PathVariable String id) {

        List<String> lst = new ArrayList<>();
        for (Kommune kommune : apiServiceGetKommuner.getKommuner()){
            if(kommune.getRegion().getKode().equals(id)){
                lst.add(kommune.getNavn());
            }
        }
        return lst;
    }






}
