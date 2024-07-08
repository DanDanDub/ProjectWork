package it.danilo.projectwork.controller;

import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import it.danilo.projectwork.Utils;
import it.danilo.projectwork.dto.CityDto;
import it.danilo.projectwork.dto.StatusDto;
import it.danilo.projectwork.entity.City;
import it.danilo.projectwork.entity.Status;
import it.danilo.projectwork.mapper.CityMapper;
import it.danilo.projectwork.mapper.StatusMapper;
import it.danilo.projectwork.repository.StatusRepository;
import it.danilo.projectwork.service.CityService;
import it.danilo.projectwork.service.StatusService;

@RestController
public class StatusController {	

	@Autowired
	private StatusService statusService;	

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private StatusMapper statusMapper;

	@Autowired
	private CityService cityService;

	@Autowired
	private CityMapper cityMapper;


	@PostMapping(value = "/status")
	public ResponseEntity<StatusDto> createStatus(@RequestBody StatusDto statusDto) {
		StatusDto createdStatus = statusService.createStatus(statusDto);
		return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
	}		
	
	/*
	@GetMapping(value = "/status/{id}")
    public ResponseEntity<StatusDto> getStatusById(@PathVariable Integer id) {
        try {
            StatusDto status = statusService.getElementById(id);
            if(status!=null) {
            	return new ResponseEntity<>(status, HttpStatus.OK);            	
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */
	
	@PostMapping(value = "/statusesList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCityById(@RequestHeader String name, @RequestHeader String password, @RequestBody String bodyString) {

		ResponseEntity<String> response = null;
		
        try {
        	
        	JSONObject body = new JSONObject(bodyString);
        	
			CityDto login = cityService.login(name, password);
			
			if(login!=null) {
				
				// Lettura body
				Date dateFrom = null;
				Date dateTo = null;
				Integer co2Min = null;
				Integer co2Max = null;
				
				if(body!=null) {
					if(body.has("dateFrom")) {
						String dateFromString = body.getString("dateFrom");
						dateFrom = Utils.yyyy_MM_dd_HH_mm_ss.parse(dateFromString);
					}
					if(body.has("dateTo")) {
						String dateToString = body.getString("dateTo");
						dateTo = Utils.yyyy_MM_dd_HH_mm_ss.parse(dateToString);
					}
					if(body.has("co2Min")) {
						co2Min = body.getInt("co2Min");
					}
					if(body.has("co2Max")) {
						co2Max = body.getInt("co2Max");
					}
				}
				
				City city = cityMapper.mapToCity(login);
				List<Status> statuses = statusRepository.findStatusCustom(city, dateFrom, dateTo, co2Min, co2Max);

		        if(statuses!=null && statuses.size()>0) {
		        	JSONObject statusesJSON = statusMapper.mapToStatusesJSON(city, statuses);
		        	response = new ResponseEntity<>(statusesJSON.toString(), HttpStatus.OK);
		        } else {
		        	response = new ResponseEntity<>(new JSONObject("No statuses found").toString(), HttpStatus.NOT_FOUND);
		        }
			} else {
				response = new ResponseEntity<>(new JSONObject("Login failed").toString(), HttpStatus.FORBIDDEN);
			}
			
        } catch (Exception e) {
            response = new ResponseEntity<>(new JSONObject("Internal server error").toString(), HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        
        return response;
    }

}