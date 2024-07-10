package it.danilo.projectwork.mapper;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private CityMapper cityMapper;

	@Autowired
	private DistrictMapper districtMapper;

	@Autowired
	private DeviceMapper deviceMapper;
	
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

	public JSONObject mapToJSONList(ArrayList<StatusDto> statusesDto) {
		
		List<Status> statuses = new ArrayList<Status>();

		if(statusesDto!=null && statusesDto.size()>0) {
			for(StatusDto statusDto : statusesDto) {
				statuses.add(mapToStatus(statusDto));
			}
		}
		
		return mapToJSONList(statuses);
	}

	public JSONObject mapToJSONList(List<Status> statuses) {
		
		JSONObject statusesJSON = new JSONObject();
		JSONArray statusesListJSON = new JSONArray();
				
		if(statuses!=null && statuses.size()>0) {
			for(Status status : statuses) {
				statusesListJSON.put(mapToJSON(status));
			}
		}

		statusesJSON.put("Statuses", statusesListJSON);
		
		return statusesJSON;
	}
	
	public JSONObject mapToJSON(StatusDto statusDto) {
		return mapToJSON(mapToStatus(statusDto));
	}
	
	public JSONObject mapToJSON(Status status) {
		JSONObject statusJSON = new JSONObject();
		statusJSON.put("city", cityMapper.mapToJSON(status.getCity()));
		statusJSON.put("district", districtMapper.mapToJSON(status.getDistrict()));
		statusJSON.put("device", deviceMapper.mapToJSON(status.getDevice()));
		statusJSON.put("timestamp", status.getTimestamp());
		statusJSON.put("co2Level", status.getCo2Level());
		return statusJSON;
	}
	
}