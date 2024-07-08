package it.danilo.projectwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.danilo.projectwork.entity.District;

public interface DistrictRepository extends JpaRepository<District, Integer> {

}