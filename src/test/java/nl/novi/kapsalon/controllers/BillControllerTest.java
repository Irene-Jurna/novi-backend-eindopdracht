package nl.novi.kapsalon.controllers;

import nl.novi.kapsalon.dtos.BillInputDto;
import nl.novi.kapsalon.services.BillService;
import nl.novi.kapsalon.services.JwtService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BillController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class BillControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JwtService jwtService;

    @MockBean
    BillService billService;

    @Test
    @DisplayName("Should delete correct bill")
    void deleteBill() throws Exception {

        BillInputDto biDto = new BillInputDto();
        biDto.customerId = 56L;
        biDto.hairdresserId = 50L;
        biDto.treatmentIds = new ArrayList<>(Arrays.asList(30L, 31L));
        biDto.productIds = new ArrayList<>(Arrays.asList(10L, 11L));
        biDto.paid = true;
        biDto.setId(123L);

        Mockito.doNothing().when(billService).deleteBill(123L);

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/bill/123"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }
}


