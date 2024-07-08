package it.danilo.projectwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import it.danilo.projectwork.dto.CityDto;
import it.danilo.projectwork.service.CityService;

@RestController
public class CityController {	

	@Autowired
	private CityService cityService;

	@PostMapping(value = "/city")
	public ResponseEntity<CityDto> createCity(@RequestBody CityDto cityDto) {
		CityDto createdCity = cityService.createCity(cityDto);
		return new ResponseEntity<>(createdCity, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/city/{id}")
    public ResponseEntity<CityDto> getCityById(@RequestHeader String name, @RequestHeader String password, @PathVariable Integer id) {

		ResponseEntity<CityDto> response = null;
		
        try {
        	
			CityDto login = cityService.login(name, password);
			
			if(login!=null) {
				CityDto city = cityService.getElementById(id);
		        if(city!=null) {
		        	CityDto cityLite = new CityDto(city.getId(), city.getName(), city.getPassword());
		            response = new ResponseEntity<>(cityLite, HttpStatus.OK);            	
		        } else {
		        	response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
			} else {
				response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return response;
    }

}