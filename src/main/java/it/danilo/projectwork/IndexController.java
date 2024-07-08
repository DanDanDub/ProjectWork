package it.danilo.projectwork;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@GetMapping("/")
	public String index() {
		return "Ciao utente!";
	}

	@GetMapping("/hello")
	public String helloDistrict() {
		
		/*
		CityDto cityDto = new CityDto("Città 1", "Password 1");
		CityDto createdCity = cityService.createCity(cityDto);
		System.out.println("--> " + new ResponseEntity<>(createdCity, HttpStatus.CREATED));
		
		DistrictDto districtDto = new DistrictDto(1, "Indirizzo 1");
		DistrictDto createdDistrict = districtService.createDistrict(districtDto);
		System.out.println("Distretto creato --> " + new ResponseEntity<>(createdDistrict, HttpStatus.CREATED));
		
		DeviceDto deviceDto = new DeviceDto(1, 1, "Device 1");
		DeviceDto createdDevice = deviceService.createDevice(deviceDto);
		System.out.println("Device creato --> " + new ResponseEntity<>(createdDevice, HttpStatus.CREATED));
		
		StatusDto statusDto = new StatusDto(1, 1, 1, 101);
		StatusDto createdStatus = statusService.createStatus(statusDto);
		System.out.println("Status creato --> " + new ResponseEntity<>(createdStatus, HttpStatus.CREATED));
		*/
		
		return "Ciao user!";
	}

}