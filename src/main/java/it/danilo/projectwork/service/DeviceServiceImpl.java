package it.danilo.projectwork.service;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.danilo.projectwork.Utils;
import it.danilo.projectwork.dto.DeviceDto;
import it.danilo.projectwork.entity.City;
import it.danilo.projectwork.entity.Device;
import it.danilo.projectwork.entity.District;
import it.danilo.projectwork.mapper.DeviceMapper;
import it.danilo.projectwork.repository.DeviceRepository;
import it.danilo.projectwork.repository.DistrictRepository;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceRepository elementRepository;

	@Autowired
	private DistrictRepository districtRepository;

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

	@Override
	public JSONObject checkFieldsJSON(City city, String procedureName, JSONObject body) {
		
		boolean keepGoing = true;
		JSONObject checkFieldJSON = new JSONObject();
		
		// Check District
		District district = null;
		if(keepGoing) {
			String stageName = procedureName + "Check District ";
			if(body.has("district") && body.getJSONObject("district")!=null && body.getJSONObject("district").has("id")) {
				Optional<District> districtOptional = districtRepository.findByIdAndCity(body.getJSONObject("district").getInt("id"), city);
				if(districtOptional!=null && districtOptional.isPresent() && districtOptional.get()!=null) {
					district = districtOptional.get();
				} else {
					keepGoing = false;
					checkFieldJSON.put(Utils.CHECK_ERROR_MESSAGE, stageName + "failed: District not found");
				}
			} else {
				keepGoing = false;
				checkFieldJSON.put(Utils.CHECK_ERROR_MESSAGE, stageName + "failed: District mandatory");
			}
		}
		
		// Check Name
		String name = null;
		if(keepGoing) {
			String stageName = procedureName + "Check Name ";
			if(body.has("name") && body.getString("name")!=null) {
				name = body.getString("name");
			} else {
				keepGoing = false;
				checkFieldJSON.put(Utils.CHECK_ERROR_MESSAGE, stageName + "failed: Name mandatory");
			}
		}

		checkFieldJSON.put(Utils.CHECK_STATUS, keepGoing ? Utils.CHECK_STATUS_SUCCESS : Utils.CHECK_STATUS_FAILED);
		checkFieldJSON.put("district", district);
		checkFieldJSON.put("name", name);
		
		return checkFieldJSON;
	}
}