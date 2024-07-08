package it.danilo.projectwork.dto;

import java.util.HashSet;
import java.util.Set;

import it.danilo.projectwork.entity.City;
import it.danilo.projectwork.entity.District;
import it.danilo.projectwork.entity.Status;

public class DeviceDto implements java.io.Serializable {

	private Integer id;
	private City city;
	private District district;
	private String name;
	private Set<Status> statuses = new HashSet<Status>(0);

	public DeviceDto() {
	}

	public DeviceDto(Integer id, City city, District district, String name) {
		this.id = id;
		this.city = city;
		this.district = district;
		this.name = name;
	}

	public DeviceDto(Integer id, City city, District district, String name, Set<Status> statuses) {
		this.id = id;
		this.city = city;
		this.district = district;
		this.name = name;
		this.statuses = statuses;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public District getDistrict() {
		return this.district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Status> getStatuses() {
		return this.statuses;
	}

	public void setStatuses(Set<Status> statuses) {
		this.statuses = statuses;
	}

}