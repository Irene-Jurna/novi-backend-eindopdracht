package nl.novi.kapsalon.services;

import jakarta.annotation.security.RolesAllowed;
import nl.novi.kapsalon.dtos.UserDto;
import nl.novi.kapsalon.exceptions.DuplicateNameException;
import nl.novi.kapsalon.exceptions.ResourceNotFoundException;
import nl.novi.kapsalon.models.User;
import nl.novi.kapsalon.repositories.RoleRepository;
import nl.novi.kapsalon.repositories.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepos;
    private final RoleRepository roleRepos;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepos, RoleRepository roleRepos, PasswordEncoder encoder) {
        this.userRepos = userRepos;
        this.roleRepos = roleRepos;
        this.encoder = encoder;
    }

    public Long createUser(UserDto userDto) {
        User existingUser = userRepos.findUsersByFirstNameAndLastName(userDto.firstName, userDto.lastName);
        if (existingUser != null) {
            throw new DuplicateNameException("Deze gebruiker staat al in het systeem");
        }
        User user = new User();
        user = transferDtoToUser(user, userDto);
        return user.getId();
    }

    public UserDto getUser(Long id) {
        User user = userRepos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Gebruiker niet gevonden"));
        return transferUserToDto(user);
    }

    public List<UserDto> getAllUsers() {
        Iterable<User> users = userRepos.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users) {
            userDtoList.add(transferUserToDto(user));
        }
        return userDtoList;
    }

    public List<UserDto> getUsersBasedOnSubString(String firstNameSubString, String lastNameSubString) {
        List<User> users = userRepos.findUsersByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(firstNameSubString, lastNameSubString);
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users) {
            userDtoList.add(transferUserToDto(user));
        }
        return userDtoList;
    }

    public void updateUser(Long id, UserDto userForUpdate) {
        Optional<User> optionalUser = userRepos.findById(id);
        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("Id '" + id + "' staat niet in het systeem");
        } else {
            User existingUser = optionalUser.get();
            User userToBeSaved = transferDtoToUser(existingUser, userForUpdate);
            userRepos.save(userToBeSaved);
        }
    }

    public void deleteUser(Long id) {
        userRepos.deleteById(id);
    }

    public User transferDtoToUser(User user, UserDto userDto) {
        user.setFirstName(userDto.firstName);
        user.setLastName(userDto.lastName);
        user.setUsername(userDto.username);
        user.setEmail(userDto.email);
        user.setPassword(encoder.encode(userDto.password));
        user.setAddress(userDto.address);
        user.setHouseNumber(userDto.houseNumber);
        user.setResidence(userDto.residence);
        user.setInCaseOfEmergencyContact(userDto.inCaseOfEmergencyContact);
        user.setEmergencyContactPhoneNumber(userDto.emergencyContactPhoneNumber);
        user.setPreferredHairdresser(userDto.preferredHairdresser);
        user.setNotes(userDto.notes);
        user.setRole(userDto.role);
        userRepos.save(user);
        return user;
    }

    public UserDto transferUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.firstName = user.getFirstName();
        userDto.lastName = user.getLastName();
        userDto.username = user.getUsername();
        userDto.email = user.getEmail();
        userDto.password = user.getPassword();
        userDto.address = user.getAddress();
        userDto.houseNumber = user.getHouseNumber();
        userDto.residence = user.getResidence();
        userDto.inCaseOfEmergencyContact = user.getInCaseOfEmergencyContact();
        userDto.emergencyContactPhoneNumber = user.getEmergencyContactPhoneNumber();
        userDto.preferredHairdresser = user.getPreferredHairdresser();
        userDto.notes = user.getNotes();
        userDto.role = user.getRole();
        return userDto;
    }

}
