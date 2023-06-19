package nl.novi.kapsalon.config;

import nl.novi.kapsalon.models.Role;
import nl.novi.kapsalon.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class WithMockUserTests {

    User testUser;
    Role testOwner = new Role("ROLE_OWNER");

    @BeforeEach
    public void setUp() {
        this.testUser = new User("Test", "User", "TestUser", "testuser@email.com", "testpassword", "testadres", 1, "testcity", "test contactperson", "test number", "test hairdresser", "test notes", testOwner);
    }
}
