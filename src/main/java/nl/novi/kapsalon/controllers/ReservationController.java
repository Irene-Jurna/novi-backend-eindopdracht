package nl.novi.kapsalon.controllers;

import jakarta.validation.Valid;
import nl.novi.kapsalon.dtos.ReservationDto;
import nl.novi.kapsalon.services.AgendaService;
import nl.novi.kapsalon.services.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reservation")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_HAIRDRESSER', 'ROLE_OWNER')")
    @PostMapping("")
    public ResponseEntity<Object> makeReservation(@Valid @RequestBody ReservationDto rDto) {
        reservationService.makeReservation(rDto);
        return new ResponseEntity<>(rDto, HttpStatus.CREATED);
    }

}
