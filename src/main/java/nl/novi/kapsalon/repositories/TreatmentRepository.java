package nl.novi.kapsalon.repositories;

import nl.novi.kapsalon.models.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
}
