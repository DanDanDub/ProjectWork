package it.danilo.projectwork.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.danilo.projectwork.entity.City;
import it.danilo.projectwork.entity.Device;
import it.danilo.projectwork.entity.District;

public interface DeviceRepository extends JpaRepository<Device, Integer> {

    List<Device> findByCity(City city);
    List<Device> findByCityAndDistrict(City city, District district);
    Optional<Device> findByIdAndCity(Integer id, City city);

}