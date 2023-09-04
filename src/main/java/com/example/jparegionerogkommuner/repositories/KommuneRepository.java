package com.example.jparegionerogkommuner.repositories;

import com.example.jparegionerogkommuner.model.Kommune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KommuneRepository extends JpaRepository <Kommune, String> {

}
