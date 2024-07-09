package it.danilo.projectwork.repository;

import java.util.Date;
import java.util.List;

import it.danilo.projectwork.entity.City;
import it.danilo.projectwork.entity.Device;
import it.danilo.projectwork.entity.District;
import it.danilo.projectwork.entity.Status;

public interface StatusRepositoryCustom {

	List<Status> findStatusCustom(City city, District district, Device device, Date dateFrom, Date dateTo, Integer co2Min, Integer co2Max);

}