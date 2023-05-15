package nl.novi.kapsalon.services;

import nl.novi.kapsalon.dtos.UserDto;
import nl.novi.kapsalon.exceptions.ResourceNotFoundException;
import nl.novi.kapsalon.models.User;
import nl.novi.kapsalon.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userrepos;

    public UserService(UserRepository userrepos) {
        this.userrepos = userrepos;
    }

    public Long createUser(UserDto userDto) {
        // Duplicatename exception gooien
        User user = transferDtoToUser(userDto);
        return user.getId();
    }

    public UserDto getUser(Long id) {
        User user = userrepos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Gebruiker niet gevonden"));
        return transferUserToDto(user);
    }

    public List<UserDto> getAllUsers() {
        Iterable<User> users = userrepos.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users) {
            userDtoList.add(transferUserToDto(user));
        }
        return userDtoList;
    }

    public User transferDtoToUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.firstName);
        user.setLastName(userDto.lastName);
        user.setEmail(userDto.email);
        user.setPassword(userDto.password);
        user.setAddress(userDto.address);
        user.setHouseNumber(userDto.houseNumber);
        user.setResidence(userDto.residence);
        user.setInCaseOfEmergencyContact(userDto.inCaseOfEmergencyContact);
        user.setEmergencyContactPhoneNumber(userDto.emergencyContactPhoneNumber);
        user.setPreferredHairdresser(userDto.preferredHairdresser);
        user.setNotes(userDto.notes);
        userrepos.save(user);
        return user;
    }

    public UserDto transferUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.firstName = user.getFirstName();
        userDto.lastName = user.getLastName();
        userDto.email = user.getEmail();
        userDto.password = user.getPassword();
        userDto.address = user.getAddress();
        userDto.houseNumber = user.getHouseNumber();
        userDto.residence = user.getResidence();
        userDto.inCaseOfEmergencyContact = user.getInCaseOfEmergencyContact();
        userDto.emergencyContactPhoneNumber = user.getEmergencyContactPhoneNumber();
        userDto.preferredHairdresser = user.getPreferredHairdresser();
        userDto.notes = user.getNotes();
        return userDto;
    }

}
