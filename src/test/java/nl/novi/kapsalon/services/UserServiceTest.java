package nl.novi.kapsalon.services;

import nl.novi.kapsalon.dtos.UserDto;
import nl.novi.kapsalon.models.User;
import nl.novi.kapsalon.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepos;

    @InjectMocks
    UserService userService;

    @Test
    @DisplayName("Should return correct User based on id")
    void getUser() {
        User user = new User("Mark", "Rensen", "markrensen", "markrensen@novi-education.nl", "novi12345", "Newtonlaan", 3, "Utrecht",
                "Nova Eeken", "0612345678", "Ellen", "", null);
        user.setId(123L);

        Mockito.when(userRepos.findById(anyLong())).thenReturn(Optional.of(user));

        UserDto userDto = userService.getUser(123L);

        assertEquals(123L, userDto.id);
        assertEquals("Mark", userDto.firstName);
        assertEquals("Rensen", userDto.lastName);
        assertEquals("markrensen", userDto.username);
        assertEquals("markrensen@novi-education.nl", userDto.email);
        assertEquals("novi12345", userDto.password);
        assertEquals("Newtonlaan", userDto.address);
        assertEquals(3, userDto.houseNumber);
        assertEquals("Nova Eeken", userDto.inCaseOfEmergencyContact);
        assertEquals("0612345678", userDto.emergencyContactPhoneNumber);
        assertEquals("Ellen", userDto.preferredHairdresser);
        assertEquals("", userDto.notes);
        assertNull(userDto.role);
    }
}