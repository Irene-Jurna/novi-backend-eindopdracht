package nl.novi.kapsalon.controllers;

import jakarta.validation.Valid;
import nl.novi.kapsalon.dtos.TreatmentDto;
import nl.novi.kapsalon.services.TreatmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("treatments")
public class TreatmentController {
    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @PostMapping("")
    public ResponseEntity<Object> createTreatment(@Valid @RequestBody TreatmentDto tdto) {
        Long treatmentId = treatmentService.createTreatment(tdto);
        return new ResponseEntity<>(tdto, HttpStatus.CREATED);
    }
}
