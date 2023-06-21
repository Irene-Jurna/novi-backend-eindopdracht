package nl.novi.kapsalon.controllers;

import nl.novi.kapsalon.configs.AbstractTest;
import nl.novi.kapsalon.services.BillService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(BillController.class)
class BillControllerTest extends AbstractTest {

    @MockBean
    private BillService billService;

    @Test
    @WithMockUser(username = "admin", roles = {"HAIRDRESSER", "OWNER"})
    @DisplayName("Should delete correct bill")
    void deleteBill() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/bill/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithMockUser(username = "fake", roles = {"FAKE"})
    @DisplayName("Should not delete bill")
    void shouldNotDeleteWithoutProperRole() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/bill/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andDo(MockMvcResultHandlers.print());
    }


}

//@ExtendWith(SpringExtension.class)
//@WebMvcTest(BillController.class)
//@ActiveProfiles("test")
//@AutoConfigureMockMvc(addFilters = false)
//class BillControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    JwtService jwtService;
//
//    @MockBean
//    BillService billService;
//
//    @Test
//    @DisplayName("Should delete correct bill")
//    void deleteBill() throws Exception {
//
//        BillInputDto biDto = new BillInputDto();
//        biDto.customerId = 56L;
//        biDto.hairdresserId = 50L;
//        biDto.treatmentIds = new ArrayList<>(Arrays.asList(30L, 31L));
//        biDto.productIds = new ArrayList<>(Arrays.asList(10L, 11L));
//        biDto.paid = true;
//        biDto.setId(123L);
//
//        Mockito.doNothing().when(billService).deleteBill(123L);
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.delete("/bill/123"))
//                .andExpect(MockMvcResultMatchers.status().isNoContent())
//                .andDo(MockMvcResultHandlers.print());
//    }
//}


