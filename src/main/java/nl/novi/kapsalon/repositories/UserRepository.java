package nl.novi.kapsalon.repositories;

import nl.novi.kapsalon.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Iterable<User> findUsersByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstNameSubstring, String lastNameSubstring);
}
