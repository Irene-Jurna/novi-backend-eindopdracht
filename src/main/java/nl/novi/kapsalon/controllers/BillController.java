package nl.novi.kapsalon.controllers;

import jakarta.validation.Valid;
import nl.novi.kapsalon.dtos.BillInputDto;
import nl.novi.kapsalon.dtos.BillOutputDto;
import nl.novi.kapsalon.services.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bill")
@PreAuthorize("hasAnyRole('ROLE_HAIRDRESSER', 'ROLE_OWNER')")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("")
    public ResponseEntity<BillOutputDto> createBill(@Valid @RequestBody BillInputDto bDto) {
        BillOutputDto boDto = billService.createBill(bDto);
        return new ResponseEntity<>(boDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<BillInputDto> getBill(@PathVariable Long id) {
        BillInputDto billDto = billService.getBill(id);
        return ResponseEntity.ok(billDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<BillInputDto> updateBill(@PathVariable("id") Long id, @RequestBody BillInputDto billDto) {
        billService.updateBill(id, billDto);
        return new ResponseEntity<>(billDto, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
        return ResponseEntity.noContent().build();
    }
}
