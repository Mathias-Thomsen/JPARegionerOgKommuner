package com.example.jparegionerogkommuner.service;

import com.example.jparegionerogkommuner.model.Region;
import com.example.jparegionerogkommuner.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class ApiServiceGetRegionerImpl implements ApiServiceGetRegioner {

    private RestTemplate restTemplate;

    public ApiServiceGetRegionerImpl(RestTemplate restTemplate) { this.restTemplate = restTemplate; }

    String regionUrl = "https://api.dataforsyningen.dk/regioner";


    @Autowired
    RegionRepository regionRepository;

    private void saveRegioner(List<Region> regioner) { regioner.forEach(reg -> regionRepository.save(reg)); }


    @Override
    public List<Region> getRegioner() {
        ResponseEntity<List<Region>> regionResponse = restTemplate.exchange(regionUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Region>>(){ });
        List<Region> regioner = regionResponse.getBody();
        saveRegioner(regioner);
        return regioner;
    }

    @Override
    public List<String> getKommuneNavne() {
        return null;
    }

}
