package it.danilo.projectwork.mapper;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import it.danilo.projectwork.dto.DeviceDto;
import it.danilo.projectwork.dto.DistrictDto;
import it.danilo.projectwork.dto.StatusDto;
import it.danilo.projectwork.entity.City;
import it.danilo.projectwork.entity.District;
import it.danilo.projectwork.entity.Status;
import it.danilo.projectwork.service.DeviceService;
import it.danilo.projectwork.service.DistrictService;

@Component
public class StatusMapper {
	
    private final DistrictService districtService;
    private final DeviceService deviceService;

    public StatusMapper(DistrictService districtService, DeviceService deviceService) {
        this.districtService = districtService;
        this.deviceService = deviceService;
    }

	public StatusDto mapToStatusDto(Status element) {
		return new StatusDto(element.getId(), element.getCity(), element.getDevice(), element.getDistrict(), element.getCo2Level());
	}

	public Status mapToStatus(StatusDto elementDto) {
		//return new Status(elementDto.getId(), elementDto.getCity(), elementDto.getDevice(), elementDto.getName(), elementDto.getStatuses());
		
		Status status = null;
		
		if(elementDto.getCity()!=null && elementDto.getDistrict()!=null) {
			// Creazione status completo
			status = new Status(elementDto.getId(), elementDto.getCity(), elementDto.getDevice(), elementDto.getDistrict(), elementDto.getCo2Level());

		} else if(elementDto.getDevice()!=null && elementDto.getDevice().getId()!=null) {
			// Creazione status con district o city non compilati
			DeviceDto device = deviceService.getElementById(elementDto.getDevice().getId());

			DistrictDto district = districtService.getElementById(device.getDistrict().getId());
			if(district!=null && district.getId()!=null) {
				// Utilizzo di districtLite per non cadere nella ricorsività delle collection District->Device->District->Device...
				District districtLite = new District(district.getId(), null, null, null, null);
				City cityLite = new City(district.getCity().getId(), null, null, null, null, null);
				status = new Status(elementDto.getId(), cityLite, elementDto.getDevice(), districtLite, elementDto.getCo2Level());
			}
			
		} else {
			//TODO: ERRORE - Nessun distretto indicato
		}
		
		return status;
	}

	public JSONObject mapToStatusesJSON(City city, List<Status> statuses) {
		
		JSONObject statusesJSON = new JSONObject();
		JSONArray statusesListJSON = new JSONArray();
		
		if(city!=null) {
			statusesJSON.put("City", city.getName());
		}
				
		if(statuses!=null && statuses.size()>0) {
			for(Status status : statuses) {
				JSONObject statusJSON = new JSONObject();
				statusJSON.put("District", status.getDistrict().getAddress());
				statusJSON.put("Device", status.getDevice().getName());
				statusJSON.put("Time", status.getTimestamp());
				statusJSON.put("CO2_Level", status.getCo2Level());
				statusesListJSON.put(statusJSON);
			}
		}

		statusesJSON.put("Statuses", statusesListJSON);
		
		return statusesJSON;
	}
	
}