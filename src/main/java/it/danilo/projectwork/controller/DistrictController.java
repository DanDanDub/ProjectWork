package it.danilo.projectwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.danilo.projectwork.dto.DistrictDto;
import it.danilo.projectwork.service.DistrictService;

@RestController
public class DistrictController {	

	@Autowired
	private DistrictService districtService;

	@PostMapping(value = "/district")
	public ResponseEntity<DistrictDto> createDistrict(@RequestBody DistrictDto districtDto) {
		DistrictDto createdDistrict = districtService.createDistrict(districtDto);
		return new ResponseEntity<>(createdDistrict, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/district/{id}")
    public ResponseEntity<DistrictDto> getDistrictById(@PathVariable Integer id) {
        try {
            DistrictDto district = districtService.getElementById(id);
            if(district!=null) {
            	return new ResponseEntity<>(district, HttpStatus.OK);            	
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}