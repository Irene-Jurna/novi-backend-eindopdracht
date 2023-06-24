package nl.novi.kapsalon.integrationtests;

import nl.novi.kapsalon.configs.AbstractTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerIntegrationtest extends AbstractTest {

    @Test
    @WithMockUser(username = "testUser", roles = {"OWNER"})
    @DisplayName("Should create correct product")
    void createProduct() throws Exception {

        String requestJson = """
                {
                "name" : "Shamboobar",
                "purchasePrice": 12.99,
                "price" : 16.00,
                "inStock" : 10
                }
                """;

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
