package ru.akhmat.SpringRestProject.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.akhmat.SpringRestProject.dto.MeasurmentsDTO;
import ru.akhmat.SpringRestProject.dto.MeasurmentsResponse;
import ru.akhmat.SpringRestProject.model.Measurments;
import ru.akhmat.SpringRestProject.service.MeasurmentsService;
import ru.akhmat.SpringRestProject.util.MeasurmentErrorsResponse;
import ru.akhmat.SpringRestProject.util.MeasurmentValidator;
import java.util.stream.Collectors;
import static ru.akhmat.SpringRestProject.util.ErrorsUtil.returnErrorsToClient;


@RestController
@RequestMapping("/measurments")
public class MeasurmentsController {

    private final MeasurmentsService measurmentsService;
    private final ModelMapper modelMapper;
    private final MeasurmentValidator measurmentValidator;

    @Autowired
    public MeasurmentsController(MeasurmentsService measurmentsService, ModelMapper modelMapper,
                                 MeasurmentValidator measurmentValidator) {
        this.measurmentsService = measurmentsService;
        this.modelMapper = modelMapper;
        this.measurmentValidator = measurmentValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurmentsDTO measurmentsDTO,
                                          BindingResult bindingResult) {
        Measurments measurmentstoAdd = converToMeasurments(measurmentsDTO);
        measurmentValidator.validate(measurmentstoAdd, bindingResult);
        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        measurmentsService.addMeasurement(measurmentstoAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount() {
        return measurmentsService.findAll().stream().filter(Measurments::isRaining).count();
    }

   @GetMapping
   public MeasurmentsResponse getMeasurments() {
        return new MeasurmentsResponse(measurmentsService.findAll().stream()
                .map(this::converToMeasurmentsDTO).collect(Collectors.toList()));
   }

    @ExceptionHandler
    public ResponseEntity<MeasurmentErrorsResponse> handleException(Exception ex) {
        MeasurmentErrorsResponse response = new MeasurmentErrorsResponse(
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Measurments converToMeasurments(MeasurmentsDTO measurmentsDTO) {
        return modelMapper.map(measurmentsDTO, Measurments.class);
    }

    private MeasurmentsDTO converToMeasurmentsDTO(Measurments measurments) {
        return modelMapper.map(measurments, MeasurmentsDTO.class);
    }

}
