package it.danilo.projectwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.danilo.projectwork.dto.DeviceDto;
import it.danilo.projectwork.service.DeviceService;

@RestController
public class DeviceController {	

	@Autowired
	private DeviceService deviceService;

	@PostMapping(value = "/device")
	public ResponseEntity<DeviceDto> createDevice(@RequestBody DeviceDto deviceDto) {
		DeviceDto createdDevice = deviceService.createDevice(deviceDto);
		return new ResponseEntity<>(createdDevice, HttpStatus.CREATED);
	}	
	
	@GetMapping(value = "/device/{id}")
    public ResponseEntity<DeviceDto> getDeviceById(@PathVariable Integer id) {
        try {
            DeviceDto device = deviceService.getElementById(id);
            if(device!=null) {
            	return new ResponseEntity<>(device, HttpStatus.OK);            	
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}