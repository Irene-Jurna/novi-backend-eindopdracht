package nl.novi.kapsalon.integrationtests;

import nl.novi.kapsalon.configs.AbstractTest;
import nl.novi.kapsalon.dtos.TreatmentDto;
import nl.novi.kapsalon.models.Treatment;
import nl.novi.kapsalon.repositories.TreatmentRepository;
import nl.novi.kapsalon.services.TreatmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static java.nio.file.Paths.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TreatmentControllerIntegrationTest extends AbstractTest {

    @Autowired
    private TreatmentService treatmentService;

    @Autowired
    private TreatmentRepository treatmentRepository;

    Treatment treat1;
    Treatment treat2;
    TreatmentDto tDto1;
    TreatmentDto tDto2;

    @BeforeEach
    public void setUp() {
        if (treatmentRepository.count() > 0) {
            treatmentRepository.deleteAll();
        }
        treat1 = new Treatment("Knippen wassen", 25, 25.00);
        treat2 = new Treatment("Haar vlechten", 30, 30.00);

        treatmentRepository.save(treat1);
        treatmentRepository.save(treat2);

        tDto1 = new TreatmentDto(treat1.getId(), "Knippen wassen", 25, 25.00);
        tDto2 = new TreatmentDto(treat2.getId(), "Haar vlechten", 30, 30.00);
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"OWNER"})
    @DisplayName("Should get all treatments from the database")
    void getTreatments() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/treatments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(treat1.getId()))
                .andExpect(jsonPath("$[0].name").value(treat1.getName()))
                .andExpect(jsonPath("$[0].durationInMinutes").value(treat1.getDurationInMinutes()))
                .andExpect(jsonPath("$[0].price").value(treat1.getPrice()))
                .andExpect(jsonPath("$[1].id").value(treat2.getId()))
                .andExpect(jsonPath("$[1].name").value(treat2.getName()))
                .andExpect(jsonPath("$[1].durationInMinutes").value(treat2.getDurationInMinutes()))
                .andExpect(jsonPath("$[1].price").value(treat2.getPrice()));
    }
}
