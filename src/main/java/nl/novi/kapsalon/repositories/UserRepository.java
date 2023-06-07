package nl.novi.kapsalon.repositories;

import nl.novi.kapsalon.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("Select u from User u where lower (u.firstName) = lower(:firstname) and lower (u.lastName) = lower(:lastname)")
    User findUsersByFirstNameAndLastName(@Param("firstname") String firstName, @Param("lastname") String lastName);
    Optional<User> findUserByUsername(@Param("username") String username);

    List<User> findUsersByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstNameSubString, String lastNameSubString);
}