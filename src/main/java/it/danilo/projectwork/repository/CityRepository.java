package it.danilo.projectwork.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.danilo.projectwork.entity.City;

public interface CityRepository extends JpaRepository<City, Integer> {

    Optional<City> findByName(String name);
    Optional<City> findByPassword(String password);
    Optional<City> findByNameAndPassword(String name, String password);

}