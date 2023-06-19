package nl.novi.kapsalon.services;

import nl.novi.kapsalon.dtos.AgendaDto;
import nl.novi.kapsalon.exceptions.ResourceNotFoundException;
import nl.novi.kapsalon.models.Agenda;
import nl.novi.kapsalon.repositories.AgendaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AgendaService {
    private final AgendaRepository agendaRepos;
    private final ModelMapper modelMapper;

    public AgendaService(AgendaRepository agendaRepos, ModelMapper modelMapper) {
        this.agendaRepos = agendaRepos;
        this.modelMapper = modelMapper;
    }

    public Long createAgenda(AgendaDto aDto) {
        Agenda agenda = transferDtoToAgenda(aDto);
        agendaRepos.save(agenda);
        return agenda.getId();
    }

    public List<AgendaDto> getAllAgendas() {
        Iterable<Agenda> agendas = agendaRepos.findAll();
        List<AgendaDto> agendaDtoList = new ArrayList<>();
        for (Agenda ag : agendas) {
            agendaDtoList.add(transferAgendaToDto(ag));
        }
        return agendaDtoList;
    }

    public void updateAgenda(Long id, AgendaDto agendaForUpdate) {
        Optional<Agenda> optionalAgenda = agendaRepos.findById(id);
        if (optionalAgenda.isEmpty()) {
            throw new ResourceNotFoundException("Het scheelde maar een haartje, maar gelukkig hebben we de fout voor je gevonden: dit agenda-id staat niet in het systeem");
        } else {
            Agenda existingAgenda = optionalAgenda.get();
            Agenda agendaToBeSaved = transferDtoToAgenda(agendaForUpdate);
            agendaToBeSaved.setId(existingAgenda.getId());
            agendaRepos.save(agendaToBeSaved);
        }
    }

    public void deleteAgenda(Long id) {
        Optional<Agenda> optionalAgenda = agendaRepos.findById(id);
        if (optionalAgenda.isEmpty()) {
            throw new ResourceNotFoundException("'Geen haar op m'n hoofd die eraan denkt!' (Dit agenda-id staat niet in het systeem)");
        } else {
            agendaRepos.deleteById(id);
        }
    }

    public Agenda transferDtoToAgenda(AgendaDto aDto) {
        return modelMapper.map(aDto, Agenda.class);
    }

    public AgendaDto transferAgendaToDto(Agenda agenda) {
        AgendaDto agendaDto = modelMapper.map(agenda, AgendaDto.class);
        return agendaDto;
    }
}
