package nl.novi.kapsalon.services;

import nl.novi.kapsalon.dtos.ReservationDto;
import nl.novi.kapsalon.models.Reservation;
import nl.novi.kapsalon.repositories.AgendaRepository;
import nl.novi.kapsalon.repositories.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final AgendaRepository agendaRepository;
    private final ModelMapper modelMapper;

    public ReservationService(ReservationRepository reservationRepository, AgendaRepository agendaRepository, ModelMapper modelMapper) {
        this.reservationRepository = reservationRepository;
        this.agendaRepository = agendaRepository;
        this.modelMapper = modelMapper;
    }

    public Long makeReservation(ReservationDto rDto) {
        Reservation reservation = transferDtoToReservation(rDto);
        reservationRepository.save(reservation);
        return reservation.getId();
    }

    public Reservation transferDtoToReservation(ReservationDto rDto) {
        return modelMapper.map(rDto, Reservation.class);
    }

}
