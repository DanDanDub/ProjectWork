package it.danilo.projectwork.service;

import it.danilo.projectwork.dto.CityDto;

public interface CityService {
	CityDto createCity(CityDto elementDto);	    
	CityDto getElementById(Integer elementId);
	CityDto login(String name, String password);
}