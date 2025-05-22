package ru.akhmat.SpringRestProject.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import ru.akhmat.SpringRestProject.model.Sensor;

public class MeasurmentsDTO {

    @Column(name = "value")
    @NotEmpty(message = "Value should not be empty")
    @Range(min = -100, max = 100)
    private Double value;

    @Column(name = "raining")
    @NotEmpty(message = "Raining should not be empty")
    private Boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
