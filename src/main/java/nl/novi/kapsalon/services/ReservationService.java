package nl.novi.kapsalon.services;

import nl.novi.kapsalon.repositories.AgendaRepository;
import nl.novi.kapsalon.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final AgendaRepository agendaRepository;

    public ReservationService(ReservationRepository reservationRepository, AgendaRepository agendaRepository) {
        this.reservationRepository = reservationRepository;
        this.agendaRepository = agendaRepository;
    }

}
