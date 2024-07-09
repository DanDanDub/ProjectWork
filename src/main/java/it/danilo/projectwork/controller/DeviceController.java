package it.danilo.projectwork.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import it.danilo.projectwork.Utils;
import it.danilo.projectwork.dto.CityDto;
import it.danilo.projectwork.dto.DeviceDto;
import it.danilo.projectwork.entity.City;
import it.danilo.projectwork.entity.Device;
import it.danilo.projectwork.entity.District;
import it.danilo.projectwork.mapper.CityMapper;
import it.danilo.projectwork.mapper.DeviceMapper;
import it.danilo.projectwork.repository.DeviceRepository;
import it.danilo.projectwork.service.CityService;
import it.danilo.projectwork.service.DeviceService;

@RestController
public class DeviceController {	

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private DeviceMapper deviceMapper;

	@Autowired
	private CityService cityService;

	@Autowired
	private CityMapper cityMapper;

	@PostMapping(value = "/device", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createDevice(@RequestHeader String name, @RequestHeader String password, @RequestBody String bodyString) {

		ResponseEntity<String> response = null;
		boolean keepGoing = true;
		String procedureName = "Device creation - ";
		
		try {
			CityDto login = cityService.login(name, password);
			City city = null;
			JSONObject body = new JSONObject(bodyString);
			
			// Check City
			if(keepGoing) {
				String stageName = "Login ";
				if(login!=null) {
					city = cityMapper.mapToCity(login);
					if(city==null) {
						keepGoing = false;
						response = new ResponseEntity<>(stageName + "failed: City not found", HttpStatus.FORBIDDEN);
					}
				} else {
					keepGoing = false;
					response = new ResponseEntity<>(stageName + "failed: Wrong username or password", HttpStatus.FORBIDDEN);
				}
			}

			// Check fields
			District district = null;
			String nameField = null;
			if(keepGoing) {
				JSONObject checkFields = deviceService.checkFieldsJSON(city, procedureName, body);
				if(checkFields.getString(Utils.CHECK_STATUS).equals(Utils.CHECK_STATUS_SUCCESS)) {
					district = (District) checkFields.get("district");
					nameField = checkFields.getString("name");
				} else {
					response = new ResponseEntity<>(checkFields.get(Utils.CHECK_ERROR_MESSAGE).toString(), HttpStatus.NOT_ACCEPTABLE);
				}
			}
			
			// Creating Device
			if(keepGoing) {
				DeviceDto deviceDto = new DeviceDto(null, city, district, nameField);
				DeviceDto createdDevice = deviceService.createDevice(deviceDto);
				response = new ResponseEntity<>(deviceMapper.mapToDeviceJSON(city, deviceMapper.mapToDevice(createdDevice)).toString(), HttpStatus.OK);
			}
			
			
	    } catch (Exception e) {
	        response = new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
	        e.printStackTrace();
	    }
        
        return response;
	}	
	
	@GetMapping(value = "/device", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDeviceById(@RequestHeader String name, @RequestHeader String password) {
        
		ResponseEntity<String> response = null;
		boolean keepGoing = true;
		
		try {
			CityDto login = cityService.login(name, password);
			City city = null;
			
			// Check City
			if(login!=null) {
				city = cityMapper.mapToCity(login);
				if(city==null) {
					keepGoing = false;
					response = new ResponseEntity<>("Login failed - City not found", HttpStatus.FORBIDDEN);
				}
			} else {
				keepGoing = false;
				response = new ResponseEntity<>("Login failed", HttpStatus.FORBIDDEN);
			}
			
			// Find Devices
			if(keepGoing) {
				List<Device> devices = deviceRepository.findByCity(city);
				
		        if(devices!=null && devices.size()>0) {
		        	JSONObject devicesJSON = deviceMapper.mapToDeviceesJSON(city, devices);
		        	response = new ResponseEntity<>(devicesJSON.toString(), HttpStatus.OK);
		        } else {
		        	response = new ResponseEntity<>("No devices found", HttpStatus.NOT_FOUND);
		        }	
			}
			
			
	    } catch (Exception e) {
	        response = new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
	        e.printStackTrace();
	    }
        
        return response;
    }
}