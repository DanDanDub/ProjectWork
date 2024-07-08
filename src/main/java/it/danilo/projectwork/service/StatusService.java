package it.danilo.projectwork.service;

import it.danilo.projectwork.dto.StatusDto;

public interface StatusService {
	StatusDto createStatus(StatusDto elementDto);	
	StatusDto getElementById(Integer id);
}