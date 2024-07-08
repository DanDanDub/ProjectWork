package it.danilo.projectwork.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.danilo.projectwork.dto.CityDto;
import it.danilo.projectwork.entity.City;
import it.danilo.projectwork.mapper.CityMapper;
import it.danilo.projectwork.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private CityMapper cityMapper;

	@Override
	public CityDto createCity(CityDto cityDto) {
		City city = cityMapper.mapToCity(cityDto);
		City createdCity = cityRepository.save(city);
		return cityMapper.mapToCityDto(createdCity);
	}

	@Override
	public CityDto getElementById(Integer cityId) {
		Optional<City> optionalElement = cityRepository.findById(cityId);
		CityDto city = null;
		if(optionalElement.isPresent()) {
			city = cityMapper.mapToCityDto(optionalElement.get());			
		}
		return city;
	}

	@Override
	public CityDto login(String name, String password) {
	
		CityDto cityLogin = null;
		
		if(name!=null && password!=null) {

			Optional<City> optionalElement = cityRepository.findByNameAndPassword(name, password);
			if(optionalElement.isPresent()) {
				cityLogin = cityMapper.mapToCityDto(optionalElement.get());			
			} else {
				logger.info(this.getClass() + " - Login errato per l'utente [name = " + name + "]");
				//TODO: Login errato - Da gestire login errati
			}

		} else {
			logger.info(this.getClass() + " - Login errato per l'utente [name = " + name + ", " + password + "]");
			//TODO: ERRORE - Da gestire login errati
		}
		
		return cityLogin;
		
	}

}