package it.danilo.projectwork.controller;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import it.danilo.projectwork.dto.CityDto;
import it.danilo.projectwork.dto.DistrictDto;
import it.danilo.projectwork.entity.City;
import it.danilo.projectwork.mapper.CityMapper;
import it.danilo.projectwork.mapper.DistrictMapper;
import it.danilo.projectwork.service.CityService;
import it.danilo.projectwork.service.DistrictService;

@RestController
public class DistrictController {	

	@Autowired
	private DistrictService districtService;

	@Autowired
	private DistrictMapper districtMapper;

	@Autowired
	private CityService cityService;

	@Autowired
	private CityMapper cityMapper;
	
	@PostMapping(value = "/district", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createDistrict(@RequestHeader String name, @RequestHeader String password, @RequestBody DistrictDto districtDto) {

		ResponseEntity<String> response = null;
		
		try {
			CityDto login = cityService.login(name, password);
			if(login!=null) {
				
				City city = cityMapper.mapToCity(login);
				if(city!=null) {
					districtDto.setCity(city);
					DistrictDto createdDistrict = districtService.createDistrict(districtDto);
					response = new ResponseEntity<>(districtMapper.mapToJSON(createdDistrict).toString(), HttpStatus.OK);
			    } else {
					response = new ResponseEntity<>("Login failed - City not found", HttpStatus.FORBIDDEN);
				}
			} else {
				response = new ResponseEntity<>("Login failed", HttpStatus.FORBIDDEN);
			}
			
	    } catch (Exception e) {
	        response = new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
	        e.printStackTrace();
	    }
        
        return response;
	}
	
	@PostMapping(value = "/districts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createDistrict(@RequestHeader String name, @RequestHeader String password, @RequestBody DistrictDto[] districtDtos) {

		ResponseEntity<String> response = null;
		
		try {
			CityDto login = cityService.login(name, password);
			if(login!=null) {
				
				City city = cityMapper.mapToCity(login);
				if(city!=null) {
					
					JSONArray JSONDistrictList = new JSONArray();
					
					for(DistrictDto districtDto : districtDtos) {
						districtDto.setCity(city);
						DistrictDto createdDistrict = districtService.createDistrict(districtDto);
						JSONDistrictList.put(districtMapper.mapToJSON(createdDistrict));
					}
					
					response = new ResponseEntity<>(JSONDistrictList.toString(), HttpStatus.OK);
					
			    } else {
					response = new ResponseEntity<>("Login failed - City not found", HttpStatus.FORBIDDEN);
				}
			} else {
				response = new ResponseEntity<>("Login failed", HttpStatus.FORBIDDEN);
			}
			
	    } catch (Exception e) {
	        response = new ResponseEntity<>("Internal server error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        e.printStackTrace();
	    }
        
        return response;
	}

}