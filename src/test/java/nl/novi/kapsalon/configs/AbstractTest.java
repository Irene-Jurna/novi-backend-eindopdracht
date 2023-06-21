package nl.novi.kapsalon.configs;

import nl.novi.kapsalon.repositories.UserRepository;
import nl.novi.kapsalon.services.JwtService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@Import(SecurityConfig.class)
@ExtendWith(SpringExtension.class)
public class AbstractTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserRepository userRepository;
}