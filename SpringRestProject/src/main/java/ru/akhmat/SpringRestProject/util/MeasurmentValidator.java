package ru.akhmat.SpringRestProject.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.akhmat.SpringRestProject.model.Measurments;
import ru.akhmat.SpringRestProject.service.SensorService;

@Component
public class MeasurmentValidator  implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurmentValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Measurments.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Measurments measurement = (Measurments) o;

        if (measurement.getSensor() == null) {
            return;
        }

        if (sensorService.findByName(measurement.getSensor().getName()).isEmpty())
            errors.rejectValue("sensor", "Нет зарегистрированного сенсора с таким именем!");
    }
}