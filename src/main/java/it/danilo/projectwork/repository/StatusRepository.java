package it.danilo.projectwork.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.danilo.projectwork.entity.City;
import it.danilo.projectwork.entity.Device;
import it.danilo.projectwork.entity.District;
import it.danilo.projectwork.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Integer>, StatusRepositoryCustom {

    List<Status> findByCity(City city);

	List<Status> findStatusCustom(City city, District district, Device device, Date dateFrom, Date dateTo, Integer co2Min, Integer co2Max);

}