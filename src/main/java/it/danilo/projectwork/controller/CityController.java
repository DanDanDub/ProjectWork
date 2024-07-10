package it.danilo.projectwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.danilo.projectwork.dto.CityDto;
import it.danilo.projectwork.mapper.CityMapper;
import it.danilo.projectwork.service.CityService;

@RestController
public class CityController {	

	@Autowired
	private CityService cityService;

	@Autowired
	private CityMapper cityMapper;

	@PostMapping(value = "/city", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createCity(@RequestBody CityDto cityDto) {
		
		ResponseEntity<String> response = null;
		
		try {
			CityDto createdCity = cityService.createCity(cityDto);
			response = new ResponseEntity<>(cityMapper.mapToJSON(cityMapper.mapToCity(createdCity)).toString(), HttpStatus.CREATED);
		} catch (Exception e) {
	        response = new ResponseEntity<>("Internal server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        e.printStackTrace();
		}

		return response;
	}

}