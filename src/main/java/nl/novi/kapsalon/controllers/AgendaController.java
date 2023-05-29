package nl.novi.kapsalon.controllers;

import jakarta.validation.Valid;
import nl.novi.kapsalon.dtos.AgendaDto;
import nl.novi.kapsalon.services.AgendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("agenda")
public class AgendaController {
    private final AgendaService agendaService;

    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @PostMapping("")
    public ResponseEntity<Object> createAgenda(@Valid @RequestBody AgendaDto aDto) {
        agendaService.createAgenda(aDto);
        return new ResponseEntity<>(aDto, HttpStatus.CREATED);
    }
}
