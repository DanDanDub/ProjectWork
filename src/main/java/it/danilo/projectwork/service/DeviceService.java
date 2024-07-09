package it.danilo.projectwork.service;

import org.json.JSONObject;

import it.danilo.projectwork.dto.DeviceDto;
import it.danilo.projectwork.entity.City;

public interface DeviceService {
	DeviceDto createDevice(DeviceDto elementDto);	
	DeviceDto getElementById(Integer id);
	JSONObject checkFieldsJSON(City city, String procedureName, JSONObject body);
}