package it.danilo.projectwork.mapper;

import org.springframework.stereotype.Component;

import it.danilo.projectwork.dto.DistrictDto;
import it.danilo.projectwork.entity.District;

@Component
public class DistrictMapper {

	public DistrictDto mapToDistrictDto(District element) {
		return new DistrictDto(element.getId(), element.getCity(), element.getAddress(), element.getDevices(), element.getStatuses());
	}

	public District mapToDistrict(DistrictDto elementDto) {
		return new District(elementDto.getId(), elementDto.getCity(), elementDto.getAddress(), elementDto.getDevices(), elementDto.getStatuses());
	}

}