package it.danilo.projectwork.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.danilo.projectwork.dto.DeviceDto;
import it.danilo.projectwork.entity.Device;
import it.danilo.projectwork.mapper.DeviceMapper;
import it.danilo.projectwork.repository.DeviceRepository;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceRepository elementRepository;

	@Autowired
	private DeviceMapper deviceMapper;

	@Override
	public DeviceDto createDevice(DeviceDto elementDto) {
		Device element = deviceMapper.mapToDevice(elementDto);
		Device createdDevice = elementRepository.save(element);
		return deviceMapper.mapToDeviceDto(createdDevice);
	}

	@Override
	public DeviceDto getElementById(Integer elementId) {
		Optional<Device> optionalElement = elementRepository.findById(elementId);
		DeviceDto element = null;
		if(optionalElement.isPresent()) {
			element = deviceMapper.mapToDeviceDto(optionalElement.get());			
		}
		return element;
	}
}