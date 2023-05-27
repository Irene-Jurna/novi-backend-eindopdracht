package nl.novi.kapsalon.repositories;

import nl.novi.kapsalon.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
