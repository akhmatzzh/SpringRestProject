package ru.akhmat.SpringRestProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akhmat.SpringRestProject.model.Sensor;
import ru.akhmat.SpringRestProject.repository.SensorRepository;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Optional<Sensor> findByName(String name) {
        return sensorRepository.findByName(name);
    }


    @Transactional
    public void registrr(Sensor sensor) {
        sensorRepository.save(sensor);
    }

}
