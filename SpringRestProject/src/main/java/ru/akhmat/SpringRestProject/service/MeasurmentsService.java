package ru.akhmat.SpringRestProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akhmat.SpringRestProject.model.Measurments;
import ru.akhmat.SpringRestProject.repository.MeasurmentsRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurmentsService {

    private final MeasurmentsRepository measurmentsRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurmentsService(MeasurmentsRepository measurmentsRepository, SensorService sensorService) {
        this.measurmentsRepository = measurmentsRepository;
        this.sensorService = sensorService;
    }

    public List<Measurments> findAll() {
        return measurmentsRepository.findAll();
    }

    @Transactional
    public void addMeasurement(Measurments measurment) {
        enrichMeasurement(measurment);
        measurmentsRepository.save(measurment);
    }

    public void enrichMeasurement(Measurments measurment) {
        measurment.setSensor(sensorService.findByName(measurment.getSensor().getName()).get());
        measurment.setMeasurementDateTime(LocalDateTime.now());
    }


}
