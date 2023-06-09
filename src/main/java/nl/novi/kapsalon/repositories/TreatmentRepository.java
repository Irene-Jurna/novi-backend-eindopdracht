package nl.novi.kapsalon.repositories;

import nl.novi.kapsalon.models.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    List<Treatment> findAllByIdIsIn(List<Long> treatmentIds);
}
