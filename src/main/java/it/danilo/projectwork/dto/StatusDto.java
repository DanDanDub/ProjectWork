package it.danilo.projectwork.dto;

import java.util.Date;

import it.danilo.projectwork.entity.City;
import it.danilo.projectwork.entity.Device;
import it.danilo.projectwork.entity.District;

public class StatusDto implements java.io.Serializable {

	private Integer id;
	private Date timestamp;
	private City city;
	private Device device;
	private District district;
	private int co2Level;

	public StatusDto() {
	}

	public StatusDto(Integer id, City city, Device device, District district, Date timestamp, int co2Level) {
		this.id = id;
		this.city = city;
		this.device = device;
		this.district = district;
		this.timestamp = timestamp;
		this.co2Level = co2Level;
	}

	public StatusDto(Integer id, City city, Device device, District district, int co2Level) {
		this.id = id;
		this.city = city;
		this.device = device;
		this.district = district;
		this.co2Level = co2Level;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Device getDevice() {
		return this.device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public District getDistrict() {
		return this.district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public int getCo2Level() {
		return this.co2Level;
	}

	public void setCo2Level(int co2Level) {
		this.co2Level = co2Level;
	}

}