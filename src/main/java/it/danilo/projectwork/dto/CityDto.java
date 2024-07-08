package it.danilo.projectwork.dto;

import java.util.HashSet;
import java.util.Set;

import it.danilo.projectwork.entity.Device;
import it.danilo.projectwork.entity.District;
import it.danilo.projectwork.entity.Status;

public class CityDto implements java.io.Serializable {

	private Integer id;
	private String name;
	private String password;
	private Set<Status> statuses = new HashSet<Status>(0);
	private Set<District> districts = new HashSet<District>(0);
	private Set<Device> devices = new HashSet<Device>(0);

	public CityDto() {
	}

	public CityDto(Integer id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public CityDto(Integer id, String name, String password, Set<Status> statuses, Set<District> districts, Set<Device> devices) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.statuses = statuses;
		this.districts = districts;
		this.devices = devices;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Status> getStatuses() {
		return this.statuses;
	}

	public void setStatuses(Set<Status> statuses) {
		this.statuses = statuses;
	}

	public Set<District> getDistricts() {
		return this.districts;
	}

	public void setDistricts(Set<District> districts) {
		this.districts = districts;
	}

	public Set<Device> getDevices() {
		return this.devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

}