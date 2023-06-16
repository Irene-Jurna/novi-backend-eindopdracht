package nl.novi.kapsalon.controllers;

import nl.novi.kapsalon.services.AgendaService;
import nl.novi.kapsalon.services.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final AgendaService agendaService;

    public ReservationController(ReservationService reservationService, AgendaService agendaService) {
        this.reservationService = reservationService;
        this.agendaService = agendaService;
    }

}
