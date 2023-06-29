package nl.novi.kapsalon.repositories;

import nl.novi.kapsalon.models.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
