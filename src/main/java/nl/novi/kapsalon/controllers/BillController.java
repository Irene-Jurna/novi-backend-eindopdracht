package nl.novi.kapsalon.controllers;

import jakarta.validation.Valid;
import nl.novi.kapsalon.dtos.BillDto;
import nl.novi.kapsalon.services.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bill")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("")
    public ResponseEntity<Object> createBill(@Valid @RequestBody BillDto bDto) {
        billService.createBill(bDto);
        return new ResponseEntity<>(bDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<BillDto> getBill(@PathVariable Long id) {
        BillDto billDto = billService.getBill(id);
        return ResponseEntity.ok(billDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<BillDto> updateBill(@PathVariable("id") Long id, @RequestBody BillDto billDto) {
        billService.updateBill(id, billDto);
        return new ResponseEntity<>(billDto, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
        return ResponseEntity.noContent().build();
    }
}
