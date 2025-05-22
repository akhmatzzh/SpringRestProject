package ru.akhmat.SpringRestProject.dto;

import ru.akhmat.SpringRestProject.model.Measurments;

import java.util.List;

public class MeasurmentsResponse {

    private List<MeasurmentsDTO> measurments;

    public MeasurmentsResponse(List<MeasurmentsDTO> measurments) {
        this.measurments = measurments;
    }

    public List<MeasurmentsDTO> getMeasurments() {
        return measurments;
    }

    public void setMeasurments(List<MeasurmentsDTO> measurments) {
        this.measurments = measurments;
    }
}
