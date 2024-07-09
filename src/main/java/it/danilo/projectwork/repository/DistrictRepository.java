package it.danilo.projectwork.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.danilo.projectwork.entity.City;
import it.danilo.projectwork.entity.District;

public interface DistrictRepository extends JpaRepository<District, Integer> {

    Optional<District> findByIdAndCity(Integer id, City city);

}