package it.danilo.projectwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.danilo.projectwork.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, Integer> {

}