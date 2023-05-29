package nl.novi.kapsalon.repositories;

import nl.novi.kapsalon.models.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
