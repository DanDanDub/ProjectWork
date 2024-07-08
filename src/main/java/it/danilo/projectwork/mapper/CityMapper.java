package it.danilo.projectwork.mapper;

import org.springframework.stereotype.Component;

import it.danilo.projectwork.dto.CityDto;
import it.danilo.projectwork.entity.City;

@Component
public class CityMapper {

	public CityDto mapToCityDto(City element) {
		return new CityDto(element.getId(), element.getName(), element.getPassword(), element.getStatuses(), element.getDistricts(), element.getDevices());
	}

	public City mapToCity(CityDto elementDto) {
		return new City(elementDto.getId(), elementDto.getName(), elementDto.getPassword(), elementDto.getStatuses(), elementDto.getDistricts(), elementDto.getDevices());
	}

}