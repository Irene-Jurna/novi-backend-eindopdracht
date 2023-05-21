package nl.novi.kapsalon.controllers;

import jakarta.validation.Valid;
import nl.novi.kapsalon.dtos.TreatmentDto;
import nl.novi.kapsalon.dtos.TreatmentRequestDto;
import nl.novi.kapsalon.models.Treatment;
import nl.novi.kapsalon.services.TreatmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("treatments")
public class TreatmentController extends CrudController<TreatmentRequestDto, TreatmentDto> {
    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        super(treatmentService);
        this.treatmentService = treatmentService;
    }

}
