package com.example.jparegionerogkommuner.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Region {



    @Id
    @Column(length = 4)
    private String kode;
    private String navn;
    private String href;


    //cascade is used so vi delete all the connected children for the deleted region.
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<Kommune> kommuner = new HashSet<>();


    @JsonIgnore
    public List<String> getKommuneNavne() {
        List<String> lst = new ArrayList<>();
        kommuner.forEach((kom) -> lst.add(kom.getNavn()));
        return lst;
    }



    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Set<Kommune> getKommuner() {
        return kommuner;
    }

    public void setKommuner(Set<Kommune> kommuner) {
        this.kommuner = kommuner;
    }
}
