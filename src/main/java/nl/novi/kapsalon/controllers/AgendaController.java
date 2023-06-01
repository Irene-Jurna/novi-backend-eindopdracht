package nl.novi.kapsalon.controllers;

import jakarta.validation.Valid;
import nl.novi.kapsalon.dtos.AgendaDto;
import nl.novi.kapsalon.services.AgendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("")
    public ResponseEntity<List<AgendaDto>> getAllAgendas() {
        List<AgendaDto> dtoList = agendaService.getAllAgendas();
        return ResponseEntity.ok(dtoList);
    }

    @PutMapping("{id}")
    public ResponseEntity<AgendaDto> updateAgenda(@Valid @PathVariable("id") Long id, @RequestBody AgendaDto agendaDto) {
        agendaService.updateAgenda(id, agendaDto);
        return new ResponseEntity<>(agendaDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteAgenda(@PathVariable("id") Long id) {
        agendaService.deleteAgenda(id);
        return ResponseEntity.noContent().build();
    }

}
