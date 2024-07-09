package it.danilo.projectwork.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.danilo.projectwork.dto.DistrictDto;
import it.danilo.projectwork.entity.District;
import it.danilo.projectwork.mapper.DistrictMapper;
import it.danilo.projectwork.repository.DistrictRepository;

@Service
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	private DistrictRepository elementRepository;

	@Autowired
	private DistrictMapper districtMapper;

	@Override
	public DistrictDto createDistrict(DistrictDto elementDto) {
		District element = districtMapper.mapToDistrict(elementDto);
		District createdDistrict = elementRepository.save(element);
		return districtMapper.mapToDistrictDto(createdDistrict);
	}

	@Override
	public DistrictDto getElementById(Integer elementId) {
		Optional<District> optionalElement = elementRepository.findById(elementId);
		DistrictDto element = null;
		if(optionalElement.isPresent()) {
			element = districtMapper.mapToDistrictDto(optionalElement.get());			
		}
		return element;
	}

}