package nl.novi.kapsalon.controllers;

import jakarta.validation.Valid;
import nl.novi.kapsalon.dtos.TreatmentDto;
import nl.novi.kapsalon.services.TreatmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("treatments")
public class TreatmentController {
    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @PostMapping("")
    public ResponseEntity<Object> createTreatment(@Valid @RequestBody TreatmentDto tdto) {
        treatmentService.createTreatment(tdto);
        return new ResponseEntity<>(tdto, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<TreatmentDto>> getAllTreatments() {
        List<TreatmentDto> dtoList = treatmentService.getAllTreatments();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/sum")
    public ResponseEntity<Integer> calculateCombinedDuration(@RequestBody List<Integer> treatmentIds) {
        int combinedDuration = treatmentService.calculateCombinedDuration(treatmentIds);
        return new ResponseEntity<>(combinedDuration, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<TreatmentDto> updateTreatment(@Valid @PathVariable("id") Integer id, @RequestBody TreatmentDto treatmentDto) {
        treatmentService.updateTreatment(id, treatmentDto);
        return new ResponseEntity(treatmentDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteTreatment(@PathVariable("id") Integer id) {
        StringBuilder sb = new StringBuilder();
        sb.append("Behandeling succesvol verwijderd");
        treatmentService.deleteTreatment(id);
        return new ResponseEntity(sb.toString(), HttpStatus.ACCEPTED);
    }
}
