package it.danilo.projectwork.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.danilo.projectwork.dto.StatusDto;
import it.danilo.projectwork.entity.Status;
import it.danilo.projectwork.mapper.StatusMapper;
import it.danilo.projectwork.repository.StatusRepository;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusRepository elementRepository;

	@Autowired
	private StatusMapper statusMapper;

	@Override
	public StatusDto createStatus(StatusDto elementDto) {
		Status element = statusMapper.mapToStatus(elementDto);
		Status createdStatus = elementRepository.save(element);
		return statusMapper.mapToStatusDto(createdStatus);
	}

	@Override
	public StatusDto getElementById(Integer elementId) {
		Optional<Status> optionalElement = elementRepository.findById(elementId);
		StatusDto element = null;
		if(optionalElement.isPresent()) {
			element = statusMapper.mapToStatusDto(optionalElement.get());			
		}
		return element;
	}
}