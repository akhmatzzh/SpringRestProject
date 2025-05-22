package ru.akhmat.SpringRestProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akhmat.SpringRestProject.model.Sensor;

import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findByName(String name);
}
