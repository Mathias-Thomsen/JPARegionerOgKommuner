package com.example.jparegionerogkommuner.repositories;

import com.example.jparegionerogkommuner.model.Kommune;
import com.example.jparegionerogkommuner.model.Region;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, String> {

}
