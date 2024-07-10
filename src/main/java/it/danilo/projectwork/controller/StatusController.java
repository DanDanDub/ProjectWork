package it.danilo.projectwork.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import it.danilo.projectwork.dto.DeviceDto;
import it.danilo.projectwork.dto.DistrictDto;
import it.danilo.projectwork.dto.StatusDto;
import it.danilo.projectwork.entity.City;
import it.danilo.projectwork.entity.Device;
import it.danilo.projectwork.entity.District;
import it.danilo.projectwork.entity.Status;
import it.danilo.projectwork.mapper.CityMapper;
import it.danilo.projectwork.mapper.DeviceMapper;
import it.danilo.projectwork.mapper.DistrictMapper;
import it.danilo.projectwork.mapper.StatusMapper;
import it.danilo.projectwork.repository.DeviceRepository;
import it.danilo.projectwork.repository.StatusRepository;
import it.danilo.projectwork.service.CityService;
import it.danilo.projectwork.service.DeviceService;
import it.danilo.projectwork.service.DistrictService;
import it.danilo.projectwork.service.StatusService;

@RestController
public class StatusController {

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private StatusService statusService;

	@Autowired
	private StatusMapper statusMapper;

	@Autowired
	private CityService cityService;

	@Autowired
	private CityMapper cityMapper;

	@Autowired
	private DistrictService districtService;

	@Autowired
	private DistrictMapper districtMapper;

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private DeviceMapper deviceMapper;

	
	@PostMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createStatus(@RequestHeader String name, @RequestHeader String password, @RequestBody String bodyString) {

		ResponseEntity<String> response = null;
		boolean keepGoing = true;
		String procedureName = "Status creation - ";
		
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
			
			// Check Device
			Device device = null;
			if(keepGoing) {
				String stageName = procedureName + "Device check ";
				if(body.has("device") && body.getJSONObject("device")!=null && body.getJSONObject("device").has("id")) {
					Optional<Device> deviceOptional = deviceRepository.findByIdAndCity(body.getJSONObject("device").getInt("id"), city);
					if(deviceOptional!=null && deviceOptional.isPresent() && deviceOptional.get()!=null) {
						device = deviceOptional.get();
					} else {
						keepGoing = false;
						response = new ResponseEntity<>(stageName + "failed: Device not found", HttpStatus.NOT_ACCEPTABLE);
					}
				} else {
					keepGoing = false;
					response = new ResponseEntity<>(stageName + "failed: Device mandatory", HttpStatus.NOT_ACCEPTABLE);
				}
			}
			
			// Check timestamp
			Date timestamp = null;
			if(keepGoing) {
				String stageName = procedureName + "Check timestamp ";
				//String stageName = "Device creation failed - timestamp check - ";
				if(body.has("timestamp") && body.getString("timestamp")!=null) {
					String timestampString = body.getString("timestamp");
					try {
						timestamp = Utils.yyyy_MM_dd_HH_mm_ss.parse(timestampString);
					} catch (Exception e) {
						keepGoing = false;
						response = new ResponseEntity<>(stageName + "failed: timestamp not valid", HttpStatus.NOT_ACCEPTABLE);
					}
				}
			}
			
			// Check Co2Level
			Integer co2Level = null;
			if(keepGoing) {
				String stageName = procedureName + "Check Co2Level ";
				if(body.has("co2Level")) {
					co2Level = body.getInt("co2Level");
				} else {
					keepGoing = false;
					response = new ResponseEntity<>(stageName + "failed: Co2Level mandatory", HttpStatus.NOT_ACCEPTABLE);
				}
			}
			
			// Creating Status
			if(keepGoing) {
				StatusDto statusDto = new StatusDto(null, city, device, device.getDistrict(), timestamp, co2Level);
				StatusDto createdStatus = statusService.createStatus(statusDto);
				response = new ResponseEntity<>(statusMapper.mapToJSON(createdStatus).toString(), HttpStatus.OK);
			}
			
			
	    } catch (Exception e) {
	        response = new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
	        e.printStackTrace();
	    }
        
        return response;
	}
	
	@PostMapping(value = "/statusesList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getStatusesList(@RequestHeader String name, @RequestHeader String password, @RequestBody String bodyString) {

		ResponseEntity<String> response = null;
		
        try {
        	
        	JSONObject body = new JSONObject(bodyString);
        	
			CityDto login = cityService.login(name, password);
			
			if(login!=null) {
				
				City city = cityMapper.mapToCity(login);
				if(city!=null) {
						
					// Lettura body
					District district = null;
					Device device = null;
					Date dateFrom = null;
					Date dateTo = null;
					Integer co2Min = null;
					Integer co2Max = null;
					
					if(body!=null) {
						if(body.has("district")) {
							DistrictDto districtDto = districtService.getElementById(body.getJSONObject("district").getInt("id"));
							if(districtDto!=null) {
								district = districtMapper.mapToDistrict(districtDto);
							}
						}
						if(body.has("device")) {
							DeviceDto deviceDto = deviceService.getElementById(body.getJSONObject("device").getInt("id"));
							device = deviceMapper.mapToDevice(deviceDto);
						}
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
					List<Status> statuses = statusRepository.findStatusCustom(city, district, device, dateFrom, dateTo, co2Min, co2Max);
	
			        if(statuses!=null && statuses.size()>0) {
			        	JSONObject statusesJSON = statusMapper.mapToJSONList(statuses);
			        	response = new ResponseEntity<>(statusesJSON.toString(), HttpStatus.OK);
			        } else {
			        	response = new ResponseEntity<>("No statuses found", HttpStatus.NOT_FOUND);
			        }				
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