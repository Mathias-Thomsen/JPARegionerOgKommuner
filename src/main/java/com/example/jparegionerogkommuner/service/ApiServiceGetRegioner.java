package com.example.jparegionerogkommuner.service;

import com.example.jparegionerogkommuner.model.Region;

import java.util.List;

public interface ApiServiceGetRegioner {

    List<Region> getRegioner();
    List<String> getKommuneNavne();

}
