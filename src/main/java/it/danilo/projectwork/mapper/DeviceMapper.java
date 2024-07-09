package it.danilo.projectwork.mapper;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import it.danilo.projectwork.dto.CityDto;
import it.danilo.projectwork.dto.DeviceDto;
import it.danilo.projectwork.dto.DistrictDto;
import it.danilo.projectwork.entity.City;
import it.danilo.projectwork.entity.Device;
import it.danilo.projectwork.service.CityService;
import it.danilo.projectwork.service.DistrictService;

@Component
public class DeviceMapper {
	
	private final CityService cityService;
    private final DistrictService districtService;

    public DeviceMapper(CityService cityService, DistrictService districtService) {
        this.cityService = cityService;
        this.districtService = districtService;
    }
    
	public DeviceDto mapToDeviceDto(Device element) {
		return new DeviceDto(element.getId(), element.getCity(), element.getDistrict(), element.getName(), element.getStatuses());
	}

	public Device mapToDevice(DeviceDto elementDto) {
		//return new Device(elementDto.getId(), elementDto.getCity(), elementDto.getDistrict(), elementDto.getName(), elementDto.getStatuses());
		
		Device device = null;
		
		if(elementDto.getCity()!=null) {
			// Creazione device completo
			device = new Device(elementDto.getId(), elementDto.getCity(), elementDto.getDistrict(), elementDto.getName(), elementDto.getStatuses());

		} else if(elementDto.getDistrict()!=null && elementDto.getDistrict().getId()!=null) {
			// Creazione device con city non compilato
			DistrictDto district = districtService.getElementById(elementDto.getDistrict().getId());
			CityDto city = cityService.getElementById(district.getCity().getId());
			if(city!=null && city.getId()!=null) {
				// Utilizzo di cityLite per non cadere nella ricorsività delle collection City->District->City->District...
				City cityLite = new City(city.getId(), null, null, null, null, null);
				device = new Device(elementDto.getId(), cityLite, elementDto.getDistrict(), elementDto.getName(), elementDto.getStatuses());
			}
		} else {
			//TODO: ERRORE - Nessun distretto indicato
		}
		
		return device;
	}
	
	public JSONObject mapToDeviceesJSON(City city, List<Device> devicees) {
		
		JSONObject deviceesJSON = new JSONObject();
		JSONArray deviceesListJSON = new JSONArray();
		
		if(city!=null) {
			deviceesJSON.put("City", city.getName());
		}
				
		if(devicees!=null && devicees.size()>0) {
			for(Device device : devicees) {
				JSONObject deviceJSON = new JSONObject();
				deviceJSON.put("District", device.getDistrict().getAddress());
				deviceJSON.put("Name", device.getName());
				deviceesListJSON.put(deviceJSON);
			}
		}

		deviceesJSON.put("Devicees", deviceesListJSON);
		
		return deviceesJSON;
	}

	public JSONObject mapToDeviceJSON(City city, Device device) {

		JSONObject deviceJSON = new JSONObject();
		deviceJSON.put("District", device.getDistrict().getAddress());
		deviceJSON.put("Name", device.getName());
		return deviceJSON;
	}

}