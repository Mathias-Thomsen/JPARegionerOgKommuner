package com.example.jparegionerogkommuner.model;


import jakarta.persistence.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@Entity
public class Kommune {


    @Id
    @Column(length = 4)
    private String kode;
    private String navn;
    private String href;


    @ManyToOne
    @JoinColumn(name = "region", referencedColumnName = "kode")
    Region region;

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
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
}
