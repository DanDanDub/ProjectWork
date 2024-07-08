package it.danilo.projectwork.dto;

import java.util.HashSet;
import java.util.Set;

import it.danilo.projectwork.entity.City;
import it.danilo.projectwork.entity.Device;
import it.danilo.projectwork.entity.Status;

public class DistrictDto implements java.io.Serializable {

	private Integer id;
	private City city;
	private String address;
	private Set<Device> devices = new HashSet<Device>(0);
	private Set<Status> statuses = new HashSet<Status>(0);

	public DistrictDto() {
	}

	public DistrictDto(Integer id, City city, String address) {
		this.id = id;
		this.city = city;
		this.address = address;
	}

	public DistrictDto(Integer id, City city, String address, Set<Device> devices, Set<Status> statuses) {
		this.id = id;
		this.city = city;
		this.address = address;
		this.devices = devices;
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

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Device> getDevices() {
		return this.devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

	public Set<Status> getStatuses() {
		return this.statuses;
	}

	public void setStatuses(Set<Status> statuses) {
		this.statuses = statuses;
	}

}