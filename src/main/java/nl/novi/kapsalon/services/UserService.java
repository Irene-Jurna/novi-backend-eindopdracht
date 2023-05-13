package nl.novi.kapsalon.services;

import nl.novi.kapsalon.dtos.UserDto;
import nl.novi.kapsalon.models.User;
import nl.novi.kapsalon.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userrepos;

    public UserService(UserRepository userrepos) {
        this.userrepos = userrepos;
    }

    public Long createUser(UserDto userDto) {
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
        return user.getId();
    }
}
