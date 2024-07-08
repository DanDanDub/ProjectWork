package it.danilo.projectwork.service;

import it.danilo.projectwork.dto.DeviceDto;

public interface DeviceService {
	DeviceDto createDevice(DeviceDto elementDto);	
	DeviceDto getElementById(Integer id);
}