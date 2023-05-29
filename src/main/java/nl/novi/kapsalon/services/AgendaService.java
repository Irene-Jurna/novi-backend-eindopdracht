package nl.novi.kapsalon.services;

import nl.novi.kapsalon.dtos.AgendaDto;
import nl.novi.kapsalon.models.Agenda;
import nl.novi.kapsalon.repositories.AgendaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

    public Agenda transferDtoToAgenda(AgendaDto aDto) {
        return modelMapper.map(aDto, Agenda.class);
    }
}
