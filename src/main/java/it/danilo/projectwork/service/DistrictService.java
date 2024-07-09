package it.danilo.projectwork.service;

import it.danilo.projectwork.dto.DistrictDto;

public interface DistrictService {
	DistrictDto createDistrict(DistrictDto elementDto);	
	DistrictDto getElementById(Integer id);	
}