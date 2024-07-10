package it.danilo.projectwork.mapper;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.danilo.projectwork.dto.DistrictDto;
import it.danilo.projectwork.entity.District;

@Component
public class DistrictMapper {

	@Autowired
	private CityMapper cityMapper;

	public DistrictDto mapToDistrictDto(District element) {
		return new DistrictDto(element.getId(), element.getCity(), element.getAddress(), element.getDevices(), element.getStatuses());
	}

	public District mapToDistrict(DistrictDto elementDto) {
		return new District(elementDto.getId(), elementDto.getCity(), elementDto.getAddress(), elementDto.getDevices(), elementDto.getStatuses());
	}

	public Object mapToJSON(DistrictDto districtDto) {
		return mapToJSON(mapToDistrict(districtDto));
	}
	
	public Object mapToJSON(District district) {
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("id", district.getId());
		jsonObject.put("city", cityMapper.mapToJSON(district.getCity()));
		jsonObject.put("address", district.getAddress());
		
		return jsonObject;
	}

}