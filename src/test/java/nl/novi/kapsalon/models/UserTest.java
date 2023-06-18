package nl.novi.kapsalon.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    @DisplayName("Should return correct firstname")
    void getFirstName() {
        User user = new User("Mark", "Rensen", "markrensen", "markrensen@novi-education.nl", "novi12345", "Newtonlaan", 3, "Utrecht",
                "Nova Eeken", "0612345678", "Ellen", "", null);

        String result = user.getFirstName();

        assertEquals("Mark", result);
    }

}