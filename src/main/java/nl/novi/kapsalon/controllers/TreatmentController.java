package nl.novi.kapsalon.controllers;

import jakarta.validation.Valid;
import nl.novi.kapsalon.dtos.TreatmentDto;
import nl.novi.kapsalon.services.TreatmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("treatments")
public class TreatmentController {
    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @PreAuthorize("hasRole('ROLE_OWNER')")
    @PostMapping("")
    public ResponseEntity<Object> createTreatment(@Valid @RequestBody TreatmentDto tdto) {
        treatmentService.createTreatment(tdto);
        return new ResponseEntity<>(tdto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_HAIRDRESSER', 'ROLE_OWNER')")
    @GetMapping("")
    public ResponseEntity<List<TreatmentDto>> getAllTreatments() {
        List<TreatmentDto> dtoList = treatmentService.getAllTreatments();
        return ResponseEntity.ok(dtoList);
    }

    @PreAuthorize("hasAnyRole('ROLE_HAIRDRESSER', 'ROLE_OWNER')")
    @GetMapping("/sum")
    public ResponseEntity<Integer> calculateCombinedDuration(@Valid @RequestBody List<Long> treatmentIds) {
        int combinedDuration = treatmentService.calculateCombinedDuration(treatmentIds);
        return new ResponseEntity<>(combinedDuration, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_OWNER')")
    @PutMapping("{id}")
    public ResponseEntity<TreatmentDto> updateTreatment(@Valid @PathVariable("id") Long id, @RequestBody TreatmentDto treatmentDto) {
        treatmentService.updateTreatment(id, treatmentDto);
        return new ResponseEntity<>(treatmentDto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_OWNER')")
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteTreatment(@PathVariable("id") Long id) {
        treatmentService.deleteTreatment(id);
        StringBuilder sb = new StringBuilder();
        sb.append("Behandeling succesvol verwijderd");
        return new ResponseEntity<>(sb.toString(), HttpStatus.ACCEPTED);
    }
}
